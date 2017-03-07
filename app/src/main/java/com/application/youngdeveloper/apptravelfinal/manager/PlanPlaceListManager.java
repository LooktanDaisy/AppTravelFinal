package com.application.youngdeveloper.apptravelfinal.manager;

import android.content.Context;

import com.application.youngdeveloper.apptravelfinal.dao.PlanListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanPlaceListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanPlaceListDao;

import java.util.ArrayList;
import java.util.Date;

public class PlanPlaceListManager {

    private static PlanPlaceListManager instance;

    public static PlanPlaceListManager getInstance() {
        if (instance == null)
            instance = new PlanPlaceListManager();
        return instance;
    }

    private Context mContext;
    private PlanPlaceListCollectionDao dao;

    private PlanPlaceListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public PlanPlaceListCollectionDao getDao() {
        return dao;
    }

    public void setDao(PlanPlaceListCollectionDao dao) {
        this.dao = dao;
    }


    public ArrayList<PlanPlaceListDao> getListPlanPlaceByDateAndPlanID(int idPlan, Date date){
        ArrayList<PlanPlaceListDao> PlanPlaceByDateAndPlanID = new ArrayList<>();
        if(getDao()!=null) {
            ArrayList<PlanPlaceListDao> ListPlanPlace = getDao().getData();
            int i;
            for (i = 0; i < ListPlanPlace.size(); i++) {
                if (ListPlanPlace.get(i).getPlanID() == idPlan) {

                    if (ListPlanPlace.get(i).getDate().compareTo(date) == 0) {
                        PlanPlaceByDateAndPlanID.add(ListPlanPlace.get(i));
                    }

                }
            }
        }
        return PlanPlaceByDateAndPlanID;
    }
}
