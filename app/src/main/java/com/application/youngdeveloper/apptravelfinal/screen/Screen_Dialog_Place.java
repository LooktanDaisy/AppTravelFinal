package com.application.youngdeveloper.apptravelfinal.screen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.adapter.PlaceListAdapter;
import com.application.youngdeveloper.apptravelfinal.config.Provinces;

/**
 * Created by Wachiraya_Kam on 2/23/2017.
 */

public class Screen_Dialog_Place extends Fragment {

    private ListView lvListPlace;

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
        lvListPlace.setAdapter(listPlaceAdapter);

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
}
