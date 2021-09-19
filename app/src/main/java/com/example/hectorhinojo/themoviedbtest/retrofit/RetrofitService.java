package com.example.hectorhinojo.themoviedbtest.retrofit;

import com.example.hectorhinojo.themoviedbtest.model.MovieDetail;
import com.example.hectorhinojo.themoviedbtest.model.MoviesResult;
import com.example.hectorhinojo.themoviedbtest.model.VideoResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("now_playing")
    Call<MoviesResult> listPlayingNow(@Query("api_key") String apiKey);

    @GET("now_playing")
    Call<MoviesResult> listPlayingNow(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("now_playing")
    Call<MoviesResult> listPlayingNow(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("popular")
    Call<MoviesResult> listPopular(@Query("api_key") String apiKey);

    @GET("popular")
    Call<MoviesResult> listPopular(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("popular")
    Call<MoviesResult> listPopular(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("{movie_id}")
    Call<MovieDetail> getMovieDetail(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("{movie_id}/videos")
    Call<VideoResult> getVideos(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("language") String language);
}
