package com.application.youngdeveloper.apptravelfinal.screen;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.MainFunction;
import com.application.youngdeveloper.apptravelfinal.config.Provinces;
import com.application.youngdeveloper.apptravelfinal.manager.CostLimit;
import com.application.youngdeveloper.apptravelfinal.manager.DataManager;
import com.application.youngdeveloper.apptravelfinal.dialog.Calendar_dialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by theerawat on 2/21/2017 AD.
 */

public class Screen_Add_Plan extends Fragment implements View.OnClickListener{


    public Screen_Add_Plan() {
        super();
    }

    public Screen_Add_Plan(int keyOfPlan){

    }

    public static Screen_Add_Plan newInstance() {
        Screen_Add_Plan fragment = new Screen_Add_Plan();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    private Spinner provinceSpinner;
    private EditText edtDateGo,edtDateBack,edtNamePlan,edtBudget,edtAccomCost,edtFoodCost;
    private Button btnAddPlan;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.screen_add_detail_plann, container, false);



        initialView(rootView);


        return rootView;
    }

    private void initialView(View rootView) {
        provinceSpinner = (Spinner) rootView.findViewById(R.id.editTextProvince);
        setSpinnerProvince(provinceSpinner);

        edtDateGo = (EditText) rootView.findViewById(R.id.editTextGo);
        edtDateGo.setOnClickListener(this);
        edtDateBack = (EditText) rootView.findViewById(R.id.editTextBack);
        edtDateBack.setOnClickListener(this);

        btnAddPlan = (Button) rootView.findViewById(R.id.buttonAddPlan);
        btnAddPlan.setOnClickListener(this);


        edtNamePlan = (EditText) rootView.findViewById(R.id.editTextNamePlan);
        edtBudget = (EditText) rootView.findViewById(R.id.editTextBudget);
        edtAccomCost = (EditText) rootView.findViewById(R.id.editTextAccomCost);
        edtFoodCost = (EditText) rootView.findViewById(R.id.editTextFoodCost);


    }

    private void setSpinnerProvince(Spinner provinceSpinner) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_item, Provinces.provinces);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        provinceSpinner.setAdapter(spinnerArrayAdapter);
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
    }

    @Override
    public void onClick(View view) {
        if(view == edtDateGo){

            Calendar_dialog calendar = new Calendar_dialog();
            calendar.show(getFragmentManager(), "Screen_Add_Plan");
            calendar.setEditText(edtDateGo);

        }else if(view == edtDateBack){
            Calendar_dialog calendar = new Calendar_dialog();
            calendar.show(getFragmentManager(), "Screen_Add_Plan");
            calendar.setEditText(edtDateBack);

        }else if(view == btnAddPlan){
            /**
             * when user click add plan button
             */


            if(checkValidEditText(edtNamePlan)){
                if(checkValidEditText(edtDateGo)){
                    if(checkValidEditText(edtDateBack)){
                        if(checkValidEditText(edtBudget)){
                            if(checkValidEditText(edtAccomCost)){
                                if(checkValidEditText(edtFoodCost)){


                                    AlertDialog alert = new AlertDialog.Builder(getContext())
                                            .setTitle(R.string.are_you_sure)
                                            .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // continue with delete

                                                    /**
                                                     * find number of date
                                                     */
                                                    int numberOfDate = 0;
                                                    String dateGo = edtDateGo.getText().toString();
                                                    String dateBack = edtDateBack.getText().toString();
                                                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                                    try {
                                                        Date date1 = formatter.parse(dateGo);
                                                        Date date2 = formatter.parse(dateBack);

                                                        date1 = MainFunction.removeTime(date1);
                                                        date2 = MainFunction.removeTime(date2);

                                                        numberOfDate = (MainFunction.daysBetween(date1.getTime(), date2.getTime()) ) + 1;
                                                        Log.d("Days : ",String.valueOf(numberOfDate));

                                                    } catch (ParseException e) {
                                                        e.printStackTrace();
                                                    }

                                                    /**
                                                     * confirmed
                                                     */

                                                    /**
                                                     * Insert Plan to Database
                                                     */
                                                    //TODO: insert Plan to app and Server

                                                    CostLimit.AccomCost = Double.parseDouble(edtAccomCost.getText().toString());
                                                    CostLimit.FoodCost = Double.parseDouble(edtFoodCost.getText().toString());

                                                    DataManager.getInstance().insertPlan();

                                                    /**
                                                     * send Budget to next screen
                                                     */
                                                    String budget = edtBudget.getText().toString();

                                                    if(numberOfDate > 0){
                                                        DataManager.getInstance().insertPlan();
                                                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                                                        ft.replace(R.id.fragment_container, new Screen_choose_plan_date(numberOfDate,dateGo,budget), "Screen_choose_plan_date");
                                                        ft.commit();
                                                    }else{
                                                        Toast.makeText(getContext(),R.string.E03,Toast.LENGTH_SHORT).show();
                                                    }


                                                }
                                            })

                                            .setIcon(R.drawable.ic_plan_active)
                                            .create();

                                    alert.show();

                                    Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                                    //Set positive button background
                                    pbutton.setTextColor(getResources().getColor(R.color.colorPrimary));





                                }
                            }
                        }
                    }
                }
            }


        }
    }



    private boolean checkValidEditText(EditText editText){
        String text = editText.getText().toString();
        if(text.isEmpty()){
            Toast.makeText(getContext(),R.string.E02,Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }


}
