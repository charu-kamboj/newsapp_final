package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewsItemRepository {

    private LiveData<List<NewsItem>> mAllNewsItem;
    private static NewsItemDao mNewsItemDao;

    public NewsItemRepository(Application application){
        NewsItemRoomDatabase db = NewsItemRoomDatabase.getDatabase(application.getApplicationContext());
        mNewsItemDao = db.newsItemDao();
        mAllNewsItem = mNewsItemDao.loadAllNewsItems();
    }

    LiveData<List<NewsItem>> getAllNewsItems() {
        return mAllNewsItem;
    }

    public void clearAll() {
        new deleteAllAsyncTask().execute(mNewsItemDao);
    }

    public void insert(List<NewsItem> newsItems) { mNewsItemDao.insertAll(newsItems); }


    public void SyncDatabase() {

        new deleteAllAsyncTask().execute(mNewsItemDao);
    }

    public static void SyncDataBaseWithJob() {
        new deleteAllAsyncTask().execute(mNewsItemDao);
    }




    public static class NewsQueryTask extends AsyncTask<URL, Void, Void> {

        private NewsItemDao mAsyncTaskDao;


        NewsQueryTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(URL... params) {
            URL searchUrl = params[0];
            try {

                mAsyncTaskDao.insertAll(JsonUtils.parseNews(NetworkUtils.getResponseFromHttpUrl(searchUrl)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

   private static class deleteAllAsyncTask extends AsyncTask<NewsItemDao, Void, Void> {

        @Override
        protected Void doInBackground(NewsItemDao... params) {

            params[0].clearAll();
            String search = "the-next-web";
            URL newsSearchUrl = NetworkUtils.SearchArticle(search);

            new NewsQueryTask(params[0]).execute(newsSearchUrl);
            return null;
        }
    }

}
