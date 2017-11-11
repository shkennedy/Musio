package com.sbu.webspotify.model;

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

	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to Advertisement
	@ManyToOne
	private Advertisement advertisement;

	public AdvertisementClick() {
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Advertisement getAdvertisement() {
		return this.advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

}