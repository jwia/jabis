package com.aroma.wakeup.utils;

import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;

public class SmsUtils {
    
    public static final SmsMessage[] getMessagesFromIntent(Intent intent) {
        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
        
        if (messages == null || messages.length == 0) {
            return null;
        }

        byte[][] pduObjs = new byte[messages.length][];
        
        for (int i = 0; i < messages.length; i++) {
            pduObjs[i] = (byte[]) messages[i];
        }
        
        byte[][] pdus = new byte[pduObjs.length][];
        int pduCount = pdus.length;
        SmsMessage[] msgs = new SmsMessage[pduCount];
        
        for (int i = 0; i < pduCount; i++) {
            pdus[i] = pduObjs[i];
            msgs[i] = SmsMessage.createFromPdu(pdus[i]);
        }

        return msgs;
    }
    
    /**
     * Send an SMS with <code>message</code> to <code>address</code>.
     * 
     * @param context
     * @param address
     * @param message
     */
    public static boolean sendMessage(Context context, String address, String message) {
        if (context == null || TextUtils.isEmpty(address) || TextUtils.isEmpty(message)) {
            return false;
        }
        
        // FIXME: check the routine and what we have to do here.
        //PendingIntent sentIntent  = PendingIntent.getBroadcast(context, 0, null, 0);
        //PendingIntent deliveredIntent = PendingIntent.getBroadcast(context, 0, null, 0);
        
        SmsManager smsManager = SmsManager.getDefault();
        
        try {
             smsManager.sendTextMessage(address, null, message, null, null);
            //sms.sendTextMessage(address, null, message, sentIntent, deliveredIntent);
        } catch (IllegalArgumentException e) {
            return false;
        }
       
        return true;
    }
}
