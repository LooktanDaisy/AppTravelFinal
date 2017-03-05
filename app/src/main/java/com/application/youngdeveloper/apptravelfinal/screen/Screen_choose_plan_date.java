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

public class Screen_choose_plan_date extends Fragment implements View.OnClickListener{

    private int numberOfDate;
    private String startDate,budget;
    private RecyclerView listDate;
    private ListDateAdapter listDateAdapter;
    private TextView tvBudget,tvBack;
    private ImageView ImgBack;


    public Screen_choose_plan_date() {
        super();
    }

    public Screen_choose_plan_date(int numberOfDate,String startDate,String budget) {
        this.numberOfDate = numberOfDate;
        this.startDate = startDate;
        this.budget = budget;
    }

    public static Screen_choose_plan_date newInstance() {
        Screen_choose_plan_date fragment = new Screen_choose_plan_date();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.screen_manage_plan_by_date, container, false);
        initInstances(rootView);

        return rootView;
    }


    private void initInstances(View rootView) {
        listDate = (RecyclerView) rootView.findViewById(R.id.list_date);
        listDate.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayout.VERTICAL, false));
        listDate.setHasFixedSize(true);
        listDateAdapter = new ListDateAdapter(getActivity(),numberOfDate,startDate);
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        listDateAdapter.setFragmentTran(ft);
        listDate.setAdapter(listDateAdapter);

        tvBudget = (TextView) rootView.findViewById(R.id.textViewBudget);
        tvBudget.setText(budget);

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

            showAlertDialog();

        }
    }




    private void showAlertDialog(){

        AlertDialog alert = new AlertDialog.Builder(getContext())
                .setTitle(R.string.are_you_sure_cancel_plan)
                .setPositiveButton(R.string.edit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: Edit plan , send key of Plan to previous screen
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_container, new Screen_Add_Plan(1000), "Screen_choose_plan_date with Key");
                        ft.commit();
                    }
                })
                .setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //TODO: delete plan , send key to server for delete and delete on model
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_container, Screen_Add_Plan.newInstance(), "Screen_choose_plan_date from delete");
                        ft.commit();

                    }
                })

                .setIcon(R.drawable.ic_edit)
                .create();

        alert.show();

        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        //Set positive button background
        pbutton.setTextColor(getResources().getColor(R.color.colorPrimary));
        nbutton.setTextColor(getResources().getColor(R.color.text_blue_trans));
    }
}
