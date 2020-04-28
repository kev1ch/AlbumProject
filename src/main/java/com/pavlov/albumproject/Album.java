
package com.pavlov.albumproject;

import java.util.Date;

public class Album {
    
    private String artist;
    private String title;
    private Date release;
    private long id;
    
    public Album(long id, String artist, String title, Date release) {
        this.id = id;
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
    
    public long getId() {
        return id;
    }
}