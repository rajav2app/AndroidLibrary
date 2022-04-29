package com.example.androidlibrary;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.androidlibrary.adapter.CustomAdapter;
import com.example.androidlibrary.customviews.vSearchView;
import com.example.androidlibrary.customviews.vTextInputLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> datasets=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vSearchView llSearch = findViewById(R.id.llSearch);
        SearchView searchView=findViewById(R.id.searchView);
        Button btn_submit=findViewById(R.id.btn_submit);

        vTextInputLayout textInputLayout=findViewById(R.id.txtinput);
        TextInputEditText editSSID=textInputLayout.findViewById(R.id.textInputEditText);
        TextInputLayout textInputLayoutSSID=textInputLayout.findViewById(R.id.textInput);
        textInputLayoutSSID.setHint("Hospital SSID");

        vTextInputLayout txtinputPassword=findViewById(R.id.txtinputPassword);
        TextInputEditText editPassword=txtinputPassword.findViewById(R.id.textInputEditText);
        TextInputLayout textInputLayoutPassword=txtinputPassword.findViewById(R.id.textInput);
        textInputLayoutPassword.setHint("Wifi-Password");

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

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("RESULT",editSSID.getText().toString());
                Log.i("RESULT",editPassword.getText().toString());

            }
        });

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