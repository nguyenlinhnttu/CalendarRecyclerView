package com.example.nguye.testcalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nguye.testcalendar.customcalendar.CalendarAdapterNew;
import com.example.nguye.testcalendar.customcalendar.OnSwipeTouchListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rcv_calendar)
    RecyclerView rcvCalendar;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    private Calendar calendar;
    private int month;
    private int year;
    private OnSwipeTouchListener onSwipeTouchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 7);
        rcvCalendar.setLayoutManager(layoutManager);
        calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        CalendarAdapterNew adapterNew = new CalendarAdapterNew(month);
        rcvCalendar.setAdapter(adapterNew);
        setupSwipeLeftAndRight();
        setTitle(theMonth(month) + " " + year);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        onSwipeTouchListener.getGestureDetector().onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    private void setupSwipeLeftAndRight() {
        onSwipeTouchListener = new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                if (month > Calendar.JANUARY) {
                    month -= 1;
                    setTitle(theMonth(month) + " " + year);
                    CalendarAdapterNew adapterNew = new CalendarAdapterNew(month);
                    rcvCalendar.setAdapter(adapterNew);
                } else {
                    //Quay lại năm sau
                    year -= 1;
                    calendar.set(Calendar.YEAR, year);
                    month = Calendar.DECEMBER;
                    setTitle(theMonth(month) + " " + year);
                    CalendarAdapterNew adapterNew = new CalendarAdapterNew(month);
                    rcvCalendar.setAdapter(adapterNew);
                }
            }

            @Override
            public void onSwipeLeft() {
                if (month < Calendar.DECEMBER) {
                    month += 1;
                    setTitle(theMonth(month) + " " + year);
                    CalendarAdapterNew adapterNew = new CalendarAdapterNew(month);
                    rcvCalendar.setAdapter(adapterNew);
                } else {
                    //Tăng Thêm 1 năm
                    year += 1;
                    calendar.set(Calendar.YEAR, year);
                    month = Calendar.JANUARY;
                    setTitle(theMonth(month) + " " + year);
                    CalendarAdapterNew adapterNew = new CalendarAdapterNew(month);
                    rcvCalendar.setAdapter(adapterNew);
                }
            }
        };
        llContent.setOnTouchListener(onSwipeTouchListener);
    }

    public String theMonth(int month) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }
}
