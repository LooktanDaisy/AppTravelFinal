package com.application.youngdeveloper.apptravelfinal.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.application.youngdeveloper.apptravelfinal.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by theerawat on 2/21/2017 AD.
 */

public class Calendar_dialog extends DialogFragment implements OnDateSelectedListener {

    private EditText DateText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_calendar, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialCalendarView widget = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        widget.setSelectionColor(getResources().getColor(R.color.colorPrimary));
        widget.setArrowColor(getResources().getColor(R.color.colorPrimary));
        widget.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        widget.setHeaderTextAppearance(R.style.TextHeaderMonth);

        Calendar calendar = Calendar.getInstance();

        widget.setSelectedDate(calendar.getTime());
        widget.state().edit()
                .setMinimumDate(calendar.getTime())
                .commit();

        widget.setOnDateChangedListener(this);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

        date.getDate();

        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");

        String date_formatted = format1.format(date.getDate());

        DateText.setText(date_formatted);

        this.dismiss();
    }

    public void setEditText(EditText editText) {
        this.DateText = editText;
    }
}
