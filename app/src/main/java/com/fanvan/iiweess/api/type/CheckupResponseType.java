package com.fanvan.iiweess.api.type;

import com.google.gson.annotations.SerializedName;

public class CheckupResponseType {
    @SerializedName("checkuped")
    private Boolean checkuped;

    public Boolean getCheckuped() {
        return checkuped;
    }
}
