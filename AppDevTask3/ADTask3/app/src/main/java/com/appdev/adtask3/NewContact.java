package com.appdev.adtask3;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class NewContact extends AppCompatActivity {
    EditText Name,No;
    ImageView photo;
    Button pic,add;
    ContactStore CS;
    ContactDbHelper ContactDB;
    Bitmap bm=null;
    byte[] dp;
    //final String TAG="com.appdev.adtask3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        Name=(EditText)findViewById(R.id.nameView);
        No=(EditText)findViewById(R.id.noView);
        pic=(Button) findViewById(R.id.picChooser);
        add=(Button)findViewById(R.id.add_button);
        photo=(ImageView)findViewById(R.id.photoView);
        ContactDB=new ContactDbHelper(this,null,null,1);


    }
    public void addPhoto(View v){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra("crop", "true");
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, 1);
    }

    public void addContact(View v){
        String cName=Name.getText().toString();
        String cNo=No.getText().toString();
        if(bm==null){
            bm=((BitmapDrawable)getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
        }

        dp=ContactStore.getByte(bm);

        photo.setImageBitmap(bm);
        //Log.i(TAG, "addContact: 3");
        if(cName.equals("")||cNo.equals("")){
            Toast.makeText(getApplicationContext(),"Enter Contact Details",Toast.LENGTH_SHORT).show();
        }
        else{
            ContactStore temp=new ContactStore(cName,cNo,bm);
            boolean check=ContactDB.createContact(temp);
            if(check){
                Toast.makeText(getApplicationContext(),"Contact added",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Contact not added",Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent=new Intent(NewContact.this,Contacts.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            /**Bundle sim =data.getExtras();
            Bitmap bmp = (Bitmap) sim.get("data");
            photo.setImageBitmap(bmp);
            pic.requestFocus();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try{
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            }
            catch(NullPointerException e){
                Toast.makeText(getApplicationContext(),"Nothing stored",Toast.LENGTH_SHORT).show();
            }
            byte[] b = stream.toByteArray();
            String encode= Base64.encodeToString(b, Base64.DEFAULT);

            byte[] bytarray = Base64.decode(encode, Base64.DEFAULT);
            Bitmap bmimage = BitmapFactory.decodeByteArray(bytarray, 0,
                    bytarray.length);
            bm=bmimage;
             **/
            Uri selectedImage = data.getData();
            //String[] filePathColumn = { MediaStore.Images.Media.DATA };
            //Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            //cursor.moveToFirst();
            //int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            //String picturePath = cursor.getString(columnIndex);
            //cursor.close();
            //ImageView imageView = (ImageView) findViewById(R.id.photoView);
            //bm =BitmapFactory.decodeFile(picturePath);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            Bitmap bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            bm=bmp;
            photo.setImageBitmap(bmp);
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
