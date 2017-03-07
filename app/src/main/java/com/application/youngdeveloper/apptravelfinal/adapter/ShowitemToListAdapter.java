package com.application.youngdeveloper.apptravelfinal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.Type_id_item;
import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanAccommodationListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanPlaceListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlanRestaurantListDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListDao;
import com.application.youngdeveloper.apptravelfinal.manager.AccommodationListManager;
import com.application.youngdeveloper.apptravelfinal.manager.HttpManager;
import com.application.youngdeveloper.apptravelfinal.manager.PlaceListManager;
import com.application.youngdeveloper.apptravelfinal.manager.PlanAccommodationListManager;
import com.application.youngdeveloper.apptravelfinal.manager.PlanListManager;
import com.application.youngdeveloper.apptravelfinal.manager.PlanPlaceListManager;
import com.application.youngdeveloper.apptravelfinal.manager.PlanRestaurantListManager;
import com.application.youngdeveloper.apptravelfinal.manager.RestaurantListManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Date;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ShowitemToListAdapter extends RecyclerView.Adapter<ShowitemToListAdapter.MainViewHolder> {

    private Context mContext;
    private String listOfID = null;
    private ArrayList<PlanPlaceListDao> ListItemPlace = new ArrayList<>();
    private ArrayList<PlanAccommodationListDao> ListItemAccom = new ArrayList<>();
    private ArrayList<PlanRestaurantListDao> ListItemRestau= new ArrayList<>();
    private int TYPE_ID = 0;
    private int PLAN_ID=0;
    private Date thisDate;

    public ShowitemToListAdapter(Context context,int typeAdapter,int keyPlan,Date date) {
        mContext = context;
        TYPE_ID = typeAdapter;
        PLAN_ID = keyPlan;
        thisDate = date;

        if(TYPE_ID == Type_id_item.TYPE_PLACE){

            ListItemPlace = PlanPlaceListManager.getInstance().getListPlanPlaceByDateAndPlanID(PLAN_ID,thisDate);

        }else if(TYPE_ID == Type_id_item.TYPE_ACCOMMODATION){

            ListItemAccom = PlanAccommodationListManager.getInstance().getListPlanAccomByDateAndPlanID(PLAN_ID,thisDate);

        }else if(TYPE_ID == Type_id_item.TYPE_RESTAURANT){

            ListItemRestau = PlanRestaurantListManager.getInstance().getListPlanAccomByDateAndPlanID(PLAN_ID,thisDate);

        }


    }


//    public void addItem(String listOfID,int TYPE_ID){
//        this.listOfID = listOfID;
//        this.TYPE_ID = TYPE_ID;
//
//        if(TYPE_ID == Type_id_item.TYPE_PLACE){
//            if(ListItemPlace.contains(PlaceListManager.getInstance().getPlace(Integer.parseInt(listOfID)))==false) {
////                ListItemPlace.add(PlaceListManager.getInstance().getPlace(Integer.parseInt(listOfID)));
//            }
//        }else if(TYPE_ID == Type_id_item.TYPE_ACCOMMODATION){
//            if(ListItemAccom.contains(AccommodationListManager.getInstance().getAccommodation(Integer.parseInt(listOfID)))==false) {
//                ListItemAccom.removeAll(ListItemAccom);
////                ListItemAccom.add(AccommodationListManager.getInstance().getAccommodation(Integer.parseInt(listOfID)));
//            }
//        }else if(TYPE_ID == Type_id_item.TYPE_RESTAURANT){
//            if(ListItemRestau.contains(RestaurantListManager.getInstance().getRestaurant(Integer.parseInt(listOfID)))==false){
//
//            }
////           ListItemRestau.add(RestaurantListManager.getInstance().getRestaurant(Integer.parseInt(listOfID)));
//        }
//        notifyDataSetChanged();
//    }



    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_add_on_list_plan, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {

            if (TYPE_ID == Type_id_item.TYPE_PLACE) {
                if(ListItemPlace.size()!=0) {
                    PlanPlaceListDao dao = ListItemPlace.get(position);
                    PlaceListDao placeDao = PlaceListManager.getInstance().getPlace(dao.getPlaceID());
                    holder.tvName.setText(placeDao.getName());
                    holder.tvCost.setText(placeDao.getCost() + "  บาท");

                    String url = HttpManager.UrlImage + placeDao.getImg();

                    Glide.with(mContext)
                            .load(url)
                            .bitmapTransform(new RoundedCornersTransformation(mContext, 20, 0,
                                    RoundedCornersTransformation.CornerType.ALL))
                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .into(holder.imagePreview);

                    holder.imageDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ListItemPlace.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                }

            } else if (TYPE_ID == Type_id_item.TYPE_ACCOMMODATION) {
                if(ListItemAccom.size()!=0) {
                    /**
                     * set pointAccom to Main
                     */

                    PlanAccommodationListDao dao = ListItemAccom.get(position);
                    AccommodationListDao accomDao = AccommodationListManager.getInstance().getAccommodation((dao.getAccomID()));


                    holder.tvName.setText(accomDao.getName());
                    holder.tvCost.setText(accomDao.getPrice() + "  บาท");

                    String url = HttpManager.UrlImage + accomDao.getImg();

                    Glide.with(mContext)
                            .load(url)
                            .bitmapTransform(new RoundedCornersTransformation(mContext, 20, 0,
                                    RoundedCornersTransformation.CornerType.ALL))
                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .into(holder.imagePreview);

                    holder.imageDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ListItemAccom.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                    /**
                     * Accom can change but can not delete
                     */
                    holder.imageDelete.setVisibility(View.GONE);
                }
            } else if (TYPE_ID == Type_id_item.TYPE_RESTAURANT) {
                if(ListItemRestau.size()!=0) {
                    PlanRestaurantListDao dao = ListItemRestau.get(position);
                    RestaurantListDao restauDao = RestaurantListManager.getInstance().getRestaurant(dao.getRestauID());
                    holder.tvName.setText(restauDao.getName());
                    holder.tvCost.setText(restauDao.getPrice() + "  บาท");

                    String url = HttpManager.UrlImage + restauDao.getImg();

                    Glide.with(mContext)
                            .load(url)
                            .bitmapTransform(new RoundedCornersTransformation(mContext, 20, 0,
                                    RoundedCornersTransformation.CornerType.ALL))
                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .into(holder.imagePreview);

                    holder.imageDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ListItemRestau.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                }
            }






    }


    @Override
    public int getItemCount() {
        if(TYPE_ID == Type_id_item.TYPE_PLACE){
           return ListItemPlace.size();
        }else if(TYPE_ID == Type_id_item.TYPE_ACCOMMODATION){
            return ListItemAccom.size();
        }else if(TYPE_ID == Type_id_item.TYPE_RESTAURANT){
            return ListItemRestau.size();
        }else{
            return 0;
        }


    }


    public class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvCost;
        private ImageView imagePreview,imageDelete;

        public MainViewHolder(View itemView) {
            super(itemView);

            imagePreview = (ImageView) itemView.findViewById(R.id.image_preview);
            imageDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvCost = (TextView) itemView.findViewById(R.id.textViewCost);

        }
    }



}
