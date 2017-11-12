package com.sbu.webspotify.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
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
	private byte isCollaborative;

	@Column(name="is_private")
	private byte isPrivate;

	private String name;

	@Column(name="num_songs")
	private int numSongs;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="owner_id")
	private User user;

	@ManyToMany(cascade = { 
        CascadeType.PERSIST, 
        CascadeType.MERGE
    })
	@JoinTable(
		name = "song_album_mapping",
		joinColumns = @JoinColumn(name = "album_id"),
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

	public byte getIsCollaborative() {
		return this.isCollaborative;
	}

	public void setIsCollaborative(byte isCollaborative) {
		this.isCollaborative = isCollaborative;
	}

	public byte getIsPrivate() {
		return this.isPrivate;
	}

	public void setIsPrivate(byte isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumSongs() {
		return this.numSongs;
	}

	public void setNumSongs(int numSongs) {
		this.numSongs = numSongs;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

}