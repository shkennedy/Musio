package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String city;

	private String country;

	private String state;

	@Column(name="street_address_line_1")
	private String streetAddressLine1;

	@Column(name="street_address_line_2")
	private String streetAddressLine2;

	@Column(name="zip_code")
	private String zipCode;

	//bi-directional many-to-one association to UserPaymentInfo
	@OneToMany(mappedBy="address")
	private List<UserPaymentInfo> userPaymentInfos;

	//bi-directional many-to-one association to Venue
	@OneToMany(mappedBy="address")
	private List<Venue> venues;

	public Address() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreetAddressLine1() {
		return this.streetAddressLine1;
	}

	public void setStreetAddressLine1(String streetAddressLine1) {
		this.streetAddressLine1 = streetAddressLine1;
	}

	public String getStreetAddressLine2() {
		return this.streetAddressLine2;
	}

	public void setStreetAddressLine2(String streetAddressLine2) {
		this.streetAddressLine2 = streetAddressLine2;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<UserPaymentInfo> getUserPaymentInfos() {
		return this.userPaymentInfos;
	}

	public void setUserPaymentInfos(List<UserPaymentInfo> userPaymentInfos) {
		this.userPaymentInfos = userPaymentInfos;
	}

	public UserPaymentInfo addUserPaymentInfo(UserPaymentInfo userPaymentInfo) {
		getUserPaymentInfos().add(userPaymentInfo);
		userPaymentInfo.setAddress(this);

		return userPaymentInfo;
	}

	public UserPaymentInfo removeUserPaymentInfo(UserPaymentInfo userPaymentInfo) {
		getUserPaymentInfos().remove(userPaymentInfo);
		userPaymentInfo.setAddress(null);

		return userPaymentInfo;
	}

	public List<Venue> getVenues() {
		return this.venues;
	}

	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}

	public Venue addVenue(Venue venue) {
		getVenues().add(venue);
		venue.setAddress(this);

		return venue;
	}

	public Venue removeVenue(Venue venue) {
		getVenues().remove(venue);
		venue.setAddress(null);

		return venue;
	}

}