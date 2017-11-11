package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the artist_alias database table.
 * 
 */
@Entity
@Table(name="artist_alias")
@NamedQuery(name="ArtistAlia.findAll", query="SELECT a FROM ArtistAlia a")
public class ArtistAlia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ArtistAliaPK id;

	//bi-directional many-to-one association to Artist
	@ManyToOne
	private Artist artist;

	public ArtistAlia() {
	}

	public ArtistAliaPK getId() {
		return this.id;
	}

	public void setId(ArtistAliaPK id) {
		this.id = id;
	}

	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

}