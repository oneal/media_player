package com.example.servicios;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by raulvelaruiz on 13/05/16.
 */
public class PlayList extends ListActivity {

    private static ArrayList<HashMap<String,String>> songList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);

        SongManager songManager = new SongManager();
        Log.d("Entra", "list");
        songList =  songManager.getPlayerList();

        ListAdapter adapter = new SimpleAdapter(this, songList, R.layout.playliste_item, new String[] { "song" }, new int[] { R.id.item });


        setListAdapter(adapter);

        ListView lv = getListView();
        // listening to single listitem click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // getting listitem index
                int songIndex = position;

                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        MainActivity.class);
                // Sending songIndex to PlayerActivity
                in.putExtra("songIndex", songIndex);
                setResult(100, in);
                // Closing PlayListView
                finish();
            }
        });
    }

    public static ArrayList<HashMap<String,String>> getSongList(){
        return songList;
    }
}
