package com.fanvan.iiweess.api.type;

import com.google.gson.annotations.SerializedName;

public class CheckupRequestType {
    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
