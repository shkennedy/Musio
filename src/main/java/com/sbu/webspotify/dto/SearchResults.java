package com.sbu.webspotify.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.sbu.webspotify.dto.identifier.AlbumIdentifier;
import com.sbu.webspotify.dto.identifier.ArtistIdentifier;
import com.sbu.webspotify.dto.identifier.GenreIdentifier;
import com.sbu.webspotify.dto.identifier.InstrumentIdentifier;
import com.sbu.webspotify.dto.identifier.PlaylistIdentifier;
import com.sbu.webspotify.dto.identifier.SongIdentifier;
import com.sbu.webspotify.dto.identifier.UserIdentifier;

public class SearchResults implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private Set<SongIdentifier> songs = new HashSet<SongIdentifier>();
    private Set<ArtistIdentifier> artists = new HashSet<ArtistIdentifier>();
    private Set<AlbumIdentifier> albums = new HashSet<AlbumIdentifier>();
	private Set<PlaylistIdentifier> playlists = new HashSet<PlaylistIdentifier>();
	private Set<InstrumentIdentifier> instruments = new HashSet<InstrumentIdentifier>();
    private Set<GenreIdentifier> genres = new HashSet<GenreIdentifier>();
    private Set<UserIdentifier> users = new HashSet<UserIdentifier>();
	
	public SearchResults() {

	}

	public Set<PlaylistIdentifier> getPlaylists() {
		return playlists;
	}
	
	public void setPlaylists(Set<PlaylistIdentifier> playlists) {
		this.playlists = playlists;
	}
	
	public Set<AlbumIdentifier> getAlbums() {
		return albums;
	}
	
	public void setAlbums(Set<AlbumIdentifier> albums) {
		this.albums = albums;
	}
	
	public Set<ArtistIdentifier> getArtists() {
		return artists;
	}
	
	public void setArtists(Set<ArtistIdentifier> artists) {
		this.artists = artists;
	}
	
	public Set<SongIdentifier> getSongs() {
		return songs;
	}
	
	public void setSongs(Set<SongIdentifier> songs) {
		this.songs = songs;
	}

	public Set<InstrumentIdentifier> getInstruments() {
		return instruments;
	}

	public void setInstruments(Set<InstrumentIdentifier> instruments) {
		this.instruments = instruments;
	}

	public Set<GenreIdentifier> getGenres() {
		return genres;
	}

	public void setGenres(Set<GenreIdentifier> genres) {
		this.genres = genres;
    }    
    
    public Set<UserIdentifier> getUsers() {
        return users;
    }

    public void setUsers(Set<UserIdentifier> users) {
        this.users = users;
    }
}
