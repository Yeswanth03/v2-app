package com.example.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity
{
    EditText inUsername,logPassword;
    TextView btn;
    Button bt;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //System.out.println("heloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        DBHelper MYDB;
        MYDB =new DBHelper(this);
        try {
            MYDB.jsonall();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = findViewById(R.id.SignUp);
        //for going to sign up page
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        bt = findViewById(R.id.btnLogin);
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                // startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                String user = inUsername.getText().toString();
                String pass = logPassword.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if (checkuserpass == true) {
                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }





        });
        inUsername=(EditText) findViewById(R.id.inUsername);
        logPassword=(EditText) findViewById(R.id.logPassword);
        DB =new DBHelper(this);


    }

}






