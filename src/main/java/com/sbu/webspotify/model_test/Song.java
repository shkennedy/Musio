package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the song database table.
 * 
 */
@Entity
@NamedQuery(name="Song.findAll", query="SELECT s FROM Song s")
public class Song implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int duration;

	private String lyrics;

	private String mbid;

	private String title;

	//bi-directional one-to-one association to ScrapeSong
	@OneToOne(mappedBy="song")
	private ScrapeSong scrapeSong;

	//bi-directional many-to-one association to Artist
	@ManyToOne
	private Artist artist;

	//bi-directional many-to-one association to Audio
	@ManyToOne
	private Audio audio;

	//bi-directional many-to-one association to SongAlbumMapping
	@OneToMany(mappedBy="song")
	private List<SongAlbumMapping> songAlbumMappings;

	//bi-directional many-to-many association to Genre
	@ManyToMany(mappedBy="songs")
	private List<Genre> genres;

	//bi-directional many-to-one association to SongInstrumentMapping
	@OneToMany(mappedBy="song")
	private List<SongInstrumentMapping> songInstrumentMappings;

	//bi-directional many-to-many association to Playlist
	@ManyToMany(mappedBy="songs")
	private List<Playlist> playlists;

	//bi-directional many-to-many association to Station
	@ManyToMany(mappedBy="songs")
	private List<Station> stations;

	//bi-directional many-to-one association to UserFavoriteSong
	@OneToMany(mappedBy="song")
	private List<UserFavoriteSong> userFavoriteSongs;

	//bi-directional many-to-one association to UserFavoriteSong
	@OneToMany(mappedBy="song")
	private List<UserFavoriteSong> userFavoriteSongs;

	//bi-directional many-to-one association to UserListeningHistory
	@OneToMany(mappedBy="song")
	private List<UserListeningHistory> userListeningHistories;

	public Song() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getLyrics() {
		return this.lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public String getMbid() {
		return this.mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ScrapeSong getScrapeSong() {
		return this.scrapeSong;
	}

	public void setScrapeSong(ScrapeSong scrapeSong) {
		this.scrapeSong = scrapeSong;
	}

	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Audio getAudio() {
		return this.audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	public List<SongAlbumMapping> getSongAlbumMappings() {
		return this.songAlbumMappings;
	}

	public void setSongAlbumMappings(List<SongAlbumMapping> songAlbumMappings) {
		this.songAlbumMappings = songAlbumMappings;
	}

	public SongAlbumMapping addSongAlbumMapping(SongAlbumMapping songAlbumMapping) {
		getSongAlbumMappings().add(songAlbumMapping);
		songAlbumMapping.setSong(this);

		return songAlbumMapping;
	}

	public SongAlbumMapping removeSongAlbumMapping(SongAlbumMapping songAlbumMapping) {
		getSongAlbumMappings().remove(songAlbumMapping);
		songAlbumMapping.setSong(null);

		return songAlbumMapping;
	}

	public List<Genre> getGenres() {
		return this.genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<SongInstrumentMapping> getSongInstrumentMappings() {
		return this.songInstrumentMappings;
	}

	public void setSongInstrumentMappings(List<SongInstrumentMapping> songInstrumentMappings) {
		this.songInstrumentMappings = songInstrumentMappings;
	}

	public SongInstrumentMapping addSongInstrumentMapping(SongInstrumentMapping songInstrumentMapping) {
		getSongInstrumentMappings().add(songInstrumentMapping);
		songInstrumentMapping.setSong(this);

		return songInstrumentMapping;
	}

	public SongInstrumentMapping removeSongInstrumentMapping(SongInstrumentMapping songInstrumentMapping) {
		getSongInstrumentMappings().remove(songInstrumentMapping);
		songInstrumentMapping.setSong(null);

		return songInstrumentMapping;
	}

	public List<Playlist> getPlaylists() {
		return this.playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<Station> getStations() {
		return this.stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public List<UserFavoriteSong> getUserFavoriteSongs() {
		return this.userFavoriteSongs;
	}

	public void setUserFavoriteSongs(List<UserFavoriteSong> userFavoriteSongs) {
		this.userFavoriteSongs = userFavoriteSongs;
	}

	public UserFavoriteSong addUserFavoriteSong(UserFavoriteSong userFavoriteSong) {
		getUserFavoriteSongs().add(userFavoriteSong);
		userFavoriteSong.setSong(this);

		return userFavoriteSong;
	}

	public UserFavoriteSong removeUserFavoriteSong(UserFavoriteSong userFavoriteSong) {
		getUserFavoriteSongs().remove(userFavoriteSong);
		userFavoriteSong.setSong(null);

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
		userFavoriteSong.setSong(this);

		return userFavoriteSong;
	}

	public UserFavoriteSong removeUserFavoriteSong(UserFavoriteSong userFavoriteSong) {
		getUserFavoriteSongs().remove(userFavoriteSong);
		userFavoriteSong.setSong(null);

		return userFavoriteSong;
	}

	public List<UserListeningHistory> getUserListeningHistories() {
		return this.userListeningHistories;
	}

	public void setUserListeningHistories(List<UserListeningHistory> userListeningHistories) {
		this.userListeningHistories = userListeningHistories;
	}

	public UserListeningHistory addUserListeningHistory(UserListeningHistory userListeningHistory) {
		getUserListeningHistories().add(userListeningHistory);
		userListeningHistory.setSong(this);

		return userListeningHistory;
	}

	public UserListeningHistory removeUserListeningHistory(UserListeningHistory userListeningHistory) {
		getUserListeningHistories().remove(userListeningHistory);
		userListeningHistory.setSong(null);

		return userListeningHistory;
	}

}