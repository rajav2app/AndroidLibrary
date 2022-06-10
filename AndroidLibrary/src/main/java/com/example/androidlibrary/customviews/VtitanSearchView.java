package com.example.androidlibrary.customviews;

import static com.google.android.material.textfield.TextInputLayout.END_ICON_CLEAR_TEXT;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlibrary.adapter.CustomAdapter;
import com.example.androidlibrary.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class VtitanSearchView extends LinearLayout  {
    private TextInputLayout textInputSearch;
    private AutoCompleteTextView autoCompleteTextView;
    private Context mContext;
    public VtitanSearchView(Context context) {
        super(context);

    }
    public VtitanSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.vsearchview, this,true);
        findViewsById(view);
      //  setupRecyclerView();
        textInputSearch.setEndIconMode(END_ICON_CLEAR_TEXT);

    }

    private void findViewsById(View view) {
        textInputSearch = (TextInputLayout) view.findViewById(R.id.textInputSpinner);
        autoCompleteTextView=(AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView);
    }

    public void setSpinnerAdapter(List<String> itemList){
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        mContext,
                        R.layout.spinner_item,
                        itemList);

        autoCompleteTextView.setAdapter(adapter);
    }

    public CharSequence getText() {
        return autoCompleteTextView.getText();
    }

    public void setText(CharSequence value) {
        autoCompleteTextView.setText(value);
    }

    public void setHint(CharSequence value){
        textInputSearch.setHint(value);
    }

    public void setenable(boolean enable){
        autoCompleteTextView.setEnabled(enable);
        if(!enable){
            textInputSearch.setBoxBackgroundColor(mContext.getColor(R.color.disable));
        }else{
            textInputSearch.setBoxBackgroundColor(mContext.getColor(R.color.black));
        }
    }
    /*private void findViewsById(View view) {
        rv_free_device = (RecyclerView) view.findViewById(R.id.rv_free_device);
        txt_noData=(TextView)view.findViewById(R.id.txt_noData);
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
           filterNameList.addAll(dataSet);
           txt_noData.setVisibility(GONE);
           customAdapter = adapter;
           customAdapter.setFreeDeviceList(dataSet);
           rv_free_device.setAdapter(customAdapter);
           rv_free_device.setVisibility(GONE);

       }else{
           rv_free_device.setVisibility(GONE);
           txt_noData.setVisibility(VISIBLE);
       }
        Log.i("ADAPTER", "Adapter has been set." + customAdapter.getItemCount());
    }


    public void filter(String text) {
        List<String> filteredList = new ArrayList<>();
        for (String item : filterNameList) {
            if (item.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if(filteredList.size()>0 && text.length()>1){
            rv_free_device.setVisibility(VISIBLE);
            txt_noData.setVisibility(GONE);
        }else{
            rv_free_device.setVisibility(GONE);
            txt_noData.setVisibility(VISIBLE);
        }
        if(text.isEmpty()){
            txt_noData.setVisibility(GONE);
        }

        customAdapter.filterList(filteredList);
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


    };*/
}
