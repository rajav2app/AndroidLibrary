package com.example.androidlibrary.customviews;

import static com.google.android.material.textfield.TextInputLayout.END_ICON_CLEAR_TEXT;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.androidlibrary.R;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class VtitanAutoCompleteTextView extends LinearLayout {

    private TextInputLayout textInputSearch;
    private AutoCompleteTextView autoCompleteTextView;
    private Context mContext;
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
    private ArrayAdapter<String> adapter;
    public interface OnEndIconClickListener{
        void onEndIconClickListener();
    }

    public interface OnStartIconClickListener{
        void onStartIconClickListener();
    }
    public interface OnItemClickListener{
        void onItemClicked(AdapterView<?> adapterView, View view, int i, long l);
    }
    public interface OnTextChangeListener{
        void vbeforeTextChanged(CharSequence s, int start, int count, int after);
        void vonTextChanged(CharSequence s, int start, int before, int count);
        void vafterTextChanged(Editable s);
     }
     public interface OnFocusChangeListener{
        void onFocusChange(View v, boolean hasFocus);
     }
     public interface OnEditorActionListener{
        boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent);
     }

    private OnStartIconClickListener mOnStartIconClickListener;
    private OnEndIconClickListener mOnEndIconClickListener;
    private OnItemClickListener mOnItemClickListener;
    private OnTextChangeListener mOnTextChangeListener;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnEditorActionListener mOnEditorActionListener;
    public VtitanAutoCompleteTextView(Context context) {
        super(context);
    }

    public VtitanAutoCompleteTextView(Context context, @Nullable AttributeSet attrs) {
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
        View view = inflater.inflate(R.layout.vautocompletetextview, this,true);
        findViewsById(view);
        setTextColour(textColour);
        setBackgroundColour(backgroundColour);
        setHint(hint);
        setTextSize(textSize);
        setHintTextColour(hintTextColor);
        setHelperText(helperText);
        setBackground(background);
        setMaxLength(maxLength);
        setEndIconMode(endiconMode);
        setEndIcon(endIconDrawable);
        setStartIcon(startIconDrawable);

        if(endIconDrawable!=null) {
            textInputSearch.setEndIconOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnEndIconClickListener != null) {
                        mOnEndIconClickListener.onEndIconClickListener();
                    }
                }
            });
        }
        if(startIconDrawable!=null) {
            textInputSearch.setStartIconOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnStartIconClickListener != null) {
                        mOnStartIconClickListener.onStartIconClickListener();
                    }
                }
            });
        }

             autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(mOnItemClickListener!=null) {
                        mOnItemClickListener.onItemClicked(adapterView, view, i, l);
                    }
                 }
             });


             autoCompleteTextView.addTextChangedListener(new TextWatcher() {
                 @Override
                 public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                     if(mOnTextChangeListener!=null) {
                         mOnTextChangeListener.vbeforeTextChanged(charSequence, i, i1, i2);
                     }
                 }

                 @Override
                 public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                     if(mOnTextChangeListener!=null) {
                         mOnTextChangeListener.vonTextChanged(charSequence, i, i1, i2);
                     }
                 }

                 @Override
                 public void afterTextChanged(Editable editable) {
                     if(mOnTextChangeListener!=null) {
                         mOnTextChangeListener.vafterTextChanged(editable);
                     }

                 }
             });


             autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                 @Override
                 public void onFocusChange(View view, boolean b) {
                     if(mOnFocusChangeListener!=null){
                         mOnFocusChangeListener.onFocusChange(view, b);
                     }
                 }
             });

        autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
               if(mOnEditorActionListener!=null){
                   mOnEditorActionListener.onEditorAction(textView,i,keyEvent);
               }
                return false;
            }
        });


    }

    public VtitanAutoCompleteTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnEditTextChangeListener(OnTextChangeListener textChangeListener){
        this.mOnTextChangeListener = textChangeListener;
    }

    public void setOnFocusChangeListener(OnFocusChangeListener focusChangeListener){
        this.mOnFocusChangeListener=focusChangeListener;
    }

    public void setOnEditorActionListener(OnEditorActionListener onEditorActionListener){
        this.mOnEditorActionListener=onEditorActionListener;
    }

    public void setOnEndIconClickListener(OnEndIconClickListener onEndIconClickListener){
        this.mOnEndIconClickListener = onEndIconClickListener;
    }
    public void setOnStartIconClickListener(OnStartIconClickListener onStartIconClickListener){
        this.mOnStartIconClickListener=onStartIconClickListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener=onItemClickListener;
    }


    private void findViewsById(View view) {
        textInputSearch = (TextInputLayout) view.findViewById(R.id.textInputAutoCompleteTextView);
        autoCompleteTextView=(AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView);
    }

    public void setAdapter(List<String> itemList){
         adapter =
                new ArrayAdapter<>(
                        mContext,
                        R.layout.spinner_item,
                        itemList);

        autoCompleteTextView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public void setFilter(int length){
        autoCompleteTextView.setFilters(new InputFilter[] {new InputFilter.LengthFilter(length),new InputFilter.AllCaps()});
    }

    public void setThreshHold(int threshold){
        autoCompleteTextView.setThreshold(threshold);
    }

    public void setenable(boolean enable){
        autoCompleteTextView.setEnabled(enable);
        if(!enable){
            textInputSearch.setBoxBackgroundColor(mContext.getColor(R.color.disable));
        }else{
            textInputSearch.setBoxBackgroundColor(mContext.getColor(R.color.black));
        }
    }

    public CharSequence getText() {
        return autoCompleteTextView.getText();
    }

    public void setText(CharSequence value) {
        autoCompleteTextView.setText(value);

    }
    public void setError(String resourse){
        autoCompleteTextView.setError(resourse);
    }
    public void clearText(){
        autoCompleteTextView.getText().clear();
    }

    public void setSelection(int position){
        autoCompleteTextView.setSelection(position);
    }

    public void setHint(CharSequence value){
        textInputSearch.setHint(value);
    }
    public void setText(int resId) {
        autoCompleteTextView.setText(resId);
    }
    public void setHint(int resId){
        textInputSearch.setHint(resId);

    }

    public void setTextColour(int resId){
        autoCompleteTextView.setTextColor(resId);
    }
    public void setBackgroundColour(int color){
        textInputSearch.setBackgroundColor(color);
    }
    public void setBackground(Drawable drawable){
        textInputSearch.setBackground(drawable);
    }
    public void setBackground(int resource){
        textInputSearch.setBackgroundResource(resource);
    }
    public void setTextSize(int size){
        autoCompleteTextView.setTextSize(size);
    }
    public void setHintTextColour(int hintColour){
        textInputSearch.setHintTextColor(ColorStateList.valueOf(hintColour));
    }

    public void setHelperText(CharSequence helperText){
        textInputSearch.setHelperText(helperText);
    }
   public void setMaxLength(int length){
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        autoCompleteTextView.setFilters(FilterArray);
    }
    public void setEndIconMode(int endIconMode){
        textInputSearch.setEndIconMode(endIconMode);
    }
    public void setEndIcon(int resource){
        textInputSearch.setEndIconDrawable(resource);
    }
    public void setEndIcon(Drawable drawable){
        textInputSearch.setEndIconDrawable(drawable);
    }
    public void setStartIcon(int resource){
        textInputSearch.setStartIconDrawable(resource);
    }
    public void setStartIcon(Drawable drawable){
        textInputSearch.setStartIconDrawable(drawable);
    }
    public void setImeOption(int imeOption){
        autoCompleteTextView.setImeOptions(imeOption);
    }

    public void setInputType(int inputType){
        autoCompleteTextView.setInputType(inputType);
    }

    public void setDigits(String digit,int inputType){
        autoCompleteTextView.setKeyListener(DigitsKeyListener.getInstance(digit));
        autoCompleteTextView.setRawInputType(inputType);
    }
    public void setsingleLine(boolean enable){
        autoCompleteTextView.setSingleLine(enable);
    }
    public void setMaxLines(int line){
        autoCompleteTextView.setMaxLines(line);
    }
    public void setMaxWidth(int width){
        autoCompleteTextView.setMaxWidth(width);
    }
    public void setMaxHeight(int height){
        autoCompleteTextView.setMaxHeight(height);
    }
    public void setAllcaps(boolean status){
        autoCompleteTextView.setAllCaps(status);
    }
    /*@Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putString("currentEdit", autoCompleteTextView.getText().toString());
        bundle.putBoolean("isFocused", autoCompleteTextView.hasFocus());
        return bundle;
    }
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            autoCompleteTextView.setText(bundle.getString("currentEdit"));
            if (bundle.getBoolean("isFocused")) {
                autoCompleteTextView.requestFocus();
            }
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(state);
    }*/
}
