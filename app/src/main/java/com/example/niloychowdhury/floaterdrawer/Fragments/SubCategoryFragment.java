package com.example.niloychowdhury.floaterdrawer.Fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.niloychowdhury.floaterdrawer.Controller.CategaryAdapter;
import com.example.niloychowdhury.floaterdrawer.Controller.CategaryManager;
import com.example.niloychowdhury.floaterdrawer.Controller.CategoryViewDialog;
import com.example.niloychowdhury.floaterdrawer.Controller.SubCategoryAdapter;
import com.example.niloychowdhury.floaterdrawer.Controller.SubCategoryManager;
import com.example.niloychowdhury.floaterdrawer.Controller.SubCategoryViewDialog;
import com.example.niloychowdhury.floaterdrawer.Model.Categary;
import com.example.niloychowdhury.floaterdrawer.Model.SubCategary;
import com.example.niloychowdhury.floaterdrawer.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryFragment extends Fragment {


    public SubCategoryFragment() {
        // Required empty public constructor
    }

public ListView subCategoryLV;
    private Categary categary;
    private ListView categaryLV;
    private ArrayList<SubCategary> subCategories;
    private SubCategoryManager subCategoryManager;
    private SubCategoryAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sub_category, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        subCategoryLV= (ListView) view.findViewById(R.id.subcategaryLV);
        SubCategoryManager subCategoryManager=new SubCategoryManager(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                SubCategoryViewDialog alert = new SubCategoryViewDialog(getActivity());
                alert.showDialogForAdd(getActivity());
            }
        });
        registerForContextMenu(subCategoryLV);
        subCategories = subCategoryManager.getSubCategory();
        adapter = new SubCategoryAdapter(getActivity(), subCategories);
        subCategoryLV.setAdapter(adapter);
        return view;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Menu");
        menu.add(0, v.getId(), 1, "Edit");
        menu.add(0, v.getId(), 2, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int index = info.position;
        final int subcategaryID = subCategories.get(index).getSubCategoryID();
        //categary = cManager.getSelectedCategory(categoryID);
        if (item.getTitle() == "Edit") {
            //String msg= getArguments().getString(String.valueOf(categoryID));

            ///view
            SubCategoryViewDialog alert = new SubCategoryViewDialog(getActivity());
            alert.showDialogForUpdate(getActivity(),subcategaryID);

        } else if (item.getTitle() == "Delete")
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            builder.setTitle("Delete");
            builder.setMessage("Are you sure");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //contacts.remove(index);
                    // RefreshData();
                    subCategoryManager=new SubCategoryManager(getActivity());
                    boolean deleted=  subCategoryManager.deleteSubCategoryItem(subcategaryID);
                    if (deleted)
                    {
                        Toast.makeText(getActivity(), "Deleted successfully", Toast.LENGTH_SHORT).show();
                        // refreshListView();
                    }
                    else
                        Toast.makeText(getActivity(), "Error in delete", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();

        }
        return super.onContextItemSelected(item);
    }
}
