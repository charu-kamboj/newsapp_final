package com.example.rkjc.news_app_2;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;


@Dao
public interface NewsItemDao {
    // Query to load all news items.
    @Query("SELECT * from news_item")
    LiveData<List<NewsItem>> loadAllNewsItems();

    // Query to insert news items.
    @Insert
    void insertAll(List<NewsItem> items);

    // Query to delete news items from the Database.
    @Query("DELETE from news_item")
    void clearAll();

}
