package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by theerawat on 2/27/2017 AD.
 */

public class PlanListCollectionDao {

    @SerializedName("success")  private int success;
    @SerializedName("plan")     private List<PlanListDao> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<PlanListDao> getData() {
        return data;
    }

    public void setData(List<PlanListDao> data) {
        this.data = data;
    }
}
