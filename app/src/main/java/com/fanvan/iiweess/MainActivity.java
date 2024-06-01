package com.fanvan.iiweess;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.fanvan.iiweess.api.repository.Callback;
import com.fanvan.iiweess.api.repository.DataRepository;
import com.fanvan.iiweess.api.repository.LessonsRepository;
import com.fanvan.iiweess.api.type.DataType;
import com.fanvan.iiweess.api.type.Name;
import com.fanvan.iiweess.api.type.User;
import com.fanvan.iiweess.databinding.ActivityMainBinding;
import com.fanvan.iiweess.store.UserStore;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private DataRepository dataRepository;
    private LessonsRepository lessonsRepository;

    private String auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataRepository = new DataRepository();
        this.init();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initNavbar();
        loadFragment(new CheckupFragment());
    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void initNavbar() {
        UserStore userStore = new UserStore(this);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setSelectedItemId(R.id.nav_checkup);
//        if (userStore.getUser().getRole()) {
//            bottomNav.findViewById(R.id.nav_list).setVisibility(View.GONE);
//        }

        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_list) {
                loadFragment(new CheckupListsFragment());
            } else if (item.getItemId() == R.id.nav_checkup) {
                loadFragment(new CheckupFragment());
            } else if (item.getItemId() == R.id.nav_profile) {
                loadFragment(new ProfileFragment());
            }

            updateMenuItems(bottomNav);
            return true;
        });
    }

    public void updateMenuItems(BottomNavigationView bottomNav) {
        UserStore userStore = new UserStore(this);

        if (userStore.getUser().getRole()) {
            bottomNav.findViewById(R.id.nav_list).setVisibility(View.GONE);
        }
    }
    public void goToOnboardingActivity() {
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
    }

    private void init() {
        UserStore userStore = new UserStore(this);
        this.auth = userStore.getAuth();

        if (this.auth == null) {
            this.goToOnboardingActivity();
            return;
        }

/*        dataRepository.getData(this.auth, new Callback<DataType>() {
            @Override
            public void onDataReceived(DataType data) {
                userStore.saveUser(data.getUser());
                System.out.println(userStore.getUser().getName().getFirstName());
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable.toString());
            }
        });*/
//        Toast.makeText(this, this.auth, Toast.LENGTH_LONG).show();
    }
}