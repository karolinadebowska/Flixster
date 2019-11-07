package com.codepath.apps.restclienttemplate.models;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
@Parcel
public class Tweet {

    public String body;
    public String createdAt;
    public User user;

    public Tweet(){}
    public static Tweet fromJson(JSONObject JsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = JsonObject.getString("text");
        tweet.createdAt=JsonObject.getString("created_at");
        tweet.user = User.fromJson(JsonObject.getJSONObject("user"));
        return tweet;
    }
    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List <Tweet> tweets = new ArrayList<>();
        for (int i =0;i<jsonArray.length();i++){
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
}
