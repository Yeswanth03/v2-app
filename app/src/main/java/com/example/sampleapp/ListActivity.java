package com.example.sampleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<ContactModel> arrcontacts = new ArrayList<>();
    RecyclerContactAdapter adapter;
    FloatingActionButton btnOpenDialog;
    Button newscan;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerContact);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);


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
                            arrcontacts.add(new ContactModel(R.drawable.ic_launcher_foreground,name));
                        } else {
                            Toast.makeText(ListActivity.this,"please enter account",Toast.LENGTH_SHORT).show();

                        }



                        adapter.notifyItemInserted(arrcontacts.size()-1);

                        recyclerView.scrollToPosition(arrcontacts.size()-1);

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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerContactAdapter(ListActivity.this,arrcontacts);
        recyclerView.setAdapter(adapter);
    }
}