package com.appdev.adtask3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.graphics.Bitmap;

import java.io.FileNotFoundException;

/**
 * Created by SasiDKM on 01-07-2016.
 */
public class ContactDbHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="contactDB";
    private static final String TABLE_NAME="myContacts";
    private static final int DATABASE_VERSION=1;

    public static final String KEY_NAME="name";
    public static final String KEY_NO="no";
    public static final String KEY_IMAGE="image_data";
    public static final String PROJECTION[]= {
            KEY_NAME,
            KEY_NO,
            KEY_IMAGE
    };

    private static final String CREATE_TABLE = "create table "+TABLE_NAME+
                                "("+KEY_NAME+" text not null,"
                                   +KEY_NO+" text,"
                                   +KEY_IMAGE+" blob);";

    private SQLiteDatabase cdb;

    public ContactDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean createContact(ContactStore cn){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,cn.get_name());
        values.put(KEY_NO,cn.get_no());
        values.put(KEY_IMAGE,ContactStore.getByte(cn.getBm()));
        SQLiteDatabase db=getWritableDatabase();
        long i= db.insert(TABLE_NAME,null,values);
        db.close();
        return !(i==-1);

    }

    public void deleteContact(String name){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NAME,KEY_NAME+"=?",new String[]{name});
    }

    public Cursor fetchContact(String name) throws SQLException{
        SQLiteDatabase db=getWritableDatabase();
        Cursor result= db.query(TABLE_NAME,PROJECTION,KEY_NAME+"=?",new String[]{name},null,null,null);
        if((result.getCount()==0)||(!result.isFirst())){
            throw new SQLException("No Note matching ID: "+name);
        }
        return result;
    }

    public Cursor fetchAllRows(){
        SQLiteDatabase db=getWritableDatabase();
        return db.query(TABLE_NAME,PROJECTION,null,null,null,null,KEY_NAME);
    }

    public Bitmap giveDp(String Cname){
        Cursor r= fetchContact(Cname);
        Bitmap btm=ContactStore.getImage(r.getBlob(2));
        return btm;
    }

    public boolean updateContact(ContactStore cn){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues args= new ContentValues();
        args.put(KEY_NAME,cn.get_name());
        args.put(KEY_NO,cn.get_no());
        args.put(KEY_IMAGE,ContactStore.getByte(cn.getBm()));
        return db.update(TABLE_NAME,args,KEY_NAME+"=?",new String[]{cn.get_name()})>0;

    }

}
