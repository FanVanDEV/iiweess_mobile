package com.fanvan.iiweess.api.type;

import com.google.gson.annotations.SerializedName;

public class CheckupListResponseType {
    @SerializedName("isCheckuped")
    private Boolean isCheckuped;

    public Boolean getCheckuped() {
        return isCheckuped;
    }
}
