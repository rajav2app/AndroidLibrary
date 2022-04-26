package com.example.androidlibrary.customviews;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlibrary.CustomAdapter;
import com.example.androidlibrary.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class searchbar extends LinearLayout  {
     private RecyclerView rv_free_device;
     private CustomAdapter customAdapter;
     private Context mContext;
     private TextView txt_noData;
     private ProgressBar loading;

    public searchbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
       // View view=LayoutInflater.from(context).inflate(R.layout.searchbar, this, true);
       // vg = (ViewGroup) view.findViewById(R.id.ll_sch_layout);
       // relativeLayout= (ViewGroup) view.findViewById(R.id.rlrecyclerView);
       // rv_free_device= (RecyclerView) view.findViewById(R.id.rv_free_device);
        /*setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
*/
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.searchbar, this,true);
        findViewsById(view);
        setupRecyclerView();
    }

    private void findViewsById(View view) {
        rv_free_device = (RecyclerView) view.findViewById(R.id.rv_free_device);
        txt_noData=(TextView)view.findViewById(R.id.txt_noData);
        loading=(ProgressBar)view.findViewById(R.id.loading);
        Log.i("ADAPTER", "RecyclerView has been initialized.");
    }

    private void setupRecyclerView() {
        rv_free_device.setHasFixedSize(true);
        rv_free_device.setLayoutManager(new LinearLayoutManager(mContext));
        Log.i("ADAPTER", "RecyclerView has been setup.");
    }

    /*@Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        final int id = child.getId();
        if (id == R.id.ll_sch_layout || id == R.id.searchView) {
            super.addView(child, index, params);
        }
        else {
            //vg.addView(child, index, params);
           // relativeLayout.addView(child,index,params);
        }
    }*/
    public void setAdapter(CustomAdapter adapter,List<String>dataSet) {
       if(dataSet!=null && dataSet.size()>0){
           txt_noData.setVisibility(GONE);
           customAdapter = adapter;
           customAdapter.setFreeDeviceList(dataSet);
           rv_free_device.setAdapter(customAdapter);

       }else{
           rv_free_device.setVisibility(GONE);
           txt_noData.setVisibility(VISIBLE);
       }
        Log.i("ADAPTER", "Adapter has been set." + customAdapter.getItemCount());
    }

    /*public void setAdapter(List<String> mNameList, LinearLayoutManager linearLayoutManager) {
       if(rv_free_device!=null) {
         rv_free_device.setLayoutManager(linearLayoutManager);
           customAdapter = new CustomAdapter();
           customAdapter.setFreeDeviceList(mNameList);
           rv_free_device.setAdapter(customAdapter);
       }else{
           Log.i("TAG","IDS : "+1);
       }

    }*/

}
