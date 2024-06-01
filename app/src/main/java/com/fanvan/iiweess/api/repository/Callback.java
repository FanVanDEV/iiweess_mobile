package com.fanvan.iiweess.api.repository;

import com.fanvan.iiweess.api.type.DataType;

public interface Callback<T> {
    void onDataReceived(T data);
    void onFailure(Throwable throwable);
}
