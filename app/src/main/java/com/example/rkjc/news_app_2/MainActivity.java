package com.example.rkjc.news_app_2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private NewsItemViewModel mNewsItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.news_recyclerview);


        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);


        final NewsRecyclerViewAdapter adapter = new NewsRecyclerViewAdapter(this, mNewsItemViewModel);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        mNewsItemViewModel.getmAllNewsItems().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                adapter.setmNewsItems(newsItems);
            }
        });

        ReminderUtilities.scheduleChargingReminder(this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {

            mNewsItemViewModel.SyncData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
