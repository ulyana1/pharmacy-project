package com.pharmacy.entities;

import java.util.Date;

import com.pharmacy.annotations.TableColumn;
import com.pharmacy.annotations.Tableable;

@Tableable(title="pharmacy_medicine")
    public class MedicineChangeRecord {
	
	private int id_medicine;
    private String title;
    private String producer;
    private double packPrice;
    private Date dateOfChange;
    
    
	public MedicineChangeRecord(int id_medicine, String title, String producer, double packPrice, Date dateOfChange){
		
		this.id_medicine = id_medicine;
		this.title = title;
		this.producer = producer;
		this.packPrice = packPrice;
		this.dateOfChange = dateOfChange;
		
	}
	
	@TableColumn(name="id")
	public int getId(){
		return id_medicine;
	}
	
	public MedicineChangeRecord setTitle(String newTitle){
		title = newTitle;
		return this;
	}	

	@TableColumn(name = "Title")
	public String getTitle(){
		return title;
	}
	
	
	@TableColumn(name = "producer")
	public String getProducer(){
		return producer;
	}
	
	public MedicineChangeRecord setProducer(String newProducer){
		producer = newProducer;
		return this;
	}
	
	@TableColumn(name = "box price")
	public double getPackPrice(){
		return packPrice;
	}
	
	public MedicineChangeRecord getPackPrice(double newPackPrice){
		packPrice = newPackPrice;
		return this;
	}

	@TableColumn(name = "quantity per box")
	public Date getDateOfChange(){
		return dateOfChange;
	}
	
	public MedicineChangeRecord setgetDateOfChange(Date newDateOfChange){
		dateOfChange = newDateOfChange;
		return this;
	}
	
	@Override
	public String toString(){
		return title + " " + producer + " " + packPrice + " " + dateOfChange;
	}

}
