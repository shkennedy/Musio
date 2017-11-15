package com.sbu.webspotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;import java.io.Serializable;

import org.springframework.data.annotation.Transient;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


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

	@Transient
	@JsonIgnore
	private String password;

	private String username;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="user_favorite_song"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="song_id")
			}
	)
	@JsonManagedReference
	private Set<Song> favoriteSongs;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="user_favorite_playlist"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="playlist_id")
			}
	)
	@JsonManagedReference
	private Set<Playlist> favoritePlaylists;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="user_favorite_album"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="album_id")
			}
	)
	@JsonManagedReference
	private Set<Album> favoriteAlbums;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="user_favorite_artist"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="artist_id")
			}
	)
	@JsonManagedReference
	private Set<Artist> favoriteArtists;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="user_favorite_station"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="station_id")
			}
	)
	@JsonManagedReference
	private Set<Station> favoriteStations;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="user_favorite_genre"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="genre_id")
			}
	)
	@JsonManagedReference
	private Set<Genre> favoriteGenres;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="user_following_user"
		, joinColumns={
			@JoinColumn(name="follower")
			}
		, inverseJoinColumns={
			@JoinColumn(name="user_being_followed")
			}
	)
	@JsonManagedReference
	private Set<User> followedUsers;

	//many-to-one association to GeoLocation
	@ManyToOne
	@JoinColumn(name="geo_location_id")
	private GeoLocation geoLocation;

	@ManyToOne
	@JoinColumn(name="profile_image_id")
	private File profileImage;

	public User() {
	}

	public void addSongToFavories(Song s) {
		this.favoriteSongs.add(s);
	}

	public boolean removeSongFromFavorites(Song s) {
		return this.favoriteSongs.remove(s);
	}

	public void addAlbumToFavories(Album a) {
		this.favoriteAlbums.add(a);
	}

	public boolean removeAlbumFromFavorites(Album a) {
		return this.favoriteAlbums.remove(a);
	}

	public void addArtistToFavories(Artist a) {
		this.favoriteArtists.add(a);
	}

	public boolean removeArtistFromFavorites(Artist a) {
		return this.favoriteArtists.remove(a);
	}

	public void addPlaylistToFavories(Playlist p) {
		this.favoritePlaylists.add(p);
	}

	public boolean removePlaylistFromFavorites(Playlist p) {
		return this.favoritePlaylists.remove(p);
	}

	public void followUser(User u) {
		this.followedUsers.add(u);
	}

	public boolean unfollowUser(User u) {
		return this.followedUsers.remove(u);
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

	public File getProfileImage() {
		return this.profileImage;
	}

	public void setProfileImage(File profileImage) {
		this.profileImage = profileImage;
	}

	public Set<Song> getFavoriteSongs() {
		return favoriteSongs;
	}

	public void setFavoriteSongs(Set<Song> favoriteSongs) {
		this.favoriteSongs = favoriteSongs;
	}

	public Set<Playlist> getFavoritePlaylists() {
		return favoritePlaylists;
	}

	public void setFavoritePlaylists(Set<Playlist> favoritePlaylists) {
		this.favoritePlaylists = favoritePlaylists;
	}

	public Set<Album> getFavoriteAlbums() {
		return favoriteAlbums;
	}

	public void setFavoriteAlbums(Set<Album> favoriteAlbums) {
		this.favoriteAlbums = favoriteAlbums;
	}

	public Set<Artist> getFavoriteArtists() {
		return favoriteArtists;
	}

	public void setFavoriteArtists(Set<Artist> favoriteArtists) {
		this.favoriteArtists = favoriteArtists;
	}

	public Set<Station> getFavoriteStations() {
		return favoriteStations;
	}

	public void setFavoriteStations(Set<Station> favoriteStations) {
		this.favoriteStations = favoriteStations;
	}

	public Set<Genre> getFavoriteGenres() {
		return favoriteGenres;
	}

	public void setFavoriteGenres(Set<Genre> favoriteGenres) {
		this.favoriteGenres = favoriteGenres;
	}

	public Set<User> getFollowedUsers() {
		return this.followedUsers;
	}

	public void setFollowedUsers(Set<User> followedUsers) { 
		this.followedUsers = followedUsers;
	}

	public GeoLocation getGeoLocation() {
		return this.geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	@Override
	public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof User) {
            User that = (User) other;
            result = (this.getId() == that.getId());
        }
        return result;
    }

	@Override
	public int hashCode() {
        return Objects.hash(this.id);
    }

	

}
