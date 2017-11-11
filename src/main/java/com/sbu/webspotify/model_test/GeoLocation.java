package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the geo_location database table.
 * 
 */
@Entity
@Table(name="geo_location")
@NamedQuery(name="GeoLocation.findAll", query="SELECT g FROM GeoLocation g")
public class GeoLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private BigDecimal latitude;

	private BigDecimal longitude;

	//bi-directional many-to-one association to Artist
	@OneToMany(mappedBy="geoLocation")
	private List<Artist> artists;

	//bi-directional many-to-one association to Performer
	@OneToMany(mappedBy="geoLocation")
	private List<Performer> performers;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="geoLocation")
	private List<User> users;

	//bi-directional many-to-one association to Venue
	@OneToMany(mappedBy="geoLocation")
	private List<Venue> venues;

	public GeoLocation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public List<Artist> getArtists() {
		return this.artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public Artist addArtist(Artist artist) {
		getArtists().add(artist);
		artist.setGeoLocation(this);

		return artist;
	}

	public Artist removeArtist(Artist artist) {
		getArtists().remove(artist);
		artist.setGeoLocation(null);

		return artist;
	}

	public List<Performer> getPerformers() {
		return this.performers;
	}

	public void setPerformers(List<Performer> performers) {
		this.performers = performers;
	}

	public Performer addPerformer(Performer performer) {
		getPerformers().add(performer);
		performer.setGeoLocation(this);

		return performer;
	}

	public Performer removePerformer(Performer performer) {
		getPerformers().remove(performer);
		performer.setGeoLocation(null);

		return performer;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setGeoLocation(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setGeoLocation(null);

		return user;
	}

	public List<Venue> getVenues() {
		return this.venues;
	}

	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}

	public Venue addVenue(Venue venue) {
		getVenues().add(venue);
		venue.setGeoLocation(this);

		return venue;
	}

	public Venue removeVenue(Venue venue) {
		getVenues().remove(venue);
		venue.setGeoLocation(null);

		return venue;
	}

}