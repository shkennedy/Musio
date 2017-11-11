package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_playlist_following database table.
 * 
 */
@Entity
@Table(name="user_playlist_following")
@NamedQuery(name="UserPlaylistFollowing.findAll", query="SELECT u FROM UserPlaylistFollowing u")
public class UserPlaylistFollowing implements Serializable {
	private static final long serialVersionUID = 1L;

	private Timestamp time;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Playlist
	@ManyToOne
	private Playlist playlist;

	public UserPlaylistFollowing() {
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Playlist getPlaylist() {
		return this.playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

}