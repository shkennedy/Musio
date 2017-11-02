package com.sbu.webspotify.domain;

import javax.persistence.*;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Column(unique=true)
    public String name;

    public String bio;

}
