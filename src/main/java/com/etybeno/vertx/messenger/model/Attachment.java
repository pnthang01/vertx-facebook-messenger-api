package com.etybeno.vertx.messenger.model;

import io.vertx.core.json.JsonObject;

/**
 * Created by thangpham on 18/08/2018.
 */
public class Attachment {

    private JsonObject json;

    public Attachment(JsonObject json) {
        this.json = json;
    }

    public boolean isFallback() {
        return "fallback".equals(json.getString("type"));
    }

    public boolean isImage() {
        return "image".equals(json.getString("type"));
    }

    public boolean isVideo() {
        return "video".equals(json.getString("video"));
    }

    public boolean isAudio() {
        return "audio".equals(json.getString("audio"));
    }

    public boolean isFile() {
        return "file".equals(json.getString("file"));
    }

    public String getMultiMediaPayload() {
        JsonObject payload = json.getJsonObject("payload");
        if(null == payload) return null;
        else return payload.getString("url");
    }
}
