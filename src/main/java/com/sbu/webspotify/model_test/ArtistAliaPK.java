package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the artist_alias database table.
 * 
 */
@Embeddable
public class ArtistAliaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String alias;

	@Column(name="artist_id", insertable=false, updatable=false)
	private int artistId;

	public ArtistAliaPK() {
	}
	public String getAlias() {
		return this.alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getArtistId() {
		return this.artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ArtistAliaPK)) {
			return false;
		}
		ArtistAliaPK castOther = (ArtistAliaPK)other;
		return 
			this.alias.equals(castOther.alias)
			&& (this.artistId == castOther.artistId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.alias.hashCode();
		hash = hash * prime + this.artistId;
		
		return hash;
	}
}