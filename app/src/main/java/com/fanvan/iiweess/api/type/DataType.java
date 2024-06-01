package com.fanvan.iiweess.api.type;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataType {

    @SerializedName("user")
    private User user;

    @SerializedName("lessons")
    private List<String> lessons;

    @SerializedName("checkups")
    private List<Boolean> checkups;

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getLessons() {
        return this.lessons;
    }

    public void setLessons(List<String> lessons) {
        this.lessons = lessons;
    }

    public List<Boolean> getCheckups() {
        return this.checkups;
    }

    public void setCheckups(List<Boolean> checkups) {
        this.checkups = checkups;
    }

    @Override
    public String toString() {
        return "DataType{" +
                "user=" + user.toString() +
                ", lessons='" + lessons.toString() + '\'' +
                ", checkups=" + checkups.toString() +
                '}';
    }
}
