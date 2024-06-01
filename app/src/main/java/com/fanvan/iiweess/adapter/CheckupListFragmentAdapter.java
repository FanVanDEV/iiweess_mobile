package com.fanvan.iiweess.adapter;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.fanvan.iiweess.CheckupListFragment;
import com.fanvan.iiweess.CheckupListsFragment;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CheckupListFragmentAdapter extends FragmentStateAdapter {
    private final int numberOfLists;
    private final List<List<String>> dataForAllLists;

    public CheckupListFragmentAdapter(Fragment fragment, int numberOfLists, List<List<String>> dataForAllLists) {
        super(fragment);
        this.numberOfLists = numberOfLists;
        this.dataForAllLists = dataForAllLists;
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        List<String> dataForPosition = dataForAllLists.get(position);

        return CheckupListFragment.newInstance(position, dataForPosition);
    }

    @Override
    public int getItemCount() {
        return numberOfLists;
    }
}