package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the artist_artist_relation database table.
 * 
 */
@Entity
@Table(name="artist_artist_relation")
@NamedQuery(name="ArtistArtistRelation.findAll", query="SELECT a FROM ArtistArtistRelation a")
public class ArtistArtistRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ArtistArtistRelationPK id;

	private float score;

	//bi-directional many-to-one association to Artist
	@ManyToOne
	private Artist artist1;

	//bi-directional many-to-one association to Artist
	@ManyToOne
	private Artist artist2;

	public ArtistArtistRelation() {
	}

	public ArtistArtistRelationPK getId() {
		return this.id;
	}

	public void setId(ArtistArtistRelationPK id) {
		this.id = id;
	}

	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Artist getArtist1() {
		return this.artist1;
	}

	public void setArtist1(Artist artist1) {
		this.artist1 = artist1;
	}

	public Artist getArtist2() {
		return this.artist2;
	}

	public void setArtist2(Artist artist2) {
		this.artist2 = artist2;
	}

}