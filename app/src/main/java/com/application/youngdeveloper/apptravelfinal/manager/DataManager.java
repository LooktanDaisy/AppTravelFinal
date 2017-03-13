package com.application.youngdeveloper.apptravelfinal.manager;

import android.util.Log;
import android.widget.Toast;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.MainFunction;
import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanListDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListDao;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    /**
     * Traveler_id,province,date_start,date_end,budget,plan_name
     */
    public int insertPlan(String userID, String province, String date_start, String date_end, String budget, String plan_name){

        /**
         * add plan to server
         */
        int planID=0;
        try {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("userID", userID));
            nameValuePairs.add(new BasicNameValuePair("province", province));
            nameValuePairs.add(new BasicNameValuePair("dateStart", date_start));
            nameValuePairs.add(new BasicNameValuePair("dateEnd", date_end));
            nameValuePairs.add(new BasicNameValuePair("budgets", budget));
            nameValuePairs.add(new BasicNameValuePair("planName", plan_name));

                /* Set to Http post*/
                /* End set Value*/
            HttpClient httpclient = new DefaultHttpClient();
                /* Set URL*/
            HttpPost httppost = new HttpPost(HttpManager.UrlPHP + "android_insert_plan.php");
                /* End Set URL*/
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String responseText = httpclient.execute(httppost, responseHandler);


            if (responseText.contains("insert plan error")) {

                Log.d("responseText", "Exception : " + responseText);

            } else {

                /**
                 * insertion is success
                 */

                planID = Integer.parseInt(responseText.trim());
                Log.d("responseText", "Data From Sever : " + responseText);

            }


        } catch (Exception e) {
            Log.d("log_err", "Error in http connection " + e.toString());

        }


        /**
         * add plan to app
         */
        Date date1 = null,date2 = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date1 = formatter.parse(date_start);
            date2 = formatter.parse(date_end);

            date1 = MainFunction.removeTime(date1);
            date2 = MainFunction.removeTime(date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        PlanListDao newPlan = new PlanListDao(planID,Integer.parseInt(User.ID),province,date1,date2,Integer.parseInt(budget),plan_name);
        PlanListManager.getInstance().getDao().getData().add(newPlan);

        return planID;

    }



    public void deletePlanByID(int planID){
        /**
         * remove from app
         */
        PlanListDao planToDelete = PlanListManager.getInstance().getPlan(planID);
        PlanListManager.getInstance().getDao().getData().remove(planToDelete);

        PlanAccommodationListManager.getInstance().removeByPlanID(planID);
        PlanPlaceListManager.getInstance().removeByPlanID(planID);
        PlanRestaurantListManager.getInstance().removeByPlanID(planID);

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

    public void addPlanAccom(int planID, Date thisDate, ArrayList<AccommodationListDao> listAccom) {

        /**
         * remove before add new accom by this date from app
         */

        if(listAccom!=null) {

            PlanAccommodationListManager.getInstance().removeByPlanID(planID);


            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            int i = 0;
            for (i = 0; i < listAccom.size(); i++) {
                AccommodationListDao accom = listAccom.get(i);
                String date_formatted = format1.format(thisDate);
//            Log.d("DATA:",planID+" "+date_formatted+" "+User.ID+" "+accom.getId());


                PlanAccommodationListManager.getInstance().addPlanAccom(planID, thisDate, accom.getId(), User.ID);

                /**
                 * remove from server
                 */
                try {
                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("planID", String.valueOf(planID)));
                    nameValuePairs.add(new BasicNameValuePair("accomID", String.valueOf(accom.getId())));
                    nameValuePairs.add(new BasicNameValuePair("date", date_formatted));
                    nameValuePairs.add(new BasicNameValuePair("userID", String.valueOf(User.ID)));
                /* Set to Http post*/
                /* End set Value*/
                    HttpClient httpclient = new DefaultHttpClient();
                /* Set URL*/
                    HttpPost httppost = new HttpPost(HttpManager.UrlPHP + "android_insert_plan_accom.php");
                /* End Set URL*/
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    final String responseText = httpclient.execute(httppost, responseHandler);


                    if (responseText.contains("insert plan accom error")) {

                        Log.d("responseText", "Exception : " + responseText);

                    } else {

                        Log.d("responseText", "Data From Sever : " + responseText);

                    }


                } catch (Exception e) {
                    Log.d("log_err", "Error in http connection " + e.toString());

                }
            }
        }
    }

    public void addPlanPlace(int planID, Date thisDate, ArrayList<PlaceListDao> listPlace) {

        /**
         * remove before add new accom by this date from app
         */

        if(listPlace!=null) {

            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            String date_formatted = format1.format(thisDate);

            PlanPlaceListManager.getInstance().removeByPlanID(planID);


            /**
             * remove from server
             */
            try {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("planID", String.valueOf(planID)));
                nameValuePairs.add(new BasicNameValuePair("placeID", String.valueOf(0)));
                nameValuePairs.add(new BasicNameValuePair("date", date_formatted));
                nameValuePairs.add(new BasicNameValuePair("userID", String.valueOf(User.ID)));
                /* Set to Http post*/
                /* End set Value*/
                HttpClient httpclient = new DefaultHttpClient();
                /* Set URL*/
                HttpPost httppost = new HttpPost(HttpManager.UrlPHP + "android_delete_plan_place.php");
                /* End Set URL*/
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String responseText = httpclient.execute(httppost, responseHandler);


                if (responseText.contains("insert plan place error")) {

                    Log.d("responseText", "Exception : " + responseText);

                } else {

                    Log.d("responseText", "Data From Sever : " + responseText);

                }


            } catch (Exception e) {
                Log.d("log_err", "Error in http connection " + e.toString());

            }


            if(listPlace.size()>0) {


                int i = 0;
                for (i = 0; i < listPlace.size(); i++) {
                    PlaceListDao accom = listPlace.get(i);
//            Log.d("DATA:",planID+" "+date_formatted+" "+User.ID+" "+accom.getId());

                    PlanPlaceListManager.getInstance().addPlanPlace(planID, thisDate, accom.getId(), User.ID);

                    Log.d("Count", listPlace.size() + "");


                    /**
                     * add to server
                     */
                    try {
                        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                        nameValuePairs.add(new BasicNameValuePair("planID", String.valueOf(planID)));
                        nameValuePairs.add(new BasicNameValuePair("placeID", String.valueOf(accom.getId())));
                        nameValuePairs.add(new BasicNameValuePair("date", date_formatted));
                        nameValuePairs.add(new BasicNameValuePair("userID", String.valueOf(User.ID)));
                /* Set to Http post*/
                /* End set Value*/
                        HttpClient httpclient = new DefaultHttpClient();
                /* Set URL*/
                        HttpPost httppost = new HttpPost(HttpManager.UrlPHP + "android_insert_plan_place.php");
                /* End Set URL*/
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

                        ResponseHandler<String> responseHandler = new BasicResponseHandler();
                        final String responseText = httpclient.execute(httppost, responseHandler);


                        if (responseText.contains("insert plan place error")) {

                            Log.d("responseText", "Exception : " + responseText);

                        } else {

                            Log.d("responseText", "Data From Sever : " + responseText);

                        }


                    } catch (Exception e) {
                        Log.d("log_err", "Error in http connection " + e.toString());

                    }
                }
            }

        }
    }

    public void addPlanRestaurant(int planID, Date thisDate, ArrayList<RestaurantListDao> listRestau) {

        /**
         * remove before add new accom by this date from app
         */

        if(listRestau!=null) {

            PlanRestaurantListManager.getInstance().removeByPlanID(planID);

            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            String date_formatted = format1.format(thisDate);


            /**
             * remove from server
             */
            try {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("planID", String.valueOf(planID)));
                nameValuePairs.add(new BasicNameValuePair("restaurantID", String.valueOf(0)));
                nameValuePairs.add(new BasicNameValuePair("date", date_formatted));
                nameValuePairs.add(new BasicNameValuePair("userID", String.valueOf(User.ID)));
                /* Set to Http post*/
                /* End set Value*/
                HttpClient httpclient = new DefaultHttpClient();
                /* Set URL*/
                HttpPost httppost = new HttpPost(HttpManager.UrlPHP + "android_delete_plan_restau.php");
                /* End Set URL*/
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String responseText = httpclient.execute(httppost, responseHandler);


                if (responseText.contains("insert plan place error")) {

                    Log.d("responseText", "Exception : " + responseText);

                } else {

                    Log.d("responseText", "Data From Sever : " + responseText);

                }


            } catch (Exception e) {
                Log.d("log_err", "Error in http connection " + e.toString());

            }

            if(listRestau.size()>0) {

                int i = 0;
                for (i = 0; i < listRestau.size(); i++) {
                    RestaurantListDao accom = listRestau.get(i);
//            Log.d("DATA:",planID+" "+date_formatted+" "+User.ID+" "+accom.getId());


                    PlanRestaurantListManager.getInstance().addPlanRestaurant(planID, thisDate, accom.getId(), User.ID);

                    /**
                     * remove from server
                     */
                    try {
                        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                        nameValuePairs.add(new BasicNameValuePair("planID", String.valueOf(planID)));
                        nameValuePairs.add(new BasicNameValuePair("restaurantID", String.valueOf(accom.getId())));
                        nameValuePairs.add(new BasicNameValuePair("date", date_formatted));
                        nameValuePairs.add(new BasicNameValuePair("userID", String.valueOf(User.ID)));
                /* Set to Http post*/
                /* End set Value*/
                        HttpClient httpclient = new DefaultHttpClient();
                /* Set URL*/
                        HttpPost httppost = new HttpPost(HttpManager.UrlPHP + "android_insert_plan_restau.php");
                /* End Set URL*/
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

                        ResponseHandler<String> responseHandler = new BasicResponseHandler();
                        final String responseText = httpclient.execute(httppost, responseHandler);


                        if (responseText.contains("insert plan restau error")) {

                            Log.d("responseText", "Exception : " + responseText);

                        } else {

                            Log.d("responseText", "Data From Sever : " + responseText);

                        }


                    } catch (Exception e) {
                        Log.d("log_err", "Error in http connection " + e.toString());

                    }
                }
            }
        }
    }
}
