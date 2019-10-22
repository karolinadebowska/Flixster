package com.codepath.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.flixster.DetailActivity;
import com.codepath.flixster.MainActivity;
import com.codepath.flixster.R;
import com.codepath.flixster.modules.Movie;

import org.apache.commons.cli.Parser;
import org.parceler.Parcels;

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
        Movie movie = movies.get(position);
        holder.bind(movie);
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
        RecyclerView container;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            overView = itemView.findViewById(R.id.overView);
            poster = itemView.findViewById(R.id.poster);
            container=itemView.findViewById(R.id.container);
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

        public void bind(final Movie movie){
            title.setText(movie.getTitle());
            overView.setText(movie.getOverview());

            Glide.with(context).load(movie.getPoster()).into(poster);
            // final String popularity = jsonObject.getString("popularity");
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context,DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });
        }
    }
}
