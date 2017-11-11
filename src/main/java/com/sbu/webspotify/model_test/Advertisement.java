package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the advertisement database table.
 * 
 */
@Entity
@NamedQuery(name="Advertisement.findAll", query="SELECT a FROM Advertisement a")
public class Advertisement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String desription;

	//bi-directional many-to-one association to File
	@ManyToOne
	@JoinColumn(name="file")
	private File fileBean;

	//bi-directional many-to-one association to AdvertisementClick
	@OneToMany(mappedBy="advertisement")
	private List<AdvertisementClick> advertisementClicks;

	public Advertisement() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesription() {
		return this.desription;
	}

	public void setDesription(String desription) {
		this.desription = desription;
	}

	public File getFileBean() {
		return this.fileBean;
	}

	public void setFileBean(File fileBean) {
		this.fileBean = fileBean;
	}

	public List<AdvertisementClick> getAdvertisementClicks() {
		return this.advertisementClicks;
	}

	public void setAdvertisementClicks(List<AdvertisementClick> advertisementClicks) {
		this.advertisementClicks = advertisementClicks;
	}

	public AdvertisementClick addAdvertisementClick(AdvertisementClick advertisementClick) {
		getAdvertisementClicks().add(advertisementClick);
		advertisementClick.setAdvertisement(this);

		return advertisementClick;
	}

	public AdvertisementClick removeAdvertisementClick(AdvertisementClick advertisementClick) {
		getAdvertisementClicks().remove(advertisementClick);
		advertisementClick.setAdvertisement(null);

		return advertisementClick;
	}

}