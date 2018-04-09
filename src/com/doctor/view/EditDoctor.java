package com.doctor.view;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import com.pharmacy.data.DoctorDataContext;

public class EditDoctor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField lastNameField;
	private JTextField firstNameField;
	private JFormattedTextField yearsField;
	private JTextField specialityField;
	private JTextField quantityField;

	/**
	 * Create the dialog.
	 */
	public EditDoctor(Frame owner, DoctorDataContext db, boolean addMode) {
		super(owner, true);
		setTitle("Add doctor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 247, 183);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblDoctor = new JLabel("Last name");
			lblDoctor.setBounds(10, 11, 57, 14);
			contentPanel.add(lblDoctor);
		}
		{
			JLabel lblPatient = new JLabel("First name");
			lblPatient.setBounds(10, 36, 57, 14);
			contentPanel.add(lblPatient);
		}

		{
			quantityField = new JTextField();
			quantityField.setBounds(66, 236, 38, 20);
			quantityField.setColumns(10);
			contentPanel.add(quantityField);
			
			JLabel lblYearsOfExperience = new JLabel("Years of experience");
			lblYearsOfExperience.setBounds(10, 61, 101, 14);
			contentPanel.add(lblYearsOfExperience);
			
			JLabel lblSpeciality = new JLabel("Speciality");
			lblSpeciality.setBounds(10, 86, 57, 14);
			contentPanel.add(lblSpeciality);
			
			lastNameField = new JTextField();
			lastNameField.setBounds(73, 8, 148, 20);
			contentPanel.add(lastNameField);
			lastNameField.setColumns(10);
			
			firstNameField = new JTextField();
			firstNameField.setColumns(10);
			firstNameField.setBounds(73, 33, 148, 20);
			contentPanel.add(firstNameField);

			NumberFormat format = NumberFormat.getInstance();
		    NumberFormatter formatter = new NumberFormatter(format);
		    formatter.setValueClass(Integer.class);
		    formatter.setMinimum(0);
		    formatter.setMaximum(100);
		    formatter.setAllowsInvalid(false);
		    formatter.setCommitsOnValidEdit(true);
			yearsField = new JFormattedTextField(formatter);
			yearsField.setColumns(10);
			yearsField.setBounds(121, 58, 100, 20);
			contentPanel.add(yearsField);
						
			specialityField = new JTextField();
			specialityField.setColumns(10);
			specialityField.setBounds(73, 83, 148, 20);
			contentPanel.add(specialityField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = firstNameField.getText();
						String surname = lastNameField.getText();
						Integer years = (Integer)yearsField.getValue();
						String speciality = specialityField.getText();
						if (years != null && !name.isEmpty() && !surname.isEmpty() && !speciality.isEmpty()) {
							db.saveDoctor(name, surname, years, speciality);
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
	}
}
