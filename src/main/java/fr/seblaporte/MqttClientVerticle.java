package fr.seblaporte;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.mqtt.MqttClient;

public class MqttClientVerticle extends AbstractVerticle {

    private MqttClient mqttClient;

    @Override
    public void start() {

        System.out.println("Demarrage de MqttClient");

        mqttClient = MqttClient.create(vertx);

        final MessageConsumer<JsonObject> consumer = vertx.eventBus().consumer("mqttClient");
        consumer.handler(this::sendMessageToTopic);
    }

    private void sendMessageToTopic(Message<JsonObject> jsonObjectMessage) {

        mqttClient.connect(1884, "192.168.5.110", s -> {
            mqttClient.publish("domoticz/in",
                    Buffer.buffer(jsonObjectMessage.body().encodePrettily()),
                    MqttQoS.AT_LEAST_ONCE,
                    false,
                    false);
            mqttClient.disconnect();
            jsonObjectMessage.reply("SUCCESS");
        });
    }

}
