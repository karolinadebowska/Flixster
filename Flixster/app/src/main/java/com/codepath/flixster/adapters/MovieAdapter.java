package com.codepath.flixster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.flixster.MainActivity;
import com.codepath.flixster.R;
import com.codepath.flixster.modules.Movie;

import java.net.URL;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    Context context;
    List<Movie> movies;
    //Movie Adapter constructor
    public MovieAdapter(MainActivity context, List<Movie> movies){
        this.context= context;
        this.movies = movies;
    }
    //to inflate(get) a layout from XML file and  create a new ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View movieView = inflater.inflate(R.layout.movie_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    //populate the data in a ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.getTitle().setText(movies.get(position).getTitle());
       holder.getOverView().setText(movies.get(position).getOverview());
       Glide.with(context).load(movies.get(position).getPoster()).into(holder.getPoster());
    }

    //returns the total number of items in adapter
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView overView;
        ImageView poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            overView = itemView.findViewById(R.id.overView);
            poster = itemView.findViewById(R.id.poster);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getOverView() {
            return overView;
        }

        public ImageView getPoster() {
            return poster;
        }
    }
}
