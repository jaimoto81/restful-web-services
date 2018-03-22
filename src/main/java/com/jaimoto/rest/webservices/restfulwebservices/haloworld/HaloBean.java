package com.jaimoto.rest.webservices.restfulwebservices.haloworld;

public class HaloBean {

	private String message;

	public HaloBean(String halo_world_bean) {
		this.message = halo_world_bean;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HaloBean{" +
			"message='" + message + '\'' +
			'}';
	}
}
