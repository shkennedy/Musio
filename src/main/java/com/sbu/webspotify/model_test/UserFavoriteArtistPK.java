package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_favorite_artist database table.
 * 
 */
@Embeddable
public class UserFavoriteArtistPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", insertable=false, updatable=false)
	private int userId;

	@Column(name="artist_id", insertable=false, updatable=false)
	private int artistId;

	public UserFavoriteArtistPK() {
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getArtistId() {
		return this.artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserFavoriteArtistPK)) {
			return false;
		}
		UserFavoriteArtistPK castOther = (UserFavoriteArtistPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.artistId == castOther.artistId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.artistId;
		
		return hash;
	}
}