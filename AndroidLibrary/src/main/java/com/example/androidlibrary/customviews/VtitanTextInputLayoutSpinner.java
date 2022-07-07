package com.example.androidlibrary.customviews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.androidlibrary.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class VtitanTextInputLayoutSpinner extends LinearLayout {
    private TextInputLayout textInputSpinner;
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
    private ArrayAdapter<String> adapter;
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onItemClicked(AdapterView<?> adapterView, View view, int i, long l);
    }
    public VtitanTextInputLayoutSpinner(Context context) {
        super(context);

    }

    public VtitanTextInputLayoutSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;

        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.
                VtitanTextInputLayout, 0, 0);
        try {
            textColour = a.getColor(R.styleable.VtitanTextInputLayout_textColor, getResources().getColor(R.color.textViewColor));
            backgroundColour=a.getColor(R.styleable.VtitanTextInputLayout_backgroundColour, 0);
            textSize=a.getDimensionPixelSize(R.styleable.VtitanTextInputLayout_textSize,14);
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
        View view = inflater.inflate(R.layout.vtextinputlayoutspinner, this,true);
        findViewsById(view);
        setTextColour(textColour);
        setBackgroundColour(backgroundColour);
        setHint(hint);
        setTextSize(textSize);
        setHintTextColour(hintTextColor);
        setHelperText(helperText);
        setBackground(background);
        setMaxLength(maxLength);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(mOnItemClickListener!=null) {
                    mOnItemClickListener.onItemClicked(adapterView, view, i, l);
                   // Log.i("TAG",""+i);
                }
            }
        });
    }

    public VtitanTextInputLayoutSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void findViewsById(View view) {
        textInputSpinner = (TextInputLayout) view.findViewById(R.id.textInputSpinner);
        autoCompleteTextView=(AutoCompleteTextView) view.findViewById(R.id.sAutoCompleteTextView);
    }

    public void setSpinnerAdapter(List<String> itemList){
       // Log.i("SPINNERLIST",itemList.toString());
         adapter = new ArrayAdapter<>(
                        mContext,
                        R.layout.spinner_item_new,
                        itemList);

        autoCompleteTextView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setenable(boolean enable){
        autoCompleteTextView.setEnabled(enable);
        if(!enable){
            textInputSpinner.setEndIconOnClickListener(null);
            textInputSpinner.setBoxBackgroundColor(mContext.getColor(R.color.disable));
        }else{
            textInputSpinner.setBoxBackgroundColor(mContext.getColor(R.color.black));
        }
    }

    public CharSequence getText() {
        return autoCompleteTextView.getText();
    }

    public void setText(CharSequence value) {
        autoCompleteTextView.setText(value);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener=onItemClickListener;
    }
    public void setHint(CharSequence value){
        textInputSpinner.setHint(value);

    }
    public void setText(int resId) {
        autoCompleteTextView.setText(resId);

    }
    public void setHint(int resId){
        textInputSpinner.setHint(resId);

    }
    public void setInputType(int inputType){
        autoCompleteTextView.setInputType(inputType);
    }
    public void setEnable(boolean enable){
        autoCompleteTextView.setEnabled(enable);
        if(!enable){
            textInputSpinner.setStartIconOnClickListener(null);
            textInputSpinner.setBoxBackgroundColor(mContext.getColor(R.color.disable));
        }else{
            textInputSpinner.setBoxBackgroundColor(mContext.getColor(R.color.black));
        }
    }

    public void setTextColour(int resId){
        autoCompleteTextView.setTextColor(resId);
    }
    public void setBackgroundColour(int color){
        textInputSpinner.setBackgroundColor(color);
    }
    public void setBackground(Drawable drawable){
        textInputSpinner.setBackground(drawable);
    }
    public void setBackground(int resource){
        textInputSpinner.setBackgroundResource(resource);
    }
    public void setTextSize(int size){
        autoCompleteTextView.setTextSize(size);
    }
    public void setHintTextColour(int hintColour){
        textInputSpinner.setHintTextColor(ColorStateList.valueOf(hintColour));
    }

    public void setHelperText(CharSequence helperText){
        textInputSpinner.setHelperText(helperText);
    }
    public void setMaxLength(int length){
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        autoCompleteTextView.setFilters(FilterArray);
    }
    public void setEndIconMode(int endIconMode){
        textInputSpinner.setEndIconMode(endIconMode);
    }
    public void setEndIcon(int resource){
        textInputSpinner.setEndIconDrawable(resource);
    }
    public void setEndIcon(Drawable drawable){
        textInputSpinner.setEndIconDrawable(drawable);
    }
    public void setStartIcon(int resource){
        textInputSpinner.setStartIconDrawable(resource);
    }
    public void setStartIcon(Drawable drawable){
        textInputSpinner.setStartIconDrawable(drawable);
    }
    public void setSelection(int position){
        autoCompleteTextView.setText(adapter.getItem(position),false);
    }

    public void setImeOption(int imeOption){
        autoCompleteTextView.setImeOptions(imeOption);
    }

}
