package com.fanvan.iiweess.api.type;

import com.google.gson.annotations.SerializedName;

public class NameResponseType {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
