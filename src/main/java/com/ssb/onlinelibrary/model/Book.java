package com.ssb.onlinelibrary.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;


@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "download_count")
	private int download_count;

	@Column(name = "name")
	private String name;

	@Column(name = "author_name")
	private String author_name;

	@Column(name = "publisher_name")
	private String publisher_name;

	@Column(name = "contact_number")
	private String contact_number;

	@Column(name = "account_enabled")
	private boolean account_enabled;

	@Column(name = "fileName")
	private String fileName;

	@Column(name = "path")
	private String path;

	@Column(name="notes")
	private String notes;

	@Column(name="type")
	private String type;

	@Lob
	@Column(name="file")
	private byte[] file;

	@Column(name="content")
	@Lob
	private Blob content;

	@Column(name="created")
	private Date created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDownload_count() {
		return download_count;
	}

	public void setDownload_count(int download_count) {
		this.download_count = download_count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getPublisher_name() {
		return publisher_name;
	}

	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
