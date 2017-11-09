package com.sbu.webspotify.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "concert")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private GeoLocation location;

    private Date dateTime;

    private Venue venue;

    private List<Artist> artists;

    public Integer getId() {
        return id;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Venue getVenue() {
        return venue;
    }

    public List<Artist> getArtists() {
        return artists;
    }
}