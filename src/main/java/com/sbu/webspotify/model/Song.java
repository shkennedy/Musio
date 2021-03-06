package com.sbu.webspotify.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * The persistent class for the song database table.
 * 
 */
@Entity
@NamedQuery(name="Song.findAll", query="SELECT s FROM Song s")	
public class Song implements Serializable {
	private static final long serialVersionUID = 1L;

	private int    id;
	private Audio  audio;
	private int    duration;
	private String lyrics;
	private String mbid;
	private String title;
	private Artist artist;

	public Song() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="audio_id")
	public Audio getAudio() {
		return this.audio;
	}

	public void setAudio(Audio audioId) {
		this.audio = audioId;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getLyrics() {
		return this.lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public String getMbid() {
		return this.mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToOne
	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	@Override
	public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Song) {
            Song that = (Song) other;
            result = (this.getId() == that.getId());
        }
        return result;
    }

	@Override
	public int hashCode() {
        return Objects.hash(this.id);
    }
}