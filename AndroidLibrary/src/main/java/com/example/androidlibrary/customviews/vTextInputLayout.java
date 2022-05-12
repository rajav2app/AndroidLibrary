package com.example.androidlibrary.customviews;

import static com.google.android.material.textfield.TextInputLayout.END_ICON_CLEAR_TEXT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
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

public class vTextInputLayout extends LinearLayout {
    private View view;
    private TextInputLayout textInput;
    private TextInputEditText textInputEditText;
    private Context mContext;
    public vTextInputLayout(Context context) {
        super(context);

    }

    public vTextInputLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.vtextinputlayout, this,true);
        findViewsById(view);
        textInput.setEndIconMode(END_ICON_CLEAR_TEXT);
    }

    public vTextInputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
    public void setinputType(int inputType){
        textInputEditText.setInputType(inputType);
    }
    public void setenable(boolean enable){
        textInputEditText.setEnabled(enable);
        if(!enable){
            textInput.setBoxBackgroundColor(mContext.getColor(R.color.disable));
        }else{
            textInput.setBoxBackgroundColor(mContext.getColor(R.color.black));
        }
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

