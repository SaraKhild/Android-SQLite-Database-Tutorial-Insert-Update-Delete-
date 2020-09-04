package com.example.androidsqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public  static final String DATABASE_NAME="Student.db";
    public static  final  String TABLE_NAME ="student_table";
    public  static  final  String COL1="ID";
    public  static  final  String COL2= "Name";
    public  static  final  String COL3= "SurName";


    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,SurName TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public  boolean insertData(String name , String surname){

        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL2,name);
        cv.put(COL3,surname);

        long result=  db.insert(TABLE_NAME,null,cv); // this one table name

        if( result ==-1)
            return false;
        else
            return  true;

    }

    public Cursor getAllData(){


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " +TABLE_NAME,null);
        return  c;

    }

    public boolean updateData ( String id , String name , String surname)

    {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL1,id);
        cv.put(COL2,name);
        cv.put(COL3,surname);

        db.update(TABLE_NAME, cv, "ID = ?",new String[] { id });

        return  true;
    }

    public Integer deleteData (String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }



}
