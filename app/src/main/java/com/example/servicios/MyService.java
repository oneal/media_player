package com.example.servicios;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static com.example.servicios.Reproductor.*;

public class MyService extends Service {

    private HiloCancion hilo;
    private NotificationCompat.Builder mBuilder;
    private int notification_id = 001;
    private NotificationManager mNotifyMgr;
    public MyService() {    }

    @Override
    public void onCreate() {
        super.onCreate();
        hilo = new HiloCancion();
        hilo.execute();
        mBuilder =
            new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.btnplay)
                    .setContentTitle("Reproducciendo")
                    .setContentText("Hello World!");
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        mBuilder.setContentIntent(pendingIntent);
        mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        iniciarCancion(getApplicationContext());
        mNotifyMgr.notify(notification_id,mBuilder.build());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        try {
            hilo.cancel(true);
            pararCancion();
            finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("No Implementado");
    }


    class HiloCancion extends AsyncTask <Void, Integer, Void> {

        private int duracionCancion = 0;
        private int currentPosition = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("hola",getMediaPlayer()+"");
            duracionCancion = getDuracionCancion();
            SeekBarPlayer.getSeekBar().setMax(duracionCancion);
        }

        @Override
        protected Void doInBackground(Void... params) {
            while (getMediaPlayer() != null && currentPosition < duracionCancion) {
                currentPosition = getPosicionCancion();
                publishProgress(currentPosition);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            SeekBarPlayer.setProgressSeekBar(values[0]);
        }

        @Override
        protected void onCancelled() {
            SeekBarPlayer.setProgressSeekBar(0);
        }
    }

}
