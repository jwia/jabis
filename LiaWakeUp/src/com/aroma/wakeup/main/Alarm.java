package com.aroma.wakeup.main;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.media.MediaPlayer;

import com.aroma.wakeup.R;


public class Alarm {

    private List<MediaPlayer> mPlayers;
    private MediaPlayer mMediaPlayer;
    private int mIndex;
    private Context mContext;
    
    private static final int[] RSC_IDS = {
            R.raw.warningsiren, R.raw.railroad, R.raw.tornadosiren, R.raw.alarm
    };
    
    public Alarm(Context context, int index){
        mIndex = index;
        mContext = context;
        mPlayers = new ArrayList<MediaPlayer>();
    }
    
    public boolean start() {
        mMediaPlayer = setMediaPlayer(mContext, mIndex);
        mMediaPlayer.start();
        mPlayers.add(mMediaPlayer);
        return true;
    }
    
    public boolean nextAlarm() {
        
        return true;
    }
    
    public boolean stop() {
        
        for (MediaPlayer player : mPlayers) {
            if (player.isLooping()) {
                player.release();
            }
        }
        
        mPlayers.clear();
        mPlayers = null;
        return true;
    }
    
    
    private MediaPlayer setMediaPlayer(Context context, int index) {
        MediaPlayer mp =  MediaPlayer.create(mContext, RSC_IDS[mIndex]);
        mp.setLooping(true);
        mp.setVolume(1f, 1f);
        return mp;
    }
    
    public static int getNumberOfAlarm() {
        return RSC_IDS.length;
    }
}
