package com.ttomovcik.flow;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Flow extends Fragment {

    public Flow(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Flow","Well, hello there!");
        return inflater.inflate(R.layout.fragment_flow, container, false);
    }

}
