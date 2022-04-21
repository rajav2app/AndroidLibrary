package com.example.androidlibrary.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;

public class VtitanSearchView extends SearchView {

    public VtitanSearchView(@NonNull Context context) {
        super(context);
    }

    public VtitanSearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public VtitanSearchView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    public void setQueryHint(CharSequence hint){
        setQueryHint(hint);
    }
    public void setEndIcon(DrawableRes icon){
        setEndIcon(icon);
    }
    public void width(float width){
        width(width);
    }
}
