package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_following_user database table.
 * 
 */
@Entity
@Table(name="user_following_user")
@NamedQuery(name="UserFollowingUser.findAll", query="SELECT u FROM UserFollowingUser u")
public class UserFollowingUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private Timestamp timestamp;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="follower")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_being_followed")
	private User user2;

	public UserFollowingUser() {
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}