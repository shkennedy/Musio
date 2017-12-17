package com.sbu.webspotify.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;


/**
 * The persistent class for the playlist database table.
 * 
 */
@Entity
@NamedQuery(name="Playlist.findAll", query="SELECT p FROM Playlist p")
public class Playlist implements Serializable {
	private static final long serialVersionUID = 1L;

	private int     id;
	private boolean isCollaborative;
	private boolean isPrivate;
	private String  name;
	private int     ownerId;
	private File 	imageFile;

	private List<Song> songs = new ArrayList<Song>();

	public Playlist() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="is_collaborative")
	public boolean getIsCollaborative() {
		return this.isCollaborative;
	}

	public void setIsCollaborative(boolean isCollaborative) {
		this.isCollaborative = isCollaborative;
	}

	@Column(name="is_private")
	public boolean getIsPrivate() {
		return this.isPrivate;
	}

	public void setIsPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(fetch=FetchType.EAGER,
	cascade = { 
        CascadeType.PERSIST, 
        CascadeType.MERGE
    })
	@JoinTable(
		name = "song_playlist_mapping",
		joinColumns = @JoinColumn(name = "playlist_id"),
		inverseJoinColumns = @JoinColumn(name = "song_id")
	)
	@JsonManagedReference
	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Playlist) {
            Playlist that = (Playlist) other;
            result = (this.getId() == that.getId());
        }
        return result;
    }

	@Override
	public int hashCode() {
        return Objects.hash(this.id);
    }

	public void addSong(Song s) {
		this.songs.add(s);
	}

	public boolean removeSong(Song s) {
		return this.songs.remove(s);
	}

	@ManyToOne
	@JoinColumn(name="image_file_id")
	public File getImageFile() {
		return this.imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	@Column(name="owner_id")
	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

}