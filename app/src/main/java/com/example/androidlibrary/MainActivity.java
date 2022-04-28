package com.example.androidlibrary;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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
        SearchView searchView=findViewById(R.id.searchView);
        addDatas();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        llSearch.setAdapter(new CustomAdapter(new CustomAdapter.FreeDeviceListener() {
            @Override
            public void onSelectedItem(String devID) {
                Log.i("RESULT",devID);
            }
        }), datasets);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.i(TAG,s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
               // Log.i(TAG,"ONCHANGES"+s);
                llSearch.filter(s);
                return false;
            }
        });
       //SearchView searchView= llSearch.findViewById(R.id.searchView);
        //String text=llSearch.getQueryText();

    }


    public void addDatas(){
        datasets.add("Jayamurugan");
        datasets.add("Udayamoorthy");
        datasets.add("Pravin");
        datasets.add("Babji");
        datasets.add("Rajkumar");
        datasets.add("Jeyachitra");
        datasets.add("Sudharshan");
        datasets.add("yugeshwaran");
        datasets.add("Muthukumar");
        datasets.add("Subarayan");
    }
}