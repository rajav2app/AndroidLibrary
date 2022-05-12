package com.example.androidlibrary;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.androidlibrary.adapter.CustomAdapter;
import com.example.androidlibrary.app.MyApplication;
import com.example.androidlibrary.customviews.vRadioButton;
import com.example.androidlibrary.customviews.vSearchView;
import com.example.androidlibrary.customviews.vTextInputLayout;
import com.example.androidlibrary.customviews.vTextInputLayoutPassword;
import com.example.androidlibrary.customviews.vTextInputLayoutSpinner;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    List<String> datasets=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //vSearchView llSearch = findViewById(R.id.llSearch);
        //SearchView searchView=findViewById(R.id.searchView);
        Button btn_submit=findViewById(R.id.btn_submit);
        Button btn_Mode=findViewById(R.id.btn_Mode);
        final SessionManager sessionManager=new SessionManager(this);

        vTextInputLayout textInputLayout=findViewById(R.id.txtInputSSID);
        textInputLayout.setHint("Hospital SSID");
        textInputLayout.setinputType(InputType.TYPE_CLASS_NUMBER);

        vTextInputLayout txtUserName=findViewById(R.id.txtUserName);
        txtUserName.setHint("User Name");


        vTextInputLayoutPassword txtinputPassword=findViewById(R.id.password);
        txtinputPassword.setHint("Wifi-Password");

        vTextInputLayoutSpinner spinner=findViewById(R.id.spinner);
        spinner.setSpinnerAdapter(datasets);
        spinner.setHint("Select Name");

        vSearchView search=findViewById(R.id.llSearch);
        search.setSpinnerAdapter(datasets);
        search.setHint("Select Name");

        final ImageButton btn_edit=findViewById(R.id.btn_edit);
        final ImageButton btn_save=findViewById(R.id.btn_save);
        textInputLayout.setenable(false);
        spinner.setenable(false);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textInputLayout.setenable(true);
                spinner.setenable(true);
                btn_edit.setVisibility(View.GONE);
                btn_save.setVisibility(View.VISIBLE);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textInputLayout.setenable(false);
                spinner.setenable(false);
                btn_edit.setVisibility(View.VISIBLE);
                btn_save.setVisibility(View.GONE);
            }
        });

        vRadioButton rb1=findViewById(R.id.rb1);
        vRadioButton rb2=findViewById(R.id.rb2);
        rb1.setBackGround(R.drawable.ic_med_route_iv);
        rb2.setBackGround(getDrawable(R.drawable.ic_med_route_im));
        if(rb1.isChecked()){
            rb1.setChecked(true);
        }else {
            rb1.setChecked(false);
        }

        if(rb2.isChecked()){
            rb2.setChecked(true);
        }else {
            rb2.setChecked(false);
        }

        addDatas();
       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
       /* llSearch.setAdapter(new CustomAdapter(new CustomAdapter.FreeDeviceListener() {
            @Override
            public void onSelectedItem(String devID) {
                Log.i("RESULT",devID);

            }
        }), datasets);*/

       /* searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        });*/
       //SearchView searchView= llSearch.findViewById(R.id.searchView);
        //String text=llSearch.getQueryText();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("RESULT",textInputLayout.getText().toString());
                Log.i("RESULT",txtinputPassword.getText().toString());

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