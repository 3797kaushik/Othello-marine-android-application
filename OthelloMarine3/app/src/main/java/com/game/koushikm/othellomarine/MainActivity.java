package com.game.koushikm.othellomarine;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static int check=0;
    static  int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.music);
        mediaPlayer.start();        mediaPlayer.setVolume(1,1);

        mediaPlayer.setLooping(true);
    }


    public void highScore(View v){
        Intent i=new Intent(MainActivity.this,highScore.class);
        startActivity(i);
        finish();
    }

    public void doThis1(View v)
    {
        final CharSequence[] photo = {"Computer","Human"};
        //char[] gen;
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Select :");
        alert.setSingleChoiceItems(photo,-1, new
                DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(photo[which]=="Computer")
                        {
                            check=1;
                        }
                        else if (photo[which]=="Human")
                        {
                            check=2;
                        }
                        else
                        {
                        }
                    }
                });
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (check==1)
                {
                    Intent i=new Intent(MainActivity.this,ComputerGame.class);
                    startActivity(i);
                    finish();
                }
                else if(check==2){
                    Intent i=new Intent(MainActivity.this,HumanGame.class);
                    startActivity(i);
                    finish();
                }
                else if(check==0){

                    Toast t=new Toast(MainActivity.this);
                    t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    Toast.makeText(MainActivity.this,"Select any option", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });
        alert.setNegativeButton("Cancel",null);
        alert.show();
    }

    public void doThis2(View view){
        Intent i=new Intent(MainActivity.this,Settings.class);
        startActivity(i);
        finish();
    }
    public void rules(View view){
        Intent i=new Intent(MainActivity.this,GameRules.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater.inflate(R.menu.my_menu,menu);
        return true;
    }

}
