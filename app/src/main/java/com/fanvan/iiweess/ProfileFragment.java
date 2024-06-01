package com.fanvan.iiweess;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fanvan.iiweess.api.repository.Callback;
import com.fanvan.iiweess.api.repository.DataRepository;
import com.fanvan.iiweess.store.UserStore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private DataRepository dataRepository;
    private UserStore userStore;

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private Button buttonUpdate;

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        editTextFirstName = view.findViewById(R.id.editTextFirstName);
        editTextLastName = view.findViewById(R.id.editTextLastName);
        buttonUpdate = view.findViewById(R.id.buttonUpdate);

        dataRepository = new DataRepository();
        userStore = new UserStore(getContext());

        editTextFirstName.setText(userStore.getUser().getName().getFirst());
        editTextLastName.setText(userStore.getUser().getName().getSecond());
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUpdate();
            }
        });

        return view;
    }

    private void sendUpdate() {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();

        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            updateUserOnServer(firstName, lastName);
        }
    }

    private void updateUserOnServer(String firstName, String lastName) {
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.VISIBLE);
        dataRepository.sendName(userStore.getAuth(), firstName, lastName, new Callback<Boolean>() {
            @Override
            public void onDataReceived(Boolean data) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}