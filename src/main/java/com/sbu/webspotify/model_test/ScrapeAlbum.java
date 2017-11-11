package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the scrape_album database table.
 * 
 */
@Entity
@Table(name="scrape_album")
@NamedQuery(name="ScrapeAlbum.findAll", query="SELECT s FROM ScrapeAlbum s")
public class ScrapeAlbum implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="album_id")
	private int albumId;

	@Lob
	private byte[] html;

	private String url;

	//bi-directional one-to-one association to Album
	@OneToOne
	private Album album;

	public ScrapeAlbum() {
	}

	public int getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
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

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}