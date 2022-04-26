package com.example.androidlibrary.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.TintTypedArray;

import com.example.androidlibrary.R;

public class VtitanSearchView extends SearchView {

    public VtitanSearchView(@NonNull Context context) {
        super(context);

    }

    public VtitanSearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackground(context.getDrawable(R.drawable.search_bg));
        setQueryHint("Search");
         LayoutParams layout = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setMargins(10, 10, 10, 10);
        setLayoutParams(layout);
        //LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
        //setLayoutParams(layoutParams);

      /*  MarginLayoutParams margins = MarginLayoutParams.class.cast(getLayoutParams());
        int margin = 15;
        margins.topMargin = margin;
        margins.bottomMargin = margin;
        margins.leftMargin = margin;
        margins.rightMargin = margin;
        setLayoutParams(margins);*/
    }

    public VtitanSearchView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }




}
