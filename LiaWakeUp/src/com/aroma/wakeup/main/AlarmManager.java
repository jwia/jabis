
package com.aroma.wakeup.main;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.aroma.wakeup.utils.Utils;

public class AlarmManager {

    private Timer mTimer;
    private TimerTask mTimerTask;
    private int mIndex = 0;
    private Context mContext;
    private static AlarmManager sAlarmManager;
    private int mCurrentVolume;
    private boolean mIsNomalRingerMode;

    private static final int TIME_OVER = 1;
    Long time;
    Handler mHandelr = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case TIME_OVER:
                    AlarmOff();
                    break;

                default:
                    break;
            }
        }

    };

    public AlarmManager(Context context) {
        mContext = context;
        mTimer = new Timer();
        sAlarmManager = this;
    }

    public boolean AlarmOn() {
        mIsNomalRingerMode = Utils.isRingerModeNomal(mContext);
        if (!mIsNomalRingerMode) {
            Utils.setRingerModeNomal(mContext);
        }
        mCurrentVolume = Utils.getCurrentVolume(mContext);
        Utils.setMaxSystemVolume(mContext);

        mTimerTask = new TimerTask() {

            @Override
            public void run() {
                if (mIndex == Alarm.getNumberOfAlarm()) {
                    mTimer.purge();
                    mTimer.cancel();
                    mHandelr.sendMessageDelayed(Message.obtain(mHandelr, TIME_OVER), 60000);
                    return;
                }
                Alarm alarm = new Alarm(mContext, mIndex++);
                alarm.start();
            }
        };

        mTimer.schedule(mTimerTask, 0, 15000);
        return true;
    }

    public boolean AlarmOff() {
        Log.d("jjw", " TIME OVER Stop");
        if (mTimer != null) {
            mTimer.purge();
            mTimer.cancel();
        }

        if (!mIsNomalRingerMode) {
            Utils.restoreRingerMode(mContext);
        }
        Utils.setRestoreVolume(mContext, mCurrentVolume);

        return true;

    }

    public static AlarmManager getCurrentAlarmManager() {
        return sAlarmManager;
    }

}
