package com.sbu.webspotify.domain;

import com.sbu.webspotify.domain.Album;
import com.sbu.webspotify.domain.Genre;
import com.sbu.webspotify.domain.Concert;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique=true)
    private String name;

    private String bio;

    private Collection<Album> albums;

    private Collection<Genre> genres;
    
    private Collection<Concert> concerts;

    private File bannerImage;

    private Collection<File> additionalImages;

    private int followerCount;

    private URL website;

    private GeoLocation location;

    // Probably should compute this per call & cache instead 
    // of store as DB entry since it will change so much
    // private Collection<Artist> relatedArtists;

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public Collection<Album> getAlbums() {
        return albums;
    }

    public Collection<Genre> getGenres() {
        return genres;
    }

    public Collection<Concert> getConcerts() {
        return concerts;
    }

    public File getBannerImage() {
        return bannerImage;
    }

    public Collection<File> getAdditionalImages() {
        return additionalImages;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public URL getWebSite() {
        return website;
    }

    public GeoLocation getGeoLocation() {
        return location;
    }
}
