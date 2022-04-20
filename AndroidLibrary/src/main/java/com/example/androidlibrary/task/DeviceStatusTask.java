package com.example.androidlibrary.task;

import static com.example.androidlibrary.util.AppConstants.ACTIVE;
import static com.example.androidlibrary.util.AppConstants.CONNECTED;
import static com.example.androidlibrary.util.AppConstants.NOALARM;
import static com.example.androidlibrary.util.AppConstants.NOTCONNECTED;

import android.os.AsyncTask;

import com.example.androidlibrary.model.DMGridItemTuple;

import java.util.Arrays;
import java.util.List;

public class DeviceStatusTask extends AsyncTask<List<DMGridItemTuple>, Integer, List<Integer>> {

    private final DeviceStatusTaskCallback callback;


    public interface DeviceStatusTaskCallback {
        void onDeviceStatusTaskFinished(List<Integer> result);
    }

    public DeviceStatusTask(DeviceStatusTaskCallback callback){
        this.callback = callback;
    }

    @Override
    protected List<Integer> doInBackground(List<DMGridItemTuple>... dcLists) {

        int iConnected = 0, iActiveOnly = 0, iAlerts = 0;
        for (DMGridItemTuple tempDC : dcLists[0]) {
            switch (tempDC.status) {
                case ACTIVE:
                    if (tempDC.alarmId != NOALARM) {
                        iAlerts++;
                    } else {
                        iActiveOnly++;
                    }
                    break;
                case CONNECTED:
                    if (tempDC.alarmId != NOALARM) {
                        iAlerts++;
                    } else {
                        iConnected++;
                    }
                    break;
                case NOTCONNECTED:
                    if (tempDC.alarmId != NOALARM) {
                        iAlerts++;
                    }
                    break;
                default:
                    //Do Nothing
            }
        }
        return Arrays.asList(iConnected, iActiveOnly, iAlerts, dcLists[0].size());
    }

    @Override
    protected void onPostExecute(List<Integer> results) {

        if (callback != null) {
            if(results!= null) {
                callback.onDeviceStatusTaskFinished(results);
            }
        }
    }
}

