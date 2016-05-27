package com.appdev.countdowntimer;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CountDownDisplay extends AppCompatActivity {
    public static final int times = 0;
    private int totalTime;
    private boolean running=false;
    private boolean wasRunning=true;
    RelativeLayout time_disp;
    ToneGenerator beep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_display);
        Intent intent = getIntent();
        totalTime = intent.getIntExtra(String.valueOf(times),0);
        running=true;
        beep = new ToneGenerator(AudioManager.STREAM_ALARM, 80);
        time_disp=(RelativeLayout)findViewById(R.id.timeDisp);
        runTimer();
    }

    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.timeView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = totalTime / 3600;
                int mins = (totalTime % 3600) / 60;
                int seconds = totalTime % 60;
                String time = String.format("%d:%02d:%02d", hours, mins, seconds);
                timeView.setText(time);
                if (running==true&&wasRunning==true) {
                    totalTime--;
                    if (totalTime < 10) {
                        time_disp.setBackgroundColor(Color.RED);
                        beep.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
                    }
                    if (totalTime <= 0) {
                        wasRunning = false;
                        Toast.makeText(CountDownDisplay.this,"Countdown terminated",Toast.LENGTH_SHORT);
                    }
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }
}

