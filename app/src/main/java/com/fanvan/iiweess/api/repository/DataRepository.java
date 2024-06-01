package com.fanvan.iiweess.api.repository;

import com.fanvan.iiweess.api.DataService;
import com.fanvan.iiweess.api.client.RetrofitClient;
import com.fanvan.iiweess.api.type.*;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Response;

public class DataRepository {

    private final DataService dataService;

    public DataRepository() {
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();

        this.dataService = retrofit.create(DataService.class);
    }

    public void getData(String token, Callback<DataType> callback) {
        dataService.getData(token).enqueue(new retrofit2.Callback<DataType>() {
            @Override
            public void onResponse(Call<DataType> call, Response<DataType> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onDataReceived(response.body());
                } else {
                    Exception exception = new Exception("Ошибка ответа сервера: " + response.code());
                    callback.onFailure(exception);
                }
            }

            @Override
            public void onFailure(Call<DataType> call, Throwable t) {
                System.out.println("EEEEEEEEEEEEEEEEEEEE");
                callback.onFailure(t);
            }
        });
    }

    public void sendName(String token, String firstName, String secondName, Callback<Boolean> callback) {
        NameRequestType request = new NameRequestType();
        User user = new User();
        Name name = new Name();
        name.setFirst(firstName);
        name.setSecond(secondName);
        user.setName(name);
        request.setUser(user);
        dataService.sendName(token, request).enqueue(new retrofit2.Callback<NameResponseType>() {
            @Override
            public void onResponse(Call<NameResponseType> call, Response<NameResponseType> response) {
                if (response.isSuccessful()) {
                    callback.onDataReceived(true);
                } else {
                    Exception exception = new Exception("Ошибка ответа сервера: " + response.code());
                    callback.onFailure(exception);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
