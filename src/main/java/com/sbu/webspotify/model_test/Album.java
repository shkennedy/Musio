package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String mbid;

	@Temporal(TemporalType.DATE)
	@Column(name="release_date")
	private Date releaseDate;

	private String title;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="album_art_id")
	private Image image;

	//bi-directional many-to-many association to Artist
	@ManyToMany(mappedBy="albums")
	private List<Artist> artists;

	//bi-directional many-to-many association to Genre
	@ManyToMany
	@JoinTable(
		name="album_genre_mapping"
		, joinColumns={
			@JoinColumn(name="album_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="genre_id")
			}
		)
	private List<Genre> genres;

	//bi-directional one-to-one association to ScrapeAlbum
	@OneToOne(mappedBy="album")
	private ScrapeAlbum scrapeAlbum;

	//bi-directional many-to-one association to SongAlbumMapping
	@OneToMany(mappedBy="album")
	private List<SongAlbumMapping> songAlbumMappings;

	//bi-directional many-to-one association to UserFavoriteAlbum
	@OneToMany(mappedBy="album")
	private List<UserFavoriteAlbum> userFavoriteAlbums;

	public Album() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMbid() {
		return this.mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<Artist> getArtists() {
		return this.artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public List<Genre> getGenres() {
		return this.genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public ScrapeAlbum getScrapeAlbum() {
		return this.scrapeAlbum;
	}

	public void setScrapeAlbum(ScrapeAlbum scrapeAlbum) {
		this.scrapeAlbum = scrapeAlbum;
	}

	public List<SongAlbumMapping> getSongAlbumMappings() {
		return this.songAlbumMappings;
	}

	public void setSongAlbumMappings(List<SongAlbumMapping> songAlbumMappings) {
		this.songAlbumMappings = songAlbumMappings;
	}

	public SongAlbumMapping addSongAlbumMapping(SongAlbumMapping songAlbumMapping) {
		getSongAlbumMappings().add(songAlbumMapping);
		songAlbumMapping.setAlbum(this);

		return songAlbumMapping;
	}

	public SongAlbumMapping removeSongAlbumMapping(SongAlbumMapping songAlbumMapping) {
		getSongAlbumMappings().remove(songAlbumMapping);
		songAlbumMapping.setAlbum(null);

		return songAlbumMapping;
	}

	public List<UserFavoriteAlbum> getUserFavoriteAlbums() {
		return this.userFavoriteAlbums;
	}

	public void setUserFavoriteAlbums(List<UserFavoriteAlbum> userFavoriteAlbums) {
		this.userFavoriteAlbums = userFavoriteAlbums;
	}

	public UserFavoriteAlbum addUserFavoriteAlbum(UserFavoriteAlbum userFavoriteAlbum) {
		getUserFavoriteAlbums().add(userFavoriteAlbum);
		userFavoriteAlbum.setAlbum(this);

		return userFavoriteAlbum;
	}

	public UserFavoriteAlbum removeUserFavoriteAlbum(UserFavoriteAlbum userFavoriteAlbum) {
		getUserFavoriteAlbums().remove(userFavoriteAlbum);
		userFavoriteAlbum.setAlbum(null);

		return userFavoriteAlbum;
	}

}