package com.example.sampleapp;

import static com.example.sampleapp.RecyclerContactAdapter.getTOTPCode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import de.taimos.totp.TOTP;

public class venderverify extends AppCompatActivity {



    public static String getTOTPCode(String secretKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venderverify);

        String secretKey="KA54QGQSZZVBUFIH3XACOAW2VNFKXXVV";

        String s = getTOTPCode(secretKey);

        System.out.println(s);

        // int otp = Integer.parseInt(s);

//        TextView txtmsg = findViewById(R.id.txtmsg);

        EditText edttextotp = findViewById(R.id.edttextotp);


        Button verifybtn = findViewById(R.id.verifybtn);
        verifybtn.setOnClickListener(new View.OnClickListener() {
            //            String str = edttextotp.getText().toString();
            @Override
            public void onClick(View view) {
                //int chk = Integer.parseInt(str);
                if (edttextotp.getText().toString().equals(getTOTPCode(secretKey)))
                {
//                    txtmsg.setText("OTP is verified");
                    Toast.makeText(getApplicationContext(),"OTP is verified",Toast.LENGTH_SHORT).show();
                }
                else
                {
//                    txtmsg.setText("INVALID OTP");
                    Toast.makeText(getApplicationContext(),"INVALID",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}