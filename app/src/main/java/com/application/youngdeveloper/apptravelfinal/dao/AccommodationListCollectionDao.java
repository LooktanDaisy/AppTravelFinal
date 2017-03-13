package com.application.youngdeveloper.apptravelfinal.dao;

import com.application.youngdeveloper.apptravelfinal.config.MainFunction;
import com.application.youngdeveloper.apptravelfinal.config.Type_id_item;
import com.application.youngdeveloper.apptravelfinal.manager.CostLimit;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AccommodationListCollectionDao {
    @SerializedName("success")  private int success;
    @SerializedName("accommodation")     private List<AccommodationListDao> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<AccommodationListDao> getData() {
        return data;
    }

    public void setData(List<AccommodationListDao> data) {
        this.data = data;
    }

    public ArrayList<AccommodationListDao> getAccomByCostLimit() {
        ArrayList<AccommodationListDao> AccomByCost = new ArrayList<AccommodationListDao>();

        if(CostLimit.AccomCost==0.0){
            CostLimit.AccomCost = 100000.00;
        }

        for(int i=0;i<getData().size();i++){
            if(getData().get(i).getPrice() <= CostLimit.AccomCost){
                AccomByCost.add(getData().get(i));
            }
        }

        return MainFunction.SortByCostAccom(AccomByCost);
    }
}
