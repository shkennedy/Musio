package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the song_album_mapping database table.
 * 
 */
@Entity
@Table(name="song_album_mapping")
@NamedQuery(name="SongAlbumMapping.findAll", query="SELECT s FROM SongAlbumMapping s")
public class SongAlbumMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SongAlbumMappingPK id;

	@Column(name="track_number")
	private int trackNumber;

	//bi-directional many-to-one association to Song
	@ManyToOne
	private Song song;

	//bi-directional many-to-one association to Album
	@ManyToOne
	private Album album;

	public SongAlbumMapping() {
	}

	public SongAlbumMappingPK getId() {
		return this.id;
	}

	public void setId(SongAlbumMappingPK id) {
		this.id = id;
	}

	public int getTrackNumber() {
		return this.trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	public Song getSong() {
		return this.song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}