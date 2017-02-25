package com.application.youngdeveloper.apptravelfinal.screen;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.Type_id_item;
import com.application.youngdeveloper.apptravelfinal.dao.AccommodationListDao;
import com.application.youngdeveloper.apptravelfinal.dao.PlaceListDao;
import com.application.youngdeveloper.apptravelfinal.dao.RestaurantListDao;
import com.application.youngdeveloper.apptravelfinal.manager.AccommodationListManager;
import com.application.youngdeveloper.apptravelfinal.manager.HttpManager;
import com.application.youngdeveloper.apptravelfinal.manager.PlaceListManager;
import com.application.youngdeveloper.apptravelfinal.manager.RestaurantListManager;
import com.application.youngdeveloper.apptravelfinal.view.PlaceListItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private SupportMapFragment mMapFragment;
    private Integer id;
    private PlaceListDao PLACE;
    private AccommodationListDao ACCOMMODATION;
    private RestaurantListDao RESTAURANT;
    private Integer id_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent getPLACE = getIntent();
        id = getPLACE.getIntExtra("ID", 0);
        id_type = getPLACE.getIntExtra("TYPE_ID",0);

        if (id_type == Type_id_item.TYPE_PLACE) {
            PLACE = PlaceListManager.getInstance().getPlace(id);
        } else if (id_type == Type_id_item.TYPE_ACCOMMODATION) {
            ACCOMMODATION = AccommodationListManager.getInstance().getAccommodation(id);
        } else if (id_type == Type_id_item.TYPE_RESTAURANT){
            RESTAURANT = RestaurantListManager.getInstance().getRestaurant(id);
        }

        /**
         * Initial Map
         */

        mMapFragment = SupportMapFragment.newInstance();
        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        if (id_type == Type_id_item.TYPE_PLACE) {

            if (PLACE != null) {
                LatLng latLng = new LatLng(Double.parseDouble(PLACE.getLat().trim()), Double.parseDouble(PLACE.getLng().trim()));
                googleMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location))
                        .position(latLng)
                        .title(PLACE.getName()));

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                googleMap.setInfoWindowAdapter(new MyInfoWindowAdapter());

            }

        } else if (id_type == Type_id_item.TYPE_ACCOMMODATION) {

            if (ACCOMMODATION != null) {
                LatLng latLng = new LatLng(Double.parseDouble(ACCOMMODATION.getLat().trim()), Double.parseDouble(ACCOMMODATION.getLng().trim()));
                googleMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location))
                        .position(latLng)
                        .title(ACCOMMODATION.getName()));

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                googleMap.setInfoWindowAdapter(new MyInfoWindowAdapter());

            }

        } else if (id_type == Type_id_item.TYPE_RESTAURANT){

            if (RESTAURANT != null) {
                LatLng latLng = new LatLng(Double.parseDouble(RESTAURANT.getLat().trim()), Double.parseDouble(RESTAURANT.getLng().trim()));
                googleMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location))
                        .position(latLng)
                        .title(RESTAURANT.getName()));

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                googleMap.setInfoWindowAdapter(new MyInfoWindowAdapter());

            }

        }

    }


    /**
     * Custom infoWindow when user click marker
     */
    private class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private View myContentsView;

//        MyInfoWindowAdapter() {
//            myContentsView = getLayoutInflater().inflate(R.layout.row_list_on_map, null);
//        }

        /** First method
         *
         * @param marker
         * @return
         */
        @Override
        public View getInfoWindow(Marker marker) {
            ContextThemeWrapper cw = new ContextThemeWrapper(
                    getApplicationContext(), R.style.Transparent_infoWindow);

            LayoutInflater inflater = (LayoutInflater) cw
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            myContentsView = inflater.inflate(R.layout.row_list_on_map,
                    null);

            TextView tvName = (TextView) myContentsView.findViewById(R.id.tvName);
            TextView tvAddress = (TextView) myContentsView.findViewById(R.id.tvAddress);
            TextView tvCost = (TextView) myContentsView.findViewById(R.id.tvCost);
            ImageView imageView = (ImageView) myContentsView.findViewById(R.id.ivImg);


            if (id_type == Type_id_item.TYPE_PLACE) {

                if (PLACE != null) {
                    tvName.setText(PLACE.getName());
                    tvAddress.setText(PLACE.getDetail());
                    tvCost.setText(String.valueOf(PLACE.getCost()));

                    /**
                     * URL image
                     */

                    String url = HttpManager.UrlImage + PLACE.getImg();

                    Glide.with(getApplicationContext())
                            .load(url)
                            .bitmapTransform(new RoundedCornersTransformation(getApplicationContext(), 20, 0,
                                    RoundedCornersTransformation.CornerType.ALL))
                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .into(imageView);
                }

            } else if (id_type == Type_id_item.TYPE_ACCOMMODATION) {

                if (ACCOMMODATION != null) {
                    tvName.setText(ACCOMMODATION.getName());
                    tvAddress.setText(ACCOMMODATION.getDetail());
                    tvCost.setText(String.valueOf(ACCOMMODATION.getPrice()));

                    /**
                     * URL image
                     */

                    String url = HttpManager.UrlImage + ACCOMMODATION.getImg();

                    Glide.with(getApplicationContext())
                            .load(url)
                            .bitmapTransform(new RoundedCornersTransformation(getApplicationContext(), 20, 0,
                                    RoundedCornersTransformation.CornerType.ALL))
                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .into(imageView);

                }

            } else if (id_type == Type_id_item.TYPE_RESTAURANT){

                if (RESTAURANT != null) {
                    tvName.setText(RESTAURANT.getName());
                    tvAddress.setText(RESTAURANT.getDetail());
                    tvCost.setText(String.valueOf(RESTAURANT.getPrice()));

                    /**
                     * URL image
                     */

                    String url = HttpManager.UrlImage + RESTAURANT.getImg();

                    Glide.with(getApplicationContext())
                            .load(url)
                            .bitmapTransform(new RoundedCornersTransformation(getApplicationContext(), 20, 0,
                                    RoundedCornersTransformation.CornerType.ALL))
                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .into(imageView);

                }

            }




            return myContentsView;

        }

        @Override
        public View getInfoContents(Marker marker) {
            return myContentsView;
        }
    }
}
