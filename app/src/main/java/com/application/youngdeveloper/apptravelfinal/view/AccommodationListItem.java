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
 * Created by Wachiraya_Kam on 2/23/2017.
 */

public class AccommodationListItem extends BaseCustomViewGroup {

    private Context mContext;
    ImageView ivImgAccomodation,ivMap;
    TextView tvNameAccommodation;
    TextView tvDetailAcccommodation;
    TextView tvCostAccomodation;

    public AccommodationListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public AccommodationListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public AccommodationListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public AccommodationListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.row_list_on_dialog_accommodation, this);
    }

    private void initInstances() {
        // findViewById here
        ivImgAccomodation = (ImageView) findViewById(R.id.ivImgAccommodation);
        tvNameAccommodation = (TextView) findViewById(R.id.tvNameAccommodation);
        tvDetailAcccommodation = (TextView) findViewById(R.id.tvDetailAccommodation);
        tvCostAccomodation = (TextView) findViewById(R.id.tvCostAccommodation);
        ivMap = (ImageView) findViewById(R.id.img_map);
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


    public void setIvImgAccommodationText (String url){

        url = HttpManager.UrlImage+url;

        Glide.with(getContext())
                .load(url)
                .bitmapTransform(new RoundedCornersTransformation(mContext, 20, 0,
                        RoundedCornersTransformation.CornerType.ALL))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(ivImgAccomodation);
    }

    public void setTvNameAccommodationText (String text){
        tvNameAccommodation.setText(text);
    }

    public void setTvDetailAccommodationText (String text){
        tvDetailAcccommodation.setText("รายละเอียด: "+text);
    }

    public void setTvCostAccommodationText (int text){
        tvCostAccomodation.setText(String.valueOf(text));
    }

    public ImageView getIvMap(){
        return ivMap;
    }
}
