package com.example.niloychowdhury.floaterdrawer.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.niloychowdhury.floaterdrawer.Controller.CategoryViewDialog;
import com.example.niloychowdhury.floaterdrawer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StackFragment extends android.app.Fragment {


    public StackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_stack, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

//             show   CategoryViewDialog alert = new CategoryViewDialog();
//                alert.showDialog(getActivity());
            }
        });
        return view;
    }

}
