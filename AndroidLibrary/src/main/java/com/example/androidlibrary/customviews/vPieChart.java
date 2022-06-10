package com.example.androidlibrary.customviews;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.androidlibrary.R;
import com.example.androidlibrary.task.DeviceStatusTask;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

public class vPieChart extends LinearLayout implements DeviceStatusTask.DeviceStatusTaskCallback{
    private Context mContext;
    private PieChart mChart;
    public vPieChart(Context context) {
        super(context);
    }

    public vPieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pie_chart, this,true);
        findViewsById(view);
    }

    public vPieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void findViewsById(View view) {
        mChart = (PieChart) view.findViewById(R.id.pieChart);
    }

    private void initializemCharts() {
        mChart.setUsePercentValues(false);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setDrawHoleEnabled(false);

        mChart.setRotationAngle(0);
        // enable rotation of the mChart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTextSize(18f);
        mChart.getLegend().setEnabled(false);
        // undo all highlights
        mChart.highlightValues(null);

        mChart.animateY(1000, Easing.EaseInOutBounce);

        mChart.setData(null);
        mChart.invalidate();
    }

    public void populatePiechart(int count, int iNonConn, int iConn, int iActive, int iAlert) {

        if(count > 0){
            final ArrayList<PieEntry> entries = new ArrayList<>();

            final PieDataSet dataSet = new PieDataSet(new ArrayList<PieEntry>(), "deviceList");

            dataSet.setDrawIcons(false);
            dataSet.setForm(null);

            dataSet.setSliceSpace(3f);
            dataSet.setIconsOffset(new MPPointF(0, 40));
            dataSet.setSelectionShift(5f);

            final PieData data = new PieData(dataSet);
            data.setValueTextSize(20f);
            data.setValueTextColor(Color.WHITE);
            data.setDrawValues(false);

            dataSet.clear();
            dataSet.resetColors();
            if (iNonConn > 0) {
                dataSet.addEntry(new PieEntry(iNonConn, "" + iNonConn));
                dataSet.addColor(getResources().getColor(R.color.giNotConnected, null));
            }
            if (iConn > 0) {
                dataSet.addEntry(new PieEntry(iConn, "" + iConn));
                dataSet.addColor(getResources().getColor(R.color.giConnected, null));
            }
            if (iActive > 0) {
                dataSet.addEntry(new PieEntry(iActive, "" + iActive));
                dataSet.addColor(getResources().getColor(R.color.giActive, null));
            }
            if (iAlert > 0) {
                dataSet.addEntry(new PieEntry(iAlert, "" + iAlert));
                dataSet.addColor(getResources().getColor(R.color.giStopped, null));
            }
            dataSet.notifyDataSetChanged();
            mChart.setData(data);
        }else{
            mChart.setData(null);
        }
        mChart.notifyDataSetChanged();
        mChart.invalidate();
    }


    @Override
    public void onDeviceStatusTaskFinished(List<Integer> results) {
        int ncDevices = results.get(3) - results.get(0) - results.get(1) - results.get(2);
        populatePiechart(results.get(3), ncDevices, results.get(0), results.get(1), results.get(2));
    }
}
