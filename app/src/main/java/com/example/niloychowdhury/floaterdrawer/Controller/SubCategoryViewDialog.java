package com.example.niloychowdhury.floaterdrawer.Controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.niloychowdhury.floaterdrawer.Model.Categary;
import com.example.niloychowdhury.floaterdrawer.Model.SubCategary;
import com.example.niloychowdhury.floaterdrawer.R;

import java.util.ArrayList;

/**
 * Created by Niloy Chowdhury on 2017-10-04.
 */

public class SubCategoryViewDialog {
    public Button addSubCategaryItemButton;
    public Button cancelButton;
    public Spinner categarySpinner;
    public EditText subcategaryNameEditText;
    public EditText subcategaryCodeEditText;
    public EditText subcategarypriceEditText;
    public EditText subCategarySaleEditText;
    public EditText subcategaryStockEditText;
    public Spinner statusSpinner;
    public Context context;
    public SubCategoryManager manager;
    public ArrayList<String>statuses;
    public ArrayList<Categary>categaries;
    public ArrayList<String>categoryNames;
    public Categary aCategary;
    public CategaryManager categaryManager;
    public SubCategary aSubCategary;
    public SubCategoryManager subCategoryManager;
    public int categoryID;
    String name,status,code,categary;
    double sale,price,stock;

    public SubCategoryViewDialog(Context context) {
        this.context=context;
    }
    public void showDialogForAdd(Activity activity){
        openDialog(activity);

        addSubCategaryItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            catchValueForsubCategoryField();
if (!name.isEmpty()||!code.isEmpty())
{
   boolean insert=subCategoryManager.addSubCategory(aSubCategary);
    if (insert)
    {
        Toast.makeText(context, "Sub Category is Added Successfully", Toast.LENGTH_SHORT).show();
    }
    else
    {
        Toast.makeText(context, " Sorry , Sub Category is not Added", Toast.LENGTH_SHORT).show();
    }
}
else
    Toast.makeText(context, "Please give all the required information", Toast.LENGTH_SHORT).show();

           }
       });

            }

    public void openDialog(Activity activity)
    {

        final Dialog dialog = new Dialog(activity,R.style.myBackgroundStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_subcategory);
        addSubCategaryItemButton= (Button) dialog.findViewById(R.id.addSubCategaryItemButton);
        cancelButton= (Button) dialog.findViewById(R.id.cancelButton);
        subcategaryNameEditText= (EditText) dialog.findViewById(R.id.subCategoryNameET);
        subcategaryCodeEditText= (EditText) dialog.findViewById(R.id.codeET);
        subcategarypriceEditText= (EditText) dialog.findViewById(R.id.priceET);
        subCategarySaleEditText= (EditText) dialog.findViewById(R.id.saleET);
        subcategaryStockEditText= (EditText) dialog.findViewById(R.id.stockET);
        statusSpinner= (Spinner) dialog.findViewById(R.id.statusSpinner);
        categarySpinner= (Spinner) dialog.findViewById(R.id.categarySpinner);
        manager=new SubCategoryManager(context);
        addValueInSpinner();
        addValueInCategarySpinner();


        dialog.show();
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }
    public void addValueInSpinner() {
        statuses=new ArrayList<>();
        statuses.add("Active");
        statuses.add("Inactive");
        SpinnerAdapter adapter=new SpinnerAdapter(context,statuses);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>( android.R.layout.simple_spinner_dropdown_item, statuses);
        // ArrayAdapter adapter= ArrayAdapter.createFromResource(this,R.array.status,android.R.layout.simple_spinner_item);
        statusSpinner.setAdapter(adapter);
    }
    public void addValueInCategarySpinner()
    {
categaryManager=new CategaryManager(context);
        categaries=categaryManager.getAllActiveCategary();
        categoryNames=new ArrayList<>();
        for (Categary category:categaries)
        {
            String categoryName= category.getCategaryName();


            categoryNames.add(categoryName);
        }
        SpinnerAdapter adapter=new SpinnerAdapter(context,categoryNames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categarySpinner.setAdapter(adapter);
        categarySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                categoryID=categaries.get(position).getId();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void showDialogForUpdate(Activity activity, final int subCategoryID){
        // this.recivedCategoryID=categaryID;
        openDialog(activity);
        addSubCategaryItemButton.setText("UPDATE");
        subCategoryManager=new SubCategoryManager(context);
        aSubCategary=subCategoryManager.getSelectedSubCategary(subCategoryID);
        subcategaryNameEditText.setText(aSubCategary.getSubCategoryName());
        subcategaryStockEditText.setText(String.valueOf(aSubCategary.getStock()));
        subCategarySaleEditText.setText(String.valueOf(aSubCategary.getSale()));
        subcategarypriceEditText.setText(String.valueOf(aSubCategary.getPrice()));
        subcategaryCodeEditText.setText(aSubCategary.getSubCategoryCode());
        subcategaryNameEditText.setText(aSubCategary.getSubCategoryName());
        categarySpinner.setSelection(categoryNames.indexOf(aSubCategary.getCategoryID()));
        statusSpinner.setSelection(statuses.indexOf(aSubCategary.getStatus()));


        addSubCategaryItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               catchValueForsubCategoryField();

                // refreshListView();

                if (!name.isEmpty()||!code.isEmpty())
                {

                    boolean updated=subCategoryManager.updateSubCategory(aSubCategary,subCategoryID);
                    if (updated)
                    {
                        //Toast.makeText(context, categaryID , Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, "Category is updated", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(context, "Sorry , Category is not updated", Toast.LENGTH_SHORT).show();
                    }
                    // refreshListView();
                }




                else

                {
                    Toast.makeText(context, "Please insert Category Name", Toast.LENGTH_SHORT).show();
                }
                /*categeries = manager.getAllCategary();
                adapter = new CategaryAdapter(context, categeries);
                categaryLV.setAdapter(adapter);*/
            }
        });

    }
    public void catchValueForsubCategoryField()
    {
        name = subcategaryNameEditText.getText().toString();
        categary = categarySpinner.getSelectedItem().toString();
        code = subcategaryCodeEditText.getText().toString();
        price = Double.valueOf(subcategarypriceEditText.getText().toString());
        stock = Double.valueOf(subcategaryStockEditText.getText().toString());
        sale = Double.valueOf(subCategarySaleEditText.getText().toString());
        status = statusSpinner.getSelectedItem().toString();
        aSubCategary = new SubCategary(name, code, sale, stock, price, status, categoryID);
               /* Toast.makeText(context, "" + categoryID, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "" + name, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "" + code, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "" + sale, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "" + stock, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "" + price, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "" + status, Toast.LENGTH_SHORT).show();*/
        subCategoryManager=new SubCategoryManager(context);
    }
}
