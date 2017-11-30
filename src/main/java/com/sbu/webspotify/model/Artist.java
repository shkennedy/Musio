package com.sbu.webspotify.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;


/**
 * The persistent class for the artist database table.
 * 
 */
@Entity
@NamedQuery(name="Artist.findAll", query="SELECT a FROM Artist a")
public class Artist implements Serializable {
	private static final long serialVersionUID = 1L;

	private int         id;
	private String      bio;
	private String      mbid;
	private String 	    name;
	private String 	    sortName;
	private String	    website;
	private User 		musicLabel;
	private GeoLocation geoLocation;
	private Image 		artistImage;
	private Image 		backgroundArt;

	public Artist() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Lob
	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getMbid() {
		return this.mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="sort_name")
	public String getSortName() {
		return this.sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@ManyToOne
	@JoinColumn(name="music_label_user_id")
	public User getMusicLabel() {
		return this.musicLabel;
	}

	public void setMusicLabel(User user) {
		this.musicLabel = user;
	}

	@ManyToOne
	@JoinColumn(name="geo_location_id")
	public GeoLocation getGeoLocation() {
		return this.geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	public void setBackgroundArt(Image backgroundArt) {
		this.backgroundArt = backgroundArt;
	}

	@ManyToOne
	@JoinColumn(name="artist_art_background_id")
	public Image getBackgroundArt() {
		return this.backgroundArt;
	}

	public void setArtistImage(Image artistImage) {
		this.artistImage = artistImage;
	}

	@ManyToOne
	@JoinColumn(name="artist_art_id")
	public Image getArtistImage() {
		return this.artistImage;
	}

	@Override
	public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Artist) {
            Artist that = (Artist) other;
            result = (this.getId() == that.getId());
        }
        return result;
    }

	@Override
	public int hashCode() {
        return Objects.hash(this.id);
    }

}