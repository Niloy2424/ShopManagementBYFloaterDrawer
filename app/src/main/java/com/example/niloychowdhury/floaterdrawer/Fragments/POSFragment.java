package com.example.niloychowdhury.floaterdrawer.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.niloychowdhury.floaterdrawer.Controller.CategaryManager;
import com.example.niloychowdhury.floaterdrawer.Controller.POSManager;
import com.example.niloychowdhury.floaterdrawer.Controller.POSViewDialog;
import com.example.niloychowdhury.floaterdrawer.Controller.SpinnerAdapter;
import com.example.niloychowdhury.floaterdrawer.Controller.SubCategoryManager;
import com.example.niloychowdhury.floaterdrawer.Model.Categary;
import com.example.niloychowdhury.floaterdrawer.Model.POS;
import com.example.niloychowdhury.floaterdrawer.Model.SubCategary;
import com.example.niloychowdhury.floaterdrawer.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class POSFragment extends Fragment {
    public Spinner pOICategarySpinner;
    public Spinner pOIItemSpinner;
    public Button addPOSButton;
    public Button resetButton;
    public EditText quantityET;
    public EditText priceET;
    public ArrayList<Categary> categories;
    public ArrayList<SubCategary>subCategories;
    public CategaryManager categaryManager;
    public SubCategoryManager subCategoryManager;
    public int categoryID,selectedSubcategoryID;
    public SubCategary aSubCategory;
    public POS aPos;
    public POSManager manager;
    double quantity,price;
    double finalQuantity;
    double finalSale;

    public POSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_pos, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
     /*   fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*

                POSViewDialog alert = new POSViewDialog();
                alert.showDialog(getActivity());
            }
        });*/
        // Inflate the layout for this fragment
        pOICategarySpinner= (Spinner)view.findViewById(R.id.pOICategorySpinner);
        pOIItemSpinner= (Spinner) view.findViewById(R.id.pOIItemSpinner);
        quantityET= (EditText) view.findViewById(R.id.pOIQuantityET);
        priceET= (EditText) view.findViewById(R.id.pOIPriceET);
        addPOSButton= (Button) view.findViewById(R.id.saveButton);
        resetButton= (Button) view.findViewById(R.id.resetButton);
        categaryManager=new CategaryManager(getActivity());
        subCategoryManager=new SubCategoryManager(getActivity());
        manager=new POSManager(getActivity());
        addValueInCategarySpinner();
        addPOSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityET.getText().toString().isEmpty()&&priceET.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(), "Give Quantity And Price", Toast.LENGTH_SHORT).show();
                }
                else {
                    quantity = Double.valueOf(quantityET.getText().toString());

                    price = Double.valueOf(priceET.getText().toString());

                    aSubCategory = subCategoryManager.getSelectedSubCategary(selectedSubcategoryID);
                    if (aSubCategory.getStock() > quantity) {
                        finalQuantity = aSubCategory.getStock() - quantity;
                        finalSale = aSubCategory.getSale() + quantity;
                        SubCategary subcategory = new SubCategary(aSubCategory.getSubCategoryName(), aSubCategory.getSubCategoryCode(),finalSale, finalQuantity, aSubCategory.getPrice(), aSubCategory.getStatus(), categoryID);
                        boolean update = subCategoryManager.updateSubCategory(subcategory, selectedSubcategoryID);
                        if (update) {
                            Toast.makeText(getActivity(), "Stock is updated ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Stock is not updated", Toast.LENGTH_SHORT).show();
                        }
                        aPos = new POS(categoryID, selectedSubcategoryID, quantity, price);
                        boolean insert = manager.addPOS(aPos);
                        if (insert) {
                            Toast.makeText(getActivity(), "Sale Record Is Inserted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Sale record isn't inserted", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "You don't have Enough Stock.Your stock for this item is" + aSubCategory.getStock(), Toast.LENGTH_LONG).show();
                    }


                }

            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityET.setText("");
                priceET.setText("");
            }
        });
        return view;


    }

    public void addValueInCategarySpinner()
    {

        categories=categaryManager.getAllActiveCategary();
        ArrayList<String>categoryNames=new ArrayList<>();
        for (Categary category:categories)
        {
            String categoryName= category.getCategaryName();

            categoryNames.add(categoryName);
        }
        SpinnerAdapter adapter=new SpinnerAdapter(getActivity(),categoryNames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pOICategarySpinner.setAdapter(adapter);
        pOICategarySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                categoryID=categories.get(position).getId();
                addValueInSubcategorySpinner();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void addValueInSubcategorySpinner()
    {

        subCategories=subCategoryManager.getSelectedSubCategaryByCategory(categoryID);
        ArrayList<String>subItemNames=new ArrayList<>();
        for (SubCategary subCategory:subCategories)
        {
            String subCategoryNames=subCategory.getSubCategoryName();

            subItemNames.add(subCategoryNames);
        }
        SpinnerAdapter adapter=new SpinnerAdapter(getActivity(),subItemNames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pOIItemSpinner.setAdapter(adapter);
        pOIItemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSubcategoryID=subCategories.get(position).subCategoryID;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
