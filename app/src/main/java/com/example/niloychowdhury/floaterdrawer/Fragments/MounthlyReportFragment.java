package com.example.niloychowdhury.floaterdrawer.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.niloychowdhury.floaterdrawer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MounthlyReportFragment extends android.app.Fragment {


    public MounthlyReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mounthlyreport, container, false);
        return view;
    }

}
