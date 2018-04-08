package com.pharmacy.data;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.pharmacy.entities.Delivery;
import com.pharmacy.entities.DeliveryRecord;
import com.pharmacy.entities.Medicine;
import com.pharmacy.entities.Pharmacy;
import com.pharmacy.entities.Purchase;
import com.pharmacy.entities.PurchaseRecord;


public class MedicineDataContext extends DataContext{
	
    private static MedicineDataContext context = new MedicineDataContext();
	
	public static MedicineDataContext getInstance(){
		return context;
	}
	
	private MedicineDataContext() {}
	
	public List<Medicine> getAllMedicine() {
		Connection conn = getConnection();
		List<Medicine> medicine = new ArrayList<Medicine>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT * FROM medicine";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id = rs.getInt("id_medicine");
		        	  String title = rs.getString("title");
		        	  String producer = rs.getString("producer");
		        	  double box_price = rs.getDouble("box_price");
		        	  int quantity_per_box = rs.getInt("quantity_per_box");
		        	  medicine.add(new Medicine(id, title, producer, box_price, quantity_per_box));	  
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return medicine; 
	}
	
	public boolean updateMedicine(Medicine med){
		Connection conn = getConnection();
		boolean res = false;
		try {

			conn.setAutoCommit(false);
			String query = "UPDATE medicine "
					+ "SET title = ? "
					+ "WHERE id_medicine = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, med.getTitle());
			st.setInt(2, med.getId());
			System.out.println(st.executeUpdate());
			conn.commit();
			st.close();
			conn.setAutoCommit(true);
	    } 
		catch (SQLException e) {
			e.printStackTrace();
	        return res;
		}
        return true;
	}
	
	public boolean addMedicine(Medicine item) {
		Connection conn = getConnection();
		boolean res = false;
		try {

			conn.setAutoCommit(false);
			String query = "INSERT INTO medicine (title, producer, box_price, quantity_per_box)"
					     + "VALUES ( ? ,  ? , ? , ? )";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, item.getTitle());
			st.setString(2, item.getProducer());
			st.setDouble(3, item.getBoxPrice());
			st.setInt(4, item.getQuantityPerBox());
			System.out.println(st.executeUpdate());
			conn.commit();
			st.close();
			conn.setAutoCommit(true);
	    } 
		catch (SQLException e) {
			e.printStackTrace();
	        return res;
		}
        return true;
	}
	
	/*
	 public List<Medicine> getAllMedicine() {
		Connection conn = getConnection();
		List<Medicine> medicine = new ArrayList<Medicine>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT * FROM medicine";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id = rs.getInt("id_medicine");
		        	  String title = rs.getString("title");
		        	  String producer = rs.getString("producer");
		        	  double box_price = rs.getDouble("box_price");
		        	  int quantity_per_box = rs.getInt("quantity_per_box");
		        	  medicine.add(new Medicine(id, title, producer, box_price, quantity_per_box));	  
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return medicine; 
	}
	  */
		public List<Medicine> getMedBalance(int pharmID) {
		Connection conn = getConnection();
		List<Medicine> medicine = new ArrayList<Medicine>();
		
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT distinct medicine.id_medicine, title, producer,box_price, quantity_per_box " + 
                    "FROM medicine " +
                    "INNER JOIN pharmacy_medicine " + 
                    "ON medicine.id_medicine = pharmacy_medicine.id_medicine " + 
                    "INNER JOIN pharmacy " +
                    "ON pharmacy.id_pharmacy = pharmacy_medicine.id_pharmacy " + 
                    "WHERE pharmacy.id_pharmacy IN (SELECT id_pharmacy " + 
                                                   "FROM pharmacy " + 
					  //"WHERE pharm_title = '" + pharmTitle + "' AND address = '" + pharmAddress + "' );";
					  "WHERE id_pharmacy = '" + pharmID + "' );";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id = rs.getInt("id_medicine");
		        	  String title = rs.getString("title");
		        	  String producer = rs.getString("producer");
		        	  double box_price = rs.getDouble("box_price");
		        	  int quantity_per_box = rs.getInt("quantity_per_box");
		        	  medicine.add(new Medicine(id, title, producer, box_price, quantity_per_box));	  
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			e.printStackTrace();
		}
        return medicine;	
	} 
	
}
