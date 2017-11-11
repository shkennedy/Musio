package com.sbu.webspotify.model_test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user_payment_info database table.
 * 
 */
@Entity
@Table(name="user_payment_info")
@NamedQuery(name="UserPaymentInfo.findAll", query="SELECT u FROM UserPaymentInfo u")
public class UserPaymentInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	@Column(name="card_number")
	private String cardNumber;

	private int ccv;

	@Temporal(TemporalType.DATE)
	@Column(name="expiration_date")
	private Date expirationDate;

	@Column(name="name_on_card")
	private String nameOnCard;

	//bi-directional many-to-one association to Address
	@ManyToOne
	private Address address;

	//bi-directional one-to-one association to User
	@OneToOne
	private User user;

	public UserPaymentInfo() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCcv() {
		return this.ccv;
	}

	public void setCcv(int ccv) {
		this.ccv = ccv;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getNameOnCard() {
		return this.nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}