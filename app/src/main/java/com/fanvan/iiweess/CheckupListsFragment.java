package com.fanvan.iiweess;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.fanvan.iiweess.adapter.CheckupListFragmentAdapter;
import com.fanvan.iiweess.api.repository.Callback;
import com.fanvan.iiweess.api.repository.LessonsRepository;
import com.fanvan.iiweess.databinding.ActivityMainBinding;
import com.fanvan.iiweess.databinding.FragmentCheckupListsBinding;
import com.fanvan.iiweess.store.UserStore;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckupListsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckupListsFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_checkup_lists, container, false);



        this.getCheckups();
        return view;
    }

    public void getCheckups() {
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.VISIBLE);
        UserStore userStore = new UserStore(getContext());

        LessonsRepository lessonsRepository = new LessonsRepository();
        lessonsRepository.getCheckupList(userStore.getAuth(), new Callback<String[][]>() {
            @Override
            public void onDataReceived(String[][] data) {
                progressBar.setVisibility(View.GONE);

                List<List<String>> listOfLists = new ArrayList<>();

                for (String[] subArray : data) {
                    listOfLists.add(Arrays.asList(subArray));
                }

                View emptyView = view.findViewById(R.id.empty_list);

                if (listOfLists.isEmpty()) {
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    emptyView.setVisibility(View.GONE);
                    initList(listOfLists);
                }

            }

            @Override
            public void onFailure(Throwable throwable) {
                progressBar.setVisibility(View.GONE);

                System.out.println(throwable);
            }
        });
    }

    public void initList(List<List<String>> data) {
        int numberOfLists = data.size();
        ViewPager2 viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new CheckupListFragmentAdapter(this, numberOfLists, data));

        TabLayout tabLayout = view.findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
            if (data.get(position).isEmpty()) {
                tab.view.setVisibility(View.GONE);
            } else {
                tab.setText((position + 1) + " пара");
            }
        }).attach();

        for (int i = 0; i < data.size(); i++) {
            if (!data.get(i).isEmpty()) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }
}