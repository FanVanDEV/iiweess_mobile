package com.fanvan.iiweess;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fanvan.iiweess.adapter.CheckupAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckupListFragment extends Fragment {
    private List<List<String>> peopleLists;

    private int listIndex;

    public static CheckupListFragment newInstance(int listIndex, List<String> data) {
        CheckupListFragment fragment = new CheckupListFragment();
        Bundle args = new Bundle();

        args.putInt("listIndex", listIndex);
        args.putStringArrayList("data", new ArrayList<>(data));

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkup_list, container, false);

        if (getArguments() == null) {
            return view;
        }

        List<String> people = getArguments().getStringArrayList("data");

        listIndex = getArguments().getInt("listIndex");
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recyclerView.setAdapter(new CheckupAdapter(people));
        return view;
    }
}
