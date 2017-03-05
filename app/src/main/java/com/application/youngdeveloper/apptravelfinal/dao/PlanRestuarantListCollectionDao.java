package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlanRestuarantListCollectionDao {
    @SerializedName("success")  private int success;
    @SerializedName("plan_restaurant")     private List<PlanRestaurantListDao> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<PlanRestaurantListDao> getData() {
        return data;
    }

    public void setData(List<PlanRestaurantListDao> data) {
        this.data = data;
    }
}
