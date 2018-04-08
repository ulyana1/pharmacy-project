package com.medicine.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.pharmacy.data.MedicineDataContext;
import com.pharmacy.data.PharmacyDataContext;
import com.pharmacy.entities.Medicine;
import com.pharmacy.entities.Pharmacy;
import com.pharmacy.view.NewPharmacyWindow;

public class NewMedicineWindow extends JFrame{

	private JTextField textTitle;
	private JTextField txtProducer;
	private JTextField txtPricePerBox;
	private JTextField txtQuantityPerBox;
	
	public NewMedicineWindow() {
		initialize();
	}
	
	private void initialize() {
		
		setBounds(100, 100, 407, 376);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("New Medicine");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textTitle = new JTextField();
		textTitle.setColumns(10);
		
		JLabel lblProducer = new JLabel("Producer");
		lblProducer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtProducer = new JTextField();
		txtProducer.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtPricePerBox = new JTextField();
		txtPricePerBox.setColumns(10);
	
		JLabel lblQuntity = new JLabel("Quantity");
		lblQuntity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtQuantityPerBox = new JTextField();
		txtQuantityPerBox.setColumns(10);
		
        JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String title = textTitle.getText();
				String producer = txtProducer.getText();
				String price = txtPricePerBox.getText();
				String quantity = txtQuantityPerBox.getText();
				
				if(title.isEmpty()){
					JOptionPane.showMessageDialog(null, "Title is empty");
					return;	
				}
				if(producer.isEmpty()){
					JOptionPane.showMessageDialog(null, "Producer is empty");
					return;	
				}
				
				if(price.isEmpty()){
					JOptionPane.showMessageDialog(null, "Price is empty");
					return;	
				}
				
				if(quantity.isEmpty()){
					JOptionPane.showMessageDialog(null, "Quntity is empty");
					return;	
				}
				
				//Pharmacy p = new Pharmacy(0, title, address);
				Medicine m = new Medicine(0,title,producer,Double.parseDouble(price),Integer.parseInt(quantity));
				boolean res = MedicineDataContext.getInstance().addMedicine(m);
				
				if(res){
					JOptionPane.showMessageDialog(null, "S U C C");
					NewMedicineWindow.this.setVisible(false);
					NewMedicineWindow.this.dispose();
				}
			}
		});
		
          JButton btnCancel = new JButton("Cancel");
		    btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NewMedicineWindow.this.setVisible(false);
				NewMedicineWindow.this.dispose();
			}
		});
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addGap(130))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblProducer, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblQuntity, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(txtQuantityPerBox)
										.addContainerGap())
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(txtPricePerBox)
										.addContainerGap())
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addComponent(txtProducer, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
										.addContainerGap())
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addComponent(textTitle, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
										.addContainerGap())))))
					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addGap(37)
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addGap(43))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNewLabel)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTitle))
						.addGap(32)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtProducer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblProducer, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGap(26)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtPricePerBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
						.addGap(26)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtQuantityPerBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblQuntity, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGap(26)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnSave)
							.addComponent(btnCancel))
						.addContainerGap(74, Short.MAX_VALUE))
			);
				getContentPane().setLayout(groupLayout);
				getContentPane().setLayout(groupLayout);
	}
	
}
