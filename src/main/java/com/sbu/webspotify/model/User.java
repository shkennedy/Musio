package com.sbu.webspotify.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String email;

	private String password;

	@Column(name="profile_image_id")
	private int profileImageId;

	private String username;

	//bi-directional many-to-one association to Artist
	@OneToMany(mappedBy="user")
	private List<Artist> artists;

	//bi-directional many-to-one association to Playlist
	@OneToMany(mappedBy="user")
	private List<Playlist> playlists;

	//bi-directional many-to-one association to SupportTicket
	@OneToMany(mappedBy="user")
	private List<SupportTicket> supportTickets;

	//bi-directional many-to-one association to GeoLocation
	@ManyToOne
	@JoinColumn(name="geo_location_id")
	private GeoLocation geoLocation;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getProfileImageId() {
		return this.profileImageId;
	}

	public void setProfileImageId(int profileImageId) {
		this.profileImageId = profileImageId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Artist> getArtists() {
		return this.artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public Artist addArtist(Artist artist) {
		getArtists().add(artist);
		artist.setUser(this);

		return artist;
	}

	public Artist removeArtist(Artist artist) {
		getArtists().remove(artist);
		artist.setUser(null);

		return artist;
	}

	public List<Playlist> getPlaylists() {
		return this.playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Playlist addPlaylist(Playlist playlist) {
		getPlaylists().add(playlist);
		playlist.setUser(this);

		return playlist;
	}

	public Playlist removePlaylist(Playlist playlist) {
		getPlaylists().remove(playlist);
		playlist.setUser(null);

		return playlist;
	}

	public List<SupportTicket> getSupportTickets() {
		return this.supportTickets;
	}

	public void setSupportTickets(List<SupportTicket> supportTickets) {
		this.supportTickets = supportTickets;
	}

	public SupportTicket addSupportTicket(SupportTicket supportTicket) {
		getSupportTickets().add(supportTicket);
		supportTicket.setUser(this);

		return supportTicket;
	}

	public SupportTicket removeSupportTicket(SupportTicket supportTicket) {
		getSupportTickets().remove(supportTicket);
		supportTicket.setUser(null);

		return supportTicket;
	}

	public GeoLocation getGeoLocation() {
		return this.geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

}