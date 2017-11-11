package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the song_instrument_mapping database table.
 * 
 */
@Entity
@Table(name="song_instrument_mapping")
@NamedQuery(name="SongInstrumentMapping.findAll", query="SELECT s FROM SongInstrumentMapping s")
public class SongInstrumentMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SongInstrumentMappingPK id;

	private int count;

	//bi-directional many-to-one association to Song
	@ManyToOne
	private Song song;

	//bi-directional many-to-one association to Instrument
	@ManyToOne
	private Instrument instrument;

	public SongInstrumentMapping() {
	}

	public SongInstrumentMappingPK getId() {
		return this.id;
	}

	public void setId(SongInstrumentMappingPK id) {
		this.id = id;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Song getSong() {
		return this.song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public Instrument getInstrument() {
		return this.instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

}