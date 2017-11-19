package com.sbu.webspotify.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;


/**
 * The persistent class for the playlist database table.
 * 
 */
@Entity
@NamedQuery(name="Playlist.findAll", query="SELECT p FROM Playlist p")
public class Playlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="is_collaborative")
	private boolean isCollaborative;

	@Column(name="is_private")
	private boolean isPrivate;

	private String name;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="owner_id")
	private User owner;

	@ManyToMany(fetch=FetchType.EAGER,
	cascade = { 
        CascadeType.PERSIST, 
        CascadeType.MERGE
    })
	@JoinTable(
		name = "song_playlist_mapping",
		joinColumns = @JoinColumn(name = "playlist_id"),
		inverseJoinColumns = @JoinColumn(name = "song_id")
	)
	@JsonManagedReference
	private List<Song> songs;

	public Playlist() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getIsCollaborative() {
		return this.isCollaborative;
	}

	public void setIsCollaborative(boolean isCollaborative) {
		this.isCollaborative = isCollaborative;
	}

	public boolean getIsPrivate() {
		return this.isPrivate;
	}

	public void setIsPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getOwner() {
		return this.owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Playlist) {
            Playlist that = (Playlist) other;
            result = (this.getId() == that.getId());
        }
        return result;
    }

	@Override
	public int hashCode() {
        return Objects.hash(this.id);
    }

	public void addSong(Song s) {
		this.songs.add(s);
	}

	public boolean removeSong(Song s) {
		return this.songs.remove(s);
	}

}