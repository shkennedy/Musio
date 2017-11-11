package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the playlist database table.
 * 
 */
@Entity
@NamedQuery(name="Playlist.findAll", query="SELECT p FROM Playlist p")
public class Playlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="is_collaborative")
	private byte isCollaborative;

	@Column(name="is_private")
	private byte isPrivate;

	private String name;

	@Column(name="num_songs")
	private int numSongs;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="owner_id")
	private User user;

	//bi-directional many-to-many association to Song
	@ManyToMany
	@JoinTable(
		name="song_playlist_mapping"
		, joinColumns={
			@JoinColumn(name="playlist_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="song_id")
			}
		)
	private List<Song> songs;

	//bi-directional many-to-one association to UserFavoritePlaylist
	@OneToMany(mappedBy="playlist")
	private List<UserFavoritePlaylist> userFavoritePlaylists;

	//bi-directional many-to-one association to UserFollowingPlaylist
	@OneToMany(mappedBy="playlist")
	private List<UserFollowingPlaylist> userFollowingPlaylists;

	//bi-directional many-to-one association to UserPlaylistFollowing
	@OneToMany(mappedBy="playlist")
	private List<UserPlaylistFollowing> userPlaylistFollowings;

	public Playlist() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getIsCollaborative() {
		return this.isCollaborative;
	}

	public void setIsCollaborative(byte isCollaborative) {
		this.isCollaborative = isCollaborative;
	}

	public byte getIsPrivate() {
		return this.isPrivate;
	}

	public void setIsPrivate(byte isPrivate) {
		this.isPrivate = isPrivate;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public List<UserFavoritePlaylist> getUserFavoritePlaylists() {
		return this.userFavoritePlaylists;
	}

	public void setUserFavoritePlaylists(List<UserFavoritePlaylist> userFavoritePlaylists) {
		this.userFavoritePlaylists = userFavoritePlaylists;
	}

	public UserFavoritePlaylist addUserFavoritePlaylist(UserFavoritePlaylist userFavoritePlaylist) {
		getUserFavoritePlaylists().add(userFavoritePlaylist);
		userFavoritePlaylist.setPlaylist(this);

		return userFavoritePlaylist;
	}

	public UserFavoritePlaylist removeUserFavoritePlaylist(UserFavoritePlaylist userFavoritePlaylist) {
		getUserFavoritePlaylists().remove(userFavoritePlaylist);
		userFavoritePlaylist.setPlaylist(null);

		return userFavoritePlaylist;
	}

	public List<UserFollowingPlaylist> getUserFollowingPlaylists() {
		return this.userFollowingPlaylists;
	}

	public void setUserFollowingPlaylists(List<UserFollowingPlaylist> userFollowingPlaylists) {
		this.userFollowingPlaylists = userFollowingPlaylists;
	}

	public UserFollowingPlaylist addUserFollowingPlaylist(UserFollowingPlaylist userFollowingPlaylist) {
		getUserFollowingPlaylists().add(userFollowingPlaylist);
		userFollowingPlaylist.setPlaylist(this);

		return userFollowingPlaylist;
	}

	public UserFollowingPlaylist removeUserFollowingPlaylist(UserFollowingPlaylist userFollowingPlaylist) {
		getUserFollowingPlaylists().remove(userFollowingPlaylist);
		userFollowingPlaylist.setPlaylist(null);

		return userFollowingPlaylist;
	}

	public List<UserPlaylistFollowing> getUserPlaylistFollowings() {
		return this.userPlaylistFollowings;
	}

	public void setUserPlaylistFollowings(List<UserPlaylistFollowing> userPlaylistFollowings) {
		this.userPlaylistFollowings = userPlaylistFollowings;
	}

	public UserPlaylistFollowing addUserPlaylistFollowing(UserPlaylistFollowing userPlaylistFollowing) {
		getUserPlaylistFollowings().add(userPlaylistFollowing);
		userPlaylistFollowing.setPlaylist(this);

		return userPlaylistFollowing;
	}

	public UserPlaylistFollowing removeUserPlaylistFollowing(UserPlaylistFollowing userPlaylistFollowing) {
		getUserPlaylistFollowings().remove(userPlaylistFollowing);
		userPlaylistFollowing.setPlaylist(null);

		return userPlaylistFollowing;
	}

}