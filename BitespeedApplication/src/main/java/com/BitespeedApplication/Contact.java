package com.BitespeedApplication;

import java.util.Date;

public class Contact {
	private Long id;
	private String phoneNumber;
	private String email;
	private Contact linkedContact;
	private String linkPrecedence;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(Long id, String phoneNumber, String email, Contact linkedContact, String linkPrecedence,
			Date createdAt, Date updatedAt, Date deletedAt) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.linkedContact = linkedContact;
		this.linkPrecedence = linkPrecedence;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Contact getLinkedContact() {
		return linkedContact;
	}

	public void setLinkedContact(Contact linkedContact) {
		this.linkedContact = linkedContact;
	}

	public String getLinkPrecedence() {
		return linkPrecedence;
	}

	public void setLinkPrecedence(String linkPrecedence) {
		this.linkPrecedence = linkPrecedence;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

}
