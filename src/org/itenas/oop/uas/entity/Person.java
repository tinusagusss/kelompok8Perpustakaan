package org.itenas.oop.uas.entity;

public abstract class Person {
	private String id;
	private String name;
	private String city;
	private String phone;
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

}
