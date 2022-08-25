package com.example.sampleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public DBHelper(Context context)
    {
        super(context, "Login.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase MyDB)
    {
        MyDB.execSQL("create Table users(userid integer  primary key autoincrement,username TEXT unique, password TEXT,email TEXT,secretkey text)");
        MyDB.execSQL("UPDATE users set secretkey= 'ddddddddd' ;");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1)
    {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password,String email)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }
    public Boolean checkusername(String username)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepassword(String username, String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ? ", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public void updatehashkey(String name,String number) throws JSONException
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        /*String sql = "SELECT email  FROM users where username= 'sen'";
        SQLiteStatement statement = MyDB.compileStatement(sql);
        long result = statement.simpleQueryForLong();
        */ //WITHOUT CURSOR USAGE
String uname = "sen";
        Cursor cursor=MyDB.rawQuery("SELECT email  FROM users where username= ?",new String[]{uname});
        String res = cursor.getString(cursor.getColumnIndex("email"));//WITH CURSOR USAGE
//        String hasahkey="" ;//we use select satement to get hashkey here to get
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"+String.valueOf(res));

        JSONObject json = new JSONObject(String.valueOf(res));


        json.put(name, number);

        String updatedsecrectkey =json.toString();

        MyDB.execSQL("UPDATE users set secretkey="+updatedsecrectkey+";");
        //String l=MyDB.execSQL("SELECT secretkey from users where username=vatsan;");
    }
}

