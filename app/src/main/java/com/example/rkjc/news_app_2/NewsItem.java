package com.example.rkjc.news_app_2;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


// Our new defined entity for DAO.
@Entity(tableName = "news_item")
public class NewsItem {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private int id;

    @NonNull
    @ColumnInfo(name="author")
    String author;
    @NonNull
    @ColumnInfo(name="title")
    String title;
    @NonNull
    @ColumnInfo(name="description")
    String description;
    @NonNull
    @ColumnInfo(name="url")
    String url;
    @NonNull
    @ColumnInfo(name="urlToImage")
    String urlToImage;
    @NonNull
    @ColumnInfo(name="publishedAt")
    String publishedAt;


    public NewsItem(String author, String title, String description, String url, String urlToImage, String publishedAt, int id) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.id = id;
    }

    @Ignore
    public NewsItem(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }



    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }


    public int getId(){
        return id;
    }

}
