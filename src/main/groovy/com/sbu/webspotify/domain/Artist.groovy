package com.sbu.webspotify.domain

import javax.persistence.*

@Entity
@Table(name = 'artist')
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id

    @Column(unique=true)
    String name


}
