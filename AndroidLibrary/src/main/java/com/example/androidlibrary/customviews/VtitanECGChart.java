package com.example.androidlibrary.customviews;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlibrary.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class VtitanECGChart extends LinearLayout {
    private Context mContext;
    private LineChart mChart;
    public VtitanECGChart(Context context) {
        super(context);
    }

    public VtitanECGChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ecg_chart, this,true);
        findViewsById(view);
    }

    public VtitanECGChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void findViewsById(View view) {
        mChart = (LineChart) view.findViewById(R.id.chart);
    }
    public void plotECGChart(List<String >dataList){
        initializeFlowRateChart(dataList);
    }
    private void initializeFlowRateChart(List<String>sindata) {

        //mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        mChart.getDescription().setEnabled(false);
        mChart.setTouchEnabled(false);
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(false);

        ValueFormatter valueFormatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                Log.i(TAG, "initializeFlowRateChart: " + Long.valueOf((long) value));
                // return super.getAxisLabel(value, axis);
                Date date = new Date(Long.valueOf((long) value));
                Format format = new SimpleDateFormat("ss");
                return format.format(date.getTime());
            }
        };

        XAxis xl = mChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setAvoidFirstLastClipping(true);
        //xl.setValueFormatter(valueFormatter);
        xl.setAvoidFirstLastClipping(false);
        xl.setDrawGridLines(true);
        xl.setGridLineWidth(1.5f);
        xl.setGridColor(getResources().getColor(R.color.grid_line_color));
        xl.setGranularity(100);
        xl.setGranularityEnabled(true);
        // xAxis.setValueFormatter(valueFormatters);
        xl.setDrawLabels(true);
        xl.setDrawAxisLine(true);
        xl.setAxisMinimum(0f);
        xl.setAxisMaximum(2500);
        xl.setLabelCount(25);

        LimitLine xll = new LimitLine(500f, "");
        xll.setLineColor(getResources().getColor(R.color.limit_line_color));
        xll.setLineWidth(2f);

        LimitLine xl2 = new LimitLine(1000f, "");
        xl2.setLineColor(getResources().getColor(R.color.limit_line_color));
        xl2.setLineWidth(2f);

        LimitLine xl3 = new LimitLine(1500f, "");
        xl3.setLineColor(getResources().getColor(R.color.limit_line_color));
        xl3.setLineWidth(2f);

        LimitLine xl4 = new LimitLine(2000f, "");
        xl4.setLineColor(getResources().getColor(R.color.limit_line_color));
        xl4.setLineWidth(2f);

        LimitLine xl5 = new LimitLine(2500f, "");
        xl5.setLineColor(getResources().getColor(R.color.limit_line_color));
        xl5.setLineWidth(2f);
        xl.addLimitLine(xll);
        xl.addLimitLine(xl2);
        xl.addLimitLine(xl3);
        xl.addLimitLine(xl4);
        xl.addLimitLine(xl5);
        // xl.setAxisMinimum(0f);

        YAxis leftAxis = mChart.getAxisLeft();
        // leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(getResources().getColor(R.color.grid_line_color));
        // leftAxis.setGranularity(150f);
        //leftAxis.setGranularityEnabled(true);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setAxisMinimum(-300);
        leftAxis.setAxisMaximum(1200);
        leftAxis.setLabelCount(25);
        leftAxis.setGridLineWidth(1.5f);
        LimitLine ll = new LimitLine(200f, "");
        ll.setLineColor(getResources().getColor(R.color.limit_line_color));
        ll.setLineWidth(2f);

        LimitLine l2 = new LimitLine(700f, "");
        l2.setLineColor(getResources().getColor(R.color.limit_line_color));
        l2.setLineWidth(2f);

        LimitLine l3 = new LimitLine(1200f, "");
        l3.setLineColor(getResources().getColor(R.color.limit_line_color));
        l3.setLineWidth(2f);

        leftAxis.addLimitLine(ll);
        leftAxis.addLimitLine(l2);
        leftAxis.addLimitLine(l3);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
        rightAxis.setDrawGridLines(false);
        Legend l = mChart.getLegend();

        l.setForm(Legend.LegendForm.LINE);
        if (sindata.size() > 0) {
            setChartdata(sindata);
        } else {
            mChart.setData(null);
        }
        mChart.invalidate();
    }
    private void setChartdata(List<String>sindata) {

        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < sindata.size(); i++) {
            float xVal = (float) i;
            float yVal = (float) Integer.parseInt(sindata.get(i).trim());
            entries.add(new Entry(xVal, yVal));

        }
        LineDataSet set1 = new LineDataSet(entries, "Sin");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setLineWidth(2f);
        set1.setDrawCircles(false);
        set1.setColor(Color.BLACK);
        set1.setHighlightEnabled(false);
        set1.setDrawValues(false);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setCubicIntensity(0.2f);

        // create a data object with the data sets
        LineData data = new LineData(set1);
        // set data
        mChart.setData(data);
        data.notifyDataChanged();

        // let the chart know it's data has changed
        mChart.notifyDataSetChanged();

        mChart.moveViewToX(data.getEntryCount());
    }




}
