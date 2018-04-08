package com.pharmacy.views;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.pharmacy.data.PatientData;
import com.pharmacy.data.PrescrData;
import com.pharmacy.data.PurchaseData;
import com.pharmacy.entities.Patient;
import com.pharmacy.entities.Prescription;
import com.pharmacy.entities.Purchase;
import com.pharmacy.view.elements.CustomTableModel;
import com.pharmacy.view.elements.CustomTableModel.TableSelectionEventHandler;

public class TableDataViewWindow<T> extends JFrame {

    private JPanel contentPane;
    private JTable table;

    public TableDataViewWindow(Class<T> clazz, List<T> entities, final CustomTableModel.TableSelectionEventHandler<T> handler) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      //================ Menu ========================
      		setBounds(new Rectangle(150, 150, 700, 450));
      		JMenuBar menuBar = new JMenuBar();
      		setJMenuBar(menuBar);
      		
      		JMenu main = new JMenu("\u0413\u043E\u043B\u043E\u0432\u043D\u0430");
      		menuBar.add(main);
      		
      		JMenuItem main_page = new JMenuItem("\u0413\u043E\u043B\u043E\u0432\u043D\u0430");
      		main_page.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				dispose();
      				MainFrame mainFrame = new MainFrame();
      				mainFrame.setVisible(true);
      			}
      		});
      		main.add(main_page);
      		
      		JMenuItem pharmacies = new JMenuItem("\u0410\u043F\u0442\u0435\u043A\u0438");
      		pharmacies.setEnabled(false);
      		main.add(pharmacies);
      		
      		JMenuItem medicines = new JMenuItem("\u041B\u0456\u043A\u0438");
      		medicines.setEnabled(false);
      		main.add(medicines);
      		
      		JMenuItem doctors = new JMenuItem("\u041B\u0456\u043A\u0430\u0440\u0456");
      		doctors.setEnabled(false);
      		main.add(doctors);
      		
      		JMenuItem patients = new JMenuItem("\u041F\u0430\u0446\u0456\u0454\u043D\u0442\u0438");
      		patients.setEnabled(false);
      		main.add(patients);
      		
      		JMenuItem finances = new JMenuItem("\u0411\u0443\u0445\u0433\u0430\u043B\u0435\u0442\u0440\u0456\u044F");
      		finances.setEnabled(false);
      		main.add(finances);
      		
      		JMenu menu = new JMenu("\u041F\u0435\u0440\u0435\u0433\u043B\u044F\u043D\u0443\u0442\u0438");
      		menuBar.add(menu);
      		
      		//========================================================
      		PatientData patientContext = PatientData.getInstance();
      		
      		JMenuItem showPatients = new JMenuItem("\u041F\u0435\u0440\u0435\u0433\u043B\u044F\u043D\u0443\u0442\u0438 \u043F\u0430\u0446\u0456\u0454\u043D\u0442\u0456\u0432");
      		showPatients.addActionListener(new ActionListener() {
      			@Override
    			public void actionPerformed(ActionEvent e) {
    				/*
    				dispose();
    				PatientFrame patientFrame = new PatientFrame();
    				patientFrame.setVisible(true);
    				*/
    				dispose();
    				CustomTableModel.TableSelectionEventHandler<Patient> selectionHandler = new TableSelectionEventHandler<Patient>(){

                        @Override
                        public void handle(Patient object, int row) {
                            new CurrentPatientFrame(object).setVisible(true);
                        }
                        
                    };
                    List<Patient> ptlist = patientContext.getAllPatients();
                    new TableDataViewWindow<>(Patient.class, ptlist, selectionHandler).setVisible(true);
    			}
      		});
      		menu.add(showPatients);
      		
      		//=========================================================
      		
      		PrescrData prescrContext = PrescrData.getInstance();
      		
      		JMenuItem showPrescr = new JMenuItem("\u041F\u0435\u0440\u0435\u0433\u043B\u044F\u043D\u0443\u0442\u0438 \u0440\u0435\u0446\u0435\u043F\u0442\u0438");
      		showPrescr.addActionListener(new ActionListener() {
      			@Override
    			public void actionPerformed(ActionEvent e) {
    				/*
    				dispose();
    				PatientFrame patientFrame = new PatientFrame();
    				patientFrame.setVisible(true);
    				*/
    				dispose();
    				CustomTableModel.TableSelectionEventHandler<Prescription> selectionHandler = new TableSelectionEventHandler<Prescription>(){

                        @Override
                        public void handle(Prescription object, int row) {
                            new PrescriptionsFrame(object).setVisible(true);
                        }
                        
                    };
                    List<Prescription> prescr_list = prescrContext.getAllPrescr();
                    new TableDataViewWindow<>(Prescription.class, prescr_list, selectionHandler).setVisible(true);
    			}
      		});
      		menu.add(showPrescr);
      		
      		PurchaseData purchContext = PurchaseData.getInstance();
      		
      		JMenuItem showPurch = new JMenuItem("\u041F\u0435\u0440\u0435\u0433\u043B\u044F\u043D\u0443\u0442\u0438 \u043F\u043E\u043A\u0443\u043F\u043A\u0438");
      		showPurch.addActionListener(new ActionListener() {
      			@Override
    			public void actionPerformed(ActionEvent e) {
    				/*
    				dispose();
    				PatientFrame patientFrame = new PatientFrame();
    				patientFrame.setVisible(true);
    				*/
    				dispose();
    				CustomTableModel.TableSelectionEventHandler<Purchase> selectionHandler = new TableSelectionEventHandler<Purchase>(){

                        @Override
                        public void handle(Purchase object, int row) {
                            new PurchaseFrame(object).setVisible(true);
                        }
                        
                    };
                    List<Purchase> ptlist = purchContext.getAllPurchases();
                    new TableDataViewWindow<>(Purchase.class, ptlist, selectionHandler).setVisible(true);
    			}
      		});
      		menu.add(showPurch);
      		
      		JMenu add = new JMenu("\u0414\u043E\u0434\u0430\u0442\u0438");
      		menuBar.add(add);
      		
      		JMenuItem addPatient = new JMenuItem("\u0414\u043E\u0434\u0430\u0442\u0438 \u043F\u0430\u0446\u0456\u0454\u043D\u0442\u0430");
      		addPatient.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				dispose();
      				AddPatientFrame addPatientFrame = new AddPatientFrame();
      				addPatientFrame.setVisible(true);
      			}
      		});
      		add.add(addPatient);
      		
      		JMenuItem addPurchase = new JMenuItem("\u0414\u043E\u0434\u0430\u0442\u0438 \u043F\u043E\u043A\u0443\u043F\u043A\u0443");
      		addPurchase.addActionListener(new ActionListener() {
//      			public void actionPerformed(ActionEvent e) {
//      				dispose();
//      				AddPurchaseFrame addPurchFrame = new AddPurchaseFrame();
//      				addPurchFrame.setVisible(true);
//      			}
      			@Override
                public void actionPerformed(ActionEvent e) {
      				dispose();
                    new AddPurchaseFrame(patientContext.getAllPatients()).setVisible(true);
//                    for(Patient p : patientContext.getAllPatients()){
//                    	System.out.println(p);
//                    }
                }
      		});
      		add.add(addPurchase);
      		//=================== Menu ========================
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        final CustomTableModel<T> model = new CustomTableModel<>(entities, clazz, handler);
        
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (handler != null)
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting()) return;
                
                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    if (!lsm.isSelectionEmpty()) {
                        int selectedRow = lsm.getMinSelectionIndex();
                        handler.handle(model.getAtRow(selectedRow), selectedRow);
                    }
                }
            });
        
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

}
