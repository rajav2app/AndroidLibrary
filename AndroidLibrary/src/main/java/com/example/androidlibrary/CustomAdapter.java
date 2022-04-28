package com.example.androidlibrary;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.FreeDeviceViewHolder> {
    private List<String> freeDeviceList;
    private FreeDeviceListener listener = null;
    public CustomAdapter(FreeDeviceListener listener ) {
       this.listener = listener;
    }



    @Override
    public FreeDeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new FreeDeviceViewHolder(view);
    }

    public void setFreeDeviceList(List<String> freeDeviceList){
        this.freeDeviceList = freeDeviceList;
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(final FreeDeviceViewHolder holder, @SuppressLint("RecyclerView") final int i) {

        if(freeDeviceList != null) {

            holder.txFreeDevice.setText(freeDeviceList.get(i));

           holder.llFreeDevice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onSelectedItem(freeDeviceList.get(i));
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        if(freeDeviceList == null){
            return 0;
        }
        return freeDeviceList.size();
    }


    class FreeDeviceViewHolder extends RecyclerView.ViewHolder {
        final TextView txFreeDevice;
       final LinearLayout llFreeDevice;

        FreeDeviceViewHolder(View view) {
            super(view);
            txFreeDevice = view.findViewById(R.id.txFreeDevice);
            llFreeDevice=view.findViewById(R.id.llFreeDevice);
        }

    }

    public interface FreeDeviceListener {
        void onSelectedItem(String devID);
    }

    public void filterList(List<String> filteredList) {
        freeDeviceList = filteredList;
        notifyDataSetChanged();
    }

}