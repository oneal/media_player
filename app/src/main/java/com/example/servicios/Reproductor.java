package com.example.servicios;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

public class Reproductor {

    private static MediaPlayer mediaPlayer = new MediaPlayer();
    private static String rutaCancion;

    public static MediaPlayer getMediaPlayer() { return mediaPlayer; }

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        Reproductor.mediaPlayer = mediaPlayer;
    }

    public static void iniciarCancion(Context context){
        Uri uri = Uri.parse(rutaCancion);
        Log.d("cancionEnviada", rutaCancion);
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(context, uri);
        mediaPlayer.start();
    }

    public static void pausarCancion(){ mediaPlayer.pause(); }

    public static int getDuracionCancion(){ return mediaPlayer.getDuration(); }

    public static int getPosicionCancion(){ return mediaPlayer.getCurrentPosition(); }

    public static void setRutaCancion(String ruta){
        rutaCancion = ruta;
    }

    public static void pararCancion(){
        mediaPlayer.stop();
    }

}