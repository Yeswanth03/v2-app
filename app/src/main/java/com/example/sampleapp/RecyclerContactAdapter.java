package com.example.sampleapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import de.taimos.totp.TOTP;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder > {

    Context context;
    ArrayList<ContactModel> arrContacts;

    public static String getTOTPCode(String secretKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }

    RecyclerContactAdapter(Context context, ArrayList<ContactModel> arrContacts){
        this.context = context;
        this.arrContacts = arrContacts;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.serviceprovider,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {


        ContactModel model = (ContactModel) arrContacts.get(position);
        holder.imagecontact.setImageResource(model.img);
        holder.txtname.setText(arrContacts.get(position).name);

        holder.realmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_update_lay);


                EditText edtname = dialog.findViewById(R.id.edtname);
                Button btnadd = dialog.findViewById(R.id.btnadd);

                TextView txtTitle = dialog.findViewById(R.id.txtTitle);

                edtname.setText((arrContacts.get(position)).name);


                txtTitle.setText("Modify");
                btnadd.setText("Modify");
                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String name="",number="";
                        if(!edtname.getText().toString().equals("")){
                            name = edtname.getText().toString();
                        } else {
                            Toast.makeText(context,"please enter contact name!",Toast.LENGTH_SHORT).show();
                        }
                        arrContacts.set(position, new ContactModel(R.drawable.ic_launcher_foreground,name));
                        notifyItemChanged(position);

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.displaytotp);

//                EditText edtname = dialog.findViewById(R.id.edtname);
//                edtname.setText(arrContacts.get(position).val);

                ImageView img_1;
                ImageView img_2;
                ImageView img_3;
                ImageView img_4;
                ImageView img_5;
                ImageView img_6;

                img_1 = dialog.findViewById(R.id.img_1);
                img_2 = dialog.findViewById(R.id.img_2);
                img_3 = dialog.findViewById(R.id.img_3);
                img_4 = dialog.findViewById(R.id.img_4);
                img_5 = dialog.findViewById(R.id.img_5);
                img_6 = dialog.findViewById(R.id.img_6);
//                TextView txtviewtotp;
//                txtviewtotp=dialog.findViewById(R.id.txtviewtotp);
//                Random random = new Random();
//                int n = random.nextInt(1000000-100000)+1;

                String secretKey="";
                String accname= arrContacts.get(position).name;
                DBHelper db  =new DBHelper(context.getApplicationContext());
                //String j = db.returnhaskeyy();
                try {
                Iterator itr = db.jsonall();
                String res = db.returnhaskeyy();
                JSONObject json = null;
                json = new JSONObject(res);

                //return res;
                     secretKey = (String) json.get(accname);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(secretKey);
                String s = getTOTPCode(secretKey);


                int n = Integer.parseInt(s);
                int j=0;
                int[] arr = new int[6];
                while(n!=0)
                {
                    arr[6-j-1] = n%10;
                    n=n/10;
                    j++;
                }
                for (int i =0;i<6;i++)
                {
                    switch (arr[i]){
                        case 0:
                            switch (i){
                                case 0:
                                    img_1.setImageResource(R.drawable.ic_0);
                                    continue;
                                case 1:
                                    img_2.setImageResource(R.drawable.ic_0);
                                    continue;
                                case 2:
                                    img_3.setImageResource(R.drawable.ic_0);
                                    continue;
                                case 3:
                                    img_4.setImageResource(R.drawable.ic_0);
                                    continue;
                                case 4:
                                    img_5.setImageResource(R.drawable.ic_0);
                                    continue;
                                case 5:
                                    img_6.setImageResource(R.drawable.ic_0);
                                    continue;
                            }
                        case 1:
                            switch (i) {
                                case 0:
                                    img_1.setImageResource(R.drawable.ic_1);
                                    continue;
                                case 1:
                                    img_2.setImageResource(R.drawable.ic_1);
                                    continue;
                                case 2:
                                    img_3.setImageResource(R.drawable.ic_1);
                                    continue;
                                case 3:
                                    img_4.setImageResource(R.drawable.ic_1);
                                    continue;
                                case 4:
                                    img_5.setImageResource(R.drawable.ic_1);
                                    continue;
                                case 5:
                                    img_6.setImageResource(R.drawable.ic_1);
                                    continue;
                            }
                        case 2:
                            switch (i) {
                                case 0:
                                    img_1.setImageResource(R.drawable.ic_2);
                                    continue;
                                case 1:
                                    img_2.setImageResource(R.drawable.ic_2);
                                    continue;
                                case 2:
                                    img_3.setImageResource(R.drawable.ic_2);
                                    continue;
                                case 3:
                                    img_4.setImageResource(R.drawable.ic_2);
                                    continue;
                                case 4:
                                    img_5.setImageResource(R.drawable.ic_2);
                                    continue;
                                case 5:
                                    img_6.setImageResource(R.drawable.ic_2);
                                    continue;
                            }
                        case 3:
                            switch (i) {
                                case 0:
                                    img_1.setImageResource(R.drawable.ic_3);
                                    continue;
                                case 1:
                                    img_2.setImageResource(R.drawable.ic_3);
                                    continue;
                                case 2:
                                    img_3.setImageResource(R.drawable.ic_3);
                                    continue;
                                case 3:
                                    img_4.setImageResource(R.drawable.ic_3);
                                    continue;
                                case 4:
                                    img_5.setImageResource(R.drawable.ic_3);
                                    continue;
                                case 5:
                                    img_6.setImageResource(R.drawable.ic_3);
                                    continue;
                            }
                        case 4:
                            switch (i) {
                                case 0:
                                    img_1.setImageResource(R.drawable.ic_4);
                                    continue;
                                case 1:
                                    img_2.setImageResource(R.drawable.ic_4);
                                    continue;
                                case 2:
                                    img_3.setImageResource(R.drawable.ic_4);
                                    continue;
                                case 3:
                                    img_4.setImageResource(R.drawable.ic_4);
                                    continue;
                                case 4:
                                    img_5.setImageResource(R.drawable.ic_4);
                                    continue;
                                case 5:
                                    img_6.setImageResource(R.drawable.ic_4);
                                    continue;
                            }
                        case 5:
                            switch (i) {
                                case 0:
                                    img_1.setImageResource(R.drawable.ic_5);
                                    continue;
                                case 1:
                                    img_2.setImageResource(R.drawable.ic_5);
                                    continue;
                                case 2:
                                    img_3.setImageResource(R.drawable.ic_5);
                                    continue;
                                case 3:
                                    img_4.setImageResource(R.drawable.ic_5);
                                    continue;
                                case 4:
                                    img_5.setImageResource(R.drawable.ic_5);
                                    continue;
                                case 5:
                                    img_6.setImageResource(R.drawable.ic_5);
                                    continue;
                            }
                        case 6:
                            switch (i) {
                                case 0:
                                    img_1.setImageResource(R.drawable.ic_6);
                                    continue;
                                case 1:
                                    img_2.setImageResource(R.drawable.ic_6);
                                    continue;
                                case 2:
                                    img_3.setImageResource(R.drawable.ic_6);
                                    continue;
                                case 3:
                                    img_4.setImageResource(R.drawable.ic_6);
                                    continue;
                                case 4:
                                    img_5.setImageResource(R.drawable.ic_6);
                                    continue;
                                case 5:
                                    img_6.setImageResource(R.drawable.ic_6);
                                    continue;
                            }
                        case 7:
                            switch (i) {
                                case 0:
                                    img_1.setImageResource(R.drawable.ic_7);
                                    continue;
                                case 1:
                                    img_2.setImageResource(R.drawable.ic_7);
                                    continue;
                                case 2:
                                    img_3.setImageResource(R.drawable.ic_7);
                                    continue;
                                case 3:
                                    img_4.setImageResource(R.drawable.ic_7);
                                    continue;
                                case 4:
                                    img_5.setImageResource(R.drawable.ic_7);
                                    continue;
                                case 5:
                                    img_6.setImageResource(R.drawable.ic_7);
                                    continue;
                            }
                        case 8:
                            switch (i) {
                                case 0:
                                    img_1.setImageResource(R.drawable.ic_8);
                                    continue;
                                case 1:
                                    img_2.setImageResource(R.drawable.ic_8);
                                    continue;
                                case 2:
                                    img_3.setImageResource(R.drawable.ic_8);
                                    continue;
                                case 3:
                                    img_4.setImageResource(R.drawable.ic_8);
                                    continue;
                                case 4:
                                    img_5.setImageResource(R.drawable.ic_8);
                                    continue;
                                case 5:
                                    img_6.setImageResource(R.drawable.ic_8);
                                    continue;
                            }
                        case 9:
                            switch (i) {
                                case 0:
                                    img_1.setImageResource(R.drawable.ic_9);
                                    continue;
                                case 1:
                                    img_2.setImageResource(R.drawable.ic_9);
                                    continue;
                                case 2:
                                    img_3.setImageResource(R.drawable.ic_9);
                                    continue;
                                case 3:
                                    img_4.setImageResource(R.drawable.ic_9);
                                    continue;
                                case 4:
                                    img_5.setImageResource(R.drawable.ic_9);
                                    continue;
                                case 5:
                                    img_6.setImageResource(R.drawable.ic_9);
                                    continue;
                            }
                    }

                }


                TextView timer;
                timer= dialog.findViewById(R.id.timer);
                new CountDownTimer(3000, 1000) {
                    @Override
                    public void onTick(long l) {
                        timer.setText( ""+l/1000);

                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                    }
                }.start();

                dialog.show();
            }
        });


        holder.newscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,ScannerView.class));
            }
        });


        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete account")
                        .setMessage("Are you sure you want to delete this?")
                        .setIcon(R.drawable.ic_delete)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                arrContacts.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                            }
                        });
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtname;
//        TextView txtnum;
        ImageView imagecontact;
        LinearLayout llRow;
        Button btndel;
        Button realmodify;
        Button newscan;
        TextView timer;
//        TextView txtviewtotp;
        ImageView img_1;
        ImageView img_2;
        ImageView img_3;
        ImageView img_4;
        ImageView img_5;
        ImageView img_6;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.txtname);
//            txtnum = itemView.findViewById(R.id.txtnum);
            imagecontact = itemView.findViewById(R.id.imgcontact);
            llRow = itemView.findViewById(R.id.llrow);
            btndel = itemView.findViewById(R.id.btndel);
            realmodify = itemView.findViewById(R.id.realmodify);
            newscan = itemView.findViewById(R.id.newscan);
            timer = itemView.findViewById(R.id.timer);
//            txtviewtotp = itemView.findViewById(R.id.txtviewtotp);
            img_1 = itemView.findViewById(R.id.img_1);
            img_2 = itemView.findViewById(R.id.img_2);
            img_3 = itemView.findViewById(R.id.img_3);
            img_4 = itemView.findViewById(R.id.img_4);
            img_5 = itemView.findViewById(R.id.img_5);
            img_6 = itemView.findViewById(R.id.img_6);
        }
    }
}