package com.application.youngdeveloper.apptravelfinal.manager;

import android.content.Context;

import com.application.youngdeveloper.apptravelfinal.dao.PlaceListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanAccommodationListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanPlaceListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanRestaurantListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanRestuarantListCollectionDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public ArrayList<PlanRestaurantListDao> getListPlanAccomByPlanID(int idPlan){
        ArrayList<PlanRestaurantListDao> PlanRestauByDateAndPlanID = new ArrayList<>();
        if(getDao()!=null) {
            ArrayList<PlanRestaurantListDao> ListPlanRestau = getDao().getData();
            int i;
            for (i = 0; i < ListPlanRestau.size(); i++) {
                if (ListPlanRestau.get(i).getPlanID() == idPlan) {

                    PlanRestauByDateAndPlanID.add(ListPlanRestau.get(i));
                }
            }
        }
        return PlanRestauByDateAndPlanID;
    }

    public void removeByPlanID(int planID){
        if(getDao()!=null) {
            ArrayList<PlanRestaurantListDao> ListPlanRest = getDao().getData();
            int i;
            for (i = 0; i < ListPlanRest.size(); i++) {
                if (ListPlanRest.get(i).getPlanID() == planID) {
                    getDao().getData().remove(ListPlanRest.get(i));
                }
            }
        }
    }

    public PlanRestaurantListDao getPlanRestaurant(int id_Restaurant) {
        PlanRestaurantListDao RESTAURANT = null;
        List<PlanRestaurantListDao> ListRESTAURANT = getDao().getData();
        int i;
        for (i = 0; i < ListRESTAURANT.size(); i++) {
            if (ListRESTAURANT.get(i).getRestauID() == id_Restaurant) {
                RESTAURANT = ListRESTAURANT.get(i);
            }
        }
        return RESTAURANT;
    }

    public void addPlanRestaurant(int planID, Date thisDate, int restuaId, String userID) {
        if(getDao()!=null){
            PlanRestaurantListDao planRestau = new PlanRestaurantListDao(planID,restuaId,thisDate,Integer.parseInt(userID));
            getDao().getData().add(planRestau);
        }
    }


}
