package com.application.youngdeveloper.apptravelfinal.screen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.application.youngdeveloper.apptravelfinal.R;

/**
 * Created by theerawat on 2/21/2017 AD.
 */

public class Screen_choose_plan_date extends Fragment implements View.OnClickListener{


    public Screen_choose_plan_date() {
        super();
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

        ImageView imageView =(ImageView) rootView.findViewById(R.id.imageView6);
        imageView.setOnClickListener(this);
        return rootView;
    }


    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
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
        if(view.getId()==R.id.imageView6){
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, Screen_Add_Plan.newInstance(), "Screen_choose_plan_date");
            ft.commit();
        }
    }
}
