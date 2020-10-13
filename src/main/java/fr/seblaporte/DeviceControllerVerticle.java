package fr.seblaporte;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;

public class DeviceControllerVerticle extends AbstractVerticle {

    @Override
    public void start() {

        System.out.println("Demarrage de DeviceController");

        final MessageConsumer<JsonObject> consumer = vertx.eventBus().consumer("deviceController");
        consumer.handler(this::controlDevice);
    }

    private void controlDevice(Message<JsonObject> jsonObjectMessage) {

        final String deviceId = jsonObjectMessage.body().getString("deviceId");
        final String command = jsonObjectMessage.body().getString("command");

        if ("action.devices.commands.OnOff".equals(command)) {
            final Boolean on = jsonObjectMessage.body().getJsonObject("params").getBoolean("on");
            vertx.eventBus().send("mqttClient", controlOnOff(deviceId, on), messageAsyncResult -> {
                replyHandler(jsonObjectMessage, messageAsyncResult);
            });
        } else if ("action.devices.commands.BrightnessAbsolute".equals(command)) {

            final Integer maxBrightnessValue = jsonObjectMessage.body().getJsonObject("customData").getInteger("maxBrightnessValue");
            final Integer requestedBrightness = jsonObjectMessage.body().getJsonObject("params").getInteger("brightness");

            final float brightness = ((float) maxBrightnessValue / 100) * requestedBrightness;

            vertx.eventBus().send("mqttClient", controlBrightness(deviceId, Math.round(brightness)), messageAsyncResult -> {
                replyHandler(jsonObjectMessage, messageAsyncResult);
            });
        }
    }

    private void replyHandler(Message<JsonObject> jsonObjectMessage, AsyncResult<Message<Object>> messageAsyncResult) {
        if (messageAsyncResult.succeeded()) {
            jsonObjectMessage.reply("SUCCESS");
        } else {
            jsonObjectMessage.reply("ERROR");
        }
    }

    private JsonObject controlOnOff(String deviceId, boolean on) {
        final String onOff = on ? "On" : "Off";
        return new JsonObject()
                .put("command", "switchlight")
                .put("idx", Long.valueOf(deviceId))
                .put("switchcmd", onOff);
    }

    private JsonObject controlBrightness(String deviceId, Integer brightness) {
        return new JsonObject()
                .put("command", "switchlight")
                .put("idx", Long.valueOf(deviceId))
                .put("switchcmd", "Set Level")
                .put("level", brightness);
    }
}
