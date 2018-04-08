package com.pharmacy.entities;

import com.pharmacy.annotations.*;

@Tableable(title="Delivery Record")
public class DeliveryRecord {
	private int deliveryId;
	private int medcineId;
	private int quantity;
	private String medcineName; 
	
	public DeliveryRecord(int deliveryId, int medcineId, String medcineName, int quantity){
		this.deliveryId = deliveryId;
		this.medcineId = medcineId;
		this.quantity = quantity;
		this.setMedcineName(medcineName);
	}
	
	public int getDeliveryId() {
		return deliveryId;
	}
	
	@TableColumn(name="Quantity")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMedcineId() {
		return medcineId;
	}
	
	@TableColumn(name="Medcine")
	public String getMedcineName() {
		return medcineName;
	}

	public void setMedcineName(String medcineName) {
		this.medcineName = medcineName;
	} 
}
