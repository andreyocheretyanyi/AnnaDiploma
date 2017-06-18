package ua.com.anna.borodina.annadiploma.views.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ua.com.anna.borodina.annadiploma.R;
import ua.com.anna.borodina.annadiploma.model.DatabaseProviderImpl;
import ua.com.anna.borodina.annadiploma.views.fragments.BaseFragment;
import ua.com.anna.borodina.annadiploma.views.fragments.MapFragment;


public class DialogWithCalendar extends DialogFragment implements CalendarView.OnDateChangeListener {

    private CalendarView calendarView;
    private String selectedDate;
    private Long currDate;
    private BaseFragment parent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_dialog_with_calendar, container, false);
        calendarView = (CalendarView) v.findViewById(R.id.calendar);
        if(currDate != null) {
            calendarView.setDate(currDate);
        }
        calendarView.setOnDateChangeListener(this);
        return v;
    }

    public void setDate(long date){
        currDate = date;
    }

    public static long convertDate(String date){
        SimpleDateFormat f = new SimpleDateFormat("dd.M.yyyy");
        long milliseconds = 0;
        try {
            Date d = f.parse(date);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return milliseconds;
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        int mYear = year;
        int mMonth = month;
        int mDay = dayOfMonth;
        selectedDate = new StringBuilder().append(mDay)
                .append(".").append(mMonth + 1).append(".").append(mYear)
                .append(" ").toString();
        parent.setDate(selectedDate);
        dismiss();
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String date){
        selectedDate = date;
    }

    public void bindParent(BaseFragment baseFragment){
        parent = baseFragment;
    }

}
