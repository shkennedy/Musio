package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the song_instrument_mapping database table.
 * 
 */
@Embeddable
public class SongInstrumentMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="song_id", insertable=false, updatable=false)
	private int songId;

	@Column(name="instrument_id", insertable=false, updatable=false)
	private int instrumentId;

	public SongInstrumentMappingPK() {
	}
	public int getSongId() {
		return this.songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public int getInstrumentId() {
		return this.instrumentId;
	}
	public void setInstrumentId(int instrumentId) {
		this.instrumentId = instrumentId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SongInstrumentMappingPK)) {
			return false;
		}
		SongInstrumentMappingPK castOther = (SongInstrumentMappingPK)other;
		return 
			(this.songId == castOther.songId)
			&& (this.instrumentId == castOther.instrumentId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.songId;
		hash = hash * prime + this.instrumentId;
		
		return hash;
	}
}