package com.application.youngdeveloper.apptravelfinal.manager;

import android.content.Context;

import com.application.youngdeveloper.apptravelfinal.dao.PlanAccommodationListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanAccommodationListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanPlaceListCollectionDao;

import java.util.ArrayList;
import java.util.Date;

public class PlanAccommodationListManager {

    private static PlanAccommodationListManager instance;

    public static PlanAccommodationListManager getInstance() {
        if (instance == null)
            instance = new PlanAccommodationListManager();
        return instance;
    }

    private Context mContext;
    private PlanAccommodationListCollectionDao dao;

    private PlanAccommodationListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public PlanAccommodationListCollectionDao getDao() {
        return dao;
    }

    public void setDao(PlanAccommodationListCollectionDao dao) {
        this.dao = dao;
    }



    public ArrayList<PlanAccommodationListDao> getListPlanAccomByDateAndPlanID(int idPlan, Date date){
        ArrayList<PlanAccommodationListDao> PlanAccomByDateAndPlanID = new ArrayList<>();
        if(getDao()!=null) {
            ArrayList<PlanAccommodationListDao> ListPlanAccom = getDao().getData();
            int i;
            for (i = 0; i < ListPlanAccom.size(); i++) {
                if (ListPlanAccom.get(i).getPlanID() == idPlan) {

                    if (ListPlanAccom.get(i).getDate().compareTo(date) == 0) {
                        PlanAccomByDateAndPlanID.add(ListPlanAccom.get(i));
                    }

                }
            }
        }
        return PlanAccomByDateAndPlanID;
    }
}
