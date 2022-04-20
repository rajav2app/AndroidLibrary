package com.example.androidlibrary.customviews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlibrary.CustomAdapter;

import java.util.List;

public class VtitanRecyclerView extends RecyclerView {
    public VtitanRecyclerView(@NonNull Context context) {
        super(context);
    }

    public VtitanRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VtitanRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter( List<String> mNameList, RecyclerView.LayoutManager linearLayoutManager) {
           setLayoutManager(linearLayoutManager);
            CustomAdapter customAdapter = new CustomAdapter(mNameList);
            setAdapter(customAdapter);
    }
}
