package com.aroma.wakeup.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

import com.aroma.wakeup.main.AlarmManager;
import com.aroma.wakeup.utils.SmsUtils;
import com.aroma.wakeup.utils.Utils;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("jjw","Msg Receive!!");
        
        SmsMessage[] msgs = SmsUtils.getMessagesFromIntent(intent);
        
        if(msgs == null || msgs.length <= 0) {
            return;
        }
        
        final SmsMessage msg = msgs[0];
        
        if (msg != null && Utils.isMessageForWakeUp(msg.getMessageBody())) {
            Log.d("jjw","Msg Intercept Success!!");
            abortBroadcast();
            
            AlarmManager am = new AlarmManager(context);
            am.AlarmOn();

        }
    }
   
}
