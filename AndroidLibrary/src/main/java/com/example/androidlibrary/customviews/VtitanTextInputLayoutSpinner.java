package com.example.androidlibrary.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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
    public VtitanTextInputLayoutSpinner(Context context) {
        super(context);

    }

    public VtitanTextInputLayoutSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.vtextinputlayoutspinner, this,true);
        findViewsById(view);

    }

    public VtitanTextInputLayoutSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void findViewsById(View view) {
        textInputSpinner = (TextInputLayout) view.findViewById(R.id.textInputSpinner);
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
        textInputSpinner.setHint(value);
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

}
