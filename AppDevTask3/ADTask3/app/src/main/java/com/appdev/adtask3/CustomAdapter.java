package com.appdev.adtask3;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by SasiDKM on 02-07-2016.
 */
public class CustomAdapter extends ArrayAdapter<String>{
    Context context;
    public Bitmap[] imags;
    public String[] no;

    TextView cName,cNo;
    ImageView cImg;

    public CustomAdapter(Context context, String[] names, String[] nos, Bitmap[] images) {
        super(context,R.layout.contact_row,names);
        this.imags=images;
        this.no=nos;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater=LayoutInflater.from(getContext());
        View myView= myInflater.inflate(R.layout.contact_row,parent,false);
        final String Name=getItem(position);

        cName=(TextView)myView.findViewById(R.id.contact_name);
        cNo=(TextView)myView.findViewById(R.id.contact_no);
        cImg=(ImageView)myView.findViewById(R.id.contact_image);
        cName.setText(Name);
        cNo.setText(no[position]);
        cImg.setImageBitmap(imags[position]);

        return myView;
    }
}
