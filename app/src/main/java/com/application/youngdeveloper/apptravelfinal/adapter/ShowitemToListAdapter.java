package com.application.youngdeveloper.apptravelfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.application.youngdeveloper.apptravelfinal.manager.User;
import com.application.youngdeveloper.apptravelfinal.screen.MapActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Date;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ShowitemToListAdapter extends RecyclerView.Adapter<ShowitemToListAdapter.MainViewHolder> {

    private Context mContext;
    private String listOfID = null;
    private ArrayList<PlaceListDao> ListItemPlace = new ArrayList<>();
    private ArrayList<AccommodationListDao> ListItemAccom = new ArrayList<>();
    private ArrayList<RestaurantListDao> ListItemRestau= new ArrayList<>();


    private int TYPE_ID = 0;
    private int PLAN_ID=0;
    private Date thisDate;
    private boolean statusEdit = false;

    public ShowitemToListAdapter(Context context,int typeAdapter,int keyPlan,Date date) {
        mContext = context;
        TYPE_ID = typeAdapter;
        PLAN_ID = keyPlan;
        thisDate = date;

        if(TYPE_ID == Type_id_item.TYPE_PLACE){
            ArrayList<PlanPlaceListDao> listPlanPlace = PlanPlaceListManager.getInstance().getListPlanPlaceByDateAndPlanID(PLAN_ID,thisDate);
            if(listPlanPlace.size()>0){
                ListItemPlace.remove(ListItemPlace);
                int i=0;
                for(i=0;i<listPlanPlace.size();i++){
                    int placeID = listPlanPlace.get(i).getPlaceID();
                    if(ListItemPlace.contains(listPlanPlace.get(i))==false) {
                        ListItemPlace.add(PlaceListManager.getInstance().getPlace(placeID));
                    }
                }
            }


        }else if(TYPE_ID == Type_id_item.TYPE_ACCOMMODATION){
            ListItemAccom.remove(ListItemAccom);
            ArrayList<PlanAccommodationListDao> listPlanAccom = PlanAccommodationListManager.getInstance().getListPlanAccomByDateAndPlanID(PLAN_ID,thisDate);
            if(listPlanAccom.size()>0){
                int i=0;
                for(i=0;i<listPlanAccom.size();i++){
                    int accomID = listPlanAccom.get(i).getAccomID();
                    ListItemAccom.add(AccommodationListManager.getInstance().getAccommodation(accomID));
                }
            }

        }else if(TYPE_ID == Type_id_item.TYPE_RESTAURANT){

            ArrayList<PlanRestaurantListDao> listPlanRestau = PlanRestaurantListManager.getInstance().getListPlanAccomByDateAndPlanID(PLAN_ID,thisDate);
            if(listPlanRestau.size()>0){
                ListItemRestau.remove(ListItemRestau);
                int i=0;
                for(i=0;i<listPlanRestau.size();i++){
                    int restuaID = listPlanRestau.get(i).getRestauID();
                    if(ListItemRestau.contains(listPlanRestau.get(i))==false) {
                        ListItemRestau.add(RestaurantListManager.getInstance().getRestaurant(restuaID));
                    }

                }
            }

        }


    }


    public int getAccomDetail(){
        if(ListItemAccom.size()>0) {
            return ListItemAccom.get(0).getId();
        }else{
            return 0;
        }
    }


    public void addItem(String listOfID,int TYPE_ID){
        this.listOfID = listOfID;
        this.TYPE_ID = TYPE_ID;

        if(TYPE_ID == Type_id_item.TYPE_PLACE){
            if(ListItemPlace.contains(PlaceListManager.getInstance().getPlace(Integer.parseInt(listOfID)))==false) {
                ListItemPlace.add(PlaceListManager.getInstance().getPlace(Integer.parseInt(listOfID)));
            }
        }else if(TYPE_ID == Type_id_item.TYPE_ACCOMMODATION){
            if(ListItemAccom.contains(AccommodationListManager.getInstance().getAccommodation(Integer.parseInt(listOfID)))==false) {
                ListItemAccom.removeAll(ListItemAccom);
                ListItemAccom.add(AccommodationListManager.getInstance().getAccommodation(Integer.parseInt(listOfID)));

            }
        }else if(TYPE_ID == Type_id_item.TYPE_RESTAURANT){
            if(ListItemRestau.contains(RestaurantListManager.getInstance().getRestaurant(Integer.parseInt(listOfID)))==false){
           ListItemRestau.add(RestaurantListManager.getInstance().getRestaurant(Integer.parseInt(listOfID)));
            }

        }
        notifyDataSetChanged();
    }



    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_add_on_list_plan, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {

            if (TYPE_ID == Type_id_item.TYPE_PLACE) {
                if(ListItemPlace.size()!=0) {
                    final PlaceListDao dao = ListItemPlace.get(position);
                    PlaceListDao placeDao = PlaceListManager.getInstance().getPlace(dao.getId());
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

                    holder.Row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent openMap = new Intent(mContext, MapActivity.class);
                            openMap.putExtra("ID", dao.getId());
                            openMap.putExtra("TYPE_ID", Type_id_item.TYPE_PLACE);

                            mContext.startActivity(openMap);
                        }
                    });

                    if(statusEdit){

                        holder.imageDelete.setVisibility(View.VISIBLE);

                    }else{

                        holder.imageDelete.setVisibility(View.INVISIBLE);
                    }


                }

            } else if (TYPE_ID == Type_id_item.TYPE_ACCOMMODATION) {
                if(ListItemAccom.size()!=0) {
                    /**
                     * set pointAccom to Main
                     */

                    final AccommodationListDao dao = ListItemAccom.get(position);
                    AccommodationListDao accomDao = AccommodationListManager.getInstance().getAccommodation((dao.getId()));


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

                    holder.Row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent openMap = new Intent(mContext, MapActivity.class);
                            openMap.putExtra("ID", dao.getId());
                            openMap.putExtra("TYPE_ID", Type_id_item.TYPE_ACCOMMODATION);

                            mContext.startActivity(openMap);
                        }
                    });

                    /**
                     * Accom can change but can not delete
                     */
                    holder.imageDelete.setVisibility(View.GONE);
                }
            } else if (TYPE_ID == Type_id_item.TYPE_RESTAURANT) {
                if(ListItemRestau.size()!=0) {
                    final RestaurantListDao dao = ListItemRestau.get(position);
                    RestaurantListDao restauDao = RestaurantListManager.getInstance().getRestaurant(dao.getId());
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

                    holder.Row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent openMap = new Intent(mContext, MapActivity.class);
                            openMap.putExtra("ID", dao.getId());
                            openMap.putExtra("TYPE_ID", Type_id_item.TYPE_RESTAURANT);

                            mContext.startActivity(openMap);
                        }
                    });

                    if(statusEdit){

                        holder.imageDelete.setVisibility(View.VISIBLE);

                    }else{

                        holder.imageDelete.setVisibility(View.INVISIBLE);
                    }
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

    public void setEnableEdit(boolean statusEdit){
        this.statusEdit = statusEdit;
        notifyDataSetChanged();
    }


    public class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvCost;
        private ImageView imagePreview,imageDelete;
        private RelativeLayout Row;

        public MainViewHolder(View itemView) {
            super(itemView);

            imagePreview = (ImageView) itemView.findViewById(R.id.image_preview);
            imageDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvCost = (TextView) itemView.findViewById(R.id.textViewCost);
            Row = (RelativeLayout) itemView.findViewById(R.id.row);
        }
    }



    public ArrayList<PlaceListDao> getListPlace() {
//
        return ListItemPlace;
    }

    public ArrayList<RestaurantListDao> getListRestaurant() {

        return  ListItemRestau;
    }

    public ArrayList<AccommodationListDao> getListAccom() {
        if (ListItemAccom.size()>0) {
            return ListItemAccom;
        } else {
            return null;
        }
    }



}
