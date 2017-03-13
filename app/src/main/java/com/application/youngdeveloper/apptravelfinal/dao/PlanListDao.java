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
    private int budgets;
    @SerializedName("plan_name")
    private String name;

    public PlanListDao(){

    }



    public PlanListDao(int id, int travelerID, String province, Date dateStart, Date dateEnd, int budgets, String name) {
        this.id = id;
        this.travelerID = travelerID;
        this.province = province;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.budgets = budgets;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public int getBudgets() {
        return budgets;
    }

    public void setBudgets(int budgets) {
        this.budgets = budgets;
    }
}
