package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_favorite_genre database table.
 * 
 */
@Entity
@Table(name="user_favorite_genre")
@NamedQuery(name="UserFavoriteGenre.findAll", query="SELECT u FROM UserFavoriteGenre u")
public class UserFavoriteGenre implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserFavoriteGenrePK id;

	private Timestamp timestamp;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Genre
	@ManyToOne
	private Genre genre;

	public UserFavoriteGenre() {
	}

	public UserFavoriteGenrePK getId() {
		return this.id;
	}

	public void setId(UserFavoriteGenrePK id) {
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

	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}