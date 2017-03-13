package com.application.youngdeveloper.apptravelfinal.screen;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.adapter.RestaurantListAdapter;

public class Screen_Dialog_Restaurant extends DialogFragment {

    private ListView lvListRestaurant;
    private Screen_add_detail_of_days ControlMainScreen;
    private TextView tvNotFound;
    private Screen_show_detail_of_days ControlMainScreenShow = null;

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
        tvNotFound = (TextView) rootView.findViewById(R.id.tvNotfound);
        tvNotFound.setVisibility(View.GONE);

        setListView();

    }

    private void setListView() {
        RestaurantListAdapter listRestaurantAdapter = new RestaurantListAdapter();
        listRestaurantAdapter.setActivity(getActivity(),this);
        listRestaurantAdapter.setMainControl(ControlMainScreen);
        listRestaurantAdapter.setMainControlShow(ControlMainScreenShow);
        listRestaurantAdapter.setTextView(tvNotFound);
        lvListRestaurant.setAdapter(listRestaurantAdapter);

//        if(listRestaurantAdapter.getCount()>0) {
//
//        }else{
//            tvNotFound.setVisibility(View.VISIBLE);
//        }

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
