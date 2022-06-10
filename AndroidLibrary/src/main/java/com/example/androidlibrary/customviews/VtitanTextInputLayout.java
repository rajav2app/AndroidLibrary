package com.example.androidlibrary.customviews;

import static com.google.android.material.textfield.TextInputLayout.END_ICON_CLEAR_TEXT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlibrary.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class VtitanTextInputLayout extends LinearLayout {
    private View view;
    private TextInputLayout textInput;
    private TextInputEditText textInputEditText;
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
    private OnStartIconClickListener mOnStartIconClickListener;
    private OnEndIconClickListener mOnEndIconClickListener;

    public VtitanTextInputLayout(Context context) {
        super(context);
    }

    public VtitanTextInputLayout(Context context, @Nullable AttributeSet attrs) {
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
        view = inflater.inflate(R.layout.vtextinputlayout, this,true);
        findViewsById(view);
        setTextColour(textColour);
        setBackgroundColour(backgroundColour);
        setHint(hint);
        setTextSize(textSize);
        setHintTextColour(hintTextColor);
        setHelperText(helperText);
        setBackground(background);
        setMaxLength(maxLength);
        setEndIconMode(endiconMode);
        setEndIcon(endIconDrawable);
        setStartIcon(startIconDrawable);

       if(endIconDrawable!=null) {
            textInput.setEndIconOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnEndIconClickListener != null) {
                        mOnEndIconClickListener.onEndIconClickListener();
                    }
                }
            });
        }
        if(startIconDrawable!=null) {
            textInput.setStartIconOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnStartIconClickListener != null) {
                        mOnStartIconClickListener.onStartIconClickListener();
                    }
                }
            });
        }

    }
    public VtitanTextInputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnEndIconClickListener(OnEndIconClickListener onEndIconClickListener){
        mOnEndIconClickListener = onEndIconClickListener;
    }
    public void setOnStartIconClickListener(OnStartIconClickListener onStartIconClickListener){
        mOnStartIconClickListener=onStartIconClickListener;
    }
    private void findViewsById(View view) {
        textInput = (TextInputLayout) view.findViewById(R.id.textInput);
        textInputEditText=(TextInputEditText) view.findViewById(R.id.textInputEditText);
    }
    public CharSequence getText() {
        return textInputEditText.getText();
    }

    public void setText(CharSequence value) {
        textInputEditText.setText(value);
        redrawLayout();
    }


    public void setHint(CharSequence value){
        textInput.setHint(value);
        redrawLayout();
    }
    public void setText(int resId) {
        textInputEditText.setText(resId);
        redrawLayout();
    }
    public void setHint(int resId){
        textInput.setHint(resId);
        redrawLayout();
    }
    public void setInputType(int inputType){
        textInputEditText.setInputType(inputType);
    }
    public void setEnable(boolean enable){
        textInputEditText.setEnabled(enable);
        if(!enable){
            textInput.setStartIconOnClickListener(null);
            textInput.setBoxBackgroundColor(mContext.getColor(R.color.disable));
        }else{
            textInput.setBoxBackgroundColor(mContext.getColor(R.color.black));
        }
    }

    public void setTextColour(int resId){
        textInputEditText.setTextColor(resId);
    }
    public void setBackgroundColour(int color){
        textInput.setBackgroundColor(color);
    }
    public void setBackground(Drawable drawable){
        textInput.setBackground(drawable);
    }
    public void setBackground(int resource){
        textInput.setBackgroundResource(resource);
    }
    public void setTextSize(int size){
        textInputEditText.setTextSize(size);
    }
    public void setHintTextColour(int hintColour){
        textInput.setHintTextColor(ColorStateList.valueOf(hintColour));
    }

    public void setHelperText(CharSequence helperText){
        textInput.setHelperText(helperText);
    }
    public void setMaxLength(int length){
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        textInputEditText.setFilters(FilterArray);
    }
    public void setEndIconMode(int endIconMode){
        textInput.setEndIconMode(endIconMode);
    }
    public void setEndIcon(int resource){
        textInput.setEndIconDrawable(resource);
    }
    public void setEndIcon(Drawable drawable){
        textInput.setEndIconDrawable(drawable);
    }
    public void setStartIcon(int resource){
        textInput.setStartIconDrawable(resource);
    }
    private void setStartIcon(Drawable drawable){
        textInput.setStartIconDrawable(drawable);
    }
    private void redrawLayout() {
        view.setDrawingCacheEnabled(true);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        //setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(getResources(), bitmap), null, null, null);
        view.setDrawingCacheEnabled(false);
    }



    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putString("currentEdit", textInputEditText.getText().toString());
        bundle.putBoolean("isFocused", textInputEditText.hasFocus());
        return bundle;
    }
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            textInputEditText.setText(bundle.getString("currentEdit"));
            if (bundle.getBoolean("isFocused")) {
                textInputEditText.requestFocus();
            }
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(state);
    }

}

