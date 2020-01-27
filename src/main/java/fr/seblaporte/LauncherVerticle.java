package fr.seblaporte;

import fr.seblaporte.domoticz.DomoticzServiceVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class LauncherVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {

        vertx.deployVerticle(SmarthomeVerticle.class.getName());
        vertx.deployVerticle(DomoticzServiceVerticle.class.getName());
        vertx.deployVerticle(MqttClientVerticle.class.getName());
        vertx.deployVerticle(DeviceControllerVerticle.class.getName());
    }

}
