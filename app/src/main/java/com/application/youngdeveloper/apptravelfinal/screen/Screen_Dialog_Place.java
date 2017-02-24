package com.application.youngdeveloper.apptravelfinal.screen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.adapter.PlaceListAdapter;
import com.application.youngdeveloper.apptravelfinal.config.Provinces;

/**
 * Created by Wachiraya_Kam on 2/23/2017.
 */

public class Screen_Dialog_Place extends DialogFragment {

    private ListView lvListPlace;
    private Screen_add_detail_of_days ControlMainScreen;

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

        setListView();

    }

    private void setListView() {
        PlaceListAdapter listPlaceAdapter = new PlaceListAdapter();
        listPlaceAdapter.setActivity(getActivity(),this);
        listPlaceAdapter.setMainControl(ControlMainScreen);
        lvListPlace.setAdapter(listPlaceAdapter);


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

}
