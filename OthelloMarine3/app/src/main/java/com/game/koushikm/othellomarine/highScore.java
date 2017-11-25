package com.game.koushikm.othellomarine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class highScore extends AppCompatActivity {

    TextView tv1,tv2,tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.music);
        mediaPlayer.start();        mediaPlayer.setVolume(1,1);

        mediaPlayer.setLooping(true);
       SharedPreferences sp=getSharedPreferences("score",MODE_PRIVATE);
        int blacks=sp.getInt("black",0);
        int whites=sp.getInt("white",0);
        tv1=(TextView) findViewById(R.id.balck_score);
        tv1.setText(String.valueOf(blacks));
         tv2=(TextView) findViewById(R.id.white_score);
        tv2.setText(String.valueOf(whites));

        String s="";
         tv3=(TextView) findViewById(R.id.concl);
        if(blacks==0 & whites==0){
            s="Neither black nor White has high score";
        }
        else{

            if(blacks>whites) {
                s = "blacks wins by the "+blacks+" Runs with the difference of "+(blacks-whites)+" points";
            }
            else if(blacks==whites){
                s="Neither black nor White has high score";
            }
            else{
                s = "whites wins by the "+whites+" Runs with the difference of "+(whites-blacks)+" points";
            }
        }
        tv3.setText(s);

//        fun();
    }
    //////////////////////////////////////////
    public void reset1(View v){
        SharedPreferences sp=getSharedPreferences("score",MODE_PRIVATE);
        SharedPreferences.Editor spe=sp.edit();

        spe.putInt("black",0);
        spe.putInt("white",0);
        spe.commit();


        int blacks=sp.getInt("black",0);
        int whites=sp.getInt("white",0);
//        TextView tv1=(TextView) v.findViewById(R.id.black_score);
        tv1.setText(String.valueOf(blacks));
//        TextView tv2=(TextView) v.findViewById(R.id.white_score);
        tv2.setText(String.valueOf(whites));

        String s="";
//        TextView tv3=(TextView) v.findViewById(R.id.concl);
        if(blacks==0 & whites==0){
            s="Neither black nor White has high score";
        }
        else{

            if(blacks<whites) {
                s = "blacks wins by the "+blacks+" Runs with the difference of "+(blacks-whites)+" points";
            }
            else if(blacks==whites){
                s="Neither black nor White has high score";
            }
            else{
                s = "whites wins by the "+whites+" Runs with the difference of "+(whites-blacks)+" points";
            }
        }
        tv3.setText(s);


    }
//////////////////////////////////////////////
    public void return1(View v){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
