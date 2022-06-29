package com.example.androidlibrary.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.androidlibrary.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class VtitanTextInputLayoutPassword extends LinearLayout {

    private TextInputLayout textInputPassword;
    private TextInputEditText etPassword;
    private Context mContext;
    private View view;

    private int textColour;
    private int backgroundColour;
    private int textSize;
    private String hint;
    private String helperText;
    private int hintTextColor;
    private int background;
    private int maxLength;
    private Drawable endIconDrawable;
    private Drawable startIconDrawable;
    private int endiconMode;
    public VtitanTextInputLayoutPassword(Context context) {
        super(context);

    }

    @SuppressLint("ResourceAsColor")
    public VtitanTextInputLayoutPassword(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.
                VtitanTextInputLayout, 0, 0);
        try {
            textColour = a.getColor(R.styleable.VtitanTextInputLayout_textColor, getResources().getColor(R.color.textViewColor));
            backgroundColour=a.getColor(R.styleable.VtitanTextInputLayout_backgroundColour, 0);
            textSize=a.getDimensionPixelSize(R.styleable.VtitanTextInputLayout_textSize,14);
            hint=a.getString(R.styleable.VtitanTextInputLayout_hint);
            helperText=a.getString(R.styleable.VtitanTextInputLayout_helperText);
            hintTextColor=a.getColor(R.styleable.VtitanTextInputLayout_hintTextColor,getResources().getColor(R.color.half_black));
            background=a.getResourceId(R.styleable.VtitanTextInputLayout_textinputbackground,0);
            maxLength=a.getDimensionPixelSize(R.styleable.VtitanTextInputLayout_maxLength,250);
            endIconDrawable=a.getDrawable(R.styleable.VtitanTextInputLayout_rightDrawable);
            startIconDrawable=a.getDrawable(R.styleable.VtitanTextInputLayout_leftDrawable);
            endiconMode=a.getInteger(R.styleable.VtitanTextInputLayout_rightIconMode,-1);
        } finally {
            a.recycle();
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.vtextinputlayoutpassword, this,true);
        findViewsById(view);

        setTextColour(textColour);
        setBackgroundColour(backgroundColour);
        setHint(hint);
        setTextSize(textSize);
        setHintTextColour(hintTextColor);
        setHelperText(helperText);
        setBackground(background);
        setMaxLength(maxLength);
        textInputPassword.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
        //setEndIconMode(endiconMode);
       // setEndIcon(endIconDrawable);
       // setStartIcon(startIconDrawable);

    }

    public VtitanTextInputLayoutPassword(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void findViewsById(View view) {
        textInputPassword = (TextInputLayout) view.findViewById(R.id.textInputPassword);
        etPassword=(TextInputEditText) view.findViewById(R.id.etPassword);
    }

    public CharSequence getText() {
        return etPassword.getText();
    }

    public void setText(CharSequence value) {
        etPassword.setText(value);
        redrawLayout();
    }


    public void setHint(CharSequence value){
        textInputPassword.setHint(value);
        redrawLayout();
    }
    public void setText(int resId) {
        etPassword.setText(resId);
        redrawLayout();
    }
    public void setHint(int resId){
        textInputPassword.setHint(resId);
        redrawLayout();
    }
    public void setInputType(int inputType){
        etPassword.setInputType(inputType);
    }
    public void setEnable(boolean enable){
        etPassword.setEnabled(enable);
        if(!enable){
            textInputPassword.setBoxBackgroundColor(mContext.getColor(R.color.disable));
            textInputPassword.setEndIconMode(TextInputLayout.END_ICON_CUSTOM);
        }else{
            textInputPassword.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
            textInputPassword.setBoxBackgroundColor(mContext.getColor(R.color.black));
        }
    }

    public void setTextColour(int resId){
        etPassword.setTextColor(resId);
    }
    public void setBackgroundColour(int color){
        textInputPassword.setBackgroundColor(color);
    }
    public void setBackground(Drawable drawable){
        textInputPassword.setBackground(drawable);
    }
    public void setBackground(int resource){
        textInputPassword.setBackgroundResource(resource);
    }
    public void setTextSize(int size){
        etPassword.setTextSize(size);
    }
    public void setHintTextColour(int hintColour){
        textInputPassword.setHintTextColor(ColorStateList.valueOf(hintColour));
    }

    public void setHelperText(CharSequence helperText){
        textInputPassword.setHelperText(helperText);
    }
    public void setMaxLength(int length){
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        etPassword.setFilters(FilterArray);
    }
    public void setEndIconMode(int endIconMode){
        textInputPassword.setEndIconMode(endIconMode);
    }
    public void setEndIcon(int resource){
        textInputPassword.setEndIconDrawable(resource);
    }
    public void setEndIcon(Drawable drawable){
        textInputPassword.setEndIconDrawable(drawable);
    }
    public void setStartIcon(int resource){
        textInputPassword.setStartIconDrawable(resource);
    }
    private void setStartIcon(Drawable drawable){
        textInputPassword.setStartIconDrawable(drawable);
    }
    public void setError(String resourse){
        etPassword.setError(resourse);
    }


    private void redrawLayout() {
        view.setDrawingCacheEnabled(true);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        //setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(getResources(), bitmap), null, null, null);
        view.setDrawingCacheEnabled(false);
    }

    public void setDigits(String digit,int inputType){
        etPassword.setKeyListener(DigitsKeyListener.getInstance(digit));
        etPassword.setRawInputType(inputType);
    }
    public void setsingleLine(boolean enable){
        etPassword.setSingleLine(enable);
    }
    public void setImeOption(int imeOption){
        etPassword.setImeOptions(imeOption);
    }

    public void setMaxLines(int line){
        etPassword.setMaxLines(line);
    }
    public void setMaxWidth(int width){
        etPassword.setMaxWidth(width);
    }
    public void setMaxHeight(int height){
        etPassword.setMaxHeight(height);
    }
    public void setAllcaps(boolean status){
        etPassword.setAllCaps(status);
    }
}

