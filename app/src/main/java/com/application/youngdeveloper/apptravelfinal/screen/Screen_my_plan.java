package com.application.youngdeveloper.apptravelfinal.screen;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.adapter.ListDateAdapter;
import com.application.youngdeveloper.apptravelfinal.adapter.ListMyPlanAdapter;

public class Screen_my_plan extends Fragment{

    private RecyclerView listMyPlan;
    private ListMyPlanAdapter listMyPlanAdapter;



    public Screen_my_plan() {
        super();
    }

    public static Screen_my_plan newInstance() {
        Screen_my_plan fragment = new Screen_my_plan();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.screen_my_plan, container, false);
        initInstances(rootView);

        return rootView;
    }


    private void initInstances(View rootView) {
        listMyPlan = (RecyclerView) rootView.findViewById(R.id.list_my_plan);
        listMyPlan.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayout.VERTICAL, false));
        listMyPlan.setHasFixedSize(true);

        listMyPlanAdapter = new ListMyPlanAdapter(getContext());
        listMyPlanAdapter.setFragmentTran(getFragmentManager().beginTransaction());

        listMyPlan.setAdapter(listMyPlanAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }

        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

}
