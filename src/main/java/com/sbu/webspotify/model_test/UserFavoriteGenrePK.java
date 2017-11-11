package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_favorite_genre database table.
 * 
 */
@Embeddable
public class UserFavoriteGenrePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", insertable=false, updatable=false)
	private int userId;

	@Column(name="genre_id", insertable=false, updatable=false)
	private int genreId;

	public UserFavoriteGenrePK() {
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGenreId() {
		return this.genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserFavoriteGenrePK)) {
			return false;
		}
		UserFavoriteGenrePK castOther = (UserFavoriteGenrePK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.genreId == castOther.genreId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.genreId;
		
		return hash;
	}
}