package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class ComposeActivity extends AppCompatActivity {
    private static final int MAX_LENGTH = 140;
    ImageButton ibSend;
    EditText etCompose;
    TwitterClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        client=TwitterApp.getRestClient(this);
        ibSend = findViewById(R.id.ibSend);
        etCompose = findViewById(R.id.etCompose);

        ibSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String userInput = etCompose.getText().toString();
                if (userInput.isEmpty()) {
                    Toast.makeText(ComposeActivity.this, "Sorry, you tweet cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if(userInput.length()>MAX_LENGTH){
                    Toast.makeText(ComposeActivity.this, "Sorry, you tweet is too long", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(ComposeActivity.this, "Tweet has been added!", Toast.LENGTH_SHORT).show();
                    client.publishTweet(userInput, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Headers headers, JSON json) {
                            try {
                                Tweet tweet = Tweet.fromJson(json.jsonObject);
                                Intent intent=new Intent();
                                intent.putExtra("tweet", Parcels.wrap(tweet));
                                setResult(RESULT_OK,intent);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

                        }
                    });
                //make an API call to Twitter
            }
        });

    }
}
