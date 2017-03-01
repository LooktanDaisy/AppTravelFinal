package com.application.youngdeveloper.apptravelfinal.screen;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.adapter.ChooseitemToListAdapter;
import com.application.youngdeveloper.apptravelfinal.config.MainFunction;
import com.application.youngdeveloper.apptravelfinal.config.Provinces;
import com.application.youngdeveloper.apptravelfinal.config.Type_id_item;
import com.application.youngdeveloper.apptravelfinal.dialog.Calendar_dialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by theerawat on 2/21/2017 AD.
 */

public class Screen_add_detail_of_days extends Fragment implements View.OnClickListener{

    private Date thisDate;
    private TextView tvDate,tvBack;
    private RecyclerView listAccom,listPlace,listRestaurant;
    private ChooseitemToListAdapter adapterAccom,adapterPlace, adapterRestaurant;
    private ImageView imgBack,imgAddAccom, imgAddPlace, imgAddRestaurant;
    private Double AccomLat = 0.0,AccomLng = 0.0;


    public Screen_add_detail_of_days() {
        super();
    }

    public Screen_add_detail_of_days(Date date){
        thisDate = date;
    }

    public static Screen_add_detail_of_days newInstance() {
        Screen_add_detail_of_days fragment = new Screen_add_detail_of_days();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.screen_manage_plan_by_date_detail, container, false);



        initialView(rootView);

        return rootView;
    }

    private void initialView(View rootView) {


        tvDate = (TextView) rootView.findViewById(R.id.tvDate);
        tvDate.setText(MainFunction.getDateString(thisDate));

        tvBack = (TextView) rootView.findViewById(R.id.textViewBack);
        tvBack.setOnClickListener(this);

        imgBack = (ImageView) rootView.findViewById(R.id.imageViewBack);
        imgBack.setOnClickListener(this);

        imgAddAccom = (ImageView) rootView.findViewById(R.id.addAccom);
        imgAddAccom.setOnClickListener(this);

        imgAddPlace = (ImageView) rootView.findViewById(R.id.add_travel_place);
        imgAddPlace.setOnClickListener(this);

        imgAddRestaurant = (ImageView) rootView.findViewById(R.id.add_restaurant);
        imgAddRestaurant.setOnClickListener(this);

        listAccom = (RecyclerView) rootView.findViewById(R.id.list_accom);
        listPlace = (RecyclerView) rootView.findViewById(R.id.list_place);
        listRestaurant = (RecyclerView) rootView.findViewById(R.id.list_restaurant);

        setAccomRecycler();
        setPlaceRecycler();
        setRestaurantRecycler();


    }

    private void setPlaceRecycler() {
        adapterPlace = new ChooseitemToListAdapter(getContext());
        listPlace.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayout.HORIZONTAL, false));
        listPlace.setHasFixedSize(true);
        listPlace.setAdapter(adapterPlace);
    }


    private void setAccomRecycler() {
       adapterAccom = new ChooseitemToListAdapter(getContext());
        listAccom.setLayoutManager(new GridLayoutManager(getActivity(),1, GridLayout.HORIZONTAL, false));
        listAccom.setHasFixedSize(true);
        listAccom.setAdapter(adapterAccom);
    }

    private void setRestaurantRecycler(){
        adapterRestaurant = new ChooseitemToListAdapter(getContext());
        listRestaurant.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayout.HORIZONTAL, false));
        listRestaurant.setHasFixedSize(true);
        listRestaurant.setAdapter(adapterRestaurant);
    }

    public void addItemToAccom(String idItem){
        adapterAccom.addItem(idItem, Type_id_item.TYPE_ACCOMMODATION);

    }

    public void addItemToPlace(String idItem){
        adapterPlace.addItem(idItem, Type_id_item.TYPE_PLACE);
    }

    public void addItemToRestaurant(String idItem){
        adapterRestaurant.addItem(idItem, Type_id_item.TYPE_RESTAURANT);
    }

    public Double getAccomLat() {
        return AccomLat;
    }

    public Double getAccomLng() {
        return AccomLng;
    }

    public void setPointAccom(Double Lat, Double Lng) {
        this.AccomLat = Lat;
        this.AccomLng = Lng;
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

        if(view == imgBack || view == tvBack){

            showAlertDialog();

        }else if(view == imgAddAccom){
            showDialogListAccommodation();
        }

        else if (view == imgAddPlace){
            if(getAccomLng()!=0.0) {
                showDialogListPlace();
            }else {
                Toast.makeText(getContext(), R.string.E06, Toast.LENGTH_SHORT).show();
            }
        }
        else if(view == imgAddRestaurant){
            if(getAccomLng()!=0.0) {
            showDialogListRestaurant();
            }else {
                Toast.makeText(getContext(), R.string.E06, Toast.LENGTH_SHORT).show();
            }
        }


    }


    private void showAlertDialog(){

        AlertDialog alert = new AlertDialog.Builder(getContext())
                .setTitle(R.string.are_you_sure_back_to_choose_day)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        getFragmentManager().popBackStack();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

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


    private void showDialogListAccommodation() {
        Screen_Dialog_Accomodation newFragment = Screen_Dialog_Accomodation.newInstance();
        newFragment.setMainControl(this);
        newFragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.DialogFullScreen);
        newFragment.show(getFragmentManager(), "Screen_Dialog_Accommodation");
    }

    private void showDialogListPlace(){
        Screen_Dialog_Place newFragment = Screen_Dialog_Place.newInstance();
        newFragment.setMainControl(this);
        newFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFullScreen );
        newFragment.show(getFragmentManager(), "Screen_Dialog_Place");
    }

    //TODO: Continue Restaurant
    private void showDialogListRestaurant() {
        Screen_Dialog_Restaurant newFragment = Screen_Dialog_Restaurant.newInstance();
        newFragment.setMainControl(this);
        newFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFullScreen);
        newFragment.show(getFragmentManager(), "Screen_Dialog_Restaurant");
    }

}
