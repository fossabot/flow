package com.ttomovcik.flow.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ttomovcik.flow.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Flow extends Fragment {

    public Flow(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flow, container, false);

        de.hdodenhof.circleimageview.CircleImageView circleImageView
                = view.findViewById(R.id.fragment_container_flow_block_greeter_profile_image);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Flow/circleImageView","Hey! I'm alive");
            }
        });

        return view;
    }

}
