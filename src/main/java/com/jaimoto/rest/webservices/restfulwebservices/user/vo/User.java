package com.jaimoto.rest.webservices.restfulwebservices.user.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@ApiModel(description = "user of the app") //swagger doc
public class User {
	private Integer id;
	@Size(min = 2)
	@ApiModelProperty(notes = "Name should be at leat 2 chars long")
	private String name;
	@Past
	@ApiModelProperty(notes = "Birthdate should be on the past")
	private Date birthDate;
	private List<Post> posts;



	public User() {
	}

	public User(Integer id, String name, Date birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		posts = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
