package com.example.niloychowdhury.floaterdrawer.Controller;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niloychowdhury.floaterdrawer.Model.Categary;
import com.example.niloychowdhury.floaterdrawer.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Niloy Chowdhury on 2017-09-19.
 */

public class CategoryViewDialog {
    public ArrayList<String> statuses;
    EditText categoryNameET;
    Spinner statusSpinner;
    Button addCategoryItemButton;
    String  categaryName,status;
    Button cancelButton;
    Categary aCategary;
    CategaryManager manager;
    public int recivedCategoryID;
    public String categoryName,categaryStatus;
    CategaryAdapter adapter;
    private Context context;
    public ArrayList<Categary> categeries;


    public void showDialogForUpdate(Activity activity, final int categaryID){
       // this.recivedCategoryID=categaryID;
        OpenDialog(activity);
        addCategoryItemButton.setText("UPDATE");
        aCategary=manager.getSelectedCategory(categaryID);
        categoryNameET.setText(aCategary.getCategryName());
        statusSpinner.setSelection(statuses.indexOf(aCategary.getStatus()));


        addCategoryItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categaryName=categoryNameET.getText().toString();
                status=statusSpinner.getSelectedItem().toString();

                       // refreshListView();

                    if (!categaryName.isEmpty())
                    {
                        aCategary = new Categary(categaryName, status);
                        boolean updated=manager.updateStudent(aCategary,categaryID);
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
public void ShowDialogForAdd(final Activity activity)
{
    OpenDialog(activity);
    addCategoryItemButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            categaryName=categoryNameET.getText().toString();
            status=statusSpinner.getSelectedItem().toString();
            if (!categaryName.isEmpty())
            {
                aCategary = new Categary(categaryName, status);
                boolean insert = manager.addCatagery(aCategary);
                if (insert) {
                    Toast.makeText(context, "Successfully inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Data not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        }
    });

}
    public void OpenDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity,R.style.myBackgroundStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_categary);
        addCategoryItemButton= (Button) dialog.findViewById(R.id.addItemButton);
        cancelButton= (Button) dialog.findViewById(R.id.cancelButton);
        manager=new CategaryManager(context);

        categoryNameET = (EditText) dialog.findViewById(R.id.categoryNameET);

        statusSpinner= (Spinner) dialog.findViewById(R.id.statusSpinner);
        addValueInSpinner();


        dialog.show();
cancelButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dialog.dismiss();
    }
});

    }

    public CategoryViewDialog(Context context) {
        this.context=context;
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
/*    public void refreshListView() {
        categeries = manager.getAllCategary();
        adapter = new CategaryAdapter(context, categeries);
        categaryLV.setAdapter(adapter);
    */}

