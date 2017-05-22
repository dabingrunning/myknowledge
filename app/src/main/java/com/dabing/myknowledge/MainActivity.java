package com.dabing.myknowledge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dabing.myknowledge.widget.calendarviews.OnCalendarClickListener;
import com.dabing.myknowledge.widget.calendarviews.week.WeekCalendarView;

public class MainActivity extends AppCompatActivity {

    private TextView monthTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monthTv = (TextView) findViewById(R.id.month);
        WeekCalendarView week = (WeekCalendarView) findViewById(R.id.wcvCalendar);
        week.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onClickDate(int year, int month, int day) {
                Toast.makeText(MainActivity.this,year+"年"+(month +1)+"月"+day+"日",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageChange(int year, int month, int day) {
                monthTv.setText(year+"."+(month+1));
            }
        });
    }

    public void next(View v){
        Intent intent = new Intent();
        intent.setClass(this,TableActivity.class);
        startActivity(intent);
    }
}
