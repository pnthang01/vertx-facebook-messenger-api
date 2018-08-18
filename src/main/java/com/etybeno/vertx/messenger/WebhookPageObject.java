package com.etybeno.vertx.messenger;

import com.etybeno.vertx.messenger.model.Message;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Created by thangpham on 18/08/2018.
 */
public interface WebhookPageObject {

    void setMessageEventHandler(Handler<Message> handler);

    void setMessagePostBackEventHandler(Handler<JsonObject> handler);

    void setMessageDeliveryEventHandler(Handler<JsonObject> handler);

    void setMessageReadEventHandler(Handler<JsonObject> messageReadEventHandler);

    void acceptPayload(JsonArray entries);
}
