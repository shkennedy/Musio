package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_favorite_station database table.
 * 
 */
@Embeddable
public class UserFavoriteStationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", insertable=false, updatable=false)
	private int userId;

	@Column(name="station_id", insertable=false, updatable=false)
	private int stationId;

	public UserFavoriteStationPK() {
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStationId() {
		return this.stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserFavoriteStationPK)) {
			return false;
		}
		UserFavoriteStationPK castOther = (UserFavoriteStationPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.stationId == castOther.stationId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.stationId;
		
		return hash;
	}
}