package com.pharmacy.views;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.pharmacy.data.PatientData;
import com.pharmacy.data.PrescrData;
import com.pharmacy.data.PurchaseData;
import com.pharmacy.entities.Purchase;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;

public class AddPurchaseFrame extends JFrame {

	private JFrame jFrame;
	
	private static final int BUTTON_WIDTH = 200;
	private static final int BUTTON_HEIGHT = 35;

	private static final int BORDER = 40;

	private static final Font font = new Font("SansSerif", Font.PLAIN, 14);
	private JPanel contentPane;
	private JTextField textFieldDate;
	private JComboBox textFieldPrescrId;
	private JComboBox textFieldPharmacy;
	private JComboBox textFieldPatient;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPatientFrame frame = new AddPatientFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public AddPurchaseFrame() {
	
		setBounds(150, 150, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textFieldDate = new JTextField();
		textFieldDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//		textFieldDate.setColumns(10);
		
		textFieldPrescrId = new JComboBox();
		textFieldPrescrId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//		textFieldPrescrId.setColumns(10);
		
		textFieldPharmacy = new JComboBox();
		textFieldPharmacy.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//		textFieldPharmacy.setColumns(10);
		
		textFieldPatient = new JComboBox();
		textFieldPatient.setFont(new Font("Times New Roman", Font.PLAIN, 18));
//		textFieldPatient.setColumns(10);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 String date = textFieldDate.getText();
				 //String prescr_id = textFieldPrescrId.getText();
				 String prescr_id = textFieldPrescrId.getName();
				 //String pharmacy = textFieldPharmacy.getText();
				 String pharmacy = textFieldPharmacy.getName();
				 //String patient = textFieldPatient.getText();
				 String patient = textFieldPatient.getName();
				 System.out.println(date + " " + prescr_id + " " + pharmacy + " " + patient);
				 if(date.isEmpty()){
						JOptionPane.showMessageDialog(null, " is empty");
						return;	
					}
				if(prescr_id.isEmpty()){
						JOptionPane.showMessageDialog(null, " is empty");
						return;	
					}
				if(pharmacy.equals(null)){
						JOptionPane.showMessageDialog(null, " is empty or not correct");
						return;	
					}
				/*if(phone.isEmpty()) {
						JOptionPane.showMessageDialog(null, " is empty");
				}*/
				Purchase pt = new Purchase(date, prescr_id, pharmacy, patient);
				boolean res = PurchaseData.getInstance().addPurchase(pt);
		
				if(res){
					JOptionPane.showMessageDialog(null, "Success");
				}
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		
		
		JLabel lblName = new JLabel("Date");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblSurame = new JLabel("Prescription");
		lblSurame.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblBirthday = new JLabel("Pharmacy");
		lblBirthday.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirthday.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPhone = new JLabel("Patient");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(80)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblName)
								.addComponent(lblSurame)
								.addComponent(lblBirthday, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
							.addGap(49)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldPatient)
								.addComponent(textFieldPharmacy, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
								.addComponent(textFieldPrescrId, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
								.addComponent(textFieldDate, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(242)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldDate, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPrescrId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSurame, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPharmacy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBirthday, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldPatient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(button)
					.addContainerGap(101, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		setTitle("Add purchase");
		
		//================ Menu ========================
  		setBounds(new Rectangle(150, 150, 700, 450));
  		
  		//========================================================
  		PatientData patientContext = PatientData.getInstance();
  		
  		//=========================================================
  		
  		PrescrData prescrContext = PrescrData.getInstance();
  		
  		PurchaseData purchContext = PurchaseData.getInstance();
  		//=================== Menu ========================
		
	}
}
