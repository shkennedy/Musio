package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the audio database table.
 * 
 */
@Entity
@NamedQuery(name="Audio.findAll", query="SELECT a FROM Audio a")
public class Audio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	//bi-directional many-to-one association to File
	@ManyToOne
	@JoinColumn(name="high_bitrate_file_id")
	private File file1;

	//bi-directional many-to-one association to File
	@ManyToOne
	@JoinColumn(name="low_bitrate_file_id")
	private File file2;

	//bi-directional many-to-one association to Song
	@OneToMany(mappedBy="audio")
	private List<Song> songs;

	public Audio() {
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

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Song addSong(Song song) {
		getSongs().add(song);
		song.setAudio(this);

		return song;
	}

	public Song removeSong(Song song) {
		getSongs().remove(song);
		song.setAudio(null);

		return song;
	}

}