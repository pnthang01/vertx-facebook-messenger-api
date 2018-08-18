package com.etybeno.vertx.messenger;

import com.etybeno.vertx.messenger.impl.MessengerClientImpl;
import io.vertx.core.Vertx;

/**
 * Created by thangpham on 17/08/2018.
 */
public interface MessengerClient {

    static MessengerClient create(Vertx vertx) {
        return new MessengerClientImpl(vertx);
    }

    MessengerWebhook webhook();

}