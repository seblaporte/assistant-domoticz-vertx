package fr.seblaporte;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class SmarthomeVerticle extends AbstractVerticle {

    @Override
    public void start() {

        System.out.println("Demarrage de Smarthome");

        Router router = Router.router(vertx);
        router.post("/smarthome").handler(this::smarthome);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);
    }

    private void smarthome(RoutingContext routingContext) {

        vertx.eventBus().send("domoticzApi", "plans", messageAsyncResult -> {
            if (messageAsyncResult.succeeded()) {
                routingContext.response().end(messageAsyncResult.result().body().toString());
            } else {
                System.out.println(messageAsyncResult.cause().getMessage());
            }
        });

    }

}
