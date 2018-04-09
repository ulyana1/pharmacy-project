package com.pharmacy.entities;

import com.pharmacy.annotations.*;

@Tableable(title="purchase records")
public class PurchaseRecord {
	private int purchId;
	private int quantity;
	private Medicine medicine;
	
	public PurchaseRecord(int purchId,int quantity,Medicine medicine){
		this.purchId = purchId;
		this.quantity = quantity;
		this.medicine = medicine;
		
	}
	
	public Medicine getMedicine(){
		return medicine;
	}
	
	@TableColumn(name="medicine", number = 1)
	public String medicineInfo(){
		return medicine.getTitle();
	}
	
	public int getPurchaseId() {
		return purchId;
	}
	@TableColumn(name="quantity", number = 2)
	public int getQuantity() {
		return quantity;
	}
	
}
