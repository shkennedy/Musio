package com.sbu.webspotify.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the performer database table.
 * 
 */
@Entity
@NamedQuery(name="Performer.findAll", query="SELECT p FROM Performer p")
public class Performer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String mbid;

	@Column(name="performer_art_id")
	private int performerArtId;

	//bi-directional many-to-one association to GeoLocation
	@ManyToOne
	@JoinColumn(name="geo_location_id")
	private GeoLocation geoLocation;

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

	public int getPerformerArtId() {
		return this.performerArtId;
	}

	public void setPerformerArtId(int performerArtId) {
		this.performerArtId = performerArtId;
	}

	public GeoLocation getGeoLocation() {
		return this.geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

}