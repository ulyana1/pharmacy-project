package com.pharmacy.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pharmacy.config.DBconfig;
import com.pharmacy.entities.Purchase;

public class PurchaseData extends DBconfig {
	
	private static PurchaseData context = new PurchaseData();
	
	public static PurchaseData getInstance(){
		return context;
	}
	
	private PurchaseData() {  	   
	}
	
	public List<Purchase> getAllPurchases() {
		Connection conn = getConnection();
		List<Purchase> purchases = new ArrayList<Purchase>();
        
		try {
			Statement st = conn.createStatement();
			//String selTable = "SELECT * FROM purchase";
			
			String selTable = "SELECT id_purch, date, id_prescr, pharmacy.pharm_title, patient.surname, patient.name " +
						      "FROM purchase " +
						      "INNER JOIN pharmacy ON purchase.id_pharmacy=pharmacy.id_pharmacy " +
						      "INNER JOIN patient ON purchase.id_patient=patient.id_patient";
						      
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id = rs.getInt("id_purch");
		        	  Date date = rs.getDate("date");
		        	  int id_prescr = rs.getInt("id_prescr");
		        	  String pharm_title = rs.getString("pharm_title");
		        	  String name = rs.getString("name");
		        	  String surname = rs.getString("surname");
	        	  purchases.add(new Purchase(id, date, id_prescr, pharm_title, name, surname));
		        	  //pharmacies.add(new Purchase(id, date, id_prescr, id_pharmacy, id_patient));
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return purchases; 
	}

	public boolean addPurchase(Purchase item) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		boolean res = false;
		try {

			conn.setAutoCommit(false);
			String query = "INSERT INTO purchase (date, id_prescr, id_pharmacy, id_patient)"
					+ "VALUES ( ? ,  ? , ? , ?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setDate(1, item.getDate());
			st.setInt(2, item.getId_prescr());
			st.setInt(3, item.getId_pharmacy());
			st.setInt(4, item.getId_patient());
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
