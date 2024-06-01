package com.fanvan.iiweess.api.type;

import com.google.gson.annotations.SerializedName;

public class NameRequestType {
    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
