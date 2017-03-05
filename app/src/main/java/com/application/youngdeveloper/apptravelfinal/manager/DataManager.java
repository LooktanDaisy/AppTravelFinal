package com.application.youngdeveloper.apptravelfinal.manager;

public class DataManager {

    private static DataManager instance;


    public static DataManager getInstance(){
        if (instance==null){
            instance = new DataManager();
            return instance;
        }else{
            return instance;
        }
    }


    public static void insertPlan(){

        insertPlanToServer();

    }

    public static void insertPlanToServer(){

    }

}
