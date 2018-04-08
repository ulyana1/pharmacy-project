package com.pharmacy.views;

import javax.swing.JFrame;

import com.pharmacy.entities.Patient;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class CurrentPatientFrame extends JFrame {
	
	private JPanel contentPane;
	public CurrentPatientFrame(Patient patient) {
		
		setResizable(false);
		
        setTitle("\u0406\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0456\u044F \u043F\u0440\u043E \u0432\u0438\u0434\u0430\u0447\u0443");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
		
		JLabel lblName = new JLabel("Name");
		lblName.setText(patient.getFirstName() + " " + patient.getLastName());;
		JLabel lblYears = new JLabel();
		lblYears.setText(patient.getDateOfBirth().toString());
		JLabel lblPhone = new JLabel("New label");
		lblPhone.setText(patient.getPhoneNumber());
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGap(75)
					.addComponent(lblYears)
					.addContainerGap(90, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(200, Short.MAX_VALUE)
					.addComponent(lblPhone)
					.addGap(189))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(lblYears))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPhone)
					.addContainerGap(211, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
	}
}
