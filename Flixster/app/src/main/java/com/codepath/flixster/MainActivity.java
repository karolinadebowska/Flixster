package com.codepath.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.flixster.adapters.MovieAdapter;
import com.codepath.flixster.modules.Movie;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String NOW_PLAYING = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "Main Activity";
    List<Movie> movies=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find a recyclerview
        RecyclerView recyclerviewer = (RecyclerView)findViewById(R.id.rvMovies);
        //create a movieAdapter
        final MovieAdapter myAdapter = new MovieAdapter(this, movies);
        //attach the adapter to a recycler view
        recyclerviewer.setAdapter(myAdapter);
        recyclerviewer.setLayoutManager(new LinearLayoutManager(this));
        //setLayoutmanager

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.e(TAG,"on success");
                //create JSON object ?
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i("results : ",results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    myAdapter.notifyDataSetChanged();
                    for (int i = 0;i<movies.size();i++){
                        Log.i("movies",movies.get(i).getTitle());
                    }
                    Log.i("movies : ",movies.toString());
                } catch (JSONException e) {
                    Log.e(TAG,"Json exception",e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.e(TAG,"on failure");
            }
        });
    }
}