package com.pharmacy.entities;

import com.pharmacy.annotations.*;

@Tableable(title="pharmacy")
public class Pharmacy {
	private int id;
	private String title;
	private String address;
	
	public Pharmacy(int id,String title, String address){
		this.id = id;
		this.title = title;
		this.address = address;
	}
	
	@TableColumn(name = "Id")
	public int getId(){
		return id;
	}
	
	public Pharmacy setTitle(String newTitle){
		title = newTitle;
		return this;
	}
	

	@TableColumn(name = "Title")
	public String getTitle(){
		return title;
	}

	public Pharmacy setAddress(String newAddress){
		address = newAddress;
		return this;
	}

	@TableColumn(name = "Address")
	public String getAddress(){
		return address;
	}
	
	
	@Override
	public String toString(){
		return title + " " + address;
	}
}
