package com.pharmacy.entities;

import com.pharmacy.annotations.TableColumn;
import com.pharmacy.annotations.Tableable;


//@Tableable(title="pharmacy")
@Tableable(title="medicine")
public class Medicine {
	private int id;
	private String title;
	private String producer;
	private double boxPrice;
	private int quantityPerBox;
	
	public Medicine(int id, String title, String producer, double boxPrice, int quantityPerBox){
		this.id = id;
		this.title = title;
		this.producer = producer;
		this.boxPrice = boxPrice;
		this.quantityPerBox = quantityPerBox;
	}
	
	@TableColumn(name="id", number = 1)
	public int getId(){
		return id;
	}
	
	public Medicine setTitle(String newTitle){
		title = newTitle;
		return this;
	}	

	@TableColumn(name = "Title", number = 2)
	public String getTitle(){
		return title;
	}
	
	@TableColumn(name = "producer", number = 3)
	public String getProducer(){
		return producer;
	}
	
	public Medicine setProducer(String newProducer){
		producer = newProducer;
		return this;
	}

	@TableColumn(name = "box price", number = 4)
	public double getBoxPrice(){
		return boxPrice;
	}
	
	public Medicine setBoxPrice(double newBoxPrice){
		boxPrice = newBoxPrice;
		return this;
	}

	@TableColumn(name = "quantity per box", number = 5)
	public int getQuantityPerBox(){
		return quantityPerBox;
	}
	
	public Medicine setQuantityPerBox(int newQuantityPerBox){
		quantityPerBox = newQuantityPerBox;
		return this;
	}
	
	@Override
	public String toString(){
		return title + " " + producer + " " + boxPrice + " " + quantityPerBox;
	}
}
