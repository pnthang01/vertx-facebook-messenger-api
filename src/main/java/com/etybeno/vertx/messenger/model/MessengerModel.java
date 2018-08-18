package com.etybeno.vertx.messenger.model;

import io.vertx.core.json.JsonObject;

import java.util.Objects;

/**
 * Created by thangpham on 18/08/2018.
 */
public abstract class MessengerModel {
    protected final JsonObject json;

    public MessengerModel(JsonObject json) {
        Objects.requireNonNull(json);
        this.json = json;
    }

    public String getId() {
        return json.getString("id");
    }

    public Long getTime() {
        return json.getLong("time");
    }
}
