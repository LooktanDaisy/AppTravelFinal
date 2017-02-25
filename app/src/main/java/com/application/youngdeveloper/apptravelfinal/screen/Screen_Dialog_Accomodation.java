package com.application.youngdeveloper.apptravelfinal.screen;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.adapter.AccommodationListAdapter;
import com.application.youngdeveloper.apptravelfinal.config.Provinces;

/**
 * Created by Wachiraya_Kam on 2/23/2017.
 */

public class Screen_Dialog_Accomodation extends DialogFragment {

    private ListView lvListAccomodation;
    private Screen_add_detail_of_days ControlMainScreen;

    public Screen_Dialog_Accomodation() {
        super();
    }

    public static Screen_Dialog_Accomodation newInstance() {
        Screen_Dialog_Accomodation fragment = new Screen_Dialog_Accomodation();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_choose_to_list_accom, container, false);



        initialView(rootView);
        return rootView;
    }

    private void initialView(View rootView) {
        lvListAccomodation = (ListView) rootView.findViewById(R.id.lvListAccommodation);

        setListView();

    }

    private void setListView() {
        AccommodationListAdapter listAccommodationAdapter = new AccommodationListAdapter();
        listAccommodationAdapter.setActivity(getActivity(),this);
        listAccommodationAdapter.setMainControl(ControlMainScreen);
        lvListAccomodation.setAdapter(listAccommodationAdapter);

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
