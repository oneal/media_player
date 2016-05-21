package com.example.servicios;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import android.net.Uri;

import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ImageButton botonPlay;
    private boolean estadoPlay = false;
    private int valorDesplaza = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonPlay = (ImageButton)findViewById(R.id.btnPlay);
        SeekBarPlayer.setSeekBar((SeekBar) findViewById(R.id.seekBar));
        SeekBarPlayer.createListener();



    }

    public void botonRewind(View view) {
        int rewind = Reproductor.getPosicionCancion() - valorDesplaza;
        if(rewind < 0 ){
            rewind = 0;
        }
        Reproductor.getMediaPlayer().seekTo(rewind);
    }

    public void botonAvance(View view) {
        int forward = Reproductor.getPosicionCancion() + valorDesplaza;
        if(forward > Reproductor.getDuracionCancion()){
            forward = Reproductor.getDuracionCancion();
        }
        Reproductor.getMediaPlayer().seekTo(forward);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void botonStop(View view) {
        estadoPlay = false;
        botonPlay.setBackground(getResources().getDrawable(R.drawable.btnplay));
        stopService(new Intent(MainActivity.this, MyService.class));
        SeekBarPlayer.setProgressSeekBar(0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void botonPlay(View view) {
        if(!estadoPlay) {
            estadoPlay = true;
            botonPlay.setBackground(getResources().getDrawable(R.drawable.btnpause));
            startService(new Intent(MainActivity.this, MyService.class));
        } else {
            estadoPlay = false;
            botonPlay.setBackground(getResources().getDrawable(R.drawable.btnplay));
            Reproductor.pausarCancion();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.playList){
            Intent intent = new Intent(MainActivity.this, PlayList.class);
            startActivityForResult(intent, 100);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 100){

            int indiceCancion = data.getExtras().getInt("songIndex");

            HashMap<String,String> cancion = PlayList.getSongList().get(indiceCancion);

            Reproductor.pararCancion();

            botonPlay.setBackground(getResources().getDrawable(R.drawable.btnplay));

            Reproductor.setRutaCancion(cancion.get("dir"));


        }
    }
}
