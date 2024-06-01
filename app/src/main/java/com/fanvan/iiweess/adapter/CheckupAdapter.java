package com.fanvan.iiweess.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckupAdapter extends RecyclerView.Adapter<CheckupAdapter.CheckupViewHolder> {

    private final List<String> people;

    public CheckupAdapter(List<String> people) {
        this.people = people;
    }

    @Override
    public CheckupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new CheckupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckupViewHolder holder, int position) {
        holder.nameTextView.setText(people.get(position));
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    static class CheckupViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        CheckupViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}