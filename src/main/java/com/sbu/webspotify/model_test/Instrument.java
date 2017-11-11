package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the instrument database table.
 * 
 */
@Entity
@NamedQuery(name="Instrument.findAll", query="SELECT i FROM Instrument i")
public class Instrument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String description;

	private String name;

	//bi-directional many-to-one association to Image
	@ManyToOne
	private Image image;

	//bi-directional many-to-many association to Performer
	@ManyToMany
	@JoinTable(
		name="performer_instrument_mapping"
		, joinColumns={
			@JoinColumn(name="instrument_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="performer_id")
			}
		)
	private List<Performer> performers;

	//bi-directional many-to-one association to SongInstrumentMapping
	@OneToMany(mappedBy="instrument")
	private List<SongInstrumentMapping> songInstrumentMappings;

	public Instrument() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<Performer> getPerformers() {
		return this.performers;
	}

	public void setPerformers(List<Performer> performers) {
		this.performers = performers;
	}

	public List<SongInstrumentMapping> getSongInstrumentMappings() {
		return this.songInstrumentMappings;
	}

	public void setSongInstrumentMappings(List<SongInstrumentMapping> songInstrumentMappings) {
		this.songInstrumentMappings = songInstrumentMappings;
	}

	public SongInstrumentMapping addSongInstrumentMapping(SongInstrumentMapping songInstrumentMapping) {
		getSongInstrumentMappings().add(songInstrumentMapping);
		songInstrumentMapping.setInstrument(this);

		return songInstrumentMapping;
	}

	public SongInstrumentMapping removeSongInstrumentMapping(SongInstrumentMapping songInstrumentMapping) {
		getSongInstrumentMappings().remove(songInstrumentMapping);
		songInstrumentMapping.setInstrument(null);

		return songInstrumentMapping;
	}

}