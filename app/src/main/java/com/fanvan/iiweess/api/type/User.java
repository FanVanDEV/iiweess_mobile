package com.fanvan.iiweess.api.type;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    private Name name;

    @SerializedName("role")
    private Boolean role;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                '}';
    }
}
