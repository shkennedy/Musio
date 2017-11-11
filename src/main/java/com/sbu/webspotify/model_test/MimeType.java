package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mime_type database table.
 * 
 */
@Entity
@Table(name="mime_type")
@NamedQuery(name="MimeType.findAll", query="SELECT m FROM MimeType m")
public class MimeType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String subtype;

	private String type;

	//bi-directional many-to-one association to File
	@OneToMany(mappedBy="mimeType")
	private List<File> files;

	public MimeType() {
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

	public String getSubtype() {
		return this.subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<File> getFiles() {
		return this.files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public File addFile(File file) {
		getFiles().add(file);
		file.setMimeType(this);

		return file;
	}

	public File removeFile(File file) {
		getFiles().remove(file);
		file.setMimeType(null);

		return file;
	}

}