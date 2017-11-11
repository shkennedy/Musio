package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the scrape_artist database table.
 * 
 */
@Entity
@Table(name="scrape_artist")
@NamedQuery(name="ScrapeArtist.findAll", query="SELECT s FROM ScrapeArtist s")
public class ScrapeArtist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="artist_id")
	private int artistId;

	@Lob
	private byte[] html;

	private String url;

	//bi-directional one-to-one association to Artist
	@OneToOne
	private Artist artist;

	public ScrapeArtist() {
	}

	public int getArtistId() {
		return this.artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public byte[] getHtml() {
		return this.html;
	}

	public void setHtml(byte[] html) {
		this.html = html;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

}