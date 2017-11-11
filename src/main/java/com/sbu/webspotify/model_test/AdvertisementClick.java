package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the advertisement_click database table.
 * 
 */
@Entity
@Table(name="advertisement_click")
@NamedQuery(name="AdvertisementClick.findAll", query="SELECT a FROM AdvertisementClick a")
public class AdvertisementClick implements Serializable {
	private static final long serialVersionUID = 1L;

	private Timestamp timestamp;

	//bi-directional many-to-one association to Advertisement
	@ManyToOne
	private Advertisement advertisement;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public AdvertisementClick() {
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Advertisement getAdvertisement() {
		return this.advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}