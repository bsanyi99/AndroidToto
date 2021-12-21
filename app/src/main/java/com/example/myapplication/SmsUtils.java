package com.example.myapplication;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SmsUtils {
    public void sendSMS(Context context, String msg){
        String phoneNumber = "1756";
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, msg, null, null);

        Toast.makeText(context, "SMS sikeresen elküldve!",
                Toast.LENGTH_LONG).show();

    }
}
