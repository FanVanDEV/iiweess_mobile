package com.fanvan.iiweess.api.repository;

import com.fanvan.iiweess.api.LessonsService;
import com.fanvan.iiweess.api.client.RetrofitClient;
import com.fanvan.iiweess.api.type.CheckupRequestType;
import com.fanvan.iiweess.api.type.CheckupResponseType;
import com.fanvan.iiweess.api.type.DataType;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LessonsRepository {
    private final LessonsService lessonsService;

    public LessonsRepository() {
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();

        this.lessonsService = retrofit.create(LessonsService.class);
    }

    public void sendData(String token, int id, Callback<CheckupResponseType> callback) {
        CheckupRequestType request = new CheckupRequestType();
        request.setId(id);
        lessonsService.sendCheckup(token, request).enqueue(new retrofit2.Callback<CheckupResponseType>() {
            @Override
            public void onResponse(Call<CheckupResponseType> call, Response<CheckupResponseType> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onDataReceived(response.body());
                } else {
                    Exception exception = new Exception("Ошибка ответа сервера: " + response.code());
                    callback.onFailure(exception);
                }
            }

            @Override
            public void onFailure(Call<CheckupResponseType> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void getCheckupList(String token, Callback<String[][]> callback) {
        lessonsService.getCheckupList(token).enqueue(new retrofit2.Callback<String[][]>() {
            @Override
            public void onResponse(Call<String[][]> call, Response<String[][]> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onDataReceived(response.body());
                } else {
                    Exception exception = new Exception("Ошибка ответа сервера: " + response.code());
                    callback.onFailure(exception);
                }
            }

            @Override
            public void onFailure(Call<String[][]> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
