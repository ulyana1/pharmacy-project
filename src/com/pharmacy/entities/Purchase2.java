package com.pharmacy.entities;

import java.util.Date;
import java.util.List;

import com.pharmacy.annotations.TableColumn;
import com.pharmacy.annotations.Tableable;

@Tableable(title="Purchase")
public class Purchase2 {
	private int id;
	private Date date;
	private int pharmacy_id;
	private int prescr_id;
	private String patient;
	private double total;
	private List<PurchaseRecord> purchaseList;
	
	public Purchase2(int id, Date date, int pharmacyId, int prescrId, String patient,double total, List<PurchaseRecord> purchaseList){
		this.id = id;
		this.date = date;
		this.pharmacy_id = pharmacyId;
		this.prescr_id = prescrId;
		this.patient = patient;
		this.purchaseList = purchaseList;
		this.total = total;
	}
	
	@TableColumn(name="Id", number = 1)
	public int getId() {
		return id;
	}
	
	@TableColumn(name="Date", number = 3)
	public Date getDate() {
		return date;
	}
	
	//@TableColumn(name="Pharmacy")
	public int getPharmacyId() {
		return pharmacy_id;
	}

	public int getPrescriptionId() {
		return prescr_id;
	}
	
	@TableColumn(name="Patient", number = 2)
	public String getPatient() {
		return patient;
	}

	public List<PurchaseRecord> getPurchaseList() {
		return purchaseList;
	}
	@TableColumn(name="Total", number = 4)
	public double getTotal() {
		return total;
	}
}
