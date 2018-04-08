package com.pharmacy.entities;

import java.sql.Date; 

import com.pharmacy.annotations.TableColumn;
import com.pharmacy.annotations.Tableable;

@Tableable(title="Patient")
public class Patient {
	private int patientID;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String date_of_birth;
	private String phoneNumber;
	
	public Patient() {
		super();
	}

	public Patient(int patientID, String firstName, String lastName, Date dateOfBirth, String phoneNumber) {
		super();
		this.patientID = patientID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
	}

	public Patient(String name, String surname, String birthday, String phone) {
		this.firstName = name;
		this.lastName = surname;
		this.date_of_birth = birthday;
		this.phoneNumber = phone;
	}

	public Patient(int patientID, String firstName, String lastName) {
		super();
		this.patientID = patientID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@TableColumn(name = "Id", number = 1)
	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	@TableColumn(name = "Name", number = 2)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@TableColumn(name = "Surname", number = 3)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@TableColumn(name = "Birthday", number = 4)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getDateOfBirth2() {
		return date_of_birth;
	}
	
	public void setDateOfBirth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	@TableColumn(name = "Phone", number = 5)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Patient [patientID=" + patientID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
