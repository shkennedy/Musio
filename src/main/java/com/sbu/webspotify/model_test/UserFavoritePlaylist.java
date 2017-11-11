package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_favorite_playlist database table.
 * 
 */
@Entity
@Table(name="user_favorite_playlist")
@NamedQuery(name="UserFavoritePlaylist.findAll", query="SELECT u FROM UserFavoritePlaylist u")
public class UserFavoritePlaylist implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserFavoritePlaylistPK id;

	private Timestamp timestamp;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Playlist
	@ManyToOne
	private Playlist playlist;

	public UserFavoritePlaylist() {
	}

	public UserFavoritePlaylistPK getId() {
		return this.id;
	}

	public void setId(UserFavoritePlaylistPK id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
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