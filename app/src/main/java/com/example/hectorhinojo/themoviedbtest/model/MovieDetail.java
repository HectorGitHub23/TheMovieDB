package com.example.hectorhinojo.themoviedbtest.model;

import java.util.List;

public class MovieDetail {
    private int id;
    private List<Genre> genres;
    private String title;
    private String release_date;
    private String overview;
    private String tagline;
    private String poster_path;
    private String backdrop_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getStrGenres() {
        String strGenres = "";
        for(int i = 0; i < genres.size(); i++) {
            strGenres+=genres.get(i).getName();
            if (i < (genres.size() - 1)) {
                strGenres+=", ";
            }
        }
        return strGenres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getYear() {
        return "(" + release_date.substring(0,4) + ")";
    }

    public String getFormatDate() {
        return release_date.substring(8) + "/" + release_date.substring(5,7) + "/"+release_date.substring(0,4);
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
