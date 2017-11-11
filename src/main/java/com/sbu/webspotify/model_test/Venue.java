package com.sbu.webspotify.model_test;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to Concert
	@OneToMany(mappedBy="venue")
	private List<Concert> concerts;

	//bi-directional many-to-one association to Address
	@ManyToOne
	private Address address;

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

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public GeoLocation getGeoLocation() {
		return this.geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

}