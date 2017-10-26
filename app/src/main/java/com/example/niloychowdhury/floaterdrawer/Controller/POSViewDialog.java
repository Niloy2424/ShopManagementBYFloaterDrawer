package com.example.niloychowdhury.floaterdrawer.Controller;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;

import com.example.niloychowdhury.floaterdrawer.R;

/**
 * Created by Niloy Chowdhury on 2017-10-04.
 */

public class POSViewDialog {

    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_point_of_sale);



        dialog.show();

    }
}
