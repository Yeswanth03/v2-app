package com.example.sampleapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/* class returnnamekeys extends SQLiteOpenHelper
{
    public returnnamekeys(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
*/
public class ListActivity extends AppCompatActivity{

    ArrayList<ContactModel> arrcontacts = new ArrayList<>();
    RecyclerContactAdapter adapter;
    FloatingActionButton btnOpenDialog;
    //    Button newscan;
    RecyclerView recyclerView;
    String name=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerContact);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);

      //  Button testbtn=findViewById(R.id.testbtn);

      /*  testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                DBHelper DB;
                DB=new DBHelper(getApplicationContext());
                String l="{\"+\"a\"+\":\"+\"a\"+\"}";
                DB.replace(l);



            }
        });
*/
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(ListActivity.this);
                dialog.setContentView(R.layout.add_update_lay);
                EditText edtname = dialog.findViewById(R.id.edtname);
                Button btnadd = dialog.findViewById(R.id.btnadd);
//                Button btnaddscan = dialog.findViewById(R.id.btnaddscan);



//                btnaddscan.setOnClickListener(new View.OnClickListener()
//                {
//                    @Override
//                    public void onClick(View view)
//                    {
//                        startActivity(new Intent(ListActivity.this,ScannerView.class));
//                    }
//                });

                btnadd.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                         String name = "";

                        if(!edtname.getText().toString().equals("")){
                            name = edtname.getText().toString();
                            DBHelper db  =new DBHelper(getApplicationContext());
                           try {
                               Iterator itr = db.jsonall();
                               String res = db.returnhaskeyy();
                               JSONObject json = new JSONObject(res);
                               //return res;

                               while(itr.hasNext()){

                                   Object e = itr.next();

                                    String i = e.toString();
                                   String val = (String) json.get(i);
                                    arrcontacts.add(new ContactModel(R.drawable.ic_launcher_foreground,i,val));

                                   System.out.println(i+"ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo "+val);

                                   // $
                               }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //arrcontacts.add(new ContactModel(R.drawable.ic_launcher_foreground,name));



                        } else {
                            Toast.makeText(ListActivity.this,"please enter account",Toast.LENGTH_SHORT).show();

                        }



                        adapter.notifyItemInserted(arrcontacts.size()-1);

                        recyclerView.scrollToPosition(arrcontacts.size()-1);
                        Intent intent = new Intent(ListActivity.this,ScannerView.class);
                        intent.putExtra("name",name);
                        startActivity(intent);
                        dialog.dismiss();
                    }

                });
                dialog.show();
            }
        });



//        setContentView(R.layout.serviceprovider);
//        newscan = findViewById(R.id.newscan);
//        newscan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ListActivity.this,ScannerView.class));
//            }
//        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerContactAdapter(ListActivity.this,arrcontacts);
        recyclerView.setAdapter(adapter);



    }

}