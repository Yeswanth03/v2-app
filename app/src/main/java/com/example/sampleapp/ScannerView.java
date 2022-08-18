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
import java.nio.channels.ScatteringByteChannel;

public class ScannerView extends AppCompatActivity
{
    Button btnscan;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_view);
        btnscan = findViewById(R.id.scanBtn);
        btnscan.setOnClickListener(v->
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
        TextView edtnumber = findViewById(R.id.edtnumber);
        if(result.getContents() !=null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder((ScannerView.this));
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("Copy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    edtnumber.setText(result.getContents());
                    dialogInterface.dismiss();

                }
            }).show();
        }
    });
}