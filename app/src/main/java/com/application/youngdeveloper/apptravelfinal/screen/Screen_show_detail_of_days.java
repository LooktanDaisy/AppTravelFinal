package com.application.youngdeveloper.apptravelfinal.screen;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.adapter.ShowitemToListAdapter;
import com.application.youngdeveloper.apptravelfinal.config.MainFunction;
import com.application.youngdeveloper.apptravelfinal.config.Type_id_item;
import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListDao;
import com.application.youngdeveloper.apptravelfinal.manager.AccommodationListManager;
import com.application.youngdeveloper.apptravelfinal.manager.DataManager;
import com.application.youngdeveloper.apptravelfinal.manager.PlanPlaceListManager;

import java.util.Date;

public class Screen_show_detail_of_days extends Fragment implements View.OnClickListener{

    private Date thisDate;
    private TextView tvDate,tvBack,tvEdit;
    private RecyclerView listAccom,listPlace,listRestaurant;
    private ShowitemToListAdapter adapterAccom,adapterPlace, adapterRestaurant;
    private ImageView imgBack,imgAddAccom, imgAddPlace, imgAddRestaurant,ivEdit;
    private Double AccomLat = 0.0,AccomLng = 0.0;
    private boolean StatusEdit = false;
    private int planID=0;


    public Screen_show_detail_of_days() {
        super();
    }

    public Screen_show_detail_of_days(Date date, int keyPlan){
        thisDate = date;
        planID = keyPlan;
    }

    public static Screen_show_detail_of_days newInstance() {
        Screen_show_detail_of_days fragment = new Screen_show_detail_of_days();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.screen_show_plan_by_date_detail, container, false);



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

        tvEdit = (TextView) rootView.findViewById(R.id.textViewDone);
        tvEdit.setOnClickListener(this);
        ivEdit = (ImageView) rootView.findViewById(R.id.imageViewDone);
        ivEdit.setOnClickListener(this);

        imgAddAccom.setVisibility(View.INVISIBLE);
        imgAddPlace.setVisibility(View.INVISIBLE);
        imgAddRestaurant.setVisibility(View.INVISIBLE);



        setAccomRecycler();
        setPlaceRecycler();
        setRestaurantRecycler();

        setPointAccom();


    }

    private void setPlaceRecycler() {
        adapterPlace = new ShowitemToListAdapter(getContext(),Type_id_item.TYPE_PLACE,planID,thisDate);
        listPlace.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayout.HORIZONTAL, false));
        listPlace.setHasFixedSize(true);
        listPlace.setAdapter(adapterPlace);
    }


    private void setAccomRecycler() {
       adapterAccom = new ShowitemToListAdapter(getContext(),Type_id_item.TYPE_ACCOMMODATION,planID,thisDate);
        listAccom.setLayoutManager(new GridLayoutManager(getActivity(),1, GridLayout.HORIZONTAL, false));
        listAccom.setHasFixedSize(true);
        listAccom.setAdapter(adapterAccom);
    }

    private void setRestaurantRecycler(){
        adapterRestaurant = new ShowitemToListAdapter(getContext(),Type_id_item.TYPE_RESTAURANT,planID,thisDate);
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

    public void setPointAccom() {
        if(adapterAccom.getAccomDetail()!=0) {
            AccommodationListDao accom = AccommodationListManager.getInstance().getAccommodation(adapterAccom.getAccomDetail());
            this.AccomLat = Double.parseDouble(accom.getLat());
            this.AccomLng = Double.parseDouble(accom.getLng());
        }
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

            getFragmentManager().popBackStack();

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
        }else if(view == tvEdit || view == ivEdit){
            if(StatusEdit == false) {
                StatusEdit = true;
                tvEdit.setText(R.string.done);
                ivEdit.setImageDrawable(getResources().getDrawable(R.drawable.ic_done));
                imgAddAccom.setVisibility(View.VISIBLE);
                imgAddPlace.setVisibility(View.VISIBLE);
                imgAddRestaurant.setVisibility(View.VISIBLE);
                adapterPlace.setEnableEdit(StatusEdit);
                adapterRestaurant.setEnableEdit(StatusEdit);
            }else{
                showFinishedDialog();
            }
        }


    }


    private void showFinishedDialog(){

        AlertDialog alert = new AlertDialog.Builder(getContext())
                .setTitle(R.string.are_you_sure_edit_plan)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        DataManager.getInstance().addPlanAccom(planID,thisDate,adapterAccom.getListAccom());
                        DataManager.getInstance().addPlanPlace(planID,thisDate,adapterPlace.getListPlace());
                        DataManager.getInstance().addPlanRestaurant(planID,thisDate,adapterRestaurant.getListRestaurant());


                        tvEdit.setText(R.string.edit);
                        ivEdit.setImageDrawable(getResources().getDrawable(R.drawable.ic_edit));
                        imgAddAccom.setVisibility(View.INVISIBLE);
                        imgAddPlace.setVisibility(View.INVISIBLE);
                        imgAddRestaurant.setVisibility(View.INVISIBLE);
                        StatusEdit = false;
                        adapterPlace.setEnableEdit(StatusEdit);
                        adapterRestaurant.setEnableEdit(StatusEdit);
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
        newFragment.setMainControlShow(this);
        newFragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.DialogFullScreen);
        newFragment.show(getFragmentManager(), "Screen_Dialog_Accommodation");
    }

    private void showDialogListPlace(){
        Screen_Dialog_Place newFragment = Screen_Dialog_Place.newInstance();
        newFragment.setMainControlShow(this);
        newFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFullScreen );
        newFragment.show(getFragmentManager(), "Screen_Dialog_Place");
    }

    private void showDialogListRestaurant() {
        Screen_Dialog_Restaurant newFragment = Screen_Dialog_Restaurant.newInstance();
        newFragment.setMainControlShow(this);
        newFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFullScreen);
        newFragment.show(getFragmentManager(), "Screen_Dialog_Restaurant");
    }

}
