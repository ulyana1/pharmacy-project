package com.pharmacy.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.pharmacy.data.PatientData;
import com.pharmacy.data.PrescrData;
import com.pharmacy.data.PurchaseData;
import com.pharmacy.entities.Patient;
import com.pharmacy.entities.Prescription;
import com.pharmacy.entities.Purchase;
import com.pharmacy.view.elements.CustomTableModel;
import com.pharmacy.view.elements.DateLabelFormatter;
import com.pharmacy.view.elements.CustomTableModel.TableSelectionEventHandler;

import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class AddPatientFrame extends JFrame {

	private JFrame jFrame;
	
	private static final int BUTTON_WIDTH = 200;
	private static final int BUTTON_HEIGHT = 35;

	private static final int BORDER = 40;

	private static final Font font = new Font("SansSerif", Font.PLAIN, 14);
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldBirthday;
	private JTextField textFieldPhone;

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
	public AddPatientFrame() {
	
		setBounds(150, 150, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textFieldSurname.setColumns(10);
		
		textFieldBirthday = new JTextField();
		textFieldBirthday.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textFieldBirthday.setColumns(10);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textFieldPhone.setColumns(10);
		
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DateLabelFormatter toDate = new DateLabelFormatter();
				
				 String name = textFieldName.getText();
				 String surname = textFieldSurname.getText();
				 String birthday = textFieldBirthday.getText();
				 String phone = textFieldPhone.getText();
				 System.out.println(name + " " + surname + " " + birthday + " " + phone);
				 if(name.isEmpty()){
						JOptionPane.showMessageDialog(null, "Name is empty");
						return;	
					}
				if(surname.isEmpty()){
						JOptionPane.showMessageDialog(null, "Surname is empty");
						return;	
					}
				if(birthday.equals(null)){
						JOptionPane.showMessageDialog(null, "Birthday is empty or not correct");
						return;	
					}
				/*if(phone.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Phone is empty");
				}*/
				Patient pt = new Patient(name, surname, birthday, phone);
				boolean res = PatientData.getInstance().addPatient(pt);
		
				if(res){
					JOptionPane.showMessageDialog(null, "Success");
				}
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblSurame = new JLabel("Surname");
		lblSurame.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirthday.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPhone = new JLabel("Phone");
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
								.addComponent(textFieldPhone)
								.addComponent(textFieldBirthday, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
								.addComponent(textFieldSurname, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
								.addComponent(textFieldName, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)))
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
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSurame, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldBirthday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBirthday, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(button)
					.addContainerGap(101, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		setTitle("Add patient");
		
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
