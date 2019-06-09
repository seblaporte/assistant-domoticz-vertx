package fr.seblaporte;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.List;
import java.util.stream.Collectors;

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

        final String intent = routingContext.getBodyAsJson().getString("intent");

        switch (intent) {
            case "action.devices.SYNC":
                createDevices(routingContext);
                break;
        }

    }

    private void createDevices(RoutingContext routingContext) {
        vertx.eventBus().send("domoticzApi", "devices", messageAsyncResult -> {
            if (messageAsyncResult.succeeded()) {
                JsonArray devicesFromDomoticz = (JsonArray) messageAsyncResult.result().body();
                JsonArray actionsDevices = new JsonArray();

                devicesFromDomoticz.stream().map(deviceFromDomoticz -> {
                    JsonObject device = new JsonObject();
                    device.put("id", ((JsonObject) deviceFromDomoticz).getValue("idx"));
                    device.put("name", ((JsonObject) deviceFromDomoticz).getString("Name"));
                    device.put("willReportState", true);
                    device.put("deviceInfo", new JsonArray()
                            .add(new JsonObject().put("HardwareName", ((JsonObject) deviceFromDomoticz).getString("HardwareName")))
                            .add(new JsonObject().put("HardwareType", ((JsonObject) deviceFromDomoticz).getString("HardwareType")))
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
                        .put("payload", new JsonObject().put("agentUserId", "123456"))
                        .put("devices", actionsDevices);

                routingContext.response().end(response.encodePrettily());
            }
        });
    }

}
