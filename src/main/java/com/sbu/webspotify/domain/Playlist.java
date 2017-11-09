package com.sbu.webspotify.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private User owner;

    private boolean isPrivate;

    private boolean isCollaborative;
    
    private boolean isStation;
    
    private boolean isHistory;

    private List<Song> songs;

    private Date dateCreated;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public boolean isCollaborative() {
        return isCollaborative;
    }

    public boolean isStation() {
        return isStation;
    }

    public boolean isHistory() {
        return isHistory;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}