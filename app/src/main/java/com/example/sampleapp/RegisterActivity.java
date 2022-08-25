package com.example.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity
{
    //logpassword is email id shit
    EditText inUsername,logPassword,inPassword,inConfirm;
    Button bt;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView btn = findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        bt = findViewById(R.id.btnRegister);
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //startActivity(new Intent(RegisterActivity.this,HomeActivity.class));

                String user = inUsername.getText().toString();
                String pass = inPassword.getText().toString();
                String repass = inConfirm.getText().toString();
                String emaill = logPassword.getText().toString();
                if(user.equals("")||pass.equals("")||repass.equals("")||emaill.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass))
                    {
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false)
                        {
                            Boolean insert = DB.insertData(user, pass,emaill);
                            if(insert==true)
                            {
                                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        inUsername =(EditText) findViewById(R.id.inUsername);
        logPassword =(EditText) findViewById(R.id.logPassword);
        inPassword =(EditText) findViewById(R.id.inPassword);
        inConfirm=(EditText) findViewById(R.id.inConfirm);
        DB =new DBHelper(this);




    }
}