
package com.example.rkjc.news_app_2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.rkjc.news_app_2.MyJobService;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

public class ReminderUtilities {


    private static final String REMINDER_JOB_TAG = "news_item_sync";

    private static boolean sInitialized;

    synchronized public static void scheduleChargingReminder(@NonNull final Context context) {

        if (sInitialized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        Log.d("SyncDataBase", "Going to start job");
        Job constraintReminderJob = dispatcher.newJobBuilder()

                .setService(MyJobService.class)
                .setTag(REMINDER_JOB_TAG)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(0, 10))
                .setReplaceCurrent(true)
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .build();


        dispatcher.mustSchedule(constraintReminderJob);


        sInitialized = true;
    }

}
