package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the song_album_mapping database table.
 * 
 */
@Embeddable
public class SongAlbumMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="song_id", insertable=false, updatable=false)
	private int songId;

	@Column(name="album_id", insertable=false, updatable=false)
	private int albumId;

	public SongAlbumMappingPK() {
	}
	public int getSongId() {
		return this.songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public int getAlbumId() {
		return this.albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SongAlbumMappingPK)) {
			return false;
		}
		SongAlbumMappingPK castOther = (SongAlbumMappingPK)other;
		return 
			(this.songId == castOther.songId)
			&& (this.albumId == castOther.albumId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.songId;
		hash = hash * prime + this.albumId;
		
		return hash;
	}
}