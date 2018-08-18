package com.etybeno.vertx.test;

import com.etybeno.vertx.messenger.MessengerClient;
import com.etybeno.vertx.messenger.MessengerWebhook;
import com.etybeno.vertx.messenger.WebhookPageObject;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * Created by thangpham on 18/08/2018.
 */
public class MessengerTest extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MessengerTest());
    }

    @Override
    public void start() throws Exception {
        MessengerClient messengerClient = MessengerClient.create(vertx);
        MessengerWebhook webhook = messengerClient.webhook().start();
        WebhookPageObject pageObject = webhook.page();
        pageObject.setMessageEventHandler(messageEvent -> {
            System.out.println(String.format("Message '%s' sent at %d by %s", messageEvent.getText(),
                    messageEvent.getTimestamp(), messageEvent.getSenderId()));
        });
    }
}
