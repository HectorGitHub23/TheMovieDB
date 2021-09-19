package com.example.hectorhinojo.themoviedbtest.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hectorhinojo.themoviedbtest.MovieDetailActivity;
import com.example.hectorhinojo.themoviedbtest.R;
import com.example.hectorhinojo.themoviedbtest.adapters.MovieListRecyclerViewAdapter;
import com.example.hectorhinojo.themoviedbtest.listeners.RecyclerItemClickListener;
import com.example.hectorhinojo.themoviedbtest.model.MoviesResult;
import com.example.hectorhinojo.themoviedbtest.retrofit.RetrofitService;
import com.example.hectorhinojo.themoviedbtest.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayingNowFragment extends Fragment {
    private static final String TAG = "MovieListFragment";

    //private GridLayoutManager layoutManager;
    private static Context mConext;
    MovieListRecyclerViewAdapter movieListRecyclerViewAdapter;
    //private static RetrofitService retrofitService;
    TextView failureMessage;


    public PlayingNowFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PlayingNowFragment newInstance(Context context) {
        PlayingNowFragment fragment = new PlayingNowFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        mConext = context;
        //retrofitService = service;
        //layoutManager = new GridLayoutManager(context, 3);



        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieListRecyclerViewAdapter = new MovieListRecyclerViewAdapter(mConext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playing_now, container, false);

        failureMessage = view.findViewById(R.id.failure_message_playing_now);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<MoviesResult> call = service.listPlayingNow(Util.API_KEY, Util.LANGUAGE);

        call.enqueue(new Callback<MoviesResult>() {
            @Override
            public void onResponse(Call<MoviesResult> call, Response<MoviesResult> response) {
                if (response.isSuccessful()) {
                    movieListRecyclerViewAdapter.setMovieList(response.body().getResults());
                    Log.d(TAG,"retrofitResponse");
                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<MoviesResult> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                failureMessage.setVisibility(View.VISIBLE);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_playing_now);
        GridLayoutManager layoutManager = new GridLayoutManager(mConext, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieListRecyclerViewAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(mConext, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(mConext, MovieDetailActivity.class);
                intent.putExtra("MOVIE_ID", movieListRecyclerViewAdapter.getMoviesList().get(position).getId());
                startActivity(intent);

            }
        }));
        return view;

    }
}