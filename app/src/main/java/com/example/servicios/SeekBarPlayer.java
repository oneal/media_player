package com.example.servicios;

import android.media.MediaPlayer;
import android.widget.SeekBar;

public class SeekBarPlayer {

    private static SeekBar seekBar;


    public static void createListener(){

        getSeekBar().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {  }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {  }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int positionSeekBar = seekBar.getProgress();
                if(Reproductor.getMediaPlayer() != null){
                    Reproductor.getMediaPlayer().seekTo(positionSeekBar);
                }
            }
        });
    }

    public static void setProgressSeekBar(int currentPosition) {
        seekBar.setProgress(currentPosition);
    }

    public static SeekBar getSeekBar(){
        return seekBar;
    }

    public static void setSeekBar(SeekBar seek){
        seekBar = seek;
    }


}
