package com.aroma.wakeup.main;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.aroma.wakeup.R;

public class AlarmActivity extends Activity implements OnClickListener {

    private AlarmManager mAlarmManager;
    private Context mContext;
    private static final int NO_PLAYNG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       
       mContext = this;
       setContentView(R.layout.main);
       
       Button stop_btn = (Button)findViewById(R.id.stop_btn);
       stop_btn.setOnClickListener(this);
       
       mAlarmManager = AlarmManager.getCurrentAlarmManager();
    }

    @Override
    public void onClick(View v) {
        Log.d("jjw","Button Click : ");    

        if(mAlarmManager != null) {
            Log.d("jjw","Cancel Start!!!!!");    
            if(!mAlarmManager.AlarmOff()) { 
                mHandler.sendMessage(mHandler.obtainMessage(NO_PLAYNG));
            }
        }
    }
    
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NO_PLAYNG:
                    Toast.makeText(mContext, R.string.no_playing, Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }
      
        
    };
    
}
