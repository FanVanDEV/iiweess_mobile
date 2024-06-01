package com.fanvan.iiweess.api.type;

import com.google.gson.annotations.SerializedName;

public class Name {
    @SerializedName("first")
    private String first;

    @SerializedName("second")
    private String second;

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public void setFirst(String firstName) {
        this.first = firstName;
    }

    public void setSecond(String secondName) {
        this.second = secondName;
    }
}
