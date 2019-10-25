package com.codepath.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.flixster.modules.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailActivity extends YouTubeBaseActivity {

    public final String YOUTUBE_API = "AIzaSyC7NwdvVjtN4O8oZNah7k3UR9m-oftEanM";
    //%d interesting
    public final String YOUTUBE_LINK = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    TextView tvtitle = findViewById(R.id.tvTitle);
    TextView tvOverview = findViewById(R.id.tvOverivew);
    RatingBar ratingBar = findViewById(R.id.rbVote);

    YouTubePlayerView youTubePlayerView = (YouTubePlayerView)findViewById(R.id.player);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvtitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        ratingBar.setRating((float) movie.getVote_average());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(YOUTUBE_LINK, movie.getMovieId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");
                    if (results.length() == 0)
                        return;
                    else {
                        String keyYoutube = results.getJSONObject(0).getString("key");
                        playYoutube(keyYoutube);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });
    }
    private void playYoutube (final String keyYoutube){
        youTubePlayerView.initialize(YOUTUBE_API,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo(keyYoutube);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }
}
