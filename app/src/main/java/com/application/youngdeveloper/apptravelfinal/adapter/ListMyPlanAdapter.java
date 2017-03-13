package com.application.youngdeveloper.apptravelfinal.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.MainFunction;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanListDao;
import com.application.youngdeveloper.apptravelfinal.manager.DataManager;
import com.application.youngdeveloper.apptravelfinal.manager.PlanListManager;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_add_detail_of_days;
import com.application.youngdeveloper.apptravelfinal.screen.Screen_choose_my_plan_date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListMyPlanAdapter extends RecyclerView.Adapter<ListMyPlanAdapter.MainViewHolder> {

    private Context mContext;
    private Date date_startDate;
    private Calendar c = Calendar.getInstance();
    private List<PlanListDao> planList;
    private FragmentTransaction fragmentTransaction;


    public ListMyPlanAdapter(Context context) {
        mContext = context;
        if(PlanListManager.getInstance().getDao()!=null) {
            planList = PlanListManager.getInstance().getDao().getData();
        }

    }


    public void setFragmentTran(FragmentTransaction ft) {
        fragmentTransaction = ft;
    }



    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_list_my_plan, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {


        if(planList!=null){

            final PlanListDao plan = planList.get(position);

            holder.tvNamePlan.setText(plan.getName());
            holder.tvBudget.setText(String.valueOf(MainFunction.commaDouble(plan.getBudgets())));
            holder.tvProvince.setText(plan.getProvince());

//            int numberOfDate = CalculateNumberOfDate(plan.getDateStart(),plan.getDateEnd());
//            c.setTime(date_startDate);
//            c.add(Calendar.DATE, numberOfDate);
//            final Date dateBack = c.getTime();

            holder.tvDateGo.setText(MainFunction.getDateString(plan.getDateStart()));
            holder.tvDateBack.setText(MainFunction.getDateString(plan.getDateEnd()));


            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog alert = new AlertDialog.Builder(mContext)
                            .setTitle(R.string.are_you_sure_delete_plan)
                            .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    DataManager.getInstance().deletePlanByID(plan.getId());
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })

                            .setIcon(R.drawable.ic_bin_active)
                            .create();

                    alert.show();

                    Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                    Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                    //Set positive button background
                    pbutton.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                    nbutton.setTextColor(mContext.getResources().getColor(R.color.text_blue_trans));

                }
            });


            holder.rowPlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /**
                     * Call next Screen and send date to screen
                     */
                    int numberOfDate = CalculateNumberOfDate(plan.getDateStart(),plan.getDateEnd());
                    fragmentTransaction.replace(R.id.fragment_container, new Screen_choose_my_plan_date(numberOfDate,plan.getId(),plan.getBudgets()), "Screen Screen_choose_my_plan_date detail of days");
                    fragmentTransaction.addToBackStack("Screen_choose_my_plan_date");
                    fragmentTransaction.commit();
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        if(planList!=null){
            return planList.size();
        }else{
            return 0;
        }
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rowPlan;
        private TextView tvNamePlan,tvProvince,tvBudget,tvDateGo,tvDateBack;
        private ImageView ivDelete;
        public MainViewHolder(View itemView) {
            super(itemView);

            rowPlan = (RelativeLayout) itemView.findViewById(R.id.row_plan);
            tvNamePlan = (TextView) itemView.findViewById(R.id.TextViewNamePlan);
            tvProvince = (TextView) itemView.findViewById(R.id.textViewProvince);
            tvBudget = (TextView) itemView.findViewById(R.id.textViewBudget);
            tvDateGo = (TextView) itemView.findViewById(R.id.textViewDateGo);
            tvDateBack = (TextView) itemView.findViewById(R.id.textViewDateBack);
            ivDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);

        }
    }


    private int CalculateNumberOfDate(Date DateGo, Date DateBack){
        /**
         * find number of date
         */
        int numberOfDate = 0;

        Date date1 = MainFunction.removeTime(DateGo);
        Date date2 = MainFunction.removeTime(DateBack);

        numberOfDate = (MainFunction.daysBetween(date1.getTime(), date2.getTime()) ) + 1;
        Log.d("Days : ",String.valueOf(numberOfDate));

        return numberOfDate;
    }
}
