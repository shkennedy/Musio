package com.sbu.webspotify.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the file database table.
 * 
 */
@Entity
@NamedQuery(name="File.findAll", query="SELECT f FROM File f")
public class File implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Lob
	private byte[] bytes;

	@Column(name="mime_type_id")
	private int mimeTypeId;

	public File() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getBytes() {
		return this.bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public int getMimeTypeId() {
		return this.mimeTypeId;
	}

	public void setMimeTypeId(int mimeTypeId) {
		this.mimeTypeId = mimeTypeId;
	}

}