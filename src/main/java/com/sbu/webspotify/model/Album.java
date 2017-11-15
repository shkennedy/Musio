package com.sbu.webspotify.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;



/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="album_art_id")
	private int albumArtId;

	private String mbid;

	@Temporal(TemporalType.DATE)
	@Column(name="release_date")
	private Date releaseDate;

	private String title;

	@ManyToMany(fetch = FetchType.EAGER,
		cascade = { 
			CascadeType.PERSIST, 
			CascadeType.MERGE
    })
	@JoinTable(
		name = "album_artist_mapping",
		joinColumns = @JoinColumn(name = "album_id"),
		inverseJoinColumns = @JoinColumn(name = "artist_id")
	)
	@JsonManagedReference
	private Set<Artist> artists;

	@ManyToMany(fetch = FetchType.EAGER,
		cascade = { 
			CascadeType.PERSIST, 
			CascadeType.MERGE
    })
	@JoinTable(
		name = "song_album_mapping",
		joinColumns = @JoinColumn(name = "album_id"),
		inverseJoinColumns = @JoinColumn(name = "song_id")
	)
	@JsonManagedReference
	private Set<Song> songs;

	public Album() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAlbumArtId() {
		return this.albumArtId;
	}

	public void setAlbumArtId(int albumArtId) {
		this.albumArtId = albumArtId;
	}

	public String getMbid() {
		return this.mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Artist> getArtists() {
		return this.artists;
	}

	public void setArtists(Set<Artist> artists) {
		this.artists = artists;
	}

	public Set<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}

}