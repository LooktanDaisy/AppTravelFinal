package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlanPlaceListCollectionDao {
    @SerializedName("success")  private int success;
    @SerializedName("plan_place")     private ArrayList<PlanPlaceListDao> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public ArrayList<PlanPlaceListDao> getData() {
        return data;
    }

    public void setData(ArrayList<PlanPlaceListDao> data) {
        this.data = data;
    }
}
