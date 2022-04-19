package com.example.androidlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_date=findViewById(R.id.tv_date);
        String str=BasicFunctions.secondsToDateTime(System.currentTimeMillis());
        tv_date.setText(str);
    }
}