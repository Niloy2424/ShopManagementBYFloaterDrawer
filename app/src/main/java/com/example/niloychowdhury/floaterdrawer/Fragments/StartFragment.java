package com.example.niloychowdhury.floaterdrawer.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.niloychowdhury.floaterdrawer.R;

/**
 * Created by Niloy Chowdhury on 2017-09-17.
 */

public class StartFragment extends Fragment {
    public StartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.start_fragment,container,false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();*/
//
//                CategoryViewDialog alert = new CategoryViewDialog();
//                alert.showDialog(getActivity());
//            }
//        });

        return view;
    }
}
