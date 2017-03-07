package com.application.youngdeveloper.apptravelfinal.manager;

import android.content.Context;

import com.application.youngdeveloper.apptravelfinal.dao.PlaceListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanPlaceListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanPlaceListDao;

import java.util.ArrayList;
import java.util.List;

public class PlanListManager {

    private static PlanListManager instance;

    public static PlanListManager getInstance() {
        if (instance == null)
            instance = new PlanListManager();
        return instance;
    }

    private PlanListCollectionDao dao;

    public PlanListCollectionDao getDao() {
        return dao;
    }

    public void setDao(PlanListCollectionDao dao) {
        this.dao = dao;
    }


    public PlanListDao getPlan(int idPlan){
        PlanListDao PLAN = null;
        List<PlanListDao> ListPLAN =  getDao().getData();
        int i;
        for(i = 0;i<ListPLAN.size();i++){
            if(ListPLAN.get(i).getId() == idPlan){
                PLAN = ListPLAN.get(i);
            }
        }

        return PLAN;
    }
}
