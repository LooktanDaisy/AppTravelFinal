package com.application.youngdeveloper.apptravelfinal.manager;

import android.util.Log;
import android.widget.Toast;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.dao.PlanListDao;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

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


    public void insertPlan(){

        insertPlanToServer();

    }

    public void insertPlanToServer(){

    }

    public void deletePlanByID(int planID){
        /**
         * remove from app
         */
        PlanListDao planToDelete = PlanListManager.getInstance().getPlan(planID);
        PlanListManager.getInstance().getDao().getData().remove(planToDelete);

        /**
         * remove from server
         */
        try {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("planID", String.valueOf(planID)));
                /* Set to Http post*/
                /* End set Value*/
            HttpClient httpclient = new DefaultHttpClient();
                /* Set URL*/
            HttpPost httppost = new HttpPost(HttpManager.UrlPHP + "android_delete_plan.php");
                /* End Set URL*/
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String responseText = httpclient.execute(httppost, responseHandler);


                    if (responseText.contains("error delete")) {

                        Log.d("responseText", "Exception : " + responseText);

                    } else {

                        Log.d("responseText", "Data From Sever : " + responseText);

                    }


        } catch (Exception e) {
            Log.d("log_err", "Error in http connection " + e.toString());

        }
    }

}
