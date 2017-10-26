package com.example.niloychowdhury.floaterdrawer.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.niloychowdhury.floaterdrawer.Model.POS;
import com.example.niloychowdhury.floaterdrawer.Model.POS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Niloy Chowdhury on 2017-09-13.
 */

public class POSManager {
    private POS pOS;
    private DatabaseHelper helper;
    public SQLiteDatabase database;

    public void open()
    {
        database=helper.getWritableDatabase();
    }
    public void close()
    {
        helper.close();
    }
    public POSManager(Context context) {

        helper=new DatabaseHelper(context);
    }

    public boolean addPOS(POS aPos) {
        this.open();

        ContentValues contentvalue=new ContentValues();
        contentvalue.put(helper.SALE_QUANTITY,aPos.getQuantity());
        contentvalue.put(helper.CATEGARY_ID,aPos.getCategoryID());
        contentvalue.put(helper.SUBCATEGARY_ID,aPos.getSubCategoryID());
        contentvalue.put(helper.SALE_PRICE,aPos.getPrice());
        contentvalue.put(helper.CREATED_AT, getDateTime());
        long insert= database.insert(helper.TABLE_NAME_POS,null,contentvalue);
        this.close();
        if (insert>0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public ArrayList<POS> getAllReport() {
        this.open();
        Cursor cursor=database.query(DatabaseHelper.TABLE_NAME_POS,null,null,null,null,null,null);
        ArrayList<POS>sales=storeSpecificDataInPOS(cursor);
        this.close();
        return sales;
    }
    public ArrayList<POS> getTodaysReport() {
        this.open();
        Cursor cursor=database.query(DatabaseHelper.TABLE_NAME_POS,null,DatabaseHelper.CREATED_AT + "=" + getDateTime(),null,null,null,null);
        ArrayList<POS>sales=storeSpecificDataInPOS(cursor);
        this.close();

        return sales;
    }

    private ArrayList<POS> storeSpecificDataInPOS(Cursor cursor)
    {
        ArrayList<POS>sales=new ArrayList<>();
        if (cursor!=null&&cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                int _id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SALE_ID));
                int _cid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.CATEGARY_ID));
                int _subid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SUBCATEGARY_ID));
                double price=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.SALE_PRICE));
                String dateTime=cursor.getString(cursor.getColumnIndex(DatabaseHelper.CREATED_AT));
                double quantity=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.SALE_QUANTITY));
                pOS=new POS(_cid,_subid,_id,quantity,price,dateTime);

                sales.add(pOS);
            }
        }
        return sales;
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
