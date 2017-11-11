package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the artist_artist_relation database table.
 * 
 */
@Embeddable
public class ArtistArtistRelationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="artist1_id", insertable=false, updatable=false)
	private int artist1Id;

	@Column(name="artist2_id", insertable=false, updatable=false)
	private int artist2Id;

	public ArtistArtistRelationPK() {
	}
	public int getArtist1Id() {
		return this.artist1Id;
	}
	public void setArtist1Id(int artist1Id) {
		this.artist1Id = artist1Id;
	}
	public int getArtist2Id() {
		return this.artist2Id;
	}
	public void setArtist2Id(int artist2Id) {
		this.artist2Id = artist2Id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ArtistArtistRelationPK)) {
			return false;
		}
		ArtistArtistRelationPK castOther = (ArtistArtistRelationPK)other;
		return 
			(this.artist1Id == castOther.artist1Id)
			&& (this.artist2Id == castOther.artist2Id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.artist1Id;
		hash = hash * prime + this.artist2Id;
		
		return hash;
	}
}