package com.pharmacy.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import com.medicine.view.MedicineSearchWindow;
import com.pharmacy.data.PharmacyDataContext;
import com.pharmacy.entities.Delivery;
import com.pharmacy.entities.DeliveryRecord;
import com.pharmacy.entities.Medicine;
import com.pharmacy.entities.Pharmacy;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;

public class NewDeliveryWindow extends JFrame {
	private JTextField txtPharmacy;
	private Pharmacy pharmacy;
	private List<DeliveryRecord> deliveryRecords;
	JList<Medicine> lstMedicine;
	DefaultListModel<Medicine> model;
	public String DeliveryCompany;
	/**
	 * Create the application.
	 */
	public NewDeliveryWindow() {
		setTitle("New delivery");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		deliveryRecords = new ArrayList<DeliveryRecord>();
		txtPharmacy = new JTextField();
		txtPharmacy.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<Pharmacy> phlist = PharmacyDataContext.getInstance().getAllPharmacies();
				PharmacySearchWindow srch = new PharmacySearchWindow(phlist, NewDeliveryWindow.this);
				srch.setVisible(true);
				
			}
		});
		
		JLabel lblPharmacy = new JLabel("Pharmacy");
		lblPharmacy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblAddMedicine = new JLabel("Add medicine");
		lblAddMedicine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JComboBox<String> deliveryCompanyList = new JComboBox<String>();
		DefaultComboBoxModel<String> mod = new DefaultComboBoxModel<String>();
		List<String> a = PharmacyDataContext.getInstance().getDeliveryCompanyList();
		for(String r : a){
			mod.addElement(r);
		}
		deliveryCompanyList.setModel(mod);
		deliveryCompanyList.setSelectedIndex(0);
		deliveryCompanyList.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				DeliveryCompany = (String)e.getItem();
			}
		});
		JButton btnSearchMedicine = new JButton("Search medicine");
		btnSearchMedicine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<Medicine> lst = PharmacyDataContext.getInstance().getMedicineByProducer(DeliveryCompany);
				
				new MedicineSearchView(lst, NewDeliveryWindow.this).setVisible(true);
			}
		});
		
		lstMedicine = new JList<Medicine>();
		lstMedicine.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstMedicine.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		model = new DefaultListModel<Medicine>();
		lstMedicine.setModel(model);
        
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Delivery del = new Delivery(0,pharmacy.getId(),pharmacy.getTitle(),Date.from(Instant.now()),deliveryRecords);
				boolean res = PharmacyDataContext.getInstance().addDelivery(del);
				if(res){
					JOptionPane.showMessageDialog(null, "Success!");
					NewDeliveryWindow.this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Something went wrong");
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NewDeliveryWindow.this.dispose();
			}
		});
		
		JLabel lblDel = new JLabel("Select deliver company");
		lblDel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPharmacy)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(20)
										.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
									.addComponent(lblAddMedicine)
									.addComponent(btnSearchMedicine))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lstMedicine, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblDel)
										.addGap(27)
										.addComponent(deliveryCompanyList, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
									.addComponent(txtPharmacy))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPharmacy)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPharmacy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDel)
						.addComponent(deliveryCompanyList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lstMedicine, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAddMedicine)
							.addGap(11)
							.addComponent(btnSearchMedicine)))
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreate)
						.addComponent(btnCancel))
					.addGap(29))
		);
		getContentPane().setLayout(groupLayout);
	}

	public void setPharmacy(Pharmacy p) {
		// TODO Auto-generated method stub
		pharmacy = p;
		txtPharmacy.setText(pharmacy.toString());
		
	}

	public void addMedicineToList(Medicine p, int count) {
		// TODO Auto-generated method stub
		deliveryRecords.add(new DeliveryRecord(0, p.getId(), p.getTitle(), count));
		model.addElement(p);
	}
}
