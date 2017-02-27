package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by theerawat on 2/27/2017 AD.
 */

public class PlanPlaceListCollectionDao {
    @SerializedName("success")  private int success;
    @SerializedName("plan_place")     private List<PlanPlaceListDao> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<PlanPlaceListDao> getData() {
        return data;
    }

    public void setData(List<PlanPlaceListDao> data) {
        this.data = data;
    }
}
