package fr.seblaporte.domoticz;

import io.vertx.core.AbstractVerticle;
import io.vertx.serviceproxy.ServiceBinder;

public class DomoticzServiceVerticle extends AbstractVerticle {

    @Override
    public void start() {

        System.out.println("Demarrage de DomoticzServiceVerticle");

        DomoticzService service = DomoticzService.create(vertx);

        new ServiceBinder(vertx)
                .setAddress("domoticz")
                .register(DomoticzService.class, service);
    }
}
