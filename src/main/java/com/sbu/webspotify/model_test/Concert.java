package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the concert database table.
 * 
 */
@Entity
@NamedQuery(name="Concert.findAll", query="SELECT c FROM Concert c")
public class Concert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String name;

	//bi-directional many-to-many association to Artist
	@ManyToMany
	@JoinTable(
		name="artist_concert_mapping"
		, joinColumns={
			@JoinColumn(name="concert_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="artist_id")
			}
		)
	private List<Artist> artists;

	//bi-directional many-to-one association to Venue
	@ManyToOne
	private Venue venue;

	public Concert() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Artist> getArtists() {
		return this.artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public Venue getVenue() {
		return this.venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

}