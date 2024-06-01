package com.fanvan.iiweess;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.fanvan.iiweess.store.UserStore;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_acrivity);

        Intent intent = getIntent();
        Uri data = intent.getData();

        if (Intent.ACTION_VIEW.equals(intent.getAction()) && data != null) {
            String token = data.getQueryParameter("token");

            UserStore userStore = new UserStore(this);
            userStore.saveAuth(token);
        }

        this.goToMainActivity();
    }

    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}