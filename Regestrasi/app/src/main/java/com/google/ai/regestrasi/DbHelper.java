package com.google.ai.regestrasi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASI_VERSION = 1;

    static final        String DATABASE_NAME = "DB.One";
    public static final String Table         = "registrasi";
    public static final String Col_name        = "name";
    public static final String Col_user       = "user";
    public static final String Col_sandi     = "sandi";
    public static final String Col_email = "email";
    public static final String Col_No = "nohp";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASI_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE " + Table + "("
                +Col_name+ " TEXT PRIMARY KEY NOT NULL,"
                +Col_user+ " TEXT NOT NULL,"
                +Col_sandi+ " TEXT NOT NULL,"
                +Col_email+ " TEXT NOT NULL,"
                +Col_No+ " TEXT NOT NULL"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table);
        onCreate(db);
    }
    public ArrayList<HashMap<String, String>> getData() {
        ArrayList<HashMap<String, String>> ListTa;
        ListTa = new ArrayList<HashMap<String, String>>();
        String         selectQuery = "SELECT * FROM " + Table;
        SQLiteDatabase database    = this.getWritableDatabase();
        Cursor         cursor      = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(Col_name, cursor.getString(0));
                map.put(Col_user, cursor.getString(1));
                map.put(Col_sandi, cursor.getString(2));
                map.put(Col_email, cursor.getString(3));
                map.put(Col_No, cursor.getString(4));
                ListTa.add(map);

            } while (cursor.moveToNext());
        }
        Log.e("select registrasi",""+ListTa);


        database.close();
        return ListTa;
    }
    public Boolean Login(String user,String sandi) {
        String selectQuery = "SELECT * FROM " + Table + " WHERE " + Col_user + "=" + "'" + user + "'" + " AND "+ Col_sandi +"=" + "'" + sandi +"'";
     SQLiteDatabase database = this.getReadableDatabase();
     Cursor cursor = database.rawQuery(selectQuery,null);
   if (cursor.getCount()>0) return true;
   else return false;
    }
    public void insert (String name,String user,String sandi,String email,String nohp){
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + Table + " ( name, user, sandi, email, nohp) " +
                "VALUES ('" + name + "','" + user + "', '" + sandi + "','" + email + "','" + nohp + "')";

        Log.e("insert registrasi", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }
    public void delete(String name) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + Table + " WHERE " + Col_name + "=" + "'" + name + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

}
