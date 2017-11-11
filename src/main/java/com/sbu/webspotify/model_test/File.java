package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the file database table.
 * 
 */
@Entity
@NamedQuery(name="File.findAll", query="SELECT f FROM File f")
public class File implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private byte[] bytes;

	//bi-directional many-to-one association to Advertisement
	@OneToMany(mappedBy="fileBean")
	private List<Advertisement> advertisements;

	//bi-directional many-to-one association to Audio
	@OneToMany(mappedBy="file1")
	private List<Audio> audios1;

	//bi-directional many-to-one association to Audio
	@OneToMany(mappedBy="file2")
	private List<Audio> audios2;

	//bi-directional many-to-one association to MimeType
	@ManyToOne
	@JoinColumn(name="mime_type_id")
	private MimeType mimeType;

	//bi-directional many-to-one association to Image
	@OneToMany(mappedBy="file1")
	private List<Image> images1;

	//bi-directional many-to-one association to Image
	@OneToMany(mappedBy="file2")
	private List<Image> images2;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="file")
	private List<User> users;

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

	public List<Advertisement> getAdvertisements() {
		return this.advertisements;
	}

	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

	public Advertisement addAdvertisement(Advertisement advertisement) {
		getAdvertisements().add(advertisement);
		advertisement.setFileBean(this);

		return advertisement;
	}

	public Advertisement removeAdvertisement(Advertisement advertisement) {
		getAdvertisements().remove(advertisement);
		advertisement.setFileBean(null);

		return advertisement;
	}

	public List<Audio> getAudios1() {
		return this.audios1;
	}

	public void setAudios1(List<Audio> audios1) {
		this.audios1 = audios1;
	}

	public Audio addAudios1(Audio audios1) {
		getAudios1().add(audios1);
		audios1.setFile1(this);

		return audios1;
	}

	public Audio removeAudios1(Audio audios1) {
		getAudios1().remove(audios1);
		audios1.setFile1(null);

		return audios1;
	}

	public List<Audio> getAudios2() {
		return this.audios2;
	}

	public void setAudios2(List<Audio> audios2) {
		this.audios2 = audios2;
	}

	public Audio addAudios2(Audio audios2) {
		getAudios2().add(audios2);
		audios2.setFile2(this);

		return audios2;
	}

	public Audio removeAudios2(Audio audios2) {
		getAudios2().remove(audios2);
		audios2.setFile2(null);

		return audios2;
	}

	public MimeType getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(MimeType mimeType) {
		this.mimeType = mimeType;
	}

	public List<Image> getImages1() {
		return this.images1;
	}

	public void setImages1(List<Image> images1) {
		this.images1 = images1;
	}

	public Image addImages1(Image images1) {
		getImages1().add(images1);
		images1.setFile1(this);

		return images1;
	}

	public Image removeImages1(Image images1) {
		getImages1().remove(images1);
		images1.setFile1(null);

		return images1;
	}

	public List<Image> getImages2() {
		return this.images2;
	}

	public void setImages2(List<Image> images2) {
		this.images2 = images2;
	}

	public Image addImages2(Image images2) {
		getImages2().add(images2);
		images2.setFile2(this);

		return images2;
	}

	public Image removeImages2(Image images2) {
		getImages2().remove(images2);
		images2.setFile2(null);

		return images2;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setFile(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setFile(null);

		return user;
	}

}