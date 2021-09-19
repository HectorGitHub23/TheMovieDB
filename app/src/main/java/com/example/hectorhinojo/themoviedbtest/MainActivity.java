package com.example.hectorhinojo.themoviedbtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.hectorhinojo.themoviedbtest.adapters.MainViewPagerAdapter;
import com.example.hectorhinojo.themoviedbtest.model.MoviesResult;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TabLayout tabLayout;
    ViewPager viewPager;
    MainViewPagerAdapter mainViewPagerAdapter;


    MoviesResult playingNowResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*playingNowResult = new PlayingNowResult();
        List<Result> results = new ArrayList<>();
        Result r = new Result();
        r.setId(1);
        r.setOriginal_title("Soy Leyenda");
        r.setPoster_path("/bZnOioDq1ldaxKfUoj3DenHU7mp.jpg");
        results.add(r);

        playingNowResult.setPage(1);
        playingNowResult.setResults(results);*/

        viewPager = findViewById(R.id.viewPager);

        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), this);

        //movieListRecyclerViewAdapter.setMovieList(playingNowResult.getResults());
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);







    }
}