package com.application.youngdeveloper.apptravelfinal.dao;

import com.application.youngdeveloper.apptravelfinal.config.MainFunction;
import com.application.youngdeveloper.apptravelfinal.config.Type_id_item;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlaceListCollectionDao {
    @SerializedName("success")  private int success;
    @SerializedName("place")     private List<PlaceListDao> data;

    public int isSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<PlaceListDao> getData() {
        return data;
    }

    public void setData(List<PlaceListDao> data) {
        this.data = data;
    }

    public ArrayList<PlaceListDao> getDataByTypePlace(int typePlace) {
        ArrayList<PlaceListDao> placeByType = new ArrayList<PlaceListDao>();

        for(int i=0;i<getData().size();i++){
            if(getData().get(i).getNameType().equals(Type_id_item.PlaceTypes[typePlace])){
                placeByType.add(getData().get(i));
            }
        }
        return placeByType;
    }

    public ArrayList<PlaceListDao> CalculateHowFarToAccom(ArrayList<PlaceListDao> placeLists,Double AccomLat,Double AccomLng){

        for(int i=0;i<placeLists.size();i++){
            placeLists.get(i).setHowfarToAccom(MainFunction.distance(AccomLat,Double.parseDouble(placeLists.get(i).getLat()),AccomLng,Double.parseDouble(placeLists.get(i).getLng())));
        }

        placeLists = MainFunction.SortByDistancePlace(placeLists);

        return  placeLists;
    }
}
