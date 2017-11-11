package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the performer database table.
 * 
 */
@Entity
@NamedQuery(name="Performer.findAll", query="SELECT p FROM Performer p")
public class Performer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String mbid;

	//bi-directional many-to-one association to GeoLocation
	@ManyToOne
	@JoinColumn(name="geo_location_id")
	private GeoLocation geoLocation;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="performer_art_id")
	private Image image;

	//bi-directional many-to-many association to Artist
	@ManyToMany(mappedBy="performers")
	private List<Artist> artists;

	//bi-directional many-to-many association to Instrument
	@ManyToMany(mappedBy="performers")
	private List<Instrument> instruments;

	public Performer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMbid() {
		return this.mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	public GeoLocation getGeoLocation() {
		return this.geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<Artist> getArtists() {
		return this.artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public List<Instrument> getInstruments() {
		return this.instruments;
	}

	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}

}