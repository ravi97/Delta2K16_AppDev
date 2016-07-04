package com.appdev.adtask3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class ContactDetails extends AppCompatActivity {
    Bitmap bMap;
    ImageView uImg;
    TextView conName,conNo;
    Button delete,update;
    String Cname,Cno;
    String TAG="com.appdev.adtask3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        uImg=(ImageView)findViewById(R.id.dpView);
        conName=(TextView)findViewById(R.id.cNameView);
        conNo=(TextView)findViewById(R.id.cNoView);
        delete=(Button)findViewById(R.id.delete_button);
        update=(Button) findViewById(R.id.update_button);

        Intent intent=getIntent();
        Bundle content=intent.getExtras();
        bMap= (Bitmap) content.get("cimg");
        Cname=content.getString("cname");
        Cno = content.getString("cno");
        uImg.setImageBitmap(bMap);
        conName.setText(Cname);
        conNo.setText(Cno);

    }

    public void changeContact(View v){
        //Log.i(TAG, "changeContact: 1");
        Bitmap temp=bMap;
        Intent intent=new Intent(this,UpdateContact.class);
        //Log.i(TAG, "changeContact: 2");
        ByteArrayOutputStream stream =new ByteArrayOutputStream();
        //Log.i(TAG, "changeContact: 4");
        temp.compress(Bitmap.CompressFormat.JPEG,100,stream);
        //Log.i(TAG, "changeContact: 3");
        intent.putExtra("cImg",temp);
        intent.putExtra("cName",Cname);
        intent.putExtra("cNo",Cno);
        startActivity(intent);
    }

    public void deleteContact(View v){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setMessage("Are you sure want to delete this contact?").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ContactDbHelper cdb = new ContactDbHelper(ContactDetails.this,null,null,1);
                cdb.deleteContact(Cname);
                Toast.makeText(getApplicationContext(), "Data Deleted SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ContactDetails.this,Contacts.class);
                startActivity(intent);
            }
        })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog ad = adb.create();
        ad.setTitle("DELETE CONTACT");
        ad.show();

    }
}
