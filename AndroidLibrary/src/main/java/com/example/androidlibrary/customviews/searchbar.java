package com.example.androidlibrary.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.androidlibrary.R;

public class searchbar extends LinearLayout {

    private AutoCompleteTextView editSearch;
    public searchbar(Context context) {
        super(context);
    }

    public searchbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public searchbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        editSearch=findViewById(R.id.editSearch);
    }
}
