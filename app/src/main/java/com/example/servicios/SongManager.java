package com.example.servicios;

import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by raulvelaruiz on 13/05/16.
 */
public class SongManager {
    final String MEDIA_PATH = new String("/sdcard/");
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

    public ArrayList<HashMap<String,String>> getPlayerList(){
        File dir = new File(MEDIA_PATH);
        Log.d("cancion","hola");
        if (dir.listFiles((FilenameFilter) new FileExtensionFilter()).length > 0) {
            for (File file : dir.listFiles((FilenameFilter) new FileExtensionFilter())) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("song", file.getName());
                hashMap.put("dir",file.getPath());
                songsList.add(hashMap);
            }
        }
        return songsList;
    }

    private class FileExtensionFilter implements FilenameFilter, FileFilter {
        @Override
        public boolean accept(File pathname) {
            return false;
        }

        @Override
        public boolean accept(File dir, String filename) {
            return ((filename.endsWith(".mp3"))||(filename.endsWith(".MP3")));
        }
    }
}
