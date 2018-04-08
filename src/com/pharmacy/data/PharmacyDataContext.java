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

public class PharmacyDataContext extends DataContext {
	
	private static PharmacyDataContext context = new PharmacyDataContext();
	
	public static PharmacyDataContext getInstance(){
		return context;
	}
	
	private PharmacyDataContext() {  	   
	}
	
	public List<Pharmacy> getAllPharmacies() {
		Connection conn = getConnection();
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT * FROM  pharmacy";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id = rs.getInt("id_pharmacy");
		        	  String name = rs.getString("pharm_title");
		        	  String address = rs.getString("address");
	        	  pharmacies.add(new Pharmacy(id, name, address));
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return pharmacies; 
	}
	
	public boolean updatePharmacy(Pharmacy p){
		Connection conn = getConnection();
		boolean res = false;
		try {

			conn.setAutoCommit(false);
			String query = "UPDATE pharmacy "
					+ "SET pharm_title = ? , address = ? "
					+ "WHERE id_pharmacy = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, p.getTitle());
			st.setString(2, p.getAddress());
			st.setInt(3, p.getId());
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

	public Pharmacy getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addPharmacy(Pharmacy item) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		boolean res = false;
		try {

			conn.setAutoCommit(false);
			String query = "INSERT INTO pharmacy (pharm_title, address)"
					+ "VALUES ( ? ,  ? )";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, item.getTitle());
			st.setString(2, item.getAddress());
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
	
	public List<Delivery> getDeliveries(){
		Connection conn = getConnection();
		List<Delivery> deliveries = new ArrayList<Delivery>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT pharmacy.id_pharmacy, id_delivery, date, pharm_title FROM  delivery INNER JOIN pharmacy ON delivery.id_pharmacy=pharmacy.id_pharmacy";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id = rs.getInt("id_delivery");
		        	  Date date = rs.getDate("date");
		        	  int pharmacyId = rs.getInt("id_pharmacy");
		        	  String pharmacyName = rs.getString("pharm_title");
		        	  List<DeliveryRecord> deliveryRecords = getDeliveryRecords(id);
		        	  deliveries.add(new Delivery(id, pharmacyId, pharmacyName, date, deliveryRecords));
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return deliveries; 
	}
	
	public List<Purchase> getPurchaseHistory(int pharmacyId){
		Connection conn = getConnection();
		List<Purchase> purchaseList = new ArrayList<Purchase>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT id_purch as id, date, name, surname FROM  purchase "
					+ "INNER JOIN patient ON purchase.id_patient=patient.id_patient "
					+ "WHERE (id_pharmacy=" + pharmacyId + ")";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id = rs.getInt("id");
		        	  Date date = rs.getDate("date");
		        	  String patient = rs.getString("name") + " " + rs.getString("surname");
		        	  List<PurchaseRecord> records = getPurchaseRecords(id);
		        	  purchaseList.add(new Purchase(id, date,pharmacyId,0,patient,records));
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return purchaseList;
	}

	public List<PurchaseRecord> getPurchaseRecords(int purchaseId) {
		Connection conn = getConnection();
		List<PurchaseRecord> purchaseRecords = new ArrayList<PurchaseRecord>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT medicine.id_medicine, medicine.title, pack_quantity FROM  purch_medicine "
					+ "INNER JOIN medicine ON purch_medicine.id_medicine=medicine.id_medicine "
					+ "WHERE (id_purch=" + purchaseId + ")";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int medicineId = rs.getInt("id_medicine");
		        	  int quantity = rs.getInt("pack_quantity");
		        	  String title = rs.getString("title");
		        	  purchaseRecords.add(new PurchaseRecord(purchaseId, quantity, new Medicine(medicineId,title,"",0,0)));
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return purchaseRecords;
	}

	public List<DeliveryRecord> getDeliveryRecords(int deliveryId){
		Connection conn = getConnection();
		List<DeliveryRecord> deliveries = new ArrayList<DeliveryRecord>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT medicine.id_medicine, box_quantity, title FROM  delivery_medicine "
					+ "INNER JOIN medicine ON delivery_medicine.id_medicine=medicine.id_medicine "
					+ "WHERE (id_delivery=" + deliveryId + ")";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int medicineId = rs.getInt("id_medicine");
		        	  int quantity = rs.getInt("box_quantity");
		        	  String medicineName = rs.getString("title");
		        	  deliveries.add(new DeliveryRecord(deliveryId, medicineId, medicineName, quantity));
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return deliveries;
	}
	/*
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
	}*/
	
}
