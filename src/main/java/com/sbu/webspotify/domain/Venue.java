package com.sbu.webspotify.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "venue")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String address;

    private GeoLocation location;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public GeoLocation getLocation() {
        return location;
    }
}