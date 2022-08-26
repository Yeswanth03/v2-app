package com.example.sampleapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Struct;
import java.util.Iterator;

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
        MyDB.execSQL("UPDATE users set secretkey= '{"+"a"+":"+"a"+"}' ;");

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
       // MyDB.execSQL("UPDATE users set secretkey= '{"+"a"+":"+"a"+"}' ;");
        //
        String sql = "SELECT secretkey  FROM users where username= 'sen'";
        SQLiteStatement statement = MyDB.compileStatement(sql);
        String res = statement.simpleQueryForString();
        String old=res;

//      String hasahkey="" ;//we use select satement to get hashkey here to get
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"+res);

        JSONObject json = new JSONObject(res);


        json.put(name, number);

        String updatedsecretkey =json.toString();

        JSONObject usk = new JSONObject(updatedsecretkey);

        System.out.println("****************************************************"+updatedsecretkey);
        String concatted = jsonConcat(old, updatedsecretkey);
       // res = string a=textbox1.text.replace('\'','\"');

        MyDB.execSQL("UPDATE users set secretkey= " + "'" +updatedsecretkey+"'" + ";");
        /*MyDB.execSQL("UPDATE\n" +
                "    users\n" +
                "SET\n" +
                "    secretkey  = REPLACE(secretkey,\"'\" +res+\"'\",\"'\" +concatted+\"'\");");*/

        concatted=null;
        //String l=MyDB.execSQL("SELECT secretkey from users where username=vatsan;");

    }

    public String jsonConcat(String o, String i){
        int lenofO = o.length();


        StringBuilder sb = new StringBuilder(o);
        sb.deleteCharAt(lenofO-1);
        sb.append(',');
        StringBuilder sb1 = new StringBuilder(i);
        sb1.deleteCharAt(0);
        sb.append(sb1);

        String finalstr = sb.toString();
        return finalstr;
    }
    public Iterator jsonall() throws JSONException {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String sql = "SELECT secretkey  FROM users where username= 'sen'";
        SQLiteStatement statement = MyDB.compileStatement(sql);
        String res = statement.simpleQueryForString();
     JSONObject json = new JSONObject(res);


              Iterator itr = json.keys();
      String i="";
      return itr;
     /* while(itr.hasNext()){

          Object e = itr.next();

          i = e.toString();

          String val = returnhashval((String) json.get(i));
          System.out.println(i+"ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo "+val);

         // $
      }*/
    }
    public String returnhashval(String val){
        return val;
    }
    public String returnwebsitename(String web){



        return web;

    }
    public boolean jsonCleaner(String name) throws JSONException {

        String cleanedJson="";

        SQLiteDatabase MyDB = this.getWritableDatabase();


        String sql = "select secretkey from users where username='sen'";
        SQLiteStatement statement = MyDB.compileStatement(sql);
        String res = statement.simpleQueryForString();
        System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii\n" +
                "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"+res);
        JSONObject js = new JSONObject(res);
        System.out.println(js.has(name));



        return js.has(name);

    }
    public String returnhaskeyy()
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String sql = "SELECT secretkey  FROM users where username= 'sen'";
        SQLiteStatement statement = MyDB.compileStatement(sql);
        String res = statement.simpleQueryForString();
        return res;
    }

}

