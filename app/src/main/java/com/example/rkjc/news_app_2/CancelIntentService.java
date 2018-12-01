package com.example.rkjc.news_app_2;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class CancelIntentService extends IntentService{
    public CancelIntentService() {
        super("CancelIntenService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {


        String action = intent.getAction();
        Log.d("INTENT_Trigger", action);
        NotificationUtils.clearAllNotifications(this);

    }
}
