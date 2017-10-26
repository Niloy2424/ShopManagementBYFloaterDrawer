package com.example.niloychowdhury.floaterdrawer.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.niloychowdhury.floaterdrawer.Controller.POSManager;
import com.example.niloychowdhury.floaterdrawer.Controller.ReportAdapter;
import com.example.niloychowdhury.floaterdrawer.Model.POS;
import com.example.niloychowdhury.floaterdrawer.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllReportFragment extends Fragment {


    public AllReportFragment() {
        // Required empty public constructor
    }
    private ListView allreportLV;
    private POSManager manager;
    private ArrayList<POS>salesAllReports;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_allreport, container, false);
        FloatingActionButton fab= (FloatingActionButton) view.findViewById(R.id.fab);
        allreportLV= (ListView) view.findViewById(R.id.allReportLV);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //foater er code (pie chart)
            }
        });
        // Inflate the layout for this fragment
        manager=new POSManager(getActivity());
        salesAllReports=manager.getAllReport();
        ReportAdapter adapter=new ReportAdapter(getActivity(),salesAllReports);
        allreportLV.setAdapter(adapter);
        return view;
    }

}
