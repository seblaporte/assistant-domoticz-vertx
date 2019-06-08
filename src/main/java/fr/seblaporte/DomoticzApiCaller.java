package fr.seblaporte;

import fr.seblaporte.DTO.domoticz.DomoticzDevicesResponse;
import fr.seblaporte.DTO.domoticz.DomoticzPlanResponse;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;

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
                        request.reply(JsonObject.mapFrom(domoticzApiResponse.result().body()));
                    } else {
                        System.out.println(domoticzApiResponse.cause().getMessage());
                    }
                });
                break;
            case "plans":
                getPlans().send(domoticzApiResponse -> {
                    if (domoticzApiResponse.succeeded()) {
                        request.reply(JsonObject.mapFrom(domoticzApiResponse.result().body()));
                    } else {
                        System.out.println(domoticzApiResponse.cause().getMessage());
                    }
                });
                break;
        }

    }

    private HttpRequest<DomoticzDevicesResponse> getDevices() {

        System.out.println("Get devices from Domoticz API");

        return client
                .get(8080, "192.168.5.110", "/json.htm")
                .addQueryParam("type", "devices")
                .addQueryParam("used", "true")
                .addQueryParam("filter", "all")
                .addQueryParam("favorite", "1")
                .as(BodyCodec.json(DomoticzDevicesResponse.class));
    }

    private HttpRequest<DomoticzPlanResponse> getPlans() {

        System.out.println("Get plans from Domoticz API");

        return client
                .get(8080, "192.168.5.110", "/json.htm")
                .addQueryParam("type", "plans")
                .addQueryParam("used", "true")
                .as(BodyCodec.json(DomoticzPlanResponse.class));
    }

}
