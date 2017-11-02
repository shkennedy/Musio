package com.sbu.webspotify.domain;

import com.sbu.webspotify.domain.Album;
import com.sbu.webspotify.domain.Artist;
import com.sbu.webspotify.domain.Song;
import com.sbu.webspotify.domain.Playlist;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
// import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_favorites")
public class UserFavorites {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	//https://vladmihalcea.com/2017/03/29/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/

    @OneToMany()
    // @JoinTable(name = "albums", joinColumns = @JoinColumn(name = "user_favorites_id"), inverseJoinColumns = @JoinColumn(name = "album_id"))
	@JoinColumn(name = "user_favorites_id")
	private Set<Album> favoriteAlbums;

    @OneToMany()
    // @JoinTable(name = "artists", joinColumns = @JoinColumn(name = "user_favorites_id"), inverseJoinColumns = @JoinColumn(name = "artist_id"))
	@JoinColumn(name = "user_favorites_id")
	private Set<Artist> favoriteArtists;
	
    @OneToMany()
    // @JoinTable(name = "songs", joinColumns = @JoinColumn(name = "user_favorites_id"), inverseJoinColumns = @JoinColumn(name = "song_id"))
	@JoinColumn(name = "user_favorites_id")
	private Set<Song> favoriteSongs;
	
    // @OneToMany()
    // @JoinTable(name = "playlists", joinColumns = @JoinColumn(name = "user_favorites_id"), inverseJoinColumns = @JoinColumn(name = "playlist_id"))
	// private Set<Playlist> favoritePlaylists;
	
    // @OneToMany()
    // @JoinTable(name = "stations", joinColumns = @JoinColumn(name = "user_favorites_id"), inverseJoinColumns = @JoinColumn(name = "station_id"))
	// private Set<Station> favoriteStations;

	public int getId() {
		return id;
	}

	public Set<Album> getFavoriteAlbums() {
		return favoriteAlbums;
	}

	public void addFavoriteAlbum(Album album) {
		favoriteAlbums.add(album);
	}

	public Set<Artist> getFavoriteArtists() {
		return favoriteArtists;
	}

	public void addFavoriteArtist(Artist artist) {
		favoriteArtists.add(artist);
	}

	public Set<Song> getFavoriteSongs() {
		return favoriteSongs;
	}

	public void addFavoriteSong(Song song) {
		favoriteSongs.add(song);
	}

	// public Set<Playlist> getFavoritePlaylists() {
	// 	return favoritePlaylists;
	// }
	
	// public void addFavoritePlaylist(Playlist playlist) {
	// 	favoritePlaylists.add(playlist);
	// }
}
