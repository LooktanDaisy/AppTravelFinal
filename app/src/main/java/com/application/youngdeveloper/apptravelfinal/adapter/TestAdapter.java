package com.application.youngdeveloper.apptravelfinal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewGroup> {
    @Override
    public ViewGroup onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewGroup holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewGroup extends RecyclerView.ViewHolder {
        public ViewGroup(View itemView) {
            super(itemView);
        }
    }
}
