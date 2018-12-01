package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {


    private final LayoutInflater mInflater;

    private List<NewsItem> mNewsItems;

    private NewsItemViewModel viewModel;


    NewsRecyclerViewAdapter(Context context, NewsItemViewModel viewModel) {
        this.viewModel = viewModel;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.news_item, parent, false);

        return new NewsViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        if (mNewsItems != null)
            return mNewsItems.size();
        return  0;
    }


    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

        NewsItem currentNewsItem = mNewsItems.get(position);

        holder.title.setText(currentNewsItem.title);
        holder.description.setText(currentNewsItem.publishedAt + "  .  " + currentNewsItem.description);
        String imageUrl = currentNewsItem.getUrlToImage();
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(holder.image);
        }
    }



    void setNewsItems(List<NewsItem> items) {
        mNewsItems = items;
        notifyDataSetChanged();
    }

    public List<NewsItem> getmNewsItems() {
        return mNewsItems;
    }

    public void setmNewsItems(List<NewsItem> mNewsItems) {
        this.mNewsItems = mNewsItems;
        notifyDataSetChanged();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView title;
        private TextView description;

        private ImageView image;

        public NewsViewHolder(View itemView) {

            super(itemView);

            title =  itemView.findViewById(R.id.news_article_title);
            description = itemView.findViewById(R.id.news_article_description);
            image = itemView.findViewById(R.id.news_article_image);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            String url = mNewsItems.get(getAdapterPosition()).url;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
                v.getContext().startActivity(intent);
            }
        }


    }

}
