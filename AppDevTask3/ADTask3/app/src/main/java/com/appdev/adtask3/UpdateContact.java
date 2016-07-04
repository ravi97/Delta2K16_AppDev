package com.appdev.adtask3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.StandaloneActionMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

public class UpdateContact extends AppCompatActivity {

    ContactDbHelper up;
    ContactStore inst;
    EditText uNo;
    ImageView udPic;
    Button uLoad,uPic;
    Bitmap bitPic;
    byte[] bytePic;

    String name;
    String No;
    String TAG="com.appdev.adtask3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        uNo=(EditText)findViewById(R.id.noChange);
        uLoad=(Button)findViewById(R.id.save_button);
        uPic=(Button)findViewById(R.id.upic_button);

        udPic=(ImageView)findViewById(R.id.dpDisp);

        Intent intent =getIntent();
        Bundle prevDet=intent.getExtras();
        name=prevDet.getString("cName");
        No=prevDet.getString("cNo");
        bitPic=intent.getParcelableExtra("cImg");
        setTitle(name);
        uNo.setText(No);
        udPic.setImageBitmap(bitPic);
        bytePic=ContactStore.getByte(bitPic);

    }

    public void saveContact(View v){
        No=uNo.getText().toString();
        Log.i(TAG, "saveContact: 12");
        if(No.equals("")){
            Toast.makeText(UpdateContact.this,"Enter Some no",Toast.LENGTH_SHORT).show();
        }
        else{
            ContactStore temp=new ContactStore(name,No,bitPic);
            Log.i(TAG, "saveContact: 23");
            ContactDbHelper upDb=new ContactDbHelper(this,null,null,1);
            boolean check=upDb.updateContact(temp);
            Log.i(TAG, "saveContact: 14");
            if(check){
                Toast.makeText(UpdateContact.this,"Saved",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(UpdateContact.this,"Saving Failed",Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent=new Intent(UpdateContact.this,Contacts.class);
        startActivity(intent);
    }

    public void updatePic(View v){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra("crop", "true");
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            /**Bitmap bmp = (Bitmap) data.getExtras().get("data");
            udPic.setImageBitmap(bmp);
            uLoad.requestFocus();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try{
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            }
            catch(NullPointerException e){
                Toast.makeText(getApplicationContext(),"Nothing stored",Toast.LENGTH_SHORT).show();
            }
            byte[] b = stream.toByteArray();
            String encode = Base64.encodeToString(b, Base64.DEFAULT);

            byte[] bytarray = Base64.decode(encode, Base64.DEFAULT);
            Bitmap bmimage = BitmapFactory.decodeByteArray(bytarray, 0,
                    bytarray.length);
            bitPic=bmimage;
             **/
            Uri selectedImage = data.getData();
            Bitmap bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            bitPic=bmp;
            udPic.setImageBitmap(bmp);


        }
    }
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

}
