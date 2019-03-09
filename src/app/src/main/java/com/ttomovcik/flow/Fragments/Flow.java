package com.ttomovcik.flow.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ttomovcik.flow.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Flow extends Fragment
{

    public Flow()
    {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_flow, container, false);
        return view;
    }
}
