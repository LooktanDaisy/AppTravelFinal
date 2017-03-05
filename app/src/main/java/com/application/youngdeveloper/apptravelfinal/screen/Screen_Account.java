package com.application.youngdeveloper.apptravelfinal.screen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.application.youngdeveloper.apptravelfinal.MainActivity;
import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.manager.User;

public class Screen_Account extends Fragment implements View.OnClickListener{



    public Screen_Account() {
        super();
    }

    public static Screen_Account newInstance() {
        Screen_Account fragment = new Screen_Account();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    private TextView tvName,tvEmail;
    private Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.screen_account, container, false);
        initailView(rootView);

        return rootView;
    }


    private void initailView(View rootView) {

        tvEmail = (TextView) rootView.findViewById(R.id.tv_email);
        tvName = (TextView) rootView.findViewById(R.id.tv_name);

        btnLogout = (Button) rootView.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);

        tvName.setText(User.NAME);
        tvEmail.setText(User.EMAIL);

    }



    @Override
    public void onClick(View view) {
        if(view == btnLogout){


                AlertDialog alert = new AlertDialog.Builder(getContext())
                        .setTitle(R.string.exit)
                        .setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                /**
                                 * confirm button
                                 */
                                Intent LogoutAndShowLogin = new Intent(getActivity(), MainActivity.class);
                                User.ID = null;
                                User.NAME = null;
                                User.EMAIL = null;
                                startActivity(LogoutAndShowLogin);
                                getActivity().finish();
                                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            }
                        })

                        .setIcon(R.drawable.ic_profile_active)
                        .create();

                alert.show();

                Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                //Set positive button background
                pbutton.setTextColor(getResources().getColor(R.color.colorPrimary));
                nbutton.setTextColor(getResources().getColor(R.color.text_blue_trans));

        }
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
