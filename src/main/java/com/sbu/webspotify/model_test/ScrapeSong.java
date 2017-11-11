package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the scrape_song database table.
 * 
 */
@Entity
@Table(name="scrape_song")
@NamedQuery(name="ScrapeSong.findAll", query="SELECT s FROM ScrapeSong s")
public class ScrapeSong implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="song_id")
	private int songId;

	@Lob
	private byte[] html;

	private String url;

	//bi-directional one-to-one association to Song
	@OneToOne
	private Song song;

	public ScrapeSong() {
	}

	public int getSongId() {
		return this.songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
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

	public Song getSong() {
		return this.song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

}