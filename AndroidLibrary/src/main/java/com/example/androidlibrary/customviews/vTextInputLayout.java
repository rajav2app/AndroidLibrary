package com.example.androidlibrary.customviews;

import android.content.Context;
import android.text.Editable;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class vTextInputLayout extends LinearLayout {

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
        View view = inflater.inflate(R.layout.vtextinputlayout, this,true);
        findViewsById(view);

    }

    public vTextInputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void findViewsById(View view) {
        textInput = (TextInputLayout) view.findViewById(R.id.textInput);
        textInputEditText=(TextInputEditText) view.findViewById(R.id.textInputEditText);
    }

    @Override
    protected void onDisplayHint(int hint) {
        super.onDisplayHint(hint);
    }
}

