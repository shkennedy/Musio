package com.sbu.webspotify.domain;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique=true)
    private String title;

    @OneToMany(targetEntity = Song.class, mappedBy = "album", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Song> songs;

    @OneToOne
    private Artist artist;

    private int numSongs;

    private Set<Genre> genres;

    private File albumArt;

    private Date releaseDate;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getNumSongs() {
        return numSongs;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public File getAlbumArt() {
        return albumArt;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}
