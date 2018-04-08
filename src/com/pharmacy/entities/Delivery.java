package com.pharmacy.entities;

import java.util.Date;
import java.util.List;

import com.pharmacy.annotations.TableColumn;
import com.pharmacy.annotations.Tableable;

@Tableable(title="pharmacy")
public class Delivery {
	private int id;
	private Date date;
	private Pharmacy pharmacy;
	private List<DeliveryRecord> deliveryRecords;
	
	public Delivery(int id, int pharmacyId, String pharmacyName, Date date, List<DeliveryRecord> deliveryRecords){
		this.id = id;
		this.pharmacy = new Pharmacy(pharmacyId,pharmacyName, "");
		
		this.date = date;
		this.deliveryRecords = deliveryRecords;
	}
	
	public List<DeliveryRecord> getDeliveryRecords() {
		return deliveryRecords;
	}

	@TableColumn(name = "Id")
	public int getId() {
		return id;
	}
	@TableColumn(name = "Date")
	public Date getDate() {
		return date;
	}

	@TableColumn(name = "Pharmacy")
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	
	public int getPharmacyId(){
		return pharmacy.getId();
	}
}
