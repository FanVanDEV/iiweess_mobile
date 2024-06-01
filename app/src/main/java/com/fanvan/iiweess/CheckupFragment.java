package com.fanvan.iiweess;

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fanvan.iiweess.api.repository.Callback;
import com.fanvan.iiweess.api.repository.DataRepository;
import com.fanvan.iiweess.api.repository.LessonsRepository;
import com.fanvan.iiweess.api.type.CheckupResponseType;
import com.fanvan.iiweess.api.type.DataType;
import com.fanvan.iiweess.databinding.ActivityMainBinding;
import com.fanvan.iiweess.databinding.FragmentCheckupBinding;
import com.fanvan.iiweess.store.UserStore;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckupFragment extends Fragment {
    private FragmentCheckupBinding binding;

    private DataRepository dataRepository;
    private LessonsRepository lessonsRepository;

    private UserStore userStore;

    private String auth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckupFragment newInstance(String param1, String param2) {
        CheckupFragment fragment = new CheckupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        this.userStore = new UserStore(getContext());
        this.lessonsRepository = new LessonsRepository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCheckupBinding.inflate(inflater, container, false);
        getData();

        binding.submitButton.setOnClickListener(v -> sendCheckups());
        return binding.getRoot();
    }

    private void getData() {
        dataRepository = new DataRepository();

        dataRepository.getData(this.userStore.getAuth(), new Callback<DataType>() {
            @Override
            public void onDataReceived(DataType data) {
                if (getActivity() == null) return;

                getActivity().runOnUiThread(() -> {
                    initData(data);
                });
            }

            @Override
            public void onFailure(Throwable t) {
                if (getActivity() == null) return;

                getActivity().runOnUiThread(() -> {
                });
            }
        });
    }

    private void initData(@NotNull DataType data) {
        this.userStore.saveUser(data.getUser());
        this.userStore.saveCheckups(data.getCheckups());
        binding.textViewName.setText(this.userStore.getUser().getName().getFirst());

        List<String> lessons = data.getLessons();
        List<Boolean> checkups = data.getCheckups();

        removeLessonCheckboxes();

        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i) == null) {
                continue;
            }

            createLessonCheckbox(i, lessons.get(i), checkups.get(i));
        }
    }

    private void createLessonCheckbox(int id, String optionText, boolean isChecked) {
        CheckBox checkBox = new CheckBox(getContext());
        checkBox.setId(id);
        checkBox.setText(optionText);
        checkBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        checkBox.setChecked(isChecked);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 10, 0, 10);
        checkBox.setLayoutParams(layoutParams);

        binding.checkboxesContainer.addView(checkBox);
    }

    private void removeLessonCheckboxes() {
        binding.checkboxesContainer.removeAllViews();
    }
    public void sendCheckups() {
        for (int i = 0; i < binding.checkboxesContainer.getChildCount(); i++) {
            View conatinerView = binding.checkboxesContainer.getChildAt(i);
            if (!(conatinerView instanceof CheckBox)) {
                continue;
            }

            CheckBox lesson = (CheckBox) conatinerView;

            List<Boolean> checkups = this.userStore.getCheckups();

            if (lesson.isChecked() != checkups.get(lesson.getId())) {
                lessonsRepository.sendData(this.userStore.getAuth(), lesson.getId(), new Callback<CheckupResponseType>() {
                    @Override
                    public void onDataReceived(CheckupResponseType data) {
                        if (getActivity() == null) return;

                        getActivity().runOnUiThread(() -> {
                            lesson.setChecked(data.getCheckuped());
                        });
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        if (getActivity() == null) return;

                        getActivity().runOnUiThread(() -> {
                        });
                    }
                });
            }
        }
        getData();
    }
}