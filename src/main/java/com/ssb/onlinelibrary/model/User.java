package com.ssb.onlinelibrary.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name="full_name")
	private String full_name;

	@Column(name = "password")
	private String password;

	@Column(name = "confirm_password")
	private String confirm_password;

	@Column(name = "email_id")
	private String email_id;

	@Column(name = "address")
	private String address;

	@Column(name = "contact_number")
	private String contact_number;

	@Column(name = "imagePath")
	private String imagePath;

	@Column(name = "libraryName")
	private String libraryName;

	@Column(name = "account_enabled")
	private boolean account_enabled;
    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "librarian_id")
//	private Librarian librarian;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public boolean isAccount_enabled() {
		return account_enabled;
	}

	public void setAccount_enabled(boolean account_enabled) {
		this.account_enabled = account_enabled;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

//	public Librarian getLibrarian() {
//		return librarian;
//	}
//
//	public void setLibrarian(Librarian librarian) {
//		this.librarian = librarian;
//	}
}
