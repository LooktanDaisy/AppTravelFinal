package com.application.youngdeveloper.apptravelfinal.screen;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.MainFunction;
import com.application.youngdeveloper.apptravelfinal.config.Provinces;
import com.application.youngdeveloper.apptravelfinal.datamanager.DataManager;
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
    private ImageView imgBack,imgAddAccom;

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

        if(view == imgBack || view == tvBack){

            showAlertDialog();

        }else if(view == imgAddAccom){
            showDialogListPlace();
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



    private void showDialogListPlace(){
        DialogFragment newFragment = Screen_Dialog_Place.newInstance();
        newFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFullScreen );
        newFragment.show(getFragmentManager(), "Screen_Dialog_Place");
    }

}
