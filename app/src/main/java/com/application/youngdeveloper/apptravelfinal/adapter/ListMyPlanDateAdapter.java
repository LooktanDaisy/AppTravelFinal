package com.application.youngdeveloper.apptravelfinal.adapter;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.MainFunction;
import com.application.youngdeveloper.apptravelfinal.dao.PlanListDao;
import com.application.youngdeveloper.apptravelfinal.manager.PlanListManager;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_add_detail_of_days;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_show_detail_of_days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ListMyPlanDateAdapter extends RecyclerView.Adapter<ListMyPlanDateAdapter.MainViewHolder> {

    private Context mContext;
    private int numberOfDate = 0;
    private Date startDate;
    private Date date_startDate;
    private Calendar c = Calendar.getInstance();
    private FragmentTransaction fragmentTran;
    private PlanListDao plan;

    public ListMyPlanDateAdapter(Context context, int numberDate, int keyPlan) {
        mContext = context;
        plan = PlanListManager.getInstance().getPlan(keyPlan);
        this.numberOfDate = numberDate;
        this.startDate = plan.getDateStart();

    }



    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_list_date_plan, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

        c.setTime(startDate);
        c.add(Calendar.DATE, position);
        final Date date = c.getTime();

        holder.tvDate.setText(MainFunction.getDateString(date));

        holder.row_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Call next Screen and send date to screen
                 */
                fragmentTran.replace(R.id.fragment_container, new Screen_show_detail_of_days(date,plan.getId()), "Screen add detail of days");
                fragmentTran.addToBackStack("Screen_add_detail_of_days");
                fragmentTran.commit();
            }
        });

    }


    @Override
    public int getItemCount() {
        return numberOfDate;
    }

    public void setFragmentTran(FragmentTransaction ft) {
        fragmentTran = ft;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout row_Date;
        private TextView tvDate;
        public MainViewHolder(View itemView) {
            super(itemView);

            row_Date = (RelativeLayout) itemView.findViewById(R.id.row_plan);
            tvDate = (TextView) itemView.findViewById(R.id.TextViewNamePlan);

        }
    }



}
