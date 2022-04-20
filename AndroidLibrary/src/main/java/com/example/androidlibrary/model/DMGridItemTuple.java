package com.example.androidlibrary.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DMGridItemTuple {
    public String devID;
    public String id;
    public String name;
    public int status;
    public int alarmId;
    public int flowRate;
    public String ward;
    public String carearea;

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this == obj){
            return true;
        }else if(obj==null){
            return false;
        }else if (this.getClass() != obj.getClass()) {
            return false;
        }
        DMGridItemTuple that = (DMGridItemTuple) obj;
        return (this.devID.equals(that.devID)) && (this.name.equals(that.name))  && (this.id.equals(that.id))&& (this.ward.equals(that.ward))
                && (this.status == that.status) && (this.alarmId == that.alarmId)
                && (this.flowRate == that.flowRate) && (this.carearea.equals(that.carearea));
    }
}
