package com.game.koushikm.othellomarine;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

public class Settings extends AppCompatActivity {
    int t = 0;
    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(R.layout.activity_settings);
        initControls();

        MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.music);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
       // int maxVolume = 50;
        //float log1=(float)(Math.log(maxVolume-0)/Math.log(maxVolume));
        mediaPlayer.setVolume(1,1);
    }

    private void initControls() {
        try {
            volumeSeekbar = (SeekBar) findViewById(R.id.seekBar1);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));


            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
//                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void return1(View v){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
