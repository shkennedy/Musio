package com.sbu.webspotify.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the artist database table.
 * 
 */
@Entity
@NamedQuery(name="Artist.findAll", query="SELECT a FROM Artist a")
public class Artist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Lob
	private String bio;

	private String mbid;

	private String name;

	@Column(name="sort_name")
	private String sortName;

	private String website;

	@ManyToOne
	@JoinColumn(name="music_label_user_id")
	private User musicLabel;

	@ManyToOne
	@JoinColumn(name="geo_location_id")
	private GeoLocation geoLocation;

	@Column(name="artist_art_id")
	private File artistImage;

	@Column(name="artist_art_background_id")
	private File backgroundArt;

	public Artist() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getMbid() {
		return this.mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSortName() {
		return this.sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public User getUser() {
		return this.musicLabel;
	}

	public void setUser(User user) {
		this.musicLabel = user;
	}

	public GeoLocation getGeoLocation() {
		return this.geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	public void setBackgroundArt(File backgroundArt) {
		this.backgroundArt = backgroundArt;
	}

	public File getBackgroundArt() {
		return this.backgroundArt;
	}

	public void setArtistImage(File artistImage) {
		this.artistImage = artistImage;
	}

	public File getArtistImage() {
		return this.artistImage;
	}

}