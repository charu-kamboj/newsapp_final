package com.example.rkjc.news_app_2;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    final static String base_url = "https://newsapi.org/v1/articles";
    final static String query_parameter = "source";
    final static String query_api = "apiKey";
    final static String api_key = "96ac03bbd97244a3a0b42c34514e2671";


    public static URL SearchArticle(String articleName) {
        Uri buildUri = Uri.parse(base_url).buildUpon()
                .appendQueryParameter(query_parameter, articleName)
                .appendQueryParameter(query_api, api_key)
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return  scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
