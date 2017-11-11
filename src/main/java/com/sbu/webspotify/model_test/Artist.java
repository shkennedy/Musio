package com.sbu.webspotify.model_test;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String bio;

	private String mbid;

	private String name;

	@Column(name="sort_name")
	private String sortName;

	private String website;

	//bi-directional many-to-many association to Album
	@ManyToMany
	@JoinTable(
		name="album_artist_mapping"
		, joinColumns={
			@JoinColumn(name="artist_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="album_id")
			}
		)
	private List<Album> albums;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="music_label_user_id")
	private User user;

	//bi-directional many-to-one association to GeoLocation
	@ManyToOne
	@JoinColumn(name="geo_location_id")
	private GeoLocation geoLocation;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="artist_art_id")
	private Image image1;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="artist_art_background_id")
	private Image image2;

	//bi-directional many-to-one association to ArtistAlia
	@OneToMany(mappedBy="artist")
	private List<ArtistAlia> artistAlias;

	//bi-directional many-to-one association to ArtistArtistRelation
	@OneToMany(mappedBy="artist1")
	private List<ArtistArtistRelation> artistArtistRelations1;

	//bi-directional many-to-one association to ArtistArtistRelation
	@OneToMany(mappedBy="artist2")
	private List<ArtistArtistRelation> artistArtistRelations2;

	//bi-directional many-to-many association to Concert
	@ManyToMany(mappedBy="artists")
	private List<Concert> concerts;

	//bi-directional many-to-many association to Genre
	@ManyToMany
	@JoinTable(
		name="artist_genre_mapping"
		, joinColumns={
			@JoinColumn(name="artist_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="genre_id")
			}
		)
	private List<Genre> genres;

	//bi-directional many-to-many association to Performer
	@ManyToMany
	@JoinTable(
		name="performer_artist_mapping"
		, joinColumns={
			@JoinColumn(name="artist_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="performer_id")
			}
		)
	private List<Performer> performers;

	//bi-directional one-to-one association to ScrapeArtist
	@OneToOne(mappedBy="artist")
	private ScrapeArtist scrapeArtist;

	//bi-directional many-to-one association to Song
	@OneToMany(mappedBy="artist")
	private List<Song> songs;

	//bi-directional many-to-one association to UserArtistFollowing
	@OneToMany(mappedBy="artist")
	private List<UserArtistFollowing> userArtistFollowings;

	//bi-directional many-to-one association to UserFavoriteArtist
	@OneToMany(mappedBy="artist")
	private List<UserFavoriteArtist> userFavoriteArtists;

	//bi-directional many-to-one association to UserFollowingArtist
	@OneToMany(mappedBy="artist")
	private List<UserFollowingArtist> userFollowingArtists;

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

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
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

	public Image getImage1() {
		return this.image1;
	}

	public void setImage1(Image image1) {
		this.image1 = image1;
	}

	public Image getImage2() {
		return this.image2;
	}

	public void setImage2(Image image2) {
		this.image2 = image2;
	}

	public List<ArtistAlia> getArtistAlias() {
		return this.artistAlias;
	}

	public void setArtistAlias(List<ArtistAlia> artistAlias) {
		this.artistAlias = artistAlias;
	}

	public ArtistAlia addArtistAlia(ArtistAlia artistAlia) {
		getArtistAlias().add(artistAlia);
		artistAlia.setArtist(this);

		return artistAlia;
	}

	public ArtistAlia removeArtistAlia(ArtistAlia artistAlia) {
		getArtistAlias().remove(artistAlia);
		artistAlia.setArtist(null);

		return artistAlia;
	}

	public List<ArtistArtistRelation> getArtistArtistRelations1() {
		return this.artistArtistRelations1;
	}

	public void setArtistArtistRelations1(List<ArtistArtistRelation> artistArtistRelations1) {
		this.artistArtistRelations1 = artistArtistRelations1;
	}

	public ArtistArtistRelation addArtistArtistRelations1(ArtistArtistRelation artistArtistRelations1) {
		getArtistArtistRelations1().add(artistArtistRelations1);
		artistArtistRelations1.setArtist1(this);

		return artistArtistRelations1;
	}

	public ArtistArtistRelation removeArtistArtistRelations1(ArtistArtistRelation artistArtistRelations1) {
		getArtistArtistRelations1().remove(artistArtistRelations1);
		artistArtistRelations1.setArtist1(null);

		return artistArtistRelations1;
	}

	public List<ArtistArtistRelation> getArtistArtistRelations2() {
		return this.artistArtistRelations2;
	}

	public void setArtistArtistRelations2(List<ArtistArtistRelation> artistArtistRelations2) {
		this.artistArtistRelations2 = artistArtistRelations2;
	}

	public ArtistArtistRelation addArtistArtistRelations2(ArtistArtistRelation artistArtistRelations2) {
		getArtistArtistRelations2().add(artistArtistRelations2);
		artistArtistRelations2.setArtist2(this);

		return artistArtistRelations2;
	}

	public ArtistArtistRelation removeArtistArtistRelations2(ArtistArtistRelation artistArtistRelations2) {
		getArtistArtistRelations2().remove(artistArtistRelations2);
		artistArtistRelations2.setArtist2(null);

		return artistArtistRelations2;
	}

	public List<Concert> getConcerts() {
		return this.concerts;
	}

	public void setConcerts(List<Concert> concerts) {
		this.concerts = concerts;
	}

	public List<Genre> getGenres() {
		return this.genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<Performer> getPerformers() {
		return this.performers;
	}

	public void setPerformers(List<Performer> performers) {
		this.performers = performers;
	}

	public ScrapeArtist getScrapeArtist() {
		return this.scrapeArtist;
	}

	public void setScrapeArtist(ScrapeArtist scrapeArtist) {
		this.scrapeArtist = scrapeArtist;
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

	public List<UserArtistFollowing> getUserArtistFollowings() {
		return this.userArtistFollowings;
	}

	public void setUserArtistFollowings(List<UserArtistFollowing> userArtistFollowings) {
		this.userArtistFollowings = userArtistFollowings;
	}

	public UserArtistFollowing addUserArtistFollowing(UserArtistFollowing userArtistFollowing) {
		getUserArtistFollowings().add(userArtistFollowing);
		userArtistFollowing.setArtist(this);

		return userArtistFollowing;
	}

	public UserArtistFollowing removeUserArtistFollowing(UserArtistFollowing userArtistFollowing) {
		getUserArtistFollowings().remove(userArtistFollowing);
		userArtistFollowing.setArtist(null);

		return userArtistFollowing;
	}

	public List<UserFavoriteArtist> getUserFavoriteArtists() {
		return this.userFavoriteArtists;
	}

	public void setUserFavoriteArtists(List<UserFavoriteArtist> userFavoriteArtists) {
		this.userFavoriteArtists = userFavoriteArtists;
	}

	public UserFavoriteArtist addUserFavoriteArtist(UserFavoriteArtist userFavoriteArtist) {
		getUserFavoriteArtists().add(userFavoriteArtist);
		userFavoriteArtist.setArtist(this);

		return userFavoriteArtist;
	}

	public UserFavoriteArtist removeUserFavoriteArtist(UserFavoriteArtist userFavoriteArtist) {
		getUserFavoriteArtists().remove(userFavoriteArtist);
		userFavoriteArtist.setArtist(null);

		return userFavoriteArtist;
	}

	public List<UserFollowingArtist> getUserFollowingArtists() {
		return this.userFollowingArtists;
	}

	public void setUserFollowingArtists(List<UserFollowingArtist> userFollowingArtists) {
		this.userFollowingArtists = userFollowingArtists;
	}

	public UserFollowingArtist addUserFollowingArtist(UserFollowingArtist userFollowingArtist) {
		getUserFollowingArtists().add(userFollowingArtist);
		userFollowingArtist.setArtist(this);

		return userFollowingArtist;
	}

	public UserFollowingArtist removeUserFollowingArtist(UserFollowingArtist userFollowingArtist) {
		getUserFollowingArtists().remove(userFollowingArtist);
		userFollowingArtist.setArtist(null);

		return userFollowingArtist;
	}

}