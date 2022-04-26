package com.example.androidlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.androidlibrary.customviews.VtitanRecyclerView;
import com.example.androidlibrary.customviews.VtitanSearchView;
import com.example.androidlibrary.customviews.searchbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> datasets=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchbar llSearch = findViewById(R.id.llSearch);
        addDatas();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        llSearch.setAdapter(new CustomAdapter(new CustomAdapter.FreeDeviceListener() {
            @Override
            public void onSelectedItem(String devID) {
                Log.i("RESULT",devID);
            }
        }), datasets);
    }

    public void addDatas(){
        datasets.add("Java");
        datasets.add("Kotlin");
        datasets.add("C");
        datasets.add("C++");
        datasets.add("Python");
        datasets.add("HTML");
    }
}