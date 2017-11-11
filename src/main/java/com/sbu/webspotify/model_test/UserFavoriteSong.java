package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_favorite_song database table.
 * 
 */
@Entity
@Table(name="user_favorite_song")
@NamedQuery(name="UserFavoriteSong.findAll", query="SELECT u FROM UserFavoriteSong u")
public class UserFavoriteSong implements Serializable {
	private static final long serialVersionUID = 1L;

	private Timestamp timestamp;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Song
	@ManyToOne
	private Song song;

	public UserFavoriteSong() {
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

	public Song getSong() {
		return this.song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

}