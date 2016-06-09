package com.appdev.adtask2;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class SpeechGet extends AppCompatActivity {

    private final int REQ_CODE_SPEECH_INPUT =100;
    String Cmnd;
    String Size;
    String Shp;
    JavaCan jc;
    //ImageButton bnSpeak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jc = new JavaCan(SpeechGet.this);
        setContentView(jc);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Shp="square";
        Size="small";

        //bnSpeak=(ImageButton)findViewById(R.id.btnTalk);

        /**
          bnSpeak.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
            promptSpeechInput();
             }
          });
         **/

        StringBuffer buffer = new StringBuffer();
        buffer.append("PLEASE HAVE A INTERNET CONNECTION\n Press 'CLICK TO TALK' to make the app listen to your voice\n"+"POSITION\n'UP': Move it up\n'Down': Move it down\n'Left': Move it to the left\n'Right': Move it to the right\nSHAPE" +
                "\n'Circle': Transform to circle\n'Square': Transform to square\n'Rectangle':Transform to rectangle" +
                "\nSIZE\n'Small': Make it small\n'Medium': Make it medium" + "\n'Large': Make it large");


        AlertDialog.Builder help= new AlertDialog.Builder(SpeechGet.this);
        help.setMessage(buffer.toString()).setCancelable(false).setPositiveButton("I Understood", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int chuma) {
                dialog.cancel();
            }
        });
        AlertDialog alert = help.create();
        alert.setTitle("Help");
        alert.show();



    }
   @Override
    public boolean onCreateOptionsMenu(Menu menu){
//inflate the menu. This adds options to the actionbar if it is present
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id=item.getItemId();
        switch(id) {

        case R.id.VoiceCommand: {

            promptSpeechInput();
            break;
        }
            default:
                Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }


    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a){
            Toast.makeText(SpeechGet.this,"OOPS,your device doesn't understand spoken commands",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_SPEECH_INPUT && resultCode == RESULT_OK) {
            List<String> Said = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Cmnd = Said.get(0);

        }

        ObjectMove();

    }

    public void ObjectMove(){
               switch (Cmnd){
            case "up":
                jc.putPos(0,-30);
                break;
            case "down":
                jc.putPos(0,30);
                break;
            case "left":
                jc.putPos(-30,0);
                break;
            case "right":
                jc.putPos(30,0);
                break;
            case "small":
                Size = "small";
                jc.putSize(Shp,Size);
                break;
            case "medium":
                Size = "medium";
                jc.putSize(Shp,Size);
                break;
            case "large":
                Size = "large";
                jc.putSize(Shp,Size);
                break;
            case "square":
                Shp = "square";
                jc.putSize(Shp,Size);
                break;
            case "rectangle":
                Shp = "rectangle";
                jc.putSize(Shp,Size);
                break;
            case "circle":
                Shp = "circle";
                jc.putSize(Shp,Size);
                break;
            default:
                 Toast.makeText(SpeechGet.this,Cmnd+" is not a valid word", Toast.LENGTH_SHORT).show();
        }
    }
}

