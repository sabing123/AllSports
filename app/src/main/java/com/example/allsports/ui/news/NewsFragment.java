package com.example.allsports.ui.news;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.allsports.R;
import com.example.allsports.ui.notifications.NotificationsViewModel;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Struct;

public class NewsFragment extends Fragment {

    private NewsViewModel NewsViewModel;

    private TextView tvtittle;
    class News extends AsyncTask<String, Void, String> {// first String means Url is in string, Void mean nothing, Third String means return type will be in string


        @Override
        protected String doInBackground(String... address) {
//String... means multiple address can be send. It acts as array
            try {
                URL url = new URL(address[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //Establish connection with address
                connection.connect();

                //retrieve data from url
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                //Retrieve data and return it as String
                int data = isr.read();
                String content = "";
                char ch;
                while (data != -1) {
                    ch = (char) data;
                    content = content + ch;
                    data = isr.read();
                }
                Log.i("Content", content);
                return content;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
        public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_news, container, false);
     tvtittle = root.findViewById(R.id.tvtitle);
        newsdisplay();

        return root;
    }

    private void newsdisplay() {

String content;
        News news = new News();
        try {
            content = news.execute("http://newsapi.org/v2/everything?q=bitcoin&from=2020-03-04&sortBy=publishedAt&apiKey=82f79cb22f8d45efa0fd0b6a7da4e538").get();
            //First we will check data is retrieve successfully or not
            Log.i("contentData", content);
//JSON
            JSONObject jsonObject = new JSONObject(content);
            String newsDATA = jsonObject.getString("articles");
            String NewsReport = jsonObject.getString("source");

        }catch (Exception e) {
            e.printStackTrace();
        }
        }



    }
