package com.sbu.webspotify.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Set;


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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;


	@ManyToMany
	@JoinTable(
		name="user_favorite_song"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="song_id")
			}
	)
	private Set<Song> favoriteSongs;

	@ManyToMany
	@JoinTable(
		name="user_favorite_playlist"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="song_id")
			}
	)
	private Set<Playlist> favoritePlaylists;

	@ManyToMany
	@JoinTable(
		name="user_favorite_album"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="song_id")
			}
	)
	private Set<Album> favoriteAlbums;

	@ManyToMany
	@JoinTable(
		name="user_favorite_artist"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="song_id")
			}
	)
	private Set<Artist> favoriteArtists;

	@ManyToMany
	@JoinTable(
		name="user_favorite_station"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="song_id")
			}
	)
	private Set<Station> favoriteStations;

	@ManyToMany
	@JoinTable(
		name="user_favorite_genre"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="song_id")
			}
	)
	private Set<Genre> favoriteGenres;

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
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