package com.example.rkjc.news_app_2;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class MyJobService extends JobService {

    private boolean verified = false;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d("SyncDataBase", "From MyJobService");
       NotificationUtils.CancelNotification(this);
        if (!verified) {
            NewsItemRepository.SyncDataBaseWithJob();
            verified = true;
        }

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
