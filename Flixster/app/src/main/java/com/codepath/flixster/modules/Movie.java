package com.codepath.flixster.modules;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    public String overview;
    public String title;
    public String poster;
    public Movie(JSONObject jsonObject) throws JSONException {
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
