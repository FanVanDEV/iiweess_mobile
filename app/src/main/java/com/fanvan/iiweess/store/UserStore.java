package com.fanvan.iiweess.store;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import com.fanvan.iiweess.api.type.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class UserStore {

    private SharedPreferences sharedPreferences;
    private final Gson gson = new Gson();

    public UserStore(Context context) {
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            this.sharedPreferences = EncryptedSharedPreferences.create(
                    context,
                    "user_secure",
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();

            this.sharedPreferences = context.getSharedPreferences("user_default", Context.MODE_PRIVATE);
        }
    }

    public void saveAuth(String token) {
        try {
            sharedPreferences.edit()
                    .putString("auth", token)
                    .apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAuth() {
        try {
            return sharedPreferences.getString("auth", null);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public void saveUser(User user) {
        String userJson = gson.toJson(user);
        sharedPreferences.edit()
                .putString("data", userJson)
                .apply();
    }

    public User getUser() {
        String userJson = sharedPreferences.getString("data", null);
        if (userJson == null) {
            return null;
        }

        return gson.fromJson(userJson, User.class);
    }

    public List<Boolean> getCheckups() {
        String checkupsJson = sharedPreferences.getString("checkups", null);
        if (checkupsJson == null) {
            return null;
        }

        return gson.fromJson(checkupsJson, new TypeToken<List<Boolean>>(){}.getType());
    }

    public void saveCheckups(List<Boolean> checkups) {
        String checkupsJson = gson.toJson(checkups);
        sharedPreferences.edit()
                .putString("checkups", checkupsJson)
                .apply();
    }
}