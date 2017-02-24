package com.application.youngdeveloper.apptravelfinal.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.manager.HttpManager;
import com.application.youngdeveloper.apptravelfinal.view.state.BundleSavedState;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Wachiraya_Kam on 2/24/2017.
 */

public class RestaurantListItem extends BaseCustomViewGroup {

    private Context mContext;
    ImageView ivImgRestaurant;
    TextView tvNameRestaurant;
    TextView tvDetailRestaurant;
    TextView tvCostRestaurant;

    public RestaurantListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public RestaurantListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public RestaurantListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public RestaurantListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.row_list_on_dialog_restaurant, this);
    }

    private void initInstances() {
        // findViewById here
        ivImgRestaurant = (ImageView) findViewById(R.id.ivImgRestaurant);
        tvNameRestaurant = (TextView) findViewById(R.id.tvNameRestaurant);
        tvDetailRestaurant = (TextView) findViewById(R.id.tvDetailRestaurant);
        tvCostRestaurant = (TextView) findViewById(R.id.tvCostRestaurant);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }


    public void setIvImgRestaurantText (String url){

        url = HttpManager.UrlImage+url;

        Glide.with(getContext())
                .load(url)
                .bitmapTransform(new RoundedCornersTransformation(mContext, 20, 0,
                        RoundedCornersTransformation.CornerType.ALL))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(ivImgRestaurant);
    }

    public void setTvNameRestaurantText (String text){
        tvNameRestaurant.setText(text);
    }

    public void setTvDetailRestaurantText (String text){
        tvDetailRestaurant.setText("รายละเอียด: "+text);
    }

    public void setTvCostRestaurantText (int text){
        tvCostRestaurant.setText(String.valueOf(text));
    }
}

