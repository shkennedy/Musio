package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_listening_history database table.
 * 
 */
@Entity
@Table(name="user_listening_history")
@NamedQuery(name="UserListeningHistory.findAll", query="SELECT u FROM UserListeningHistory u")
public class UserListeningHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	private Timestamp timestamp;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Song
	@ManyToOne
	private Song song;

	public UserListeningHistory() {
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