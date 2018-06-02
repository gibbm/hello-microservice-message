package io.vertx.book.message;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class HelloMicroservice extends AbstractVerticle {

    @Override
    public void start() {
        vertx.eventBus().<String>consumer("hello", stringMessage -> {
            JsonObject json = new JsonObject().put("served-by", this.toString());

            if (stringMessage.body().isEmpty()) {
                stringMessage.reply(json.put("message", "hello"));
            } else {
                stringMessage.reply(json.put("message", "hello " + stringMessage.body()));
            }
        });
    }

}
