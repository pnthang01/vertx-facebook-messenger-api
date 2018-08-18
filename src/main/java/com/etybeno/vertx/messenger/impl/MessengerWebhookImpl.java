package com.etybeno.vertx.messenger.impl;

import com.etybeno.vertx.messenger.MessengerWebhook;
import com.etybeno.vertx.messenger.WebhookPageObject;
import io.netty.util.internal.StringUtil;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

/**
 * Created by thangpham on 17/08/2018.
 */
public class MessengerWebhookImpl implements MessengerWebhook {

    private Vertx vertx;

    private WebhookPageObject pageObject;

    String VERIFY_TOKEN = "123456";

    public MessengerWebhookImpl(Vertx vertx) {
        this.vertx = vertx;
    }

    public MessengerWebhook start() {
        return start("/webhook");
    }

    public MessengerWebhook start(String path) {
        HttpServer httpServer = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router.post(path).handler(context -> {
            HttpServerResponse response = context.response();
            HttpServerRequest request = context.request();

            request.bodyHandler(bodyHandler -> {
                JsonObject payload = bodyHandler.toJsonObject();
                switch (payload.getString("object")) {
                    case "page":
                        System.out.println("page type " + payload.toString());
                        if (null != pageObject) {
                            pageObject.acceptPayload(payload.getJsonArray("entry"));
                        }
                }
                response.setStatusCode(200).end("EVENT_RECEIVED");
            });
        });

        //Verification
        router.get(path).handler(context -> {
            HttpServerResponse response = context.response();
            HttpServerRequest request = context.request();
            String mode = request.getParam("hub.mode");
            String token = request.getParam("hub.verify_token");
            String challenge = request.getParam("hub.challenge");
            if (!StringUtil.isNullOrEmpty(mode) && !StringUtil.isNullOrEmpty(token)) {
                if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(token)) {
                    response.setStatusCode(200).end(challenge);
                } else response.setStatusCode(403);
            }
        });
        httpServer.requestHandler(router::accept).listen(8080, "localhost");
        return this;
    }

    public synchronized WebhookPageObject page() {
        if (null == pageObject) pageObject = new WebhookPageObjectImpl(vertx);
        return pageObject;
    }

}
