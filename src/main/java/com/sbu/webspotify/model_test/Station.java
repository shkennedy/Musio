package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the station database table.
 * 
 */
@Entity
@NamedQuery(name="Station.findAll", query="SELECT s FROM Station s")
public class Station implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	@Column(name="num_songs")
	private int numSongs;

	//bi-directional many-to-many association to Song
	@ManyToMany
	@JoinTable(
		name="song_station_mapping"
		, joinColumns={
			@JoinColumn(name="station_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="song_id")
			}
		)
	private List<Song> songs;

	//bi-directional many-to-one association to UserFavoriteStation
	@OneToMany(mappedBy="station")
	private List<UserFavoriteStation> userFavoriteStations;

	public Station() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumSongs() {
		return this.numSongs;
	}

	public void setNumSongs(int numSongs) {
		this.numSongs = numSongs;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public List<UserFavoriteStation> getUserFavoriteStations() {
		return this.userFavoriteStations;
	}

	public void setUserFavoriteStations(List<UserFavoriteStation> userFavoriteStations) {
		this.userFavoriteStations = userFavoriteStations;
	}

	public UserFavoriteStation addUserFavoriteStation(UserFavoriteStation userFavoriteStation) {
		getUserFavoriteStations().add(userFavoriteStation);
		userFavoriteStation.setStation(this);

		return userFavoriteStation;
	}

	public UserFavoriteStation removeUserFavoriteStation(UserFavoriteStation userFavoriteStation) {
		getUserFavoriteStations().remove(userFavoriteStation);
		userFavoriteStation.setStation(null);

		return userFavoriteStation;
	}

}