package com.application.youngdeveloper.apptravelfinal.manager;

import android.content.Context;

import com.application.youngdeveloper.apptravelfinal.dao.PlanAccommodationListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanRestaurantListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanRestuarantListCollectionDao;

import java.util.ArrayList;
import java.util.Date;

public class PlanRestaurantListManager {

    private static PlanRestaurantListManager instance;

    public static PlanRestaurantListManager getInstance() {
        if (instance == null)
            instance = new PlanRestaurantListManager();
        return instance;
    }

    private Context mContext;
    private PlanRestuarantListCollectionDao dao;

    private PlanRestaurantListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public PlanRestuarantListCollectionDao getDao() {
        return dao;
    }

    public void setDao(PlanRestuarantListCollectionDao dao) {
        this.dao = dao;
    }

    public ArrayList<PlanRestaurantListDao> getListPlanAccomByDateAndPlanID(int idPlan, Date date){
        ArrayList<PlanRestaurantListDao> PlanRestauByDateAndPlanID = new ArrayList<>();
        if(getDao()!=null) {
            ArrayList<PlanRestaurantListDao> ListPlanRestau = getDao().getData();
            int i;
            for (i = 0; i < ListPlanRestau.size(); i++) {
                if (ListPlanRestau.get(i).getPlanID() == idPlan) {

                    if (ListPlanRestau.get(i).getDate().compareTo(date) == 0) {
                        PlanRestauByDateAndPlanID.add(ListPlanRestau.get(i));
                    }

                }
            }
        }
        return PlanRestauByDateAndPlanID;
    }
}
