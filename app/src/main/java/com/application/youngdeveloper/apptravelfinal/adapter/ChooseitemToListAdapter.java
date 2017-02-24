package com.application.youngdeveloper.apptravelfinal.adapter;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.MainFunction;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_add_detail_of_days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by theerawat on 2/22/2017 AD.
 */

public class ChooseitemToListAdapter extends RecyclerView.Adapter<ChooseitemToListAdapter.MainViewHolder> {

    private Context mContext;
    private String[] listOfID;
    private int TYPE_ID;

    public ChooseitemToListAdapter(Context context, String[] listOfID,int TYPE_ID) {
        mContext = context;
        this.listOfID = listOfID;
        this.TYPE_ID = TYPE_ID;

    }



    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_add_on_list_plan, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {



    }


    @Override
    public int getItemCount() {
        return listOfID.length;
    }


    public class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvCost;
        private ImageView imagePreview,imageDelete;

        public MainViewHolder(View itemView) {
            super(itemView);

            imagePreview = (ImageView) itemView.findViewById(R.id.image_preview);
            imageDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvCost = (TextView) itemView.findViewById(R.id.textViewCost);

        }
    }



}
