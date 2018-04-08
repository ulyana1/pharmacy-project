package com.pharmacy.entities;

import java.sql.Date;

import com.pharmacy.annotations.TableColumn;
import com.pharmacy.annotations.Tableable;

@Tableable(title="Purchase")
public class Purchase {
	private int id_purch;
	private Date date;
	private int id_pharmacy;
	private int id_prescr;
	private int id_patient;
	
	private String pharm_title;
	private String name;
	private String surname;

	public Purchase() {
	}
	
	public Purchase(int id_purch, Date date, int id_pharmacy, int id_prescr, int id_patient) {
		super();
		this.id_purch = id_purch;
		this.date = date;
		this.id_pharmacy = id_pharmacy;
		this.id_prescr = id_prescr;
		this.id_patient = id_patient;
	}
	
	public Purchase(int id_purch, Date date, int id_prescr, String pharm_title, String name, String surname) {
		super();
		this.id_purch = id_purch;
		this.date = date;
		this.id_prescr = id_prescr;
		this.pharm_title = pharm_title;
		this.name = name;
		this.surname = surname;
	}
	

	@TableColumn(name="ID", number = 1)
	public int getId_purch() {
		return id_purch;
	}

	public void setId_purch(int id_purch) {
		this.id_purch = id_purch;
	}

	@TableColumn(name="Date", number = 2)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@TableColumn(name="Pharmacy", number = 3)
	public String getPharm_title() {
		return pharm_title;
	}

	public void setPharm_title(String pharm_title) {
		this.pharm_title = pharm_title;
	}
	
	public int getId_pharmacy() {
		return id_pharmacy;
	}

	public void setId_pharmacy(int id_pharmacy) {
		this.id_pharmacy = id_pharmacy;
	}

	@TableColumn(name="Prescription", number = 4)
	public int getId_prescr() {
		return id_prescr;
	}

	public void setId_prescr(int id_prescr) {
		this.id_prescr = id_prescr;
	}
	
	public int getId_patient() {
		return id_patient;
	}

	public void setId_patient(int id_patient) {
		this.id_patient = id_patient;
	}
	
	@TableColumn(name="Patient", number = 5)
	public String getNameSurname() {
		return name + " " + surname;
	}

	public void setNameSurname(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}


	@Override
	public String toString() {
		return "Purchase [id_purch=" + id_purch + ", date=" + date + ", id_pharmacy=" + id_pharmacy + ", id_prescr="
				+ id_prescr + ", id_patient=" + id_patient + "]";
	}

	
	
}