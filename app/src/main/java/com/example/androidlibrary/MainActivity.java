package com.example.androidlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidlibrary.app.MyApplication;
import com.example.androidlibrary.customviews.DebouncedOnClickListener;
import com.example.androidlibrary.customviews.VtitanAutoCompleteTextView;
import com.example.androidlibrary.customviews.VtitanRadioButton;
import com.example.androidlibrary.customviews.VtitanSearchView;
import com.example.androidlibrary.customviews.VtitanTextInputLayout;
import com.example.androidlibrary.customviews.VtitanTextInputLayoutPassword;
import com.example.androidlibrary.customviews.VtitanTextInputLayoutSpinner;
import com.google.android.material.textfield.TextInputEditText;

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
        addDatas();
        VtitanTextInputLayout textInputLayout=findViewById(R.id.txtInputSSID);
        textInputLayout.setHint("Hospital SSID");
        textInputLayout.setInputType(InputType.TYPE_CLASS_TEXT);

        VtitanTextInputLayout txtUserName=findViewById(R.id.txtUserName);
        txtUserName.setHint("User Name");
        //txtUserName.setFilter(10);

        //txtUserName.setEndIcon(getDrawable(R.drawable.ic_search));
        //txtUserName.setMaxLength(10);
        VtitanTextInputLayoutPassword txtinputPassword=findViewById(R.id.password);
        txtinputPassword.setHint("Wifi-Password");

        //txtinputPassword.setEnable(true);
       // txtinputPassword.setEnable(false);

        VtitanTextInputLayoutSpinner spinner=findViewById(R.id.spinner);
        spinner.setSpinnerAdapter(datasets);
        spinner.setHint("Select Name");
        spinner.setSelection(0);
        spinner.setOnItemClickListener(new VtitanTextInputLayoutSpinner.OnItemClickListener() {
            @Override
            public void onItemClicked(AdapterView<?> adapterView, View view, int i, long l) {
               // Log.i("Clicked_Item",""+adapterView.getItemAtPosition(i));
            }
        });

        TextInputEditText textInputEditText=findViewById(R.id.edtext);

        VtitanSearchView search=findViewById(R.id.llSearch);
        search.setAdapter(datasets);
        search.setHint("Select Name");
        //search.setEndIcon(R.drawable.ic_search);
        //search.setEndIconMode(2);
        VtitanAutoCompleteTextView autoCompleteTextView=findViewById(R.id.autoCompleteTextview);
        autoCompleteTextView.setAdapter(datasets);
        autoCompleteTextView.setHint(getString(R.string.device_name));
        autoCompleteTextView.setDigits("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        autoCompleteTextView.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        autoCompleteTextView.setOnEditTextChangeListener(new VtitanAutoCompleteTextView.OnTextChangeListener() {
            @Override
            public void vbeforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void vonTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void vafterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                   txtUserName.setText(s.toString());
                }
                //txtUserName.setText(s.toString());}
               // Log.i("TextListener_test",s.toString().trim());
            }
        });
        //autoCompleteTextView.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        autoCompleteTextView.setImeOption(EditorInfo.IME_ACTION_SEARCH);
       /* autoCompleteTextView.setOnItemClickListener(new VtitanAutoCompleteTextView.OnItemClickListener() {
            @Override
            public void onItemClicked(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("OnItemCLICK",""+adapterView.getItemAtPosition(i));
            }
        });*/

     /* autoCompleteTextView.setOnFocusChangeListener(new VtitanAutoCompleteTextView.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    //txtUserName.setText("Raja");
                }
              //  Log.i("FOCUS","CHANGED");
            }
        });*/

        autoCompleteTextView.setOnEditorActionListener(new VtitanAutoCompleteTextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    if (textView.getText().toString().trim().length() > 0) {
                        Log.i("EDITER_ACTION_LISTENER", textView.getText().toString().trim());
                    }
                }
                return false;
            }
        });

        autoCompleteTextView.setFilter(10);
       // autoCompleteTextView.setMaxLength(10);
        //autoCompleteTextView.setThreshHold(3);

        // autoCompleteTextView.addTextChangeListerner();
        final ImageButton btn_edit=findViewById(R.id.btn_edit);
        final ImageButton btn_save=findViewById(R.id.btn_save);
        textInputLayout.setEnable(false);
        //spinner.setenable(true);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textInputLayout.setEnable(true);
                //spinner.setenable(true);
                btn_edit.setVisibility(View.GONE);
                btn_save.setVisibility(View.VISIBLE);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textInputLayout.setEnable(false);
               // spinner.setenable(false);
                btn_edit.setVisibility(View.VISIBLE);
                btn_save.setVisibility(View.GONE);
            }
        });


/*

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
*/


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

        btn_submit.setOnClickListener(new DebouncedOnClickListener(DebouncedOnClickListener.CLICK_INT) {
            @Override
            public void onDebouncedClick(View v) {
                Log.i("RESULT",textInputLayout.getText().toString());
                Log.i("RESULT",txtinputPassword.getText().toString());
            }
        });

        /*btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("RESULT",textInputLayout.getText().toString());
                Log.i("RESULT",txtinputPassword.getText().toString());

            }
        });*/

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