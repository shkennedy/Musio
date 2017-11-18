package com.sbu.webspotify.dto;

import java.io.Serializable;
import java.util.Set;

import com.sbu.webspotify.dto.identifier.AlbumIdentifier;
import com.sbu.webspotify.dto.identifier.ArtistIdentifier;
import com.sbu.webspotify.dto.identifier.PlaylistIdentifier;
import com.sbu.webspotify.dto.identifier.SongIdentifier;

public class SearchResults implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private Set<SongIdentifier> songs;
    private Set<ArtistIdentifier> artists;
    private Set<AlbumIdentifier> albums;
	private Set<PlaylistIdentifier> playlists;
	
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

}
