package fr.seblaporte.domoticz;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.client.WebClient;
import io.vertx.serviceproxy.ServiceBinder;

public class DomoticzServiceVerticle extends AbstractVerticle {

    @Override
    public void start() {

        System.out.println("Demarrage de DomoticzServiceVerticle");

        DomoticzService service = new DomoticzServiceImpl(WebClient.create(vertx));

        new ServiceBinder(vertx)
                .setAddress("domoticz")
                .register(DomoticzService.class, service);
    }
}
