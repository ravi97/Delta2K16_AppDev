package com.appdev.adtask3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by SasiDKM on 01-07-2016.
 */
public class ContactStore {
    private String _name;
    private String _no;
    private Bitmap bm;
    byte[] bt;

    public ContactStore(){

    }

    public ContactStore(String _name,String _no,Bitmap bm) {
        this._name = _name;
        this._no=_no;
        this.bm=bm;
    }


    public String get_name() {
        return _name;
    }

    public String get_no() {
        return _no;
    }

    public Bitmap getBm() {
        return bm;
    }

    public static byte[] getByte (Bitmap b){
        ByteArrayOutputStream stream =new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG,100,stream);
        return stream.toByteArray();
    }

    public  static Bitmap getImage(byte[] image){
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        return BitmapFactory.decodeStream(imageStream);
    }

}
