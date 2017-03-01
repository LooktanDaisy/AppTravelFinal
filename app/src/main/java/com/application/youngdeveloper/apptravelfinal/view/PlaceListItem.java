package com.application.youngdeveloper.apptravelfinal.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.youngdeveloper.apptravelfinal.R;
import com.application.youngdeveloper.apptravelfinal.config.MainFunction;
import com.application.youngdeveloper.apptravelfinal.manager.HttpManager;
import com.application.youngdeveloper.apptravelfinal.view.state.BundleSavedState;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Wachiraya_Kam on 2/22/2017.
 */

public class PlaceListItem extends BaseCustomViewGroup {

    private Context mContext;

    ImageView ivImgPlace,ivMap,ivAdd;
    TextView tvNamePlace;
    TextView tvAddressPlace;
    TextView tvCostPlace;
    TextView HowFar;


    public PlaceListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public PlaceListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public PlaceListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public PlaceListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.row_list_on_dialog, this);
    }

    private void initInstances() {
        // findViewById here
        ivImgPlace = (ImageView) findViewById(R.id.ivImgPlace);
        tvNamePlace = (TextView) findViewById(R.id.tvNamePlace);
        tvAddressPlace = (TextView) findViewById(R.id.tvAddressPlace);
        tvCostPlace = (TextView) findViewById(R.id.tvCostPlace);
        ivMap = (ImageView) findViewById(R.id.img_map);
        ivAdd = (ImageView) findViewById(R.id.ivAdd);
        HowFar = (TextView) findViewById(R.id.textViewHowFar);
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

    public void setIvImgPlaceText (String url){

        url = HttpManager.UrlImage+url;

        Glide.with(getContext())
                .load(url)
                .bitmapTransform(new RoundedCornersTransformation(mContext, 20, 0,
                        RoundedCornersTransformation.CornerType.ALL))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(ivImgPlace);
    }

    public void setTvNamePlaceText (String text){
        tvNamePlace.setText(text);
    }

    public void setTvAddressPlaceText (String text){
        tvAddressPlace.setText("ที่อยู่ : "+text);
    }

    public void setTvCostPlaceText (int text){
        tvCostPlace.setText(String.valueOf(text));
    }

    public ImageView getIvMap(){
        return ivMap;
    }

    public ImageView getIvAdd() {
        return ivAdd;
    }

    public void setHowFar(Double howFar) {
        HowFar.setText(String.valueOf(MainFunction.commaTwoDigit(howFar)) + " กิโลเมตร จากที่พัก");
    }
}
