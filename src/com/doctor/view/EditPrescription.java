package com.doctor.view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.NumberFormatter;

import com.pharmacy.data.DoctorDataContext;

import javax.swing.border.EmptyBorder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.*;

public class EditPrescription extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField quantityField;

	private ResultSet doctorsSet; 
	private ResultSet patientsSet; 
	private ResultSet medicineSet;

	private ArrayList<Integer> doctorsIds = new ArrayList<>();
	private ArrayList<Integer> patientsIds = new ArrayList<>();
	private ArrayList<Integer> medicineIds = new ArrayList<>();
	
	private ArrayList<MedicineRecord> selectedMedicineList = new ArrayList<>();

	/**
	 * Create the dialog.
	 */
	public EditPrescription(Frame owner, DoctorDataContext db, boolean addMode) {
		super(owner, true);
		setTitle("Add prescription");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 265, 338);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblDoctor = new JLabel("Doctor");
			lblDoctor.setBounds(10, 11, 46, 14);
			contentPanel.add(lblDoctor);
		}
		{
			JLabel lblPatient = new JLabel("Patient");
			lblPatient.setBounds(10, 40, 46, 14);
			contentPanel.add(lblPatient);
		}
		{
			JLabel lblMedicine = new JLabel("Medicine");
			lblMedicine.setBounds(10, 208, 46, 14);
			contentPanel.add(lblMedicine);
		}
		
		JComboBox doctorsBox = new JComboBox();
		doctorsBox.setBounds(66, 8, 173, 20);
		contentPanel.add(doctorsBox);
		
		JComboBox patientsBox = new JComboBox();
		patientsBox.setBounds(66, 37, 173, 20);
		contentPanel.add(patientsBox);
		
		JComboBox medicineBox = new JComboBox();
		medicineBox.setBounds(66, 205, 173, 20);
		contentPanel.add(medicineBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 229, 97);
		contentPanel.add(scrollPane);
		
		DefaultListModel listModel = new DefaultListModel();
		JList medicineList = new JList(listModel);
		medicineList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(medicineList);
		
		JButton btnAddMedicineTo = new JButton("Add medicine to list");
		btnAddMedicineTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = medicineBox.getSelectedIndex();
				int selectedId = medicineIds.get(index);
				try {
					int quantity = Integer.parseInt(quantityField.getText());
					selectedMedicineList.add(new MedicineRecord(selectedId, quantity));
					listModel.addElement(medicineBox.getItemAt(index) + " (" + Integer.toString(quantity) + ")");
				} catch (NumberFormatException ex) {
				}
			}
		});
		btnAddMedicineTo.setBounds(114, 235, 125, 23);
		contentPanel.add(btnAddMedicineTo);

		{
			NumberFormat format = NumberFormat.getInstance();
		    NumberFormatter formatter = new NumberFormatter(format);
		    formatter.setValueClass(Integer.class);
		    formatter.setMinimum(1);
		    formatter.setMaximum(9999);
		    formatter.setAllowsInvalid(false);
		    formatter.setCommitsOnValidEdit(true);
		    quantityField = new JFormattedTextField(formatter);
			quantityField.setBounds(66, 236, 38, 20);
			contentPanel.add(quantityField);
			quantityField.setColumns(10);
		}
		{
			JLabel lblQuantity = new JLabel("Quantity");
			lblQuantity.setBounds(10, 239, 46, 14);
			contentPanel.add(lblQuantity);
		}
		{
			JButton btnRemoveSelected = new JButton("Remove selected");
			btnRemoveSelected.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = medicineList.getSelectedIndex();
					if (index > -1) {
						selectedMedicineList.remove(index);
						listModel.removeElementAt(index);
					}
				}
			});
			btnRemoveSelected.setBounds(114, 171, 125, 23);
			contentPanel.add(btnRemoveSelected);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selectedMedicineList.size() > 0) {
							int doctorIndex = doctorsBox.getSelectedIndex();
							int patientIndex = doctorsBox.getSelectedIndex();
							int doctorId = doctorsIds.get(doctorIndex);
							int patientId = patientsIds.get(patientIndex);
							db.savePrescription(doctorId, patientId, selectedMedicineList);
							selectedMedicineList.clear();
							listModel.clear();
							setVisible(false);
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				buttonPane.add(cancelButton);
			}
		}

		doctorsSet = db.getAllDoctors();
		medicineSet = db.getAllMedicine();
		patientsSet = db.getAllPatients();
		try {
			while (doctorsSet.next()) {
				String name = doctorsSet.getString("surname") + " " + doctorsSet.getString("name");
				doctorsIds.add(doctorsSet.getInt("id_doctor"));
				doctorsBox.addItem(name);
			}
			while (patientsSet.next()) {
				String name = patientsSet.getString("surname") + " " + patientsSet.getString("name");
				patientsIds.add(patientsSet.getInt("id_patient"));
				patientsBox.addItem(name);
			}
			while (medicineSet.next()) {
				String name = medicineSet.getString("title");
				medicineIds.add(medicineSet.getInt("id_medicine"));
				medicineBox.addItem(name);
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		
	}
}
