package com.example.androidlibrary.util;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;

import java.util.Date;
import java.util.List;

public class BasicFunctions {
    public static String getDuration(int duration)
    {
        int hours = (int)duration / 3600;
        int remainder = (int) duration - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;
        return ""+String.format("%02d",hours)+":"+String.format("%02d",mins)+":"+String.format("%02d",secs);
    }

    public static String secondsToDateTime(long Seconds)
    {
        Date date = new Date(Seconds);
        SimpleDateFormat formatter_time = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa");
        formatter_time.setTimeZone(TimeZone.getTimeZone("IST"));
        return formatter_time.format(date);
    }


    public static String secondsTohms(long Seconds)
    {
        long lastupdatedTime = Seconds * 1000;
        Date date = new Date(lastupdatedTime);
        final SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }

   /* public static void setAdapter(RecyclerView recyclerView, List<String> mNameList, RecyclerView.LayoutManager linearLayoutManager) {
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
            CustomAdapter customAdapter = new CustomAdapter(mNameList);
            recyclerView.setAdapter(customAdapter);
        }
    }*/

    public static void initializecharts(PieChart chart){
            chart.setUsePercentValues(false);
            chart.getDescription().setEnabled(false);
            chart.setExtraOffsets(5, 10, 5, 5);

            chart.setDragDecelerationFrictionCoef(0.95f);

            chart.setDrawHoleEnabled(false);

            chart.setRotationAngle(0);
            // enable rotation of the chart by touch
            chart.setRotationEnabled(true);
            chart.setHighlightPerTapEnabled(true);

            chart.setEntryLabelColor(Color.WHITE);
            chart.setEntryLabelTextSize(18f);
            chart.getLegend().setEnabled(false);
            // undo all highlights
            chart.highlightValues(null);

            chart.animateY(1000, Easing.EaseInOutBounce);

            chart.setData(null);
            chart.invalidate();
        }

}
