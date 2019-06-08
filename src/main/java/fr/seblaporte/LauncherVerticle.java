package fr.seblaporte;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class LauncherVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {
        vertx.deployVerticle(SmarthomeVerticle.class.getName());
        vertx.deployVerticle(DomoticzApiCaller.class.getName());
    }

}
