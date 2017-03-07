package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlanListCollectionDao {

    @SerializedName("success")  private int success;
    @SerializedName("plan")     private List<PlanListDao> data = null;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<PlanListDao> getData() {
        if(data == null){
            return null;
        }else {
            return data;
        }
    }

    public void setData(List<PlanListDao> data) {
        this.data = data;
    }
}
