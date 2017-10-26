package com.example.niloychowdhury.floaterdrawer.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.niloychowdhury.floaterdrawer.Model.Categary;
import com.example.niloychowdhury.floaterdrawer.Model.Categary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Niloy Chowdhury on 2017-08-31.
 */

public class CategaryManager {
    private Categary categary;
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
    public  CategaryManager(Context context)
    {
        helper=new DatabaseHelper(context);
    }
    public boolean addCatagery(Categary aCategary) {
        this.open();

        ContentValues contentvalue=new ContentValues();
        contentvalue.put(DatabaseHelper.CATAGORY_NAME,aCategary.getCategryName());
        contentvalue.put(DatabaseHelper.STATUS,aCategary.getStatus());
        contentvalue.put(helper.CREATED_AT, getDateTime());
       long insert= database.insert(DatabaseHelper.TABLE_NAME_CATEGARY,null,contentvalue);
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
    public ArrayList<Categary> getAllCategary() {
        ArrayList<Categary>categaries=new ArrayList<>();
        this.open();
        Cursor cursor=database.query(DatabaseHelper.TABLE_NAME_CATEGARY,null,null,null,null,null,null);
        if (cursor!=null&&cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                int _id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.CATEGARY_ID));
                String name=cursor.getString(cursor.getColumnIndex(DatabaseHelper.CATAGORY_NAME));
                String dateTime=cursor.getString(cursor.getColumnIndex(DatabaseHelper.CREATED_AT));
                String status=cursor.getString(cursor.getColumnIndex(DatabaseHelper.STATUS));
                categary=new Categary(_id,name,dateTime,status);
                categaries.add(categary);
            }
        }



        this.close();
return categaries;

    }
    public String getCategoryNameById(int id){
        Categary categary = getSelectedCategory(id);
        String name = categary.getCategaryName();
        return  name;
    }

    public Categary getSelectedCategory(int recivedCategoryID) {



            this.open();
            Cursor cursor=database.query(helper.TABLE_NAME_CATEGARY,null,helper.CATEGARY_ID + " = " + recivedCategoryID,null,null,null,null);
            if (cursor!=null&&cursor.getCount()>0)
            {
                cursor.moveToFirst();
                int _id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.CATEGARY_ID));
                String name=cursor.getString(cursor.getColumnIndex(DatabaseHelper.CATAGORY_NAME));
                String dateTime=cursor.getString(cursor.getColumnIndex(DatabaseHelper.CREATED_AT));
                String status=cursor.getString(cursor.getColumnIndex(DatabaseHelper.STATUS));
                categary=new Categary(_id,name,dateTime,status);

            }
            this.close();


            return categary;

    }

    public boolean updateStudent(Categary aCategary, int recivedCategoryID) {

        this.open();

        ContentValues contentVales=new ContentValues();
                contentVales.put(helper.CATAGORY_NAME,aCategary.getCategryName());
                contentVales.put(helper.STATUS,aCategary.getStatus());
        contentVales.put(helper.CREATED_AT, getDateTime());
       long update= database.update(helper.TABLE_NAME_CATEGARY,contentVales,helper.CATEGARY_ID + "=" +recivedCategoryID,null);
        this.close();
        if (update>0)
        {
            return true;
        }
        else
        {
            return false;
        }




    }

    public boolean deleteCategory(int recivedCategoryID) {

        this.open();
        int deleted=0;
        deleted=database.delete(helper.TABLE_NAME_CATEGARY,helper.CATEGARY_ID+ "=" + recivedCategoryID,null);
        this.close();
        if (deleted>0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public ArrayList<Categary> getAllActiveCategary() {
        ArrayList<Categary>categaries=new ArrayList<>();
        this.open();
        Cursor cursor=database.query(helper.TABLE_NAME_CATEGARY,null,helper.STATUS+ " = " + "'Active'" ,null,null,null,null);
        if (cursor!=null&&cursor.getCount()>0)
        {

            while (cursor.moveToNext())
            {
                int _id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.CATEGARY_ID));
                String name=cursor.getString(cursor.getColumnIndex(DatabaseHelper.CATAGORY_NAME));
                String dateTime=cursor.getString(cursor.getColumnIndex(DatabaseHelper.CREATED_AT));
               String status=cursor.getString(cursor.getColumnIndex(DatabaseHelper.STATUS));
                categary=new Categary(_id,name,dateTime,status);
                categaries.add(categary);
            }
        }



        this.close();
        return categaries;

    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
