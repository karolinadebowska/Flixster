package com.codepath.flixster.modules;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    //needed by Parceler library
    public Movie(){}
    public String overview;
    public String title;
    public String poster;
    public double vote_average;
    public Movie(JSONObject jsonObject) throws JSONException {
        vote_average=jsonObject.getDouble("vote_average");
       overview = jsonObject.getString("overview");
       title = jsonObject.getString("title");
       poster=jsonObject.getString("poster_path");
    }

    public static List<Movie> fromJsonArray(JSONArray moviesFromArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i=0;i<moviesFromArray.length();i++){
            movies.add(new Movie(moviesFromArray.getJSONObject(i)));
        }
        return movies;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    //%s means - replace that with second parameter
    public String getPoster() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",poster);
    }
}
