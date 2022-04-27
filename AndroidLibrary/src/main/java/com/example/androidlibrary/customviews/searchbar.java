package com.example.androidlibrary.customviews;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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
     private SearchView searchView;
    String querytext="";

    public searchbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
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
        searchView=(SearchView)view.findViewById(R.id.searchView);
        Log.i("ADAPTER", "RecyclerView has been initialized.");
    }

    private void setupRecyclerView() {
        rv_free_device.setHasFixedSize(true);
        rv_free_device.setLayoutManager(new LinearLayoutManager(mContext));
        Log.i("ADAPTER", "RecyclerView has been setup.");
    }

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


    SearchView.OnQueryTextListener listener=new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String query) {
            // collapse the view ?
            //menu.findItem(R.id.menu_search).collapseActionView();
            Log.e("queryText",query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            // search goes here !!
            // listAdapter.getFilter().filter(query);
            Log.e("queryText",newText);
            return false;
        }


    };
}
