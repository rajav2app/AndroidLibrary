package com.example.androidlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.androidlibrary.app.MyApplication;
import com.example.androidlibrary.customviews.VtitanRadioButton;
import com.example.androidlibrary.customviews.VtitanSearchView;
import com.example.androidlibrary.customviews.VtitanTextInputLayout;
import com.example.androidlibrary.customviews.VtitanTextInputLayoutPassword;
import com.example.androidlibrary.customviews.VtitanTextInputLayoutSpinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements VtitanTextInputLayout.OnEndIconClickListener, VtitanTextInputLayout.OnStartIconClickListener {
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

        VtitanTextInputLayout textInputLayout=findViewById(R.id.txtInputSSID);
        textInputLayout.setHint("Hospital SSID");
        textInputLayout.setInputType(InputType.TYPE_CLASS_TEXT);

        VtitanTextInputLayout txtUserName=findViewById(R.id.txtUserName);
        txtUserName.setHint("User Name");
        //txtUserName.setEndIcon(getDrawable(R.drawable.ic_search));
        //txtUserName.setMaxLength(10);
        txtUserName.setOnEndIconClickListener(this::onEndIconClickListener);
        txtUserName.setOnStartIconClickListener(this::onStartIconClickListener);
        VtitanTextInputLayoutPassword txtinputPassword=findViewById(R.id.password);
        txtinputPassword.setHint("Wifi-Password");

        //txtinputPassword.setEnable(true);
       // txtinputPassword.setEnable(false);

        VtitanTextInputLayoutSpinner spinner=findViewById(R.id.spinner);
        spinner.setSpinnerAdapter(datasets);
        spinner.setHint("Select Name");

        VtitanSearchView search=findViewById(R.id.llSearch);
        search.setSpinnerAdapter(datasets);
        search.setHint("Select Name");

        final ImageButton btn_edit=findViewById(R.id.btn_edit);
        final ImageButton btn_save=findViewById(R.id.btn_save);
        textInputLayout.setEnable(false);
        spinner.setenable(false);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textInputLayout.setEnable(true);
                spinner.setenable(true);
                btn_edit.setVisibility(View.GONE);
                btn_save.setVisibility(View.VISIBLE);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textInputLayout.setEnable(false);
                spinner.setenable(false);
                btn_edit.setVisibility(View.VISIBLE);
                btn_save.setVisibility(View.GONE);
            }
        });

        VtitanRadioButton rb1=findViewById(R.id.rb1);
        VtitanRadioButton rb2=findViewById(R.id.rb2);
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

    @Override
    public void onEndIconClickListener() {
        Log.i("RESULT","EndIconClicked");
    }

    @Override
    public void onStartIconClickListener() {

    }
}