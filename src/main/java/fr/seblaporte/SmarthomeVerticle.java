package fr.seblaporte;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SmarthomeVerticle extends AbstractVerticle {

    @Override
    public void start() {

        System.out.println("Demarrage de Smarthome");

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.post("/smarthome").handler(this::smarthome);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);
    }

    private void smarthome(RoutingContext routingContext) {

        final String intent = routingContext
                .getBodyAsJson()
                .getJsonArray("inputs")
                .getJsonObject(0)
                .getString("intent");

        switch (intent) {
            case "action.devices.SYNC":
                createDevices(routingContext);
                break;
            case "action.devices.EXECUTE":
                executeAction(routingContext);
                break;
        }

    }

    private void executeAction(RoutingContext routingContext) {

        final JsonArray inputs = routingContext
                .getBodyAsJson()
                .getJsonArray("inputs");

        final String requestId = routingContext
                .getBodyAsJson()
                .getString("requestId");

        final List<JsonObject> deviceControlList = inputs.stream()
                .map(inputItem -> getCommandsFromInput((JsonObject) inputItem))
                .flatMap(commands -> commands.stream()
                        .flatMap(commandItem -> createDeviceControlFromCommand((JsonObject) commandItem)))
                .collect(Collectors.toList());

        deviceControlList.forEach(deviceControl -> {
            vertx.eventBus().send("deviceController", deviceControl);
        });

        final List<String> deviceIdList = deviceControlList.stream()
                .map(deviceControl -> deviceControl.getString("deviceId"))
                .collect(Collectors.toList());

        JsonObject response = new JsonObject()
                .put("payload", new JsonObject()
                        .put("commands", new JsonArray()
                                .add(new JsonObject()
                                        .put("ids", deviceIdList)
                                        .put("status", "SUCCESS"))))
                .put("requestId", requestId);

        routingContext.response().end(response.encodePrettily());
    }

    private JsonArray getCommandsFromInput(JsonObject inputItem) {
        final JsonObject input = inputItem;

        return input
                .getJsonObject("payload")
                .getJsonArray("commands");
    }

    private Stream<JsonObject> createDeviceControlFromCommand(JsonObject commandItem) {

        final JsonObject execution = commandItem
                .getJsonArray("execution")
                .getJsonObject(0);

        return commandItem.getJsonArray("devices").stream().map(deviceItem -> {

            JsonObject device = (JsonObject) deviceItem;
            String deviceId = device.getString("id");
            String commandType = execution.getString("command");
            JsonObject params = execution.getJsonObject("params");

            JsonObject deviceControl = new JsonObject();
            deviceControl.put("deviceId", deviceId);
            deviceControl.put("command", commandType);
            deviceControl.put("params", params);

            return deviceControl;
        });
    }

    private void createDevices(RoutingContext routingContext) {
        vertx.eventBus().send("domoticzApi", "devices", messageAsyncResult -> {
            if (messageAsyncResult.succeeded()) {

                final String requestId = routingContext.getBodyAsJson().getString("requestId");

                JsonArray devicesFromDomoticz = (JsonArray) messageAsyncResult.result().body();
                JsonArray actionsDevices = new JsonArray();

                devicesFromDomoticz.stream().map(deviceFromDomoticz -> {
                    JsonObject device = new JsonObject();
                    device.put("id", ((JsonObject) deviceFromDomoticz).getValue("idx"));
                    device.put("name", new JsonObject().put("name", ((JsonObject) deviceFromDomoticz).getString("Name")));
                    device.put("willReportState", true);
                    device.put("deviceInfo", new JsonObject()
                            .put("manufacturer", ((JsonObject) deviceFromDomoticz).getString("HardwareName"))
                            .put("model", ((JsonObject) deviceFromDomoticz).getString("HardwareType"))
                    );

                    final String switchType = ((JsonObject) deviceFromDomoticz).getString("SwitchType");

                    if ("Dimmer".equals(switchType)) {
                        device.put("type", "action.devices.types.LIGHT");
                        device.put("traits", new JsonArray()
                                .add("action.devices.traits.OnOff")
                                .add("action.devices.traits.Brightness")
                        );
                    } else if ("On/Off".equals(switchType)) {
                        device.put("type", "action.devices.types.OUTLET");
                        device.put("traits", new JsonArray().add("action.devices.traits.OnOff"));
                    }

                    return device;
                }).forEach(actionsDevices::add);

                JsonObject response = new JsonObject()
                        .put("payload", new JsonObject()
                                .put("agentUserId", "123456")
                                .put("devices", actionsDevices))
                        .put("requestId", requestId);

                routingContext.response().end(response.encodePrettily());
            }
        });
    }

}
