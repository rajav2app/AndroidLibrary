package com.example.androidlibrary.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidlibrary.R;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class vRadioButton extends LinearLayout {

    private View view;
    private RadioButton radioButton;
    private Context mContext;

    public interface OnCheckedChangeListener{
        void onCheckedChangeListener(boolean ischecked);
    }
    private OnCheckedChangeListener mOnCheckedChangeListener;
    public vRadioButton(Context context) {
        super(context);
    }

    public vRadioButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.vradiobutton, this,true);
        findViewsById(view);
      radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(mOnCheckedChangeListener != null){
                    mOnCheckedChangeListener.onCheckedChangeListener(ischecked);
                }
            }
        });

    }

    public vRadioButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void findViewsById(View view) {
        radioButton = (RadioButton) view.findViewById(R.id.rb_med_route);
    }


    public void setBackGround(Drawable drawable){
        radioButton.setBackground(drawable);
    }

    public void setBackGround(int resId){
        radioButton.setBackgroundResource(resId);
    }

    public void setText(CharSequence value) {
        radioButton.setText(value);
    }

    public void setText(int resId) {
        radioButton.setText(resId);

    }
    public void setGravity(int gravity){
        radioButton.setGravity(gravity);
    }

    public void setChecked(boolean ischecked){
        radioButton.setChecked(ischecked);
    }

    public boolean isChecked(){
        if(radioButton.isChecked()){
            return true;
        }else{
            return false;
        }
    }

    public void setmOnCheckedChangeListener(OnCheckedChangeListener onLoadingClickListener){
        mOnCheckedChangeListener = onLoadingClickListener;
    }

}
