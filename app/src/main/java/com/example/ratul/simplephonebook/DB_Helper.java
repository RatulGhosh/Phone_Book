package com.example.ratul.simplephonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Ratul on 11/1/2015.
 */
public class DB_Helper extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "phonebook";
    final static int DATABASE_VERSION = 1;
    final static String TABLE_CONTACTS = "contacts";
    final static String CONTACTS_ID = "_id";
    final static String CONTACTS_NAME = "name";
    final static String CONTACTS_PHONE = "phone";
    final static String QUERY_CREATE_TABLE ="CREATE TABLE IF NOT EXISTS "+ TABLE_CONTACTS +" (" +
            CONTACTS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            CONTACTS_NAME+" TEXT,"+
            CONTACTS_PHONE+" TEXT)";
    final static String QUERY_DROP_TABLE = "DELETE TABLE IF EXISTS "+TABLE_CONTACTS;

    public DB_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(QUERY_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUERY_DROP_TABLE);
    }

    public long insertData(ContactHolder values){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(CONTACTS_NAME,values.name);
        val.put(CONTACTS_PHONE,values.phone);
       // db.rawQuery("");
        long ret = db.insert(TABLE_CONTACTS,null,val);//null means no column should be blank. insert() function return long.
        return ret;
    }

    public ArrayList<ContactHolder> getContactList(){
        ArrayList<ContactHolder> arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, null, null, null, null, null, null);//null for all columns
        cursor.moveToFirst();//come to the first row
        for(int i = 0;i<cursor.getCount();i++){
            int id = cursor.getInt(cursor.getColumnIndex(CONTACTS_ID));
            String name = cursor.getString(cursor.getColumnIndex(CONTACTS_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(CONTACTS_PHONE));
            ContactHolder contactHolder = new ContactHolder(id,name,phone);
            arrayList.add(contactHolder);
            cursor.moveToNext();
        }
        return arrayList;
    }
}
