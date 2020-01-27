package fr.seblaporte.domoticz;

import fr.seblaporte.DTO.domoticz.DomoticzDevice;
import fr.seblaporte.DTO.domoticz.DomoticzDevicesResponse;
import fr.seblaporte.DTO.domoticz.DomoticzPlan;
import fr.seblaporte.DTO.domoticz.DomoticzPlanResponse;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.types.EventBusService;

import java.util.List;
import java.util.stream.Collectors;

public class DomoticzServiceImpl implements DomoticzService {

    private WebClient client;

    public DomoticzServiceImpl(WebClient webClient) {
        this.client = webClient;
    }

    public void getDevices(Handler<AsyncResult<JsonArray>> resultHandler) {
        getDevices().send(domoticzApiResponse -> {
            if (domoticzApiResponse.succeeded()) {
                final List<DomoticzDevice> listDomoticzDevices = domoticzApiResponse.result().body().getResult();
                resultHandler.handle(Future.succeededFuture(serializeDomoticzDeviceList(listDomoticzDevices)));
            } else {
                System.out.println(domoticzApiResponse.cause().getMessage());
            }
        });
    }

    public void getPlans(Handler<AsyncResult<JsonArray>> resultHandler) {
        getPlans().send(domoticzApiResponse -> {
            if (domoticzApiResponse.succeeded()) {
                final List<DomoticzPlan> listDomoticzPlans = domoticzApiResponse.result().body().getResult();
                resultHandler.handle(Future.succeededFuture(serializeDomoticzPlanList(listDomoticzPlans)));
            } else {
                System.out.println(domoticzApiResponse.cause().getMessage());
            }
        });
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
