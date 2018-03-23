package com.jaimoto.rest.webservices.restfulwebservices.user.vo;

import java.net.URL;
import java.util.Date;

public class Post {
	private int id;
	private Date timestamp;
	private String message;
	private URL picture;

	public Post() {
	}

	public Post(int id, Date timestamp, String message, URL picture) {
		this.id = id;
		this.timestamp = timestamp;
		this.message = message;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public URL getPicture() {
		return picture;
	}

	public void setPicture(URL picture) {
		this.picture = picture;
	}
}
