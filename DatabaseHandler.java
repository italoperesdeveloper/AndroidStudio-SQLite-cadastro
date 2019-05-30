package com.italo.dbagenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DatabaseVersion=1;
    private static final String DatabaseName="db_agenda";
    private static final String CreateTable_EmpInfo="Create Table EmpInfo(ID Integer Primary Key AutoIncrement, NAME Text, Address Text, City Text, State Text, Mobile Text, CreateOn DateTime)";
    private static DatabaseHandler mInstance = null;
    public static DatabaseHandler getmInstance(Context context){
        if(mInstance==null){
            mInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return mInstance;
    }
    public DatabaseHandler(Context context){
        super (context, DatabaseName, null, DatabaseVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CreateTable_EmpInfo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }

    private String getDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public long insertIntoEmpInfo(String NAME, String Address, String City, String State, String Mobile){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME",NAME);
        values.put("Address",Address);
        values.put("City",City);
        values.put("State",State);
        values.put("Mobile",Mobile);
        values.put("CreateOn",getDateTime());

        long id = db.insert("EmpInfo", null, values);

        return id;

    }
}
