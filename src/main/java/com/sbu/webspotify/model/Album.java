package com.sbu.webspotify.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;



/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	private int         id;
	private int         albumArtId;
	private String      mbid;
	private Date        releaseDate;
	private String      title;
	private Set<Artist> artists;
	private Set<Song>   songs;

	public Album() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="album_art_id")
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

	@Temporal(TemporalType.DATE)
	@Column(name="release_date")
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
	public Set<Artist> getArtists() {
		return this.artists;
	}

	public void setArtists(Set<Artist> artists) {
		this.artists = artists;
	}

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
	public Set<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}

	@Override
	public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Album) {
            Album that = (Album) other;
            result = (this.getId() == that.getId());
        }
        return result;
    }

	@Override
	public int hashCode() {
        return Objects.hash(this.id);
    }

}