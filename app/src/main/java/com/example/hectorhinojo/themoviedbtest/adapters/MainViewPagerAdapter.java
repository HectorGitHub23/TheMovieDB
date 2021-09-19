package com.example.hectorhinojo.themoviedbtest.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.hectorhinojo.themoviedbtest.fragments.PlayingNowFragment;
import com.example.hectorhinojo.themoviedbtest.fragments.PopularFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    Context mContext;


    public MainViewPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0: fragment = PlayingNowFragment.newInstance(mContext); break;
            case 1: fragment = PopularFragment.newInstance(mContext); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Playing Now";
            case 1: return  "Most Popular";
            default: return "None";
        }
    }
}
