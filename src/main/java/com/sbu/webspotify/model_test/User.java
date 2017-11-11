package com.sbu.webspotify.model_test;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	private String password;

	private String username;

	//bi-directional many-to-one association to AdvertisementClick
	@OneToMany(mappedBy="user")
	private List<AdvertisementClick> advertisementClicks;

	//bi-directional many-to-one association to Artist
	@OneToMany(mappedBy="user")
	private List<Artist> artists;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="following"
		, joinColumns={
			@JoinColumn(name="user_being_followed")
			}
		, inverseJoinColumns={
			@JoinColumn(name="follower")
			}
		)
	private List<User> users1;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="users1")
	private List<User> users2;

	//bi-directional many-to-one association to Playlist
	@OneToMany(mappedBy="user")
	private List<Playlist> playlists;

	//bi-directional many-to-one association to SupportTicket
	@OneToMany(mappedBy="user1")
	private List<SupportTicket> supportTickets1;

	//bi-directional many-to-one association to SupportTicket
	@OneToMany(mappedBy="user2")
	private List<SupportTicket> supportTickets2;

	//bi-directional many-to-one association to GeoLocation
	@ManyToOne
	@JoinColumn(name="geo_location_id")
	private GeoLocation geoLocation;

	//bi-directional many-to-one association to File
	@ManyToOne
	@JoinColumn(name="profile_image_id")
	private File file;

	//bi-directional many-to-one association to UserArtistFollowing
	@OneToMany(mappedBy="user")
	private List<UserArtistFollowing> userArtistFollowings;

	//bi-directional many-to-one association to UserFavoriteAlbum
	@OneToMany(mappedBy="user")
	private List<UserFavoriteAlbum> userFavoriteAlbums;

	//bi-directional many-to-one association to UserFavoriteArtist
	@OneToMany(mappedBy="user")
	private List<UserFavoriteArtist> userFavoriteArtists;

	//bi-directional many-to-one association to UserFavoriteGenre
	@OneToMany(mappedBy="user")
	private List<UserFavoriteGenre> userFavoriteGenres;

	//bi-directional many-to-one association to UserFavoritePlaylist
	@OneToMany(mappedBy="user")
	private List<UserFavoritePlaylist> userFavoritePlaylists;

	//bi-directional many-to-one association to UserFavoriteSong
	@OneToMany(mappedBy="user")
	private List<UserFavoriteSong> userFavoriteSongs;

	//bi-directional many-to-one association to UserFavoriteSong
	@OneToMany(mappedBy="user")
	private List<UserFavoriteSong> userFavoriteSongs;

	//bi-directional many-to-one association to UserFavoriteStation
	@OneToMany(mappedBy="user")
	private List<UserFavoriteStation> userFavoriteStations;

	//bi-directional many-to-one association to UserFollowingArtist
	@OneToMany(mappedBy="user")
	private List<UserFollowingArtist> userFollowingArtists;

	//bi-directional many-to-one association to UserFollowingPlaylist
	@OneToMany(mappedBy="user")
	private List<UserFollowingPlaylist> userFollowingPlaylists;

	//bi-directional many-to-one association to UserFollowingUser
	@OneToMany(mappedBy="user1")
	private List<UserFollowingUser> userFollowingUsers1;

	//bi-directional many-to-one association to UserFollowingUser
	@OneToMany(mappedBy="user2")
	private List<UserFollowingUser> userFollowingUsers2;

	//bi-directional many-to-one association to UserListeningHistory
	@OneToMany(mappedBy="user")
	private List<UserListeningHistory> userListeningHistories;

	//bi-directional one-to-one association to UserPaymentInfo
	@OneToOne(mappedBy="user")
	private UserPaymentInfo userPaymentInfo;

	//bi-directional many-to-one association to UserPlaylistFollowing
	@OneToMany(mappedBy="user")
	private List<UserPlaylistFollowing> userPlaylistFollowings;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="user_role"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private List<Role> roles;

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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<AdvertisementClick> getAdvertisementClicks() {
		return this.advertisementClicks;
	}

	public void setAdvertisementClicks(List<AdvertisementClick> advertisementClicks) {
		this.advertisementClicks = advertisementClicks;
	}

	public AdvertisementClick addAdvertisementClick(AdvertisementClick advertisementClick) {
		getAdvertisementClicks().add(advertisementClick);
		advertisementClick.setUser(this);

		return advertisementClick;
	}

	public AdvertisementClick removeAdvertisementClick(AdvertisementClick advertisementClick) {
		getAdvertisementClicks().remove(advertisementClick);
		advertisementClick.setUser(null);

		return advertisementClick;
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

	public List<User> getUsers1() {
		return this.users1;
	}

	public void setUsers1(List<User> users1) {
		this.users1 = users1;
	}

	public List<User> getUsers2() {
		return this.users2;
	}

	public void setUsers2(List<User> users2) {
		this.users2 = users2;
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

	public List<SupportTicket> getSupportTickets1() {
		return this.supportTickets1;
	}

	public void setSupportTickets1(List<SupportTicket> supportTickets1) {
		this.supportTickets1 = supportTickets1;
	}

	public SupportTicket addSupportTickets1(SupportTicket supportTickets1) {
		getSupportTickets1().add(supportTickets1);
		supportTickets1.setUser1(this);

		return supportTickets1;
	}

	public SupportTicket removeSupportTickets1(SupportTicket supportTickets1) {
		getSupportTickets1().remove(supportTickets1);
		supportTickets1.setUser1(null);

		return supportTickets1;
	}

	public List<SupportTicket> getSupportTickets2() {
		return this.supportTickets2;
	}

	public void setSupportTickets2(List<SupportTicket> supportTickets2) {
		this.supportTickets2 = supportTickets2;
	}

	public SupportTicket addSupportTickets2(SupportTicket supportTickets2) {
		getSupportTickets2().add(supportTickets2);
		supportTickets2.setUser2(this);

		return supportTickets2;
	}

	public SupportTicket removeSupportTickets2(SupportTicket supportTickets2) {
		getSupportTickets2().remove(supportTickets2);
		supportTickets2.setUser2(null);

		return supportTickets2;
	}

	public GeoLocation getGeoLocation() {
		return this.geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<UserArtistFollowing> getUserArtistFollowings() {
		return this.userArtistFollowings;
	}

	public void setUserArtistFollowings(List<UserArtistFollowing> userArtistFollowings) {
		this.userArtistFollowings = userArtistFollowings;
	}

	public UserArtistFollowing addUserArtistFollowing(UserArtistFollowing userArtistFollowing) {
		getUserArtistFollowings().add(userArtistFollowing);
		userArtistFollowing.setUser(this);

		return userArtistFollowing;
	}

	public UserArtistFollowing removeUserArtistFollowing(UserArtistFollowing userArtistFollowing) {
		getUserArtistFollowings().remove(userArtistFollowing);
		userArtistFollowing.setUser(null);

		return userArtistFollowing;
	}

	public List<UserFavoriteAlbum> getUserFavoriteAlbums() {
		return this.userFavoriteAlbums;
	}

	public void setUserFavoriteAlbums(List<UserFavoriteAlbum> userFavoriteAlbums) {
		this.userFavoriteAlbums = userFavoriteAlbums;
	}

	public UserFavoriteAlbum addUserFavoriteAlbum(UserFavoriteAlbum userFavoriteAlbum) {
		getUserFavoriteAlbums().add(userFavoriteAlbum);
		userFavoriteAlbum.setUser(this);

		return userFavoriteAlbum;
	}

	public UserFavoriteAlbum removeUserFavoriteAlbum(UserFavoriteAlbum userFavoriteAlbum) {
		getUserFavoriteAlbums().remove(userFavoriteAlbum);
		userFavoriteAlbum.setUser(null);

		return userFavoriteAlbum;
	}

	public List<UserFavoriteArtist> getUserFavoriteArtists() {
		return this.userFavoriteArtists;
	}

	public void setUserFavoriteArtists(List<UserFavoriteArtist> userFavoriteArtists) {
		this.userFavoriteArtists = userFavoriteArtists;
	}

	public UserFavoriteArtist addUserFavoriteArtist(UserFavoriteArtist userFavoriteArtist) {
		getUserFavoriteArtists().add(userFavoriteArtist);
		userFavoriteArtist.setUser(this);

		return userFavoriteArtist;
	}

	public UserFavoriteArtist removeUserFavoriteArtist(UserFavoriteArtist userFavoriteArtist) {
		getUserFavoriteArtists().remove(userFavoriteArtist);
		userFavoriteArtist.setUser(null);

		return userFavoriteArtist;
	}

	public List<UserFavoriteGenre> getUserFavoriteGenres() {
		return this.userFavoriteGenres;
	}

	public void setUserFavoriteGenres(List<UserFavoriteGenre> userFavoriteGenres) {
		this.userFavoriteGenres = userFavoriteGenres;
	}

	public UserFavoriteGenre addUserFavoriteGenre(UserFavoriteGenre userFavoriteGenre) {
		getUserFavoriteGenres().add(userFavoriteGenre);
		userFavoriteGenre.setUser(this);

		return userFavoriteGenre;
	}

	public UserFavoriteGenre removeUserFavoriteGenre(UserFavoriteGenre userFavoriteGenre) {
		getUserFavoriteGenres().remove(userFavoriteGenre);
		userFavoriteGenre.setUser(null);

		return userFavoriteGenre;
	}

	public List<UserFavoritePlaylist> getUserFavoritePlaylists() {
		return this.userFavoritePlaylists;
	}

	public void setUserFavoritePlaylists(List<UserFavoritePlaylist> userFavoritePlaylists) {
		this.userFavoritePlaylists = userFavoritePlaylists;
	}

	public UserFavoritePlaylist addUserFavoritePlaylist(UserFavoritePlaylist userFavoritePlaylist) {
		getUserFavoritePlaylists().add(userFavoritePlaylist);
		userFavoritePlaylist.setUser(this);

		return userFavoritePlaylist;
	}

	public UserFavoritePlaylist removeUserFavoritePlaylist(UserFavoritePlaylist userFavoritePlaylist) {
		getUserFavoritePlaylists().remove(userFavoritePlaylist);
		userFavoritePlaylist.setUser(null);

		return userFavoritePlaylist;
	}

	public List<UserFavoriteSong> getUserFavoriteSongs() {
		return this.userFavoriteSongs;
	}

	public void setUserFavoriteSongs(List<UserFavoriteSong> userFavoriteSongs) {
		this.userFavoriteSongs = userFavoriteSongs;
	}

	public UserFavoriteSong addUserFavoriteSong(UserFavoriteSong userFavoriteSong) {
		getUserFavoriteSongs().add(userFavoriteSong);
		userFavoriteSong.setUser(this);

		return userFavoriteSong;
	}

	public UserFavoriteSong removeUserFavoriteSong(UserFavoriteSong userFavoriteSong) {
		getUserFavoriteSongs().remove(userFavoriteSong);
		userFavoriteSong.setUser(null);

		return userFavoriteSong;
	}

	public List<UserFavoriteSong> getUserFavoriteSongs() {
		return this.userFavoriteSongs;
	}

	public void setUserFavoriteSongs(List<UserFavoriteSong> userFavoriteSongs) {
		this.userFavoriteSongs = userFavoriteSongs;
	}

	public UserFavoriteSong addUserFavoriteSong(UserFavoriteSong userFavoriteSong) {
		getUserFavoriteSongs().add(userFavoriteSong);
		userFavoriteSong.setUser(this);

		return userFavoriteSong;
	}

	public UserFavoriteSong removeUserFavoriteSong(UserFavoriteSong userFavoriteSong) {
		getUserFavoriteSongs().remove(userFavoriteSong);
		userFavoriteSong.setUser(null);

		return userFavoriteSong;
	}

	public List<UserFavoriteStation> getUserFavoriteStations() {
		return this.userFavoriteStations;
	}

	public void setUserFavoriteStations(List<UserFavoriteStation> userFavoriteStations) {
		this.userFavoriteStations = userFavoriteStations;
	}

	public UserFavoriteStation addUserFavoriteStation(UserFavoriteStation userFavoriteStation) {
		getUserFavoriteStations().add(userFavoriteStation);
		userFavoriteStation.setUser(this);

		return userFavoriteStation;
	}

	public UserFavoriteStation removeUserFavoriteStation(UserFavoriteStation userFavoriteStation) {
		getUserFavoriteStations().remove(userFavoriteStation);
		userFavoriteStation.setUser(null);

		return userFavoriteStation;
	}

	public List<UserFollowingArtist> getUserFollowingArtists() {
		return this.userFollowingArtists;
	}

	public void setUserFollowingArtists(List<UserFollowingArtist> userFollowingArtists) {
		this.userFollowingArtists = userFollowingArtists;
	}

	public UserFollowingArtist addUserFollowingArtist(UserFollowingArtist userFollowingArtist) {
		getUserFollowingArtists().add(userFollowingArtist);
		userFollowingArtist.setUser(this);

		return userFollowingArtist;
	}

	public UserFollowingArtist removeUserFollowingArtist(UserFollowingArtist userFollowingArtist) {
		getUserFollowingArtists().remove(userFollowingArtist);
		userFollowingArtist.setUser(null);

		return userFollowingArtist;
	}

	public List<UserFollowingPlaylist> getUserFollowingPlaylists() {
		return this.userFollowingPlaylists;
	}

	public void setUserFollowingPlaylists(List<UserFollowingPlaylist> userFollowingPlaylists) {
		this.userFollowingPlaylists = userFollowingPlaylists;
	}

	public UserFollowingPlaylist addUserFollowingPlaylist(UserFollowingPlaylist userFollowingPlaylist) {
		getUserFollowingPlaylists().add(userFollowingPlaylist);
		userFollowingPlaylist.setUser(this);

		return userFollowingPlaylist;
	}

	public UserFollowingPlaylist removeUserFollowingPlaylist(UserFollowingPlaylist userFollowingPlaylist) {
		getUserFollowingPlaylists().remove(userFollowingPlaylist);
		userFollowingPlaylist.setUser(null);

		return userFollowingPlaylist;
	}

	public List<UserFollowingUser> getUserFollowingUsers1() {
		return this.userFollowingUsers1;
	}

	public void setUserFollowingUsers1(List<UserFollowingUser> userFollowingUsers1) {
		this.userFollowingUsers1 = userFollowingUsers1;
	}

	public UserFollowingUser addUserFollowingUsers1(UserFollowingUser userFollowingUsers1) {
		getUserFollowingUsers1().add(userFollowingUsers1);
		userFollowingUsers1.setUser1(this);

		return userFollowingUsers1;
	}

	public UserFollowingUser removeUserFollowingUsers1(UserFollowingUser userFollowingUsers1) {
		getUserFollowingUsers1().remove(userFollowingUsers1);
		userFollowingUsers1.setUser1(null);

		return userFollowingUsers1;
	}

	public List<UserFollowingUser> getUserFollowingUsers2() {
		return this.userFollowingUsers2;
	}

	public void setUserFollowingUsers2(List<UserFollowingUser> userFollowingUsers2) {
		this.userFollowingUsers2 = userFollowingUsers2;
	}

	public UserFollowingUser addUserFollowingUsers2(UserFollowingUser userFollowingUsers2) {
		getUserFollowingUsers2().add(userFollowingUsers2);
		userFollowingUsers2.setUser2(this);

		return userFollowingUsers2;
	}

	public UserFollowingUser removeUserFollowingUsers2(UserFollowingUser userFollowingUsers2) {
		getUserFollowingUsers2().remove(userFollowingUsers2);
		userFollowingUsers2.setUser2(null);

		return userFollowingUsers2;
	}

	public List<UserListeningHistory> getUserListeningHistories() {
		return this.userListeningHistories;
	}

	public void setUserListeningHistories(List<UserListeningHistory> userListeningHistories) {
		this.userListeningHistories = userListeningHistories;
	}

	public UserListeningHistory addUserListeningHistory(UserListeningHistory userListeningHistory) {
		getUserListeningHistories().add(userListeningHistory);
		userListeningHistory.setUser(this);

		return userListeningHistory;
	}

	public UserListeningHistory removeUserListeningHistory(UserListeningHistory userListeningHistory) {
		getUserListeningHistories().remove(userListeningHistory);
		userListeningHistory.setUser(null);

		return userListeningHistory;
	}

	public UserPaymentInfo getUserPaymentInfo() {
		return this.userPaymentInfo;
	}

	public void setUserPaymentInfo(UserPaymentInfo userPaymentInfo) {
		this.userPaymentInfo = userPaymentInfo;
	}

	public List<UserPlaylistFollowing> getUserPlaylistFollowings() {
		return this.userPlaylistFollowings;
	}

	public void setUserPlaylistFollowings(List<UserPlaylistFollowing> userPlaylistFollowings) {
		this.userPlaylistFollowings = userPlaylistFollowings;
	}

	public UserPlaylistFollowing addUserPlaylistFollowing(UserPlaylistFollowing userPlaylistFollowing) {
		getUserPlaylistFollowings().add(userPlaylistFollowing);
		userPlaylistFollowing.setUser(this);

		return userPlaylistFollowing;
	}

	public UserPlaylistFollowing removeUserPlaylistFollowing(UserPlaylistFollowing userPlaylistFollowing) {
		getUserPlaylistFollowings().remove(userPlaylistFollowing);
		userPlaylistFollowing.setUser(null);

		return userPlaylistFollowing;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}