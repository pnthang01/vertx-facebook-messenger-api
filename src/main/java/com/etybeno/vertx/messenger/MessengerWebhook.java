package com.etybeno.vertx.messenger;

/**
 * Created by thangpham on 17/08/2018.
 */
public interface MessengerWebhook {

    MessengerWebhook start();

    MessengerWebhook start(String path);

    WebhookPageObject page();
}
