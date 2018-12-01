package com.example.rkjc.news_app_2;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;


import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {



    private NewsItemRepository newsItemRepository;


    private LiveData<List<NewsItem>> mAllNewsItems;

    public NewsItemViewModel (Application application) {
        super(application);
        newsItemRepository = new NewsItemRepository(application);
        mAllNewsItems = newsItemRepository.getAllNewsItems();
    }

    public LiveData<List<NewsItem>> getmAllNewsItems() {
        return mAllNewsItems;
    }


    public void insert(List<NewsItem> newsItem) {
        newsItemRepository.insert(newsItem);
    }

    public void clearAll(){
        newsItemRepository.clearAll();
    }

    public void SyncData() {
        newsItemRepository.SyncDatabase();
    }

}
