package com.pharmacy.data;

import java.util.List;

import javax.swing.JList;

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
import com.pharmacy.entities.Purchase2;
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
	
	public List<Purchase2> getPurchaseHistory(int pharmacyId){
		Connection conn = getConnection();
		List<Purchase2> purchaseList = new ArrayList<Purchase2>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT purchase.id_purch as id, date, name, surname, total FROM  purchase "
					+ "INNER JOIN patient ON purchase.id_patient=patient.id_patient "
					+ "INNER JOIN cash_flow ON purchase.id_purch=cash_flow.id_purch "
					+ "WHERE (id_pharmacy=" + pharmacyId + ")";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id = rs.getInt("id");
		        	  double total = rs.getDouble("total");
		        	  Date date = rs.getDate("date");
		        	  String patient = rs.getString("name") + " " + rs.getString("surname");
		        	  List<PurchaseRecord> records = getPurchaseRecords(id);
		        	  purchaseList.add(new Purchase2(id, date,pharmacyId,0,patient, total,records));
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

	public List<Medicine> getAllMedicine() {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		List<Medicine> pharmacies = new ArrayList<Medicine>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT * FROM  medicine";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id = rs.getInt("id_medicine");
		        	  String name = rs.getString("title");
		        	  String producer = rs.getString("producer");
		        	  double boxPrice = rs.getDouble("box_price");
		        	  int quantityPerBox = rs.getInt("quantity_per_box");
		        	  pharmacies.add(new Medicine(id,name,producer,boxPrice,quantityPerBox));
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return pharmacies;
	}
	
	public List<Medicine> getMedicineByProducer(String producer) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		List<Medicine> pharmacies = new ArrayList<Medicine>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT * FROM  medicine WHERE producer = '" + producer + "'";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  int id = rs.getInt("id_medicine");
		        	  String name = rs.getString("title");
		        	  String prod = rs.getString("producer");
		        	  double boxPrice = rs.getDouble("box_price");
		        	  int quantityPerBox = rs.getInt("quantity_per_box");
		        	  pharmacies.add(new Medicine(id,name,prod,boxPrice,quantityPerBox));
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return pharmacies;
	}

	public boolean addDelivery(Delivery del) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		boolean res = false;
		try {
			conn.setAutoCommit(false);
			String query = "INSERT INTO delivery (date, id_pharmacy)"
					+ "VALUES ( ? ,  ? )";
			PreparedStatement st = conn.prepareStatement(query);
			st.setDate(1, new java.sql.Date(del.getDate().getTime()));
			st.setInt(2, del.getPharmacyId());
			int resRow = st.executeUpdate();
			conn.commit();
			if(resRow > 0){
				resRow = addDeliveryRecords(del.getDeliveryRecords())? 1 : 0;
			}
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

	public boolean addDeliveryRecords(List<DeliveryRecord> deliveryRecords) {
		// TODO Auto-generated method stub
				Connection conn = getConnection();
				try {
					conn.setAutoCommit(false);
					String query = "INSERT INTO delivery_medicine (id_delivery, id_medicine, box_quantity)"
							+ "VALUES ( ? , ? , ? )";
					PreparedStatement st = conn.prepareStatement(query);
					for(DeliveryRecord record : deliveryRecords){
						st.setInt(1, record.getDeliveryId());
						st.setInt(2, record.getMedcineId());
						st.setInt(3, record.getQuantity());
						
					}
					st.executeBatch();
					conn.commit();
					st.close();
					conn.setAutoCommit(true);
			    } 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
		        return true;
	}

	public List<String> getDeliveryCompanyList() {
		Connection conn = getConnection();
		List<String> pharmacies = new ArrayList<String>();
        
		try {
			Statement st = conn.createStatement();
			String selTable = "SELECT DISTINCT producer FROM  medicine";
	           st.execute(selTable);
	           ResultSet rs = st.getResultSet();
	           while(rs.next()){
		        	  String name = rs.getString("producer");
		        	  pharmacies.add(name);
	           }
	       st.close();
	    } 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return pharmacies;
	}
}
