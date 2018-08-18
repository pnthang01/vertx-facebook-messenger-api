package com.etybeno.vertx.messenger.impl;

import com.etybeno.vertx.messenger.WebhookPageObject;
import com.etybeno.vertx.messenger.model.Message;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Created by thangpham on 18/08/2018.
 */
public class WebhookPageObjectImpl implements WebhookPageObject {

    private final Vertx vertx;

    private Handler<Message> messageEventHandler;
    private Handler<JsonObject> messagePostBackEventHandler;
    private Handler<JsonObject> messageDeliveryEventHandler;
    private Handler<JsonObject> messageReadEventHandler;

    public WebhookPageObjectImpl(Vertx vertx) {
        this.vertx = vertx;
    }

    public synchronized void setMessageEventHandler(Handler<Message> handler) {
        this.messageEventHandler = handler;
    }

    public synchronized void setMessagePostBackEventHandler(Handler<JsonObject> handler) {
        this.messagePostBackEventHandler = handler;
    }

    public synchronized void setMessageDeliveryEventHandler(Handler<JsonObject> handler) {
        this.messageDeliveryEventHandler = handler;
    }

    public synchronized void setMessageReadEventHandler(Handler<JsonObject> messageReadEventHandler) {
        this.messageReadEventHandler = messageReadEventHandler;
    }

    public void acceptPayload(JsonArray entries) {
        vertx.getOrCreateContext().runOnContext(event -> {
            for (int i = 0; i < entries.size(); i++) {
                JsonObject entry = entries.getJsonObject(i);
                if (entry.containsKey("messaging")) {
                    if (null != messageEventHandler) messageEventHandler.handle(new Message(entry));
                } else if (entry.containsKey("messaging_account_linking")) {
                    System.out.println("Not implemented yet");
                } else if (entry.containsKey("delivery")) {
                    if (null != messageDeliveryEventHandler) messageDeliveryEventHandler.handle(entry);
                } else if (entry.containsKey("message_echoes")) {
                    System.out.println("Not implemented yet");
                } else if (entry.containsKey("messaging_game_plays")) {

                } else if (entry.containsKey("messaging_optins")) {

                } else if (entry.containsKey("postback")) {
                    if (null != messagePostBackEventHandler) messagePostBackEventHandler.handle(entry);
                } else if (entry.containsKey("read")) {
                    if (null != messageReadEventHandler) messageReadEventHandler.handle(entry);
                } else if (entry.containsKey("messaging_referrals")) {

                }
            }
        });
    }
}
