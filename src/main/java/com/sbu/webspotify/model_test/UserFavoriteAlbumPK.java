package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_favorite_album database table.
 * 
 */
@Embeddable
public class UserFavoriteAlbumPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", insertable=false, updatable=false)
	private int userId;

	@Column(name="album_id", insertable=false, updatable=false)
	private int albumId;

	public UserFavoriteAlbumPK() {
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAlbumId() {
		return this.albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserFavoriteAlbumPK)) {
			return false;
		}
		UserFavoriteAlbumPK castOther = (UserFavoriteAlbumPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.albumId == castOther.albumId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.albumId;
		
		return hash;
	}
}