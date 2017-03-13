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
import com.application.youngdeveloper.apptravelfinal.adapter.ListMyPlanDateAdapter;
import com.application.youngdeveloper.apptravelfinal.config.MainFunction;
import com.application.youngdeveloper.apptravelfinal.dao.PlanListDao;
import com.application.youngdeveloper.apptravelfinal.manager.PlanListManager;

import java.util.Date;

public class Screen_choose_my_plan_date extends Fragment implements View.OnClickListener{

    private int numberOfDate,keyPlan;
    private int budget;
    private RecyclerView listDate;
    private ListMyPlanDateAdapter listMyPlanDateAdapter;
    private TextView tvBudget,tvBack;
    private ImageView ImgBack;


    public Screen_choose_my_plan_date() {
        super();
    }

    public Screen_choose_my_plan_date(int numberOfDate, int keyPlan,int budget) {
        this.numberOfDate = numberOfDate;
        this.keyPlan = keyPlan;
        this.budget = budget;
    }

    public static Screen_choose_my_plan_date newInstance() {
        Screen_choose_my_plan_date fragment = new Screen_choose_my_plan_date();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.screen_my_plan_by_date, container, false);
        initInstances(rootView);

        return rootView;
    }


    private void initInstances(View rootView) {
        listDate = (RecyclerView) rootView.findViewById(R.id.list_date);
        listDate.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayout.VERTICAL, false));
        listDate.setHasFixedSize(true);
        listMyPlanDateAdapter = new ListMyPlanDateAdapter(getActivity(),numberOfDate,keyPlan);
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        listMyPlanDateAdapter.setFragmentTran(ft);
        listDate.setAdapter(listMyPlanDateAdapter);

        tvBudget = (TextView) rootView.findViewById(R.id.textViewBudget);
        tvBudget.setText(MainFunction.commaDouble(budget));

        ImgBack =(ImageView) rootView.findViewById(R.id.imageViewBack);
        ImgBack.setOnClickListener(this);

        tvBack = (TextView) rootView.findViewById(R.id.textViewBack);
        tvBack.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        if(view == ImgBack || view == tvBack){

            getFragmentManager().popBackStack();

        }
    }





}
