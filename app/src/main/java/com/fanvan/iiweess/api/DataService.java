package com.fanvan.iiweess.api;

import com.fanvan.iiweess.api.type.DataType;
import com.fanvan.iiweess.api.type.NameRequestType;
import com.fanvan.iiweess.api.type.NameResponseType;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;

public interface DataService {
    @GET("/api/data")
    Call<DataType> getData(@Header("Authorization") String token);

    @PATCH("/api/data/name")
    Call<NameResponseType> sendName(@Header("Authorization") String token, @Body NameRequestType data);
}