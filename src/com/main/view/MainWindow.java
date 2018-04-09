package com.main.view;
import java.awt.EventQueue;
import java.awt.FontFormatException;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.BorderLayout;

import net.proteanit.sql.DbUtils;

import java.awt.event.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import com.accounting.AccountingWindow;
import com.doctor.view.EditDoctor;
import com.doctor.view.EditPrescription;
import com.medicine.view.TesterMedicine;
import com.pharmacy.data.PatientData;
import com.pharmacy.data.PrescrData;
import com.pharmacy.data.PurchaseData;
import com.pharmacy.entities.Patient;
import com.pharmacy.entities.Prescription;
import com.pharmacy.entities.Purchase;
import com.pharmacy.view.*;
import com.pharmacy.view.elements.CustomTableModel;
import com.pharmacy.view.elements.CustomTableModel.TableSelectionEventHandler;
import com.pharmacy.views.AddPatientFrame;
import com.pharmacy.views.AddPurchaseFrame;
import com.pharmacy.views.CurrentPatientFrame;
import com.pharmacy.views.MainFrame;
import com.pharmacy.views.PrescriptionsFrame;
import com.pharmacy.views.PurchaseFrame;
import com.pharmacy.views.TableDataViewWindow;
import com.toedter.calendar.JDateChooser;

import connection.MySqlConnection;

public class MainWindow {
	
	TestMainFrame pharmacyView;
	TesterMedicine medicineView;

	private MySqlConnection db;
	private int doctorsSortBy = 1;
	private boolean doctorsSortAsc = true;
	private int prescriptionsSortBy = 1;
	private boolean prescriptionsSortAsc = true;
	
	private ResultSet specialitiesSet;
	
	private JFrame frame;
	private JTextField nameField;
	private JTable tableDoctors;
	private JTextField doctorNameField;
	private JTextField patientNameField;
	private JTable tablePrescriptions;
	private JDateChooser fromDateField;
	private JDateChooser toDateField;
	private JCheckBox notFulfilledYetCheckbox;
	private JFormattedTextField expFromField;
	private JFormattedTextField expToField;
	private JComboBox specialityBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		db = new MySqlConnection();
		pharmacyView = new TestMainFrame();
		medicineView = new TesterMedicine(); 
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Doctor");
		frame.setBounds(100, 100, 684, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel doctorsPanel = new JPanel();
		tabbedPane.addTab("Doctors", null, doctorsPanel, null);
		doctorsPanel.setLayout(null);
		
		nameField = new JTextField();
		nameField.setBounds(10, 27, 158, 20);
		doctorsPanel.add(nameField);
		
		JLabel lblNewLabel = new JLabel("Name or surname");
		lblNewLabel.setBounds(10, 11, 107, 14);
		doctorsPanel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 643, 374);
		doctorsPanel.add(scrollPane);
		
		tableDoctors = new JTable();
		tableDoctors.setEnabled(false);
		scrollPane.setViewportView(tableDoctors);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchDoctors();
			}
		});
		btnNewButton.setBounds(10, 58, 89, 23);
		doctorsPanel.add(btnNewButton);
		
		JPanel prescriptionsPanel = new JPanel();
		tabbedPane.addTab("Prescriptions", null, prescriptionsPanel, null);
		prescriptionsPanel.setLayout(null);
		
		fromDateField = new JDateChooser();
		fromDateField.setBounds(10, 78, 133, 20);
		prescriptionsPanel.add(fromDateField);
		
		doctorNameField = new JTextField();
		doctorNameField.setBounds(10, 29, 133, 20);
		prescriptionsPanel.add(doctorNameField);
		doctorNameField.setColumns(10);
		
		patientNameField = new JTextField();
		patientNameField.setBounds(153, 29, 133, 20);
		prescriptionsPanel.add(patientNameField);
		patientNameField.setColumns(10);
		
		JLabel lblDoctorName = new JLabel("Doctor");
		lblDoctorName.setBounds(10, 11, 95, 14);
		prescriptionsPanel.add(lblDoctorName);
		
		JLabel lblPatient = new JLabel("Patient");
		lblPatient.setBounds(154, 11, 95, 14);
		prescriptionsPanel.add(lblPatient);
		
		JLabel lblDateFrom = new JLabel("From date");
		lblDateFrom.setBounds(10, 60, 95, 14);
		prescriptionsPanel.add(lblDateFrom);
		
		toDateField = new JDateChooser();
		toDateField.setBounds(153, 78, 133, 20);
		prescriptionsPanel.add(toDateField);
		
		JLabel lblToDate = new JLabel("To date");
		lblToDate.setBounds(153, 60, 95, 14);
		prescriptionsPanel.add(lblToDate);
		
		notFulfilledYetCheckbox = new JCheckBox("Prescription not fulfilled yet");
		notFulfilledYetCheckbox.setBounds(292, 28, 187, 23);
		prescriptionsPanel.add(notFulfilledYetCheckbox);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 109, 643, 357);
		prescriptionsPanel.add(scrollPane_1);
		
		tablePrescriptions = new JTable();
		tablePrescriptions.setEnabled(false);
		scrollPane_1.setViewportView(tablePrescriptions);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String doctorName = doctorNameField.getText();
				String patientName = patientNameField.getText();
				Date fromDate = fromDateField.getDate();
				Date toDate = toDateField.getDate();
				ResultSet rs = db.findPrescriptions(doctorName, patientName, fromDate, toDate, notFulfilledYetCheckbox.isSelected(), prescriptionsSortBy, prescriptionsSortAsc);
				tablePrescriptions.setModel(DbUtils.resultSetToTableModel(rs));
			}
		});
		btnSearch.setBounds(296, 75, 89, 23);
		prescriptionsPanel.add(btnSearch);

		tableDoctors.getTableHeader().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int col = tableDoctors.columnAtPoint(e.getPoint());
		        changeDoctorsSortBy(col + 1);
		    }
		});
		tablePrescriptions.getTableHeader().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int col = tablePrescriptions.columnAtPoint(e.getPoint());
		        changePrescriptionsSortBy(col + 1);
		    }
		});
		tableDoctors.getTableHeader().setReorderingAllowed(false);
		tablePrescriptions.getTableHeader().setReorderingAllowed(false);

		tableDoctors.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int rowNumber = tableDoctors.rowAtPoint(e.getPoint());
		        int id = (int)tableDoctors.getValueAt(rowNumber, 0);
				System.out.println("Row: " + rowNumber + ", Id: " + id);
				int dialogResult = JOptionPane.showConfirmDialog (frame, "Are you sure you want to delete doctor with ID " + id + "?", "Delete?", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					db.deleteDoctor(id);
					updateSpecialitiesList();
					searchDoctors();
				}
		    }
		});
		tablePrescriptions.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int rowNumber = tablePrescriptions.rowAtPoint(e.getPoint());
		        int id = (int)tablePrescriptions.getValueAt(rowNumber, 0);
				System.out.println("Row: " + rowNumber + ", Id: " + id);
				int dialogResult = JOptionPane.showConfirmDialog (frame, "Are you sure you want to delete prescription with ID " + id + " and all medicine associated with it?", "Delete?", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					db.deletePrescription(id);
					searchPrescriptions();
				}
		    }
		});

		ResultSet rs = db.findDoctors("", null, null, null, doctorsSortBy, doctorsSortAsc);
		tableDoctors.setModel(DbUtils.resultSetToTableModel(rs));
		
		JButton button = new JButton("Add new");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditDoctor dialog = new EditDoctor(frame, db, true);
				dialog.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent e) 
					{
						searchDoctors();
						updateSpecialitiesList();
					}
				});
				dialog.setVisible(true);
			}
		});
		button.setBounds(564, 58, 89, 23);
		doctorsPanel.add(button);
		
		JLabel lblSpeciality = new JLabel("Speciality");
		lblSpeciality.setBounds(178, 11, 46, 14);
		doctorsPanel.add(lblSpeciality);
		
		JLabel lblExperienceFrom = new JLabel("Experience from");
		lblExperienceFrom.setBounds(346, 11, 89, 14);
		doctorsPanel.add(lblExperienceFrom);

		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(100);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
		expFromField = new JFormattedTextField(formatter);
		expFromField.setBounds(346, 27, 100, 20);
		doctorsPanel.add(expFromField);
		
		expToField = new JFormattedTextField(formatter);
		expToField.setBounds(456, 27, 100, 20);
		doctorsPanel.add(expToField);
		
		JLabel lblExperienceTo = new JLabel("Experience to");
		lblExperienceTo.setBounds(456, 11, 89, 14);
		doctorsPanel.add(lblExperienceTo);
		
		specialityBox = new JComboBox();
		specialityBox.setBounds(178, 27, 158, 20);
		doctorsPanel.add(specialityBox);
		rs = db.findPrescriptions("", "", null, null, false, prescriptionsSortBy, prescriptionsSortAsc);
		tablePrescriptions.setModel(DbUtils.resultSetToTableModel(rs));
		
		JButton btnAddNew = new JButton("Add new");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditPrescription dialog = new EditPrescription(frame, db, true);
				dialog.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent e) 
					{
						searchPrescriptions();
					}
				});
				dialog.setVisible(true);
			}
		});
		btnAddNew.setBounds(564, 75, 89, 23);
		prescriptionsPanel.add(btnAddNew);
		
		JPanel patientsPanel = new JPanel();
		tabbedPane.addTab("Patients", null, patientsPanel, null);
		patientsPanel.setLayout(null);
		
		JPanel patWinPanel = new JPanel();
		patWinPanel.setBounds(10, 45, 643, 422);
		patientsPanel.add(patWinPanel);
		
		PrescrData prescrContext = PrescrData.getInstance();
		PatientData patientContext = PatientData.getInstance();
		
		JButton btnPatients = new JButton("Patients");
		btnPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		  	
		  		

				CustomTableModel.TableSelectionEventHandler<Patient> selectionHandler = new TableSelectionEventHandler<Patient>(){
                    @Override
                    public void handle(Patient object, int row) {
                        new CurrentPatientFrame(object).setVisible(true);
                    }
                };
                List<Patient> ptlist = patientContext.getAllPatients();
                patWinPanel.removeAll();
                patWinPanel.add(new TableDataViewWindow<>(Patient.class, ptlist, selectionHandler).getContentPane());
                patWinPanel.revalidate();
			}
		});
		btnPatients.setBounds(10, 11, 89, 23);
		patientsPanel.add(btnPatients);
		
		PurchaseData purchContext = PurchaseData.getInstance();
  		
		JButton btnPurchases = new JButton("Purchases");
		btnPurchases.setBounds(114, 11, 89, 23);
		btnPurchases.addActionListener(new ActionListener() {
  			@Override
			public void actionPerformed(ActionEvent e) {
				
				CustomTableModel.TableSelectionEventHandler<Purchase> selectionHandler = new TableSelectionEventHandler<Purchase>(){

                    @Override
                    public void handle(Purchase object, int row) {
                        new PurchaseFrame(object).setVisible(true);
                    }
                    
                };
                List<Purchase> ptlist = purchContext.getAllPurchases();
                patWinPanel.removeAll();
                patWinPanel.add(new TableDataViewWindow<>(Purchase.class, ptlist, selectionHandler).getContentPane());
                patWinPanel.revalidate();
			}
  		});
		patientsPanel.add(btnPurchases);
		
		
		JButton btnAddpatient = new JButton("Add patient");
		btnAddpatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
  			
  				AddPatientFrame addPatientFrame = new AddPatientFrame();
  				addPatientFrame.setVisible(true);
  			}
		});
		btnAddpatient.setBounds(221, 11, 89, 23);
		patientsPanel.add(btnAddpatient);
		
		JButton btnAddPurchase = new JButton("Add Purchase");
		btnAddPurchase.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
	            new AddPurchaseFrame(patientContext.getAllPatientsNames()).setVisible(true);
	          //new PharmacySearchWindow(pharmacyContext.getAllPharmacies()).setVisible(true);
	        }
		});
		btnAddPurchase.setBounds(320, 11, 107, 23);
		patientsPanel.add(btnAddPurchase);
		
		updateSpecialitiesList();

		tabbedPane.addTab("Pharmacies", pharmacyView.contentPane);
		tabbedPane.addTab("Medicine", medicineView.contentPane);
		
		AccountingWindow accountingWindow;
		try {
			accountingWindow = new AccountingWindow();
			tabbedPane.addTab("Accounting", accountingWindow.content);
		} catch (ClassNotFoundException | IOException | SQLException | FontFormatException e1) {
			e1.printStackTrace();
		}
	}
	
	private void changeDoctorsSortBy(int colName) {
		if (colName == doctorsSortBy) {
			doctorsSortAsc = !doctorsSortAsc;
		} else {
			doctorsSortAsc = true;
			doctorsSortBy = colName;
		}
		searchDoctors();
	}
	
	private void changePrescriptionsSortBy(int colName) {
		if (colName == prescriptionsSortBy) {
			prescriptionsSortAsc = !prescriptionsSortAsc;
		} else {
			prescriptionsSortAsc = true;
			prescriptionsSortBy = colName;
		}
		searchPrescriptions();
	}
	
	private void searchDoctors() {
		ResultSet rs = db.findDoctors(nameField.getText(), (String)specialityBox.getSelectedItem(), (Integer)expFromField.getValue(), (Integer)expToField.getValue(), doctorsSortBy, doctorsSortAsc);
		tableDoctors.setModel(DbUtils.resultSetToTableModel(rs));
	}
	
	private void searchPrescriptions() {
		String doctorName = doctorNameField.getText();
		String patientName = patientNameField.getText();
		Date fromDate = fromDateField.getDate();
		Date toDate = toDateField.getDate();
		ResultSet rs = db.findPrescriptions(doctorName, patientName, fromDate, toDate, notFulfilledYetCheckbox.isSelected(), prescriptionsSortBy, prescriptionsSortAsc);
		tablePrescriptions.setModel(DbUtils.resultSetToTableModel(rs));
	}
	
	private void updateSpecialitiesList() {
		specialityBox.removeAllItems();
		specialitiesSet = db.getAllSpecialities();
		try {
			specialityBox.addItem("");
			while (specialitiesSet.next()) {
				String name = specialitiesSet.getString("speciality");
				specialityBox.addItem(name);
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
}
