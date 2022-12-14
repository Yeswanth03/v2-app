package com.example.sampleapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONException;

import java.nio.channels.ScatteringByteChannel;

public class ScannerView extends AppCompatActivity
{
    Button scanbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_view);
        scanbtn = findViewById(R.id.scanBtn);
        scanbtn.setOnClickListener(v->
        {
            scanCode();
        });
    }




    private void scanCode()
    {
        ScanOptions options= new ScanOptions();
        options.setPrompt("volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(),result ->
    {
        if(result.getContents() !=null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder((ScannerView.this));
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            String hashK = result.getContents();
            System.out.println(hashK);
            String webname= getIntent().getStringExtra("name");
            System.out.println(webname);
              DBHelper db  =new DBHelper(this);;
            try {
                if(!db.jsonCleaner(webname)){
                db.updatehashkey(webname,hashK);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            try {
//                db.updatehashkey(webname, hashK);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(ScannerView.this,ListActivity.class));
                    dialogInterface.dismiss();

                }
            }).show();
        }
    });
}