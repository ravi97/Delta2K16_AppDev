package com.appdev.countdowntimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class CountDown extends Activity {
        private int sec=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
    }
    public void onClickStart(View v)
    {

         EditText secView= (EditText)findViewById(R.id.time_edit);
         String getSec =secView.getText().toString();
         sec=Integer.parseInt(getSec);
         Intent intent = new Intent(this,CountDownDisplay.class);
         intent.putExtra(String.valueOf(CountDownDisplay.times),sec);
         startActivity(intent);
    }
}
