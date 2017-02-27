package com.application.youngdeveloper.apptravelfinal.manager;

/**
 * Created by theerawat on 2/21/2017 AD.
 */

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
