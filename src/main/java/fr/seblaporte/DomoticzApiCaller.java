package fr.seblaporte;

import fr.seblaporte.DTO.domoticz.DomoticzDevice;
import fr.seblaporte.DTO.domoticz.DomoticzDevicesResponse;
import fr.seblaporte.DTO.domoticz.DomoticzPlan;
import fr.seblaporte.DTO.domoticz.DomoticzPlanResponse;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;

import java.util.List;
import java.util.stream.Collectors;

public class DomoticzApiCaller extends AbstractVerticle {

    private WebClient client;

    @Override
    public void start() {

        System.out.println("Demarrage de DomoticzApiCaller");

        client = WebClient.create(vertx);

        final MessageConsumer<String> consumer = vertx.eventBus().consumer("domoticzApi");
        consumer.handler(this::callDomoticz);
    }

    private void callDomoticz(Message<String> request) {

        switch (request.body()) {
            case "devices":
                getDevices().send(domoticzApiResponse -> {
                    if (domoticzApiResponse.succeeded()) {
                        final List<DomoticzDevice> listDomoticzDevices = domoticzApiResponse.result().body().getResult();
                        request.reply(serializeDomoticzDeviceList(listDomoticzDevices));
                    } else {
                        System.out.println(domoticzApiResponse.cause().getMessage());
                    }
                });
                break;
            case "plans":
                getPlans().send(domoticzApiResponse -> {
                    if (domoticzApiResponse.succeeded()) {
                        final List<DomoticzPlan> listDomoticzPlans = domoticzApiResponse.result().body().getResult();
                        request.reply(serializeDomoticzPlanList(listDomoticzPlans));
                    } else {
                        System.out.println(domoticzApiResponse.cause().getMessage());
                    }
                });
                break;
        }

    }

    private JsonArray serializeDomoticzDeviceList(List<DomoticzDevice> objectList) {
        final List<JsonObject> devices = objectList.stream()
                .map(JsonObject::mapFrom)
                .collect(Collectors.toList());
        JsonArray objects = new JsonArray();
        devices.forEach(objects::add);
        return objects;
    }

    private JsonArray serializeDomoticzPlanList(List<DomoticzPlan> objectList) {
        final List<JsonObject> devices = objectList.stream()
                .map(JsonObject::mapFrom)
                .collect(Collectors.toList());
        JsonArray objects = new JsonArray();
        devices.forEach(objects::add);
        return objects;
    }

    private HttpRequest<DomoticzDevicesResponse> getDevices() {

        System.out.println("Get devices from Domoticz API");

        return client
                .get(8081, "127.0.0.1", "/json.htm")
                .addQueryParam("type", "devices")
                .addQueryParam("used", "true")
                .addQueryParam("filter", "all")
                .addQueryParam("favorite", "1")
                .as(BodyCodec.json(DomoticzDevicesResponse.class));
    }

    private HttpRequest<DomoticzPlanResponse> getPlans() {

        System.out.println("Get plans from Domoticz API");

        return client
                .get(8081, "127.0.0.1", "/json.htm")
                .addQueryParam("type", "plans")
                .addQueryParam("used", "true")
                .as(BodyCodec.json(DomoticzPlanResponse.class));
    }

}
