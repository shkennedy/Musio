package com.sbu.webspotify.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the image database table.
 * 
 */
@Entity
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String description;

	@Column(name="full_file_id")
	private int fullFileId;

	@Column(name="thumb_file_id")
	private int thumbFileId;

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

	public int getFullFileId() {
		return this.fullFileId;
	}

	public void setFullFileId(int fullFileId) {
		this.fullFileId = fullFileId;
	}

	public int getThumbFileId() {
		return this.thumbFileId;
	}

	public void setThumbFileId(int thumbFileId) {
		this.thumbFileId = thumbFileId;
	}

}