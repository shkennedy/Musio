package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_favorite_album database table.
 * 
 */
@Entity
@Table(name="user_favorite_album")
@NamedQuery(name="UserFavoriteAlbum.findAll", query="SELECT u FROM UserFavoriteAlbum u")
public class UserFavoriteAlbum implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserFavoriteAlbumPK id;

	private Timestamp timestamp;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Album
	@ManyToOne
	private Album album;

	public UserFavoriteAlbum() {
	}

	public UserFavoriteAlbumPK getId() {
		return this.id;
	}

	public void setId(UserFavoriteAlbumPK id) {
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

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}