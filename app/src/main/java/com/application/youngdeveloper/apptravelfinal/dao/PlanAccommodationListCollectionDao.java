package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlanAccommodationListCollectionDao {
    @SerializedName("success")  private int success;
    @SerializedName("plan_accommodation")     private ArrayList<PlanAccommodationListDao> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public ArrayList<PlanAccommodationListDao> getData() {
        return data;
    }

    public void setData(ArrayList<PlanAccommodationListDao> data) {
        this.data = data;
    }
}
