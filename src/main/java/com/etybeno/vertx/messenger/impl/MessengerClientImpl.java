package com.etybeno.vertx.messenger.impl;

import com.etybeno.vertx.messenger.MessengerClient;
import com.etybeno.vertx.messenger.MessengerWebhook;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.ext.web.client.WebClient;

/**
 * Created by thangpham on 17/08/2018.
 */
public class MessengerClientImpl implements MessengerClient {

    private Vertx vertx;

    private MessengerWebhook webhook;

    public MessengerClientImpl(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public synchronized MessengerWebhook webhook() {
        if(null == webhook) webhook = new MessengerWebhookImpl(vertx);
        return webhook;
    }
}
