package com.etybeno.vertx.messenger.model;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thangpham on 18/08/2018.
 */
public class Message extends MessengerModel {

    private JsonObject messaging;

    public Message(JsonObject json) {
        super(json);
        this.messaging = json.getJsonArray("messaging").getJsonObject(0);
    }


    public String getSenderId() {
        return messaging.getJsonObject("sender").getString("id");
    }

    public String getRecipientId() {
        return messaging.getJsonObject("recipient").getString("id");
    }

    public Long getTimestamp() {
        return messaging.getLong("timestamp");
    }

    public String getMessageId() {
        return messaging.getJsonObject("message").getString("mid");
    }

    public String getText() {
        return messaging.getJsonObject("message").getString("text");
    }

    public List<Attachment> getAttachments() {
        JsonArray attachmentsArr = messaging.getJsonArray("attachment");
        if (null == attachmentsArr) return null;
        else {
            List<Attachment> attachments = new ArrayList<>();
            for (int i = 0; i < attachmentsArr.size(); i++) {
                attachments.add(new Attachment(attachmentsArr.getJsonObject(i)));
            }
            return attachments;
        }
    }
}
