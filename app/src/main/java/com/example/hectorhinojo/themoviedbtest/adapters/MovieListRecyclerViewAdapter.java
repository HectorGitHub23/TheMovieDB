package com.example.hectorhinojo.themoviedbtest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hectorhinojo.themoviedbtest.R;
import com.example.hectorhinojo.themoviedbtest.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieListRecyclerViewAdapter extends RecyclerView.Adapter<MovieListRecyclerViewAdapter.ViewHolder> {

    Context mContext;
    List<Movie> data;

    public MovieListRecyclerViewAdapter(Context context) {
        mContext = context;
        data = new ArrayList<>();
    }

    public MovieListRecyclerViewAdapter(Context context, List<Movie> data) {
        mContext = context;
        this.data = data;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.movieTitle.setText(String.valueOf(data.get(position).getTitle()));
        viewHolder.releaseDate.setText(String.valueOf(data.get(position).getRelease_date()));
        Glide.with(mContext)
                .load("https://image.tmdb.org/t/p/w500"+data.get(position).getPoster_path())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.movieImage);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }



    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView movieImage;
        public final TextView movieTitle;
        public final TextView releaseDate;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            movieImage = view.findViewById(R.id.movie_image);
            movieTitle = view.findViewById(R.id.movie_title);
            releaseDate = view.findViewById(R.id.movie_release_date);

        }

    }

    public void setMovieList(List<Movie> movies) {
        this.data.addAll(movies);
        notifyDataSetChanged();
    }

    public List<Movie> getMoviesList() {
        return data;
    }
}
