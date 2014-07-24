package com.aroma.wakeup.utils;

import android.content.Context;
import android.media.AudioManager;
import android.text.TextUtils;
import android.util.Log;

public class Utils {
    
    private final static String WAKEUP_MESSAGE = "�Ͼ!!!";
    
    public static boolean isMessageForWakeUp(String msgBody) {
        if(TextUtils.isEmpty(msgBody)) {
            return false;
        }
        Log.d("jjw","interpret msg : " + msgBody);
        return msgBody.contains(WAKEUP_MESSAGE);
    }

    public static boolean setMaxSystemVolume(Context context) {
        if(context == null) {
            return false;
        }
            
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(AudioManager.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_VIBRATE);
        return true;
    }
    
    public static boolean setRingerModeNomal(Context context) {
        if(context == null) {
            return false;
        }
        
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        return true;
    }
    
    public static boolean setRestoreVolume(Context context, int previousVolume) {
        if(context == null) {
            return false;
        }
        
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(AudioManager.STREAM_MUSIC, previousVolume, AudioManager.FLAG_PLAY_SOUND);
        return true;
    }
    
    public static int getCurrentVolume(Context context) {
        if(context == null) {
            return -1;
        }
        
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return am.getStreamVolume(AudioManager.STREAM_MUSIC);
    }
    
    public static boolean isRingerModeNomal(Context context) {
        if(context == null) {
            return false;
        }
        
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if(am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL) {
            return true;
        }
        
        return false;
    }
    
    public static boolean restoreRingerMode(Context context) {
        if(context == null) {
            return false;
        }
        
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        return true;
    }
}
