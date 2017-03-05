package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PlanListDao {
    @SerializedName("plan_id")
    private int id;
    @SerializedName("traveler_id")
    private int travelerID;
    @SerializedName("province")
    private String province;
    @SerializedName("date_start")
    private Date dateStart;
    @SerializedName("date_end")
    private Date dateEnd;
    @SerializedName("budgets")
    private Double budgets;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTravelerID() {
        return travelerID;
    }

    public void setTravelerID(int travelerID) {
        this.travelerID = travelerID;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Double getBudgets() {
        return budgets;
    }

    public void setBudgets(Double budgets) {
        this.budgets = budgets;
    }
}
