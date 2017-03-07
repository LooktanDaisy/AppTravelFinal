package com.application.youngdeveloper.apptravelfinal.dao;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PlanAccommodationListDao {
    @SerializedName("plan_id")
    private int planID;
    @SerializedName("accommodation_id")
    private int accomID;
    @SerializedName("date")
    private Date date;
    @SerializedName("traveler_id")
    private int traveler_id;

    public int getTraveler_id() {
        return traveler_id;
    }

    public void setTraveler_id(int traveler_id) {
        this.traveler_id = traveler_id;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public int getAccomID() {
        return accomID;
    }

    public void setAccomID(int accomID) {
        this.accomID = accomID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
