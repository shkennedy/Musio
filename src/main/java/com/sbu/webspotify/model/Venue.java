package com.sbu.webspotify.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the venue database table.
 * 
 */
@Entity
@NamedQuery(name="Venue.findAll", query="SELECT v FROM Venue v")
public class Venue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="address_id")
	private int addressId;

	private String name;

	//bi-directional many-to-one association to Concert
	@OneToMany(mappedBy="venue")
	private List<Concert> concerts;

	//bi-directional many-to-one association to GeoLocation
	@ManyToOne
	@JoinColumn(name="location_id")
	private GeoLocation geoLocation;

	public Venue() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAddressId() {
		return this.addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Concert> getConcerts() {
		return this.concerts;
	}

	public void setConcerts(List<Concert> concerts) {
		this.concerts = concerts;
	}

	public Concert addConcert(Concert concert) {
		getConcerts().add(concert);
		concert.setVenue(this);

		return concert;
	}

	public Concert removeConcert(Concert concert) {
		getConcerts().remove(concert);
		concert.setVenue(null);

		return concert;
	}

	public GeoLocation getGeoLocation() {
		return this.geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

}