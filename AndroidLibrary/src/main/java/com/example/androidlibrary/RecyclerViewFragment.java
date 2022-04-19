package com.example.androidlibrary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFragment extends Fragment {

    public RecyclerViewFragment() {
        // Required empty public constructor
    }
    List<String>mNameList=new ArrayList<>();
    CustomAdapter customAdapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_recycler_view, container, false);
        recyclerView=rootView.findViewById(R.id.recyclerView);

        return rootView;
    }

    public void setAdapter(List<String>mNameList){
        if(recyclerView!=null){
            linearLayoutManager=new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            customAdapter=new CustomAdapter(mNameList);
            recyclerView.setAdapter(customAdapter);
        }
    }
}