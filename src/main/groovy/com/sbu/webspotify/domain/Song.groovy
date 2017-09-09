package com.sbu.webspotify.domain

import javax.persistence.*

import com.fasterxml.jackson.annotation.JsonIgnore


@Entity
@Table(name = 'song')
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id

    String title

    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonIgnore
    Album album

}
