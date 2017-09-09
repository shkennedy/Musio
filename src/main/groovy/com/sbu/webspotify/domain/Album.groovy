package com.sbu.webspotify.domain

import javax.persistence.*

@Entity
@Table(name = 'album')
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    @Column(unique=true)
    String title

    @OneToMany(targetEntity = Song.class, mappedBy = "album", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Song> songs;

    @OneToOne
    Artist artist

}
