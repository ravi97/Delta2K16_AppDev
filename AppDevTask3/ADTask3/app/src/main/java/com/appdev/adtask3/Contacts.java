package com.appdev.adtask3;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Contacts extends AppCompatActivity{

    private static final int CONTACT_CREATE=0;
    private static final int CONTACT_EDIT=1;

    ListView cList;

    private ContactDbHelper cdbh;
    private Cursor c;
    ContactStore cs;
    Bitmap[] image;
    String[] name;
    String[] no;
    EditText search;
    List<Bitmap> imageList= new ArrayList<>();
    List<String> nameList=new ArrayList<>();
    List<String> noList=new ArrayList<>();
    String TAG="com.appdev.adtask3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        search=(EditText)findViewById(R.id.search_field);
        cList=(ListView)findViewById(R.id.contact_list);
        cdbh= new ContactDbHelper(this,null,null,1);
        fillData();
        image= new Bitmap[imageList.size()];
        name= new String [nameList.size()];
        no= new String[noList.size()];
        int temp=0;
        while(temp<nameList.size()){
            image[temp]=imageList.get(temp);
            name[temp]=nameList.get(temp);
            no[temp]=noList.get(temp);
            temp++;
        }

        ListAdapter myAdapter =new CustomAdapter(Contacts.this,name,no,image);
        cList.setAdapter(myAdapter);

        AdapterView.OnItemClickListener itemClickListener =new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tName=String.valueOf(parent.getItemAtPosition(position));
                String tNo=no[position];
                ImageView img=(ImageView)view.findViewById(R.id.contact_image);
                //img.setDrawingCacheEnabled(true);
                //Bitmap i=img.getDrawingCache();
                Bitmap i=image[position];
                //Cursor r=cdbh.fetchContact(tName);
                //Bitmap i=ContactStore.getImage(r.getBlob(2));
                Bundle details =new Bundle();
                Intent intent=new Intent(Contacts.this,ContactDetails.class);
                details.putParcelable("cimg",i);
                intent.putExtra("cname",tName);
                intent.putExtra("cno",tNo);
                intent.putExtras(details);
                startActivity(intent);
            }
        };

        cList.setOnItemClickListener(itemClickListener);


    }

    public void searchStart(View v){
        Pattern tempPat = Pattern.compile("^[0-9]+$");
        String serNo=search.getText().toString();
        boolean result=false;
        if(tempPat.matcher(serNo).matches()){
            for(int i=0;i<no.length;i++){
                if(no[i].equals(serNo)){
                    Toast.makeText(getApplicationContext(),"Name: "+name[i],Toast.LENGTH_LONG).show();
                    result=true;
                    break;
                }
            }
        }
        else{
            for(int i=0;i<name.length;i++){
                if(name[i].equalsIgnoreCase(serNo)){
                    Toast.makeText(getApplicationContext(),"ContactNo: "+no[i],Toast.LENGTH_LONG).show();
                    result=true;
                    break;
                }
            }
        }
        if(!result)
            Toast.makeText(getApplicationContext(),"Contact not found",Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater iMenu=getMenuInflater();
        iMenu.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.new_contact:
                Intent intent=new Intent(this,NewContact.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillData() {

        Cursor result=cdbh.fetchAllRows();

        if(result.getCount()==0){
            return;
        }
        while(result.moveToNext()){
            imageList.add(ContactStore.getImage(result.getBlob(2)));
            nameList.add(result.getString(0));
            noList.add(result.getString(1));

        }
    }
}
