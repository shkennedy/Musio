package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the image database table.
 * 
 */
@Entity
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="image")
	private List<Album> albums;

	//bi-directional many-to-one association to Artist
	@OneToMany(mappedBy="image1")
	private List<Artist> artists1;

	//bi-directional many-to-one association to Artist
	@OneToMany(mappedBy="image2")
	private List<Artist> artists2;

	//bi-directional many-to-one association to Genre
	@OneToMany(mappedBy="image")
	private List<Genre> genres;

	//bi-directional many-to-one association to File
	@ManyToOne
	@JoinColumn(name="full_file_id")
	private File file1;

	//bi-directional many-to-one association to File
	@ManyToOne
	@JoinColumn(name="thumb_file_id")
	private File file2;

	//bi-directional many-to-one association to Instrument
	@OneToMany(mappedBy="image")
	private List<Instrument> instruments;

	//bi-directional many-to-one association to Performer
	@OneToMany(mappedBy="image")
	private List<Performer> performers;

	public Image() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setImage(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setImage(null);

		return album;
	}

	public List<Artist> getArtists1() {
		return this.artists1;
	}

	public void setArtists1(List<Artist> artists1) {
		this.artists1 = artists1;
	}

	public Artist addArtists1(Artist artists1) {
		getArtists1().add(artists1);
		artists1.setImage1(this);

		return artists1;
	}

	public Artist removeArtists1(Artist artists1) {
		getArtists1().remove(artists1);
		artists1.setImage1(null);

		return artists1;
	}

	public List<Artist> getArtists2() {
		return this.artists2;
	}

	public void setArtists2(List<Artist> artists2) {
		this.artists2 = artists2;
	}

	public Artist addArtists2(Artist artists2) {
		getArtists2().add(artists2);
		artists2.setImage2(this);

		return artists2;
	}

	public Artist removeArtists2(Artist artists2) {
		getArtists2().remove(artists2);
		artists2.setImage2(null);

		return artists2;
	}

	public List<Genre> getGenres() {
		return this.genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Genre addGenre(Genre genre) {
		getGenres().add(genre);
		genre.setImage(this);

		return genre;
	}

	public Genre removeGenre(Genre genre) {
		getGenres().remove(genre);
		genre.setImage(null);

		return genre;
	}

	public File getFile1() {
		return this.file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
	}

	public File getFile2() {
		return this.file2;
	}

	public void setFile2(File file2) {
		this.file2 = file2;
	}

	public List<Instrument> getInstruments() {
		return this.instruments;
	}

	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}

	public Instrument addInstrument(Instrument instrument) {
		getInstruments().add(instrument);
		instrument.setImage(this);

		return instrument;
	}

	public Instrument removeInstrument(Instrument instrument) {
		getInstruments().remove(instrument);
		instrument.setImage(null);

		return instrument;
	}

	public List<Performer> getPerformers() {
		return this.performers;
	}

	public void setPerformers(List<Performer> performers) {
		this.performers = performers;
	}

	public Performer addPerformer(Performer performer) {
		getPerformers().add(performer);
		performer.setImage(this);

		return performer;
	}

	public Performer removePerformer(Performer performer) {
		getPerformers().remove(performer);
		performer.setImage(null);

		return performer;
	}

}