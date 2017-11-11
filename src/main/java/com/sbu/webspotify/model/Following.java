package com.sbu.webspotify.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the following database table.
 * 
 */
@Entity
@NamedQuery(name="Following.findAll", query="SELECT f FROM Following f")
public class Following implements Serializable {
	private static final long serialVersionUID = 1L;

	private int follower;

	@Column(name="user_being_followed")
	private int userBeingFollowed;

	public Following() {
	}

	public int getFollower() {
		return this.follower;
	}

	public void setFollower(int follower) {
		this.follower = follower;
	}

	public int getUserBeingFollowed() {
		return this.userBeingFollowed;
	}

	public void setUserBeingFollowed(int userBeingFollowed) {
		this.userBeingFollowed = userBeingFollowed;
	}

}