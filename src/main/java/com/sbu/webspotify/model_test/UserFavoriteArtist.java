package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_favorite_artist database table.
 * 
 */
@Entity
@Table(name="user_favorite_artist")
@NamedQuery(name="UserFavoriteArtist.findAll", query="SELECT u FROM UserFavoriteArtist u")
public class UserFavoriteArtist implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserFavoriteArtistPK id;

	private Timestamp timestamp;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Artist
	@ManyToOne
	private Artist artist;

	public UserFavoriteArtist() {
	}

	public UserFavoriteArtistPK getId() {
		return this.id;
	}

	public void setId(UserFavoriteArtistPK id) {
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

	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

}