package com.sbu.webspotify.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @Column(unique=true)
    public String title;

    @OneToMany(targetEntity = Song.class, mappedBy = "album", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Song> songs;;

    @OneToOne
    public Artist artist;

}
