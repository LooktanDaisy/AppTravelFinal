package com.application.youngdeveloper.apptravelfinal.screen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.adapter.PlaceListAdapter;
import com.application.youngdeveloper.apptravelfinal.config.Provinces;
import com.application.youngdeveloper.apptravelfinal.config.Type_id_item;
import com.application.youngdeveloper.apptravelfinal.manager.PlaceListManager;

public class Screen_Dialog_Place extends DialogFragment {

    private ListView lvListPlace;
    private Screen_add_detail_of_days ControlMainScreen;
    private Spinner spinnerTypePlace;
    private PlaceListAdapter listPlaceAdapter;
    private TextView tvNotFound;
    private Screen_show_detail_of_days ControlMainScreenShow = null;

    public Screen_Dialog_Place() {
        super();
    }

    public static Screen_Dialog_Place newInstance() {
        Screen_Dialog_Place fragment = new Screen_Dialog_Place();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_choose_to_list, container, false);

        initialView(rootView);
        return rootView;
    }

    private void initialView(View rootView) {
        lvListPlace = (ListView) rootView.findViewById(R.id.lvListPlace);
        tvNotFound = (TextView) rootView.findViewById(R.id.tvNotfound);
        tvNotFound.setVisibility(View.GONE);
        spinnerTypePlace = (Spinner) rootView.findViewById(R.id.spinnerType);

        setListView();
        setSpinnerType(spinnerTypePlace);

    }

    private void setListView() {
        listPlaceAdapter = new PlaceListAdapter();
        listPlaceAdapter.setActivity(getActivity(),this);
        listPlaceAdapter.setMainControl(ControlMainScreen);
        listPlaceAdapter.setMainControlShow(ControlMainScreenShow);
        listPlaceAdapter.setTextView(tvNotFound);
        lvListPlace.setAdapter(listPlaceAdapter);

//        if(listPlaceAdapter.getCount()>0) {
//            tvNotFound.setVisibility(View.GONE);
//        }else{
//            tvNotFound.setVisibility(View.VISIBLE);
//        }
    }


    private void setSpinnerType(Spinner typeSpinner) {


            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, Type_id_item.PlaceTypes);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            typeSpinner.setAdapter(spinnerArrayAdapter);

            typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    listPlaceAdapter.selectTypePlace(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


    }


    @Override
    public void onStart() {
        super.onStart();
        if(getDialog()!=null) {
            getDialog().getWindow().setWindowAnimations(
                    R.style.Dialog_animation_fast);
        }
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


    public void setMainControl(Screen_add_detail_of_days activity) {
        ControlMainScreen = activity;
    }

    public void setMainControlShow(Screen_show_detail_of_days screen_show_detail_of_days) {
        ControlMainScreenShow = screen_show_detail_of_days;
    }
}
