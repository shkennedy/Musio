package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_favorite_station database table.
 * 
 */
@Entity
@Table(name="user_favorite_station")
@NamedQuery(name="UserFavoriteStation.findAll", query="SELECT u FROM UserFavoriteStation u")
public class UserFavoriteStation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserFavoriteStationPK id;

	private Timestamp timestamp;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Station
	@ManyToOne
	private Station station;

	public UserFavoriteStation() {
	}

	public UserFavoriteStationPK getId() {
		return this.id;
	}

	public void setId(UserFavoriteStationPK id) {
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

	public Station getStation() {
		return this.station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

}