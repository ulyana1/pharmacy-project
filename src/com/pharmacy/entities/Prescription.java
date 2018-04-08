package com.pharmacy.entities;

import java.util.Date;

import com.pharmacy.annotations.TableColumn;
import com.pharmacy.annotations.Tableable;

@Tableable(title="Prescription")
public class Prescription {
	private int id_prescr;
	private Date date;
	private int id_doctor;
	private int id_patient;
	
	private String pt_name;
	private String pt_surname;
	private String doc_surname;
	private String med_title;
	private int pack_quantity;
	private int pack_bought;
	
	public Prescription() {
		// TODO Auto-generated constructor stub
	}

	public Prescription(int id_prescr, Date date, int id_doctor, int id_patient) {
		super();
		this.id_prescr = id_prescr;
		this.date = date;
		this.id_doctor = id_doctor;
		this.id_patient = id_patient;
	}
	
	public Prescription(int id_prescr, Date date, String pt_name, String pt_surname, String doc_surname, String med_title, int pack_quantity, int pack_bought) {
		super();
		this.id_prescr = id_prescr;
		this.date = date;
		this.pt_name = pt_name;
		this.pt_surname = pt_surname;
		this.doc_surname = doc_surname;
		this.med_title = med_title;
		this.pack_quantity = pack_quantity;
		this.pack_bought = pack_bought;
	}

	@TableColumn(name="ID", number = 1)
	public int getId_prescr() {
		return id_prescr;
	}

	public void setId_prescr(int id_prescr) {
		this.id_prescr = id_prescr;
	}

	@TableColumn(name="Date", number = 2)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId_doctor() {
		return id_doctor;
	}

	public void setId_doctor(int id_doctor) {
		this.id_doctor = id_doctor;
	}

	public int getId_patient() {
		return id_patient;
	}

	public void setId_patient(int id_patient) {
		this.id_patient = id_patient;
	}
	
	@TableColumn(name="Patient", number = 3)
	public String getPt_nameSurname() {
		return pt_name + " " + getPt_surname();
	}

	public void setPt_name(String pt_name) {
		this.pt_name = pt_name;
	}

	public String getPt_surname() {
		return pt_surname;
	}

	public void setPt_surname(String pt_surname) {
		this.pt_surname = pt_surname;
	}

	@TableColumn(name="Doctor", number = 4)
	public String getDoc_surname() {
		return doc_surname;
	}

	public void setDoc_surname(String doc_surname) {
		this.doc_surname = doc_surname;
	}

	@TableColumn(name="Medicine", number = 5)
	public String getMed_title() {
		return med_title;
	}

	public void setMed_title(String med_title) {
		this.med_title = med_title;
	}

	@TableColumn(name="Pack quantity", number = 6)
	public int getPack_quantity() {
		return pack_quantity;
	}

	public void setPack_quantity(int pack_quantity) {
		this.pack_quantity = pack_quantity;
	}

	@TableColumn(name="Pack bought", number = 7)
	public int getPack_bought() {
		return pack_bought;
	}

	public void setPack_bought(int pack_bought) {
		this.pack_bought = pack_bought;
	}

	@Override
	public String toString() {
		return "Prescription [id_prescr=" + id_prescr + ", date=" + date + ", id_doctor=" + id_doctor + ", id_patient="
				+ id_patient + "]";
	}
	
}
