package com.appdev.adtask1;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import java.util.Random;
import android.widget.TextView;
import  android.widget.RelativeLayout;

public class ClickCounter extends Activity {

    private int count=0;
    private int col=Color.BLACK;
    TextView Counted;
    RelativeLayout Bgrnd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_counter);
        Counted= (TextView) findViewById(R.id.count);

        //An Instance of Shared Preference is created and an XML file file created to persist data
        SharedPreferences Counter_Data=getSharedPreferences("Count_Data",MODE_PRIVATE);
        count= Counter_Data.getInt("Click_Count",0);
        col= Counter_Data.getInt("Click_Col",Color.BLACK);

        //Creating Instance of all the Xml objects to be Manipulated
        Bgrnd =(RelativeLayout) findViewById(R.id.Rel_Layout);
        String strCount= String.valueOf(count);
        //This is to ensure that the counter and Background color remain same after the activity is destroyed or Orientation is changed
        Counted.setText(strCount);
        Bgrnd.setBackgroundColor(col);

    }
    //The function that is called When the Hit_counter Button is clicked
    public void CounterColorChange(View v){

        Random rand = new Random();
        col=Color.argb(205,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
        Bgrnd.setBackgroundColor(col);
        count=count+1;

        //An Instance of Shared Preference is created and an Count_Data.XML file is created to persist data
        SharedPreferences Counter_Data=getSharedPreferences("Count_Data",MODE_PRIVATE);

        //An Edit function is used to change the values of the Count_Data.XML
        SharedPreferences.Editor Count_Edit = Counter_Data.edit();
        Count_Edit.putInt("Click_Count",count);
        Count_Edit.putInt("Click_Col",col);

        //commiting the changes to save the data in the Count_Data.XML
        Count_Edit.commit();
        String strCount= String.valueOf(count);
        Counted.setText(strCount);

    }
//A function that is called before the activity is destroyed
    public void onSaveInstanceState(Bundle savedInsState) {
        savedInsState.putInt("count",count);
        savedInsState.putInt("col",col);
    }
//A function to reset the counter and background color
    public void CounterReset(View v){

        count=0;
        col=Color.BLACK;
        Bgrnd.setBackgroundColor(col);
        String strCount= String.valueOf(count);
        Counted.setText(strCount);
        SharedPreferences Counter_Data=getSharedPreferences("Count_Data",MODE_PRIVATE);
        SharedPreferences.Editor Count_Edit = Counter_Data.edit();
        Count_Edit.putInt("Click_Count",count);
        Count_Edit.putInt("Click_Col",col);
        Count_Edit.commit();


    }



}


