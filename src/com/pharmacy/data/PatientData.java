package com.pharmacy.data;

import java.sql.ResultSet;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import com.pharmacy.config.DBconfig;
import com.pharmacy.entities.Patient;

public class PatientData extends DBconfig {
	
	private static PatientData context = new PatientData();
	
	public static PatientData getInstance(){
		return context;
	}
	
	private PatientData() {  	   
	}
	
	public List<Patient> getAllPatients() {
		Connection conn = getConnection();
		List<Patient> patients = new ArrayList<Patient>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT * FROM  patient";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id = rs.getInt("id_patient");
		        	  String name = rs.getString("name");
		        	  String surname = rs.getString("surname");
		        	  Date dateOfBirth = rs.getDate("date_of_birth");
		        	  String phone = rs.getString("phone");
	        	  patients.add(new Patient(id, name, surname, dateOfBirth, phone));
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return patients; 
	}
	
	public List<Patient> getAllPatientsNames() {
		Connection conn = getConnection();
		List<Patient> patientsNames = new ArrayList<Patient>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT id, name, surname FROM  patient";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id = rs.getInt("id_patient");
		        	  String name = rs.getString("name");
		        	  String surname = rs.getString("surname");
	        	  patientsNames.add(new Patient(id, name, surname));
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return patientsNames; 
	}
	
	public Patient getPatientById(int id_pat) {
		Connection conn = getConnection();
		Patient patient = new Patient();
		try {
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM Patient WHERE patientId=" + id_pat;
				st.execute(sql);
				ResultSet rs = st.getResultSet();
				while (rs.next()) {
					int id = rs.getInt("id_patient");
		        	  String name = rs.getString("name");
		        	  String surname = rs.getString("surname");
		        	  Date dateOfBirth = rs.getDate("date_of_birth");
		        	  String phone = rs.getString("phone");
		        	  patient = new Patient (id, name, surname, dateOfBirth, phone);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return patient;
	}

	public boolean addPatient(Patient item) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		boolean res = false;
		try {

			conn.setAutoCommit(false);
			String query = "INSERT INTO patient (name, surname, date_of_birth, phone)"
					+ "VALUES ( ? ,  ? , ? , ?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, item.getFirstName());
			st.setString(2, item.getLastName());
			st.setString(3, item.getDateOfBirth2());
			st.setString(4, item.getPhoneNumber());
			System.out.println(st.executeUpdate());
			conn.commit();
			st.close();
			conn.setAutoCommit(true);
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        return res;
		}
        return true;
	}
}
