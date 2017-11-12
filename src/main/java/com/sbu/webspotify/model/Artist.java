package com.sbu.webspotify.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	@Column(name="artist_art_background_id")
	private int artistArtBackgroundId;

	@Column(name="artist_art_id")
	private int artistArtId;

	@Lob
	private String bio;

	private String mbid;

	private String name;

	@Column(name="sort_name")
	private String sortName;

	private String website;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="music_label_user_id")
	private User user;

	//bi-directional many-to-one association to GeoLocation
	@ManyToOne
	@JoinColumn(name="geo_location_id")
	private GeoLocation geoLocation;

	//bi-directional many-to-one association to Song
	@OneToMany(mappedBy="artist")
	private List<Song> songs;

	public Artist() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArtistArtBackgroundId() {
		return this.artistArtBackgroundId;
	}

	public void setArtistArtBackgroundId(int artistArtBackgroundId) {
		this.artistArtBackgroundId = artistArtBackgroundId;
	}

	public int getArtistArtId() {
		return this.artistArtId;
	}

	public void setArtistArtId(int artistArtId) {
		this.artistArtId = artistArtId;
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
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GeoLocation getGeoLocation() {
		return this.geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Song addSong(Song song) {
		getSongs().add(song);
		song.setArtist(this);

		return song;
	}

	public Song removeSong(Song song) {
		getSongs().remove(song);
		song.setArtist(null);

		return song;
	}

}