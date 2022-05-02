package com.example.androidlibrary;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.androidlibrary.adapter.CustomAdapter;
import com.example.androidlibrary.app.MyApplication;
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
        Button btn_Mode=findViewById(R.id.btn_Mode);
        final SessionManager sessionManager=new SessionManager(this);
        vTextInputLayout textInputLayout=findViewById(R.id.txtInputSSID);
        TextInputEditText editSSID=textInputLayout.findViewById(R.id.textInputEditText);
        TextInputLayout textInputLayoutSSID=textInputLayout.findViewById(R.id.textInput);
        textInputLayoutSSID.setHint("Hospital SSID");

        vTextInputLayout txtinputPassword=findViewById(R.id.txtInputPassword);
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

        btn_Mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sessionManager.isLightModeOn()) {
                    sessionManager.savelightmode(false);
                    MyApplication.setDarkLightTheme(0);
                    overridePendingTransition(0, 0);
                    //img_btn_dark_light.setImageDrawable(getResources().getDrawable(R.drawable.ic_light_mode));
                } else {
                    sessionManager.savelightmode(true);
                    MyApplication.setDarkLightTheme(1);
                    overridePendingTransition(0, 0);
                   // img_btn_dark_light.setImageDrawable(getResources().getDrawable(R.drawable.ic_night_light));
                }
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