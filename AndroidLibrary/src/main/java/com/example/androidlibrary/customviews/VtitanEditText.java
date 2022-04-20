package com.example.androidlibrary.customviews;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

public class VtitanEditText extends androidx.appcompat.widget.AppCompatEditText {
    public VtitanEditText(Context context) {
        super(context);
    }

    public VtitanEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(textWatcher);
    }

    public VtitanEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

}
