package com.codepath.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.flixster.modules.Movie;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    TextView tvtitle = findViewById(R.id.tvTitle);
    TextView tvOverview = findViewById(R.id.tvOverivew);
    RatingBar ratingBar = findViewById(R.id.rbVote);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvtitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        ratingBar.setRating((float)movie.getVote_average());
    }
}
