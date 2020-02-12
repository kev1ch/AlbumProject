
package com.pavlov.albumproject;

import java.util.Date;

public class Album {
    
    private String artist;
    private String title;
    private Date release;
    
    public Album(String artist, String title, Date release) {
        this.artist = artist;
        this.title = title;
        this.release = release;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public Date getRelease() {
        return release;
    }
    
}