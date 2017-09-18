package com.example.nguye.testcalendar.customcalendar;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguye.testcalendar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nguye on 9/18/2017.
 */

public class CalendarAdapterNew extends RecyclerView.Adapter<CalendarAdapterNew.CalendarHolder> {
    public List<String> days;
    public Calendar calendar;
    public CalendarAdapterNew(int month) {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH,month);
        prepareData();
    }

    private void prepareData () {
        days = new ArrayList<>();
        int firstDayOfMonth = getFirstDay();
        int lastDayOfMonth = getLastDay();
        Log.d("prepareData: ", firstDayOfMonth +"/t" + lastDayOfMonth);
        for (int i  = 0; i < lastDayOfMonth; i ++) {
            days.add(String.valueOf(i + 1));
        }
        List<String> dayEmpty = new ArrayList<>();
        for (int i = 1; i<firstDayOfMonth ; i ++) {
            dayEmpty.add("Empty");
        }
        days.addAll(0,dayEmpty);
    }
    @Override
    public CalendarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item,parent,false);
        return new CalendarHolder(view);
    }

    @Override
    public void onBindViewHolder(CalendarHolder holder, int position) {

        String date = days.get(position);
        Log.d("onBindViewHolder: ",date);
        if (date.equals("Empty")) {
            holder.tvDate.setText("");
        } else {
            final String day = days.get(position);
            holder.tvDate.setText(day);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("onClick: " , day);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    class  CalendarHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.date)
        TextView tvDate;
        @BindView(R.id.date_icon)
        ImageView imgTest;
        public CalendarHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private int getFirstDay(){
        int firstDay = (int)calendar.get(Calendar.DAY_OF_WEEK);
        return firstDay;
    }
    private int getLastDay () {
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return lastDay;
    }
}
