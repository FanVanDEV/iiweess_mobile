package com.fanvan.iiweess.api;

import com.fanvan.iiweess.api.type.CheckupRequestType;
import com.fanvan.iiweess.api.type.CheckupResponseType;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface LessonsService {
    @POST("/api/checkup")
    Call<CheckupResponseType> sendCheckup(@Header("Authorization") String token, @Body CheckupRequestType data);

    @GET("/api/checkup/list")
    Call<String[][]> getCheckupList(@Header("Authorization") String token);
}
