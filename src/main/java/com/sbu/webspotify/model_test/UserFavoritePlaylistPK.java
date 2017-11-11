package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_favorite_playlist database table.
 * 
 */
@Embeddable
public class UserFavoritePlaylistPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", insertable=false, updatable=false)
	private int userId;

	@Column(name="playlist_id", insertable=false, updatable=false)
	private int playlistId;

	public UserFavoritePlaylistPK() {
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPlaylistId() {
		return this.playlistId;
	}
	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserFavoritePlaylistPK)) {
			return false;
		}
		UserFavoritePlaylistPK castOther = (UserFavoritePlaylistPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.playlistId == castOther.playlistId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.playlistId;
		
		return hash;
	}
}