package fr.seblaporte.domoticz;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.client.WebClient;

@ProxyGen
public interface DomoticzService {

    static DomoticzService create(Vertx vertx) {

        WebClient webClient = WebClient.create(vertx);

        return new DomoticzServiceImpl(webClient);
    }

    static DomoticzService createProxy(Vertx vertx, String address) {
        return new DomoticzServiceVertxEBProxy(vertx, address);
    }

    void getDevices(Handler<AsyncResult<JsonArray>> resultHandler);

    void getPlans(Handler<AsyncResult<JsonArray>> resultHandler);
}
