package com.pharmacy.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pharmacy.config.DBconfig;
import com.pharmacy.entities.Prescription;

public class PrescrData extends DBconfig {
	
	private static PrescrData context = new PrescrData();
	
	public static PrescrData getInstance(){
		return context;
	}
	
	private PrescrData() {  	   
	}
	
	public List<Prescription> getAllPrescr() {
		Connection conn = getConnection();
		List<Prescription> prescr = new ArrayList<Prescription>();
        
		try {
			Statement st = conn.createStatement();
			//String selTable = "SELECT * FROM purchase";
			
			String selTable = "SELECT prescription.id_prescr, date, patient.name AS pt_name, patient.surname AS pt_surname, " + 
			"doctor.surname AS doc_surname, medicine.title, prescr_medicine.pack_quantity, prescr_medicine.pack_bought " +
			"FROM prescription " + 
			"INNER JOIN patient ON prescription.id_patient=patient.id_patient " + 
			"INNER JOIN doctor ON prescription.id_doctor=doctor.id_doctor " +
			"INNER JOIN (prescr_medicine INNER JOIN medicine ON prescr_medicine.id_medicine=medicine.id_medicine) " + 
					"ON prescription.id_prescr=prescr_medicine.id_prescr ";
						      
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id_prescr = rs.getInt("id_prescr");
		        	  Date date = rs.getDate("date");
		        	  String pt_name = rs.getString("pt_name");
		        	  String pt_surname = rs.getString("pt_surname");
		        	  String doc_surname = rs.getString("doc_surname");
		        	  String med_title = rs.getString("title");
		        	  int pack_quantity = rs.getInt("pack_quantity");
		        	  int pack_bought = rs.getInt("pack_bought");
	        	  prescr.add(new Prescription(id_prescr, date, pt_name, pt_surname, doc_surname, med_title, pack_quantity, pack_bought));
		        	  //pharmacies.add(new Purchase(id, date, id_prescr, id_pharmacy, id_patient));
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return prescr; 
	}


}
