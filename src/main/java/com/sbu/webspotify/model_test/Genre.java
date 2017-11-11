package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the genre database table.
 * 
 */
@Entity
@NamedQuery(name="Genre.findAll", query="SELECT g FROM Genre g")
public class Genre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String description;

	private String name;

	//bi-directional many-to-many association to Album
	@ManyToMany(mappedBy="genres")
	private List<Album> albums;

	//bi-directional many-to-many association to Artist
	@ManyToMany(mappedBy="genres")
	private List<Artist> artists;

	//bi-directional many-to-one association to Image
	@ManyToOne
	private Image image;

	//bi-directional many-to-many association to Song
	@ManyToMany
	@JoinTable(
		name="song_genre_mapping"
		, joinColumns={
			@JoinColumn(name="genre_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="song_id")
			}
		)
	private List<Song> songs;

	//bi-directional many-to-one association to UserFavoriteGenre
	@OneToMany(mappedBy="genre")
	private List<UserFavoriteGenre> userFavoriteGenres;

	public Genre() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<Artist> getArtists() {
		return this.artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public List<UserFavoriteGenre> getUserFavoriteGenres() {
		return this.userFavoriteGenres;
	}

	public void setUserFavoriteGenres(List<UserFavoriteGenre> userFavoriteGenres) {
		this.userFavoriteGenres = userFavoriteGenres;
	}

	public UserFavoriteGenre addUserFavoriteGenre(UserFavoriteGenre userFavoriteGenre) {
		getUserFavoriteGenres().add(userFavoriteGenre);
		userFavoriteGenre.setGenre(this);

		return userFavoriteGenre;
	}

	public UserFavoriteGenre removeUserFavoriteGenre(UserFavoriteGenre userFavoriteGenre) {
		getUserFavoriteGenres().remove(userFavoriteGenre);
		userFavoriteGenre.setGenre(null);

		return userFavoriteGenre;
	}

}