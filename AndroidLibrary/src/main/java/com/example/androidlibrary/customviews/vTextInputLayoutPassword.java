package com.example.androidlibrary.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.androidlibrary.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class vTextInputLayoutPassword extends LinearLayout {

    private TextInputLayout textInputPassword;
    private TextInputEditText etPassword;
    private Context mContext;
    private View view;
    public vTextInputLayoutPassword(Context context) {
        super(context);

    }

    public vTextInputLayoutPassword(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.vtextinputlayoutpassword, this,true);
        findViewsById(view);

    }

    public vTextInputLayoutPassword(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    }

    public void setHint(CharSequence value){
        textInputPassword.setHint(value);
    }

    public void setenable(boolean enable){
        etPassword.setEnabled(enable);
        if(!enable){
            textInputPassword.setBoxBackgroundColor(mContext.getColor(R.color.disable));
        }

    }

    public void setText(int resId) {
        etPassword.setText(resId);
        redrawLayout();
    }
    public void setHint(int resId){
        textInputPassword.setHint(resId);
        redrawLayout();
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
}

