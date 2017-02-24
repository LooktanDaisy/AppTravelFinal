package com.application.youngdeveloper.apptravelfinal.screen;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.adapter.RestaurantListAdapter;

/**
 * Created by Wachiraya_Kam on 2/24/2017.
 */

public class Screen_Dialog_Restaurant extends DialogFragment {

    private ListView lvListRestaurant;

    public Screen_Dialog_Restaurant() {
        super();
    }

    public static Screen_Dialog_Restaurant newInstance() {
        Screen_Dialog_Restaurant fragment = new Screen_Dialog_Restaurant();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_choose_to_list_restaurant, container, false);



        initialView(rootView);
        return rootView;
    }

    private void initialView(View rootView) {
        lvListRestaurant = (ListView) rootView.findViewById(R.id.lvListRestaurant);

        setListView();

    }

    private void setListView() {
        RestaurantListAdapter listRestaurantAdapter = new RestaurantListAdapter();
        lvListRestaurant.setAdapter(listRestaurantAdapter);

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
}
