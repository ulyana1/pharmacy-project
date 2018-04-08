package connection;


import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.doctor.view.MedicineRecord;

public class MySqlConnection {
	
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	final private String host = "localhost:3306";
	final private String databaseName = "pharmacy_db";
	final private String user = "root";
	final private String password = "";
	 
	public MySqlConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + databaseName + "?" + "user=" + user + "&password=" + password + "&useUnicode=true&characterEncoding=utf-8" );
		} catch (Exception ex) {
			System.out.println("Error: " +ex);
		}
	}
	
	public ResultSet getAllDoctors() {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from doctor ORDER BY surname ASC");
			return resultSet;
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return null;
	}
	
	public ResultSet getAllSpecialities() {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT DISTINCT speciality FROM doctor ORDER BY speciality ASC");
			return resultSet;
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return null;
	}

	
	public ResultSet getAllPatients() {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from patient ORDER BY surname ASC");
			return resultSet;
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return null;
	}

	
	public ResultSet getAllMedicine() {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from medicine ORDER BY title ASC");
			return resultSet;
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return null;
	}


	public ResultSet findPrescriptions(String doctorName, String patientName, java.util.Date fromDate, java.util.Date toDate, boolean onlyNotFulfilled, int sortBy, boolean sortAsc) {
		try {
			statement = connection.createStatement();
			String query = "SELECT prescription.id_prescr AS 'ID', doctor.surname AS 'Doctor last name', doctor.name AS 'Doctor first name', "
				+ "patient.surname AS 'Patient last name', patient.name AS 'Patient first name', medicine.title AS 'Title', "
				+ "pm.pack_quantity AS 'Prescribed quantity', pm.pack_bought AS 'Bought quantity', prescription.date AS 'Date' "
				+ "FROM prescription "
				+ "JOIN doctor ON doctor.id_doctor = prescription.id_doctor "
				+ "JOIN patient ON patient.id_patient = prescription.id_patient "
				+ "JOIN prescr_medicine AS pm ON pm.id_prescr = prescription.id_prescr "
				+ "JOIN medicine ON medicine.id_medicine = pm.id_medicine "
				+ "WHERE (doctor.surname LIKE '%" + doctorName + "%' OR doctor.name LIKE '%" + doctorName + "%') "
				+ "AND (patient.surname LIKE '%" + patientName + "%' OR patient.name LIKE '%" + patientName + "%') "
				+ (fromDate != null ? "AND prescription.date >= '" + new java.sql.Date(fromDate.getTime()) + "' " : "")
				+ (toDate != null ? "AND prescription.date <= '" + new java.sql.Date(toDate.getTime()) + "' " : "")
				+ (onlyNotFulfilled ? "AND pm.pack_quantity != pm.pack_bought " : "")
				+ "ORDER BY " + sortBy + " " + (sortAsc ? "ASC" : "DESC");
			System.out.println(query);
			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return null;
	}
	
	
	public ResultSet findDoctors(String name, String speciality, Integer fromExp, Integer toExp, int sortBy, boolean sortAsc) {
		try {
			statement = connection.createStatement();
			String query = "SELECT doctor.id_doctor AS 'ID', doctor.surname AS 'Last name', doctor.name AS 'First name', doctor.speciality AS 'Speciality', doctor.years_of_practice AS 'Years of practice' FROM doctor "
				+ "WHERE (name LIKE '%" + name + "%' OR surname LIKE '%" + name + "%') "
				+ (speciality != null && speciality != "" ? "AND speciality = '" + speciality + "' " : "")
				+ (fromExp != null ? "AND years_of_practice >= " + fromExp + " " : "")
				+ (toExp != null ? "AND years_of_practice <= " + toExp + " " : "")
				+ "ORDER BY " + sortBy + " " + (sortAsc ? "ASC" : "DESC");
			System.out.println(query);
			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return null;
	}
	
	public void savePrescription(int doctorId, int patientId, ArrayList<MedicineRecord> list) {
		try {
			statement = connection.createStatement();
			String query = String.format("INSERT INTO prescription VALUES(NULL, CURDATE(), %d, %d)", doctorId, patientId);
			System.out.println(query);
			statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int idPrescr = rs.getInt(1);
			System.out.println(idPrescr);
			for (MedicineRecord record : list) {
				query = String.format("INSERT INTO prescr_medicine VALUES(%d, %d, %d, %d)", idPrescr, record.medicineId, record.quantity, 0);
				System.out.println(query);
				statement.executeUpdate(query);
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
	
	public void saveDoctor(String name, String surname, int years, String spec) {
		try {
			statement = connection.createStatement();
			String query = String.format("INSERT INTO doctor VALUES(NULL, '%s', '%s', '%s', %d)", surname, name, spec, years);
			System.out.println(query);
			statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
	
	public void deletePrescription(int id) {
		try {
			statement = connection.createStatement();
			String query = String.format("DELETE FROM prescr_medicine WHERE id_prescr = %d", id);
			System.out.println(query);
			statement.executeUpdate(query);
			query = String.format("DELETE FROM prescription WHERE id_prescr = %d", id);
			System.out.println(query);
			statement.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}

	
	public void deleteDoctor(int id) {
		try {
			statement = connection.createStatement();
			String query = String.format("DELETE FROM doctor WHERE id_doctor = %d", id);
			System.out.println(query);
			statement.executeUpdate(query);
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
			System.out.println("Constraint error: " + ex);
			JOptionPane.showMessageDialog(null, "Cannot delete a doctor with prescriptions. Please delete prescriptions first");
		} catch (Exception ex ) {
			System.out.println("Error: " + ex);
		}
	}
	

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			int id = resultSet.getInt("id_doctor");
			System.out.println("id: " + id);
		}
	}

}
