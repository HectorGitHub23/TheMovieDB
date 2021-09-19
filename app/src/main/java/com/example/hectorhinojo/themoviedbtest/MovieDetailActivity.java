package com.example.hectorhinojo.themoviedbtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hectorhinojo.themoviedbtest.model.MovieDetail;
import com.example.hectorhinojo.themoviedbtest.model.Video;
import com.example.hectorhinojo.themoviedbtest.model.VideoResult;
import com.example.hectorhinojo.themoviedbtest.retrofit.RetrofitService;
import com.example.hectorhinojo.themoviedbtest.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String TAG = "MovieDetailActivity";

    ImageView imagePoster, bgPoster;
    TextView movieName, movieYear, movieDate, movieGenres, movieTagline, movieOverview;
    Button trailerButton;
    boolean existVideo;
    String youtubeVideoKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imagePoster = findViewById(R.id.image_poster);
        bgPoster = findViewById(R.id.bg_poster);
        movieName = findViewById(R.id.movie_detail_name);
        movieYear = findViewById(R.id.movie_detail_year);
        movieDate = findViewById(R.id.movie_detail_date);
        movieGenres = findViewById(R.id.movie_detail_genres);
        movieTagline = findViewById(R.id.movie_detail_tagline);
        movieOverview = findViewById(R.id.movie_detail_overview);
        trailerButton = findViewById(R.id.trailer_button);

        int movieId = getIntent().getIntExtra("MOVIE_ID", -1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<MovieDetail> call = service.getMovieDetail(movieId, Util.API_KEY, Util.LANGUAGE);

        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful()) {
                    MovieDetail movieDetail = response.body();
                    movieName.setText(movieDetail.getTitle());
                    movieYear.setText(movieDetail.getYear());
                    movieDate.setText(movieDetail.getFormatDate());
                    movieGenres.setText(movieDetail.getStrGenres());
                    movieTagline.setText(movieDetail.getTagline());
                    movieOverview.setText(movieDetail.getOverview());

                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w500"+movieDetail.getPoster_path())
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imagePoster);
                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w500"+movieDetail.getBackdrop_path())
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(bgPoster);

                    Log.d(TAG,"retrofitResponse");
                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });


        Call<VideoResult> callVideos = service.getVideos(movieId, Util.API_KEY, Util.LANGUAGE);

        callVideos.enqueue(new Callback<VideoResult>() {
            @Override
            public void onResponse(Call<VideoResult> call, Response<VideoResult> response) {

                if (response.isSuccessful()) {
                    VideoResult videoResult = response.body();
                    if (videoResult.getResults().size() > 0) {
                        trailerButton.setTextColor(Color.WHITE);
                        existVideo = true;
                        youtubeVideoKey = videoResult.getResults().get(0).getKey();
                        if (videoResult.getResults().size() > 1) {
                            for(Video v : videoResult.getResults()) {
                                if (v.getType().equalsIgnoreCase("Trailer") && v.isOfficial()) {
                                    youtubeVideoKey = v.getKey();
                                }
                            }
                        }
                    }

                    Log.d(TAG,"retrofitResponse");
                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<VideoResult> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        trailerButton.setOnClickListener(v -> {
            if (existVideo) {
                Intent intent = new Intent(this, YouTubeViewerActivity.class);
                intent.putExtra("YOUTUBE_VIDEO_KEY", youtubeVideoKey);
                startActivity(intent);
            } else {
                Toast.makeText(this, "No hay videos disponibles.", Toast.LENGTH_LONG).show();
            }
        });
    }
}