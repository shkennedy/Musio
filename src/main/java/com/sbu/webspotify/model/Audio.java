package com.sbu.webspotify.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the audio database table.
 * 
 */
@Entity
@NamedQuery(name="Audio.findAll", query="SELECT a FROM Audio a")
public class Audio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String description;

	@Column(name="high_bitrate_file_id")
	private int highBitrateFileId;

	@Column(name="low_bitrate_file_id")
	private int lowBitrateFileId;

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

	public int getHighBitrateFileId() {
		return this.highBitrateFileId;
	}

	public void setHighBitrateFileId(int highBitrateFileId) {
		this.highBitrateFileId = highBitrateFileId;
	}

	public int getLowBitrateFileId() {
		return this.lowBitrateFileId;
	}

	public void setLowBitrateFileId(int lowBitrateFileId) {
		this.lowBitrateFileId = lowBitrateFileId;
	}

}