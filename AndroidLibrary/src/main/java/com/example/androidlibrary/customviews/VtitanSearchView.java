package com.example.androidlibrary.customviews;

import static com.google.android.material.textfield.TextInputLayout.END_ICON_CLEAR_TEXT;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputFilter;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class VtitanSearchView extends LinearLayout  {
    private TextInputLayout textInputSearch;
    private AutoCompleteTextView autoCompleteTextView;
    private Context mContext;
    private int textColour;
    private int backgroundColour;
    private int textSize;
    private String hint;
    private String helperText;
    private int hintTextColor;
    private int background;
    private int maxLength;
    private Drawable endIconDrawable;
    private Drawable startIconDrawable;
    private int endiconMode;
    public interface OnEndIconClickListener{
        void onEndIconClickListener();
    }

    public interface OnStartIconClickListener{
        void onStartIconClickListener();
    }
    private VtitanTextInputLayout.OnStartIconClickListener mOnStartIconClickListener;
    private VtitanTextInputLayout.OnEndIconClickListener mOnEndIconClickListener;

    public VtitanSearchView(Context context) {
        super(context);

    }
    public VtitanSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;

        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.
                VtitanTextInputLayout, 0, 0);
        try {
            textColour = a.getColor(R.styleable.VtitanTextInputLayout_textColor, getResources().getColor(R.color.textViewColor));
            backgroundColour=a.getColor(R.styleable.VtitanTextInputLayout_backgroundColour, 0);
            textSize=a.getDimensionPixelSize(R.styleable.VtitanTextInputLayout_textSize,16);
            hint=a.getString(R.styleable.VtitanTextInputLayout_hint);
            helperText=a.getString(R.styleable.VtitanTextInputLayout_helperText);
            hintTextColor=a.getColor(R.styleable.VtitanTextInputLayout_hintTextColor,getResources().getColor(R.color.half_black));
            background=a.getResourceId(R.styleable.VtitanTextInputLayout_textinputbackground,0);
            maxLength=a.getDimensionPixelSize(R.styleable.VtitanTextInputLayout_maxLength,250);
            endIconDrawable=a.getDrawable(R.styleable.VtitanTextInputLayout_rightDrawable);
            startIconDrawable=a.getDrawable(R.styleable.VtitanTextInputLayout_leftDrawable);
            endiconMode=a.getInteger(R.styleable.VtitanTextInputLayout_rightIconMode,-1);
        } finally {
            a.recycle();
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.vsearchview, this,true);
        findViewsById(view);
      //  setupRecyclerView();
        textInputSearch.setEndIconMode(END_ICON_CLEAR_TEXT);
        setTextColour(textColour);
        setBackgroundColour(backgroundColour);
        setHint(hint);
        setTextSize(textSize);
        setHintTextColour(hintTextColor);
        setHelperText(helperText);
        setBackground(background);
        setMaxLength(maxLength);
       //etEndIconMode(endiconMode);
       //etEndIcon(endIconDrawable);
      //setStartIcon(startIconDrawable);
/*
        if(endIconDrawable!=null) {
            textInputSearch.setEndIconOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnEndIconClickListener != null) {
                        mOnEndIconClickListener.onEndIconClickListener();
                    }
                }
            });
        }
        if(startIconDrawable!=null) {
            textInputSearch.setStartIconOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnStartIconClickListener != null) {
                        mOnStartIconClickListener.onStartIconClickListener();
                    }
                }
            });
        }*/


    }

    private void findViewsById(View view) {
        textInputSearch = (TextInputLayout) view.findViewById(R.id.textInputSpinner);
        autoCompleteTextView=(AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView);
    }

    public void setAdapter(List<String> itemList){
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        mContext,
                        R.layout.spinner_item,
                        itemList);

        autoCompleteTextView.setAdapter(adapter);
    }

    public void setenable(boolean enable){
        autoCompleteTextView.setEnabled(enable);
        if(!enable){
            textInputSearch.setBoxBackgroundColor(mContext.getColor(R.color.disable));
        }else{
            textInputSearch.setBoxBackgroundColor(mContext.getColor(R.color.black));
        }
    }

    public void setfocusable(boolean enable){
        autoCompleteTextView.setFocusable(false);
        autoCompleteTextView.setClickable(true);
    }
    public void setOnEndIconClickListener(VtitanTextInputLayout.OnEndIconClickListener onEndIconClickListener){
        mOnEndIconClickListener = onEndIconClickListener;
    }
    public void setOnStartIconClickListener(VtitanTextInputLayout.OnStartIconClickListener onStartIconClickListener){
        mOnStartIconClickListener=onStartIconClickListener;
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
    public void setText(int resId) {
        autoCompleteTextView.setText(resId);

    }
    public void setHint(int resId){
        textInputSearch.setHint(resId);
    }
    public void setInputType(int inputType){
        autoCompleteTextView.setInputType(inputType);
    }

    public void setTextColour(int resId){
        autoCompleteTextView.setTextColor(resId);
    }
    public void setBackgroundColour(int color){
        textInputSearch.setBackgroundColor(color);
    }
    public void setBackground(Drawable drawable){
        textInputSearch.setBackground(drawable);
    }
    public void setBackground(int resource){
        textInputSearch.setBackgroundResource(resource);
    }
    public void setTextSize(int size){
        autoCompleteTextView.setTextSize(size);
    }
    public void setHintTextColour(int hintColour){
        textInputSearch.setHintTextColor(ColorStateList.valueOf(hintColour));
    }

    public void setHelperText(CharSequence helperText){
        textInputSearch.setHelperText(helperText);
    }
    public void setMaxLength(int length){
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        autoCompleteTextView.setFilters(FilterArray);
    }
    public void setEndIconMode(int endIconMode){
        textInputSearch.setEndIconMode(endIconMode);
    }
    public void setEndIcon(int resource){
        textInputSearch.setEndIconDrawable(resource);
    }
    public void setEndIcon(Drawable drawable){
        textInputSearch.setEndIconDrawable(drawable);
    }
    public void setStartIcon(int resource){
        textInputSearch.setStartIconDrawable(resource);
    }
    private void setStartIcon(Drawable drawable){
        textInputSearch.setStartIconDrawable(drawable);
    }




    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putString("currentEdit", autoCompleteTextView.getText().toString());
        bundle.putBoolean("isFocused", autoCompleteTextView.hasFocus());
        return bundle;
    }
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            autoCompleteTextView.setText(bundle.getString("currentEdit"));
            if (bundle.getBoolean("isFocused")) {
                autoCompleteTextView.requestFocus();
            }
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(state);
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
