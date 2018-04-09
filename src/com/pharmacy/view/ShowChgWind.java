package com.pharmacy.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.pharmacy.data.MedicineDataContext;
import com.pharmacy.data.PharmacyDataContext;
import com.pharmacy.entities.Medicine;
import com.pharmacy.entities.Pharmacy;

public class ShowChgWind extends JFrame{
	
    private JPanel contentPane;
    private JTextField txtPharmTitle;
    private JTextField txtPharmAddress;
    private JTextField txtMedTitle;
    private JTextField txtMedPrice;

	public ShowChgWind(Pharmacy pharm, Medicine med){
		
	    setResizable(false);
        setTitle("\u0406\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0456\u044F \u043F\u0440\u043E \u0432\u0438\u0434\u0430\u0447\u0443");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 320, 320);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
		
        JLabel lblPharmTitle = new JLabel("Pharmacy Title");
        txtPharmTitle = new JTextField();
        txtPharmTitle.setColumns(10);
        txtPharmTitle.setText(pharm.getTitle());
        
        JLabel lblPharmAddress = new JLabel("Pharmacy Address");
        txtPharmAddress = new JTextField();
        txtPharmAddress.setColumns(10);
        txtPharmAddress.setText(pharm.getAddress());
        
        JLabel lblMedTitle = new JLabel("Medicine");
        txtMedTitle = new JTextField();
        txtMedTitle.setColumns(10);
        txtMedTitle.setText(med.getTitle());
        
        JLabel lblMedPrice = new JLabel("Medicine Price");
        txtMedPrice = new JTextField();
        txtMedPrice.setColumns(10);
        txtMedPrice.setText(med.getBoxPrice() + "");
        
        
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Pharmacy p = new Pharmacy(Integer.parseInt(txtId.getText()), txtTitle.getText(),txtAddress.getText());
				//boolean res = PharmacyDataContext.getInstance().updatePharmacy(p);
				Medicine med1 = new Medicine(med.getId(), txtMedTitle.getText(), med.getProducer(), Double.parseDouble(txtMedPrice.getText()), med.getQuantityPerBox());
				boolean res = MedicineDataContext.getInstance().updateMed$inPharm(pharm, med1);
				if(res)
					JOptionPane.showMessageDialog(null, "Medicine in Pharmacy was updated successfully.");
			}
		});
        
        JButton btnClose = new JButton("\u0417\u0430\u043A\u0440\u0438\u0442\u0438");
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ShowChgWind.this.setVisible(false);
            	ShowChgWind.this.dispose();
            }
        });
        
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lblPharmTitle, Alignment.LEADING)
        				.addComponent(txtPharmTitle, Alignment.LEADING, 192, 192, 192)
        				
        				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblPharmAddress)
        						.addComponent(txtPharmAddress, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED))
        				
        				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
            					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
            						.addComponent(lblMedTitle)
            						.addComponent(txtMedTitle, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
            					.addPreferredGap(ComponentPlacement.RELATED))
        				
        				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
            					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
            						.addComponent(lblMedPrice)
            						.addComponent(txtMedPrice, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
            					.addPreferredGap(ComponentPlacement.RELATED))
        				/*
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING))
        					.addGap(18)
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING))
        					.addPreferredGap(ComponentPlacement.RELATED)) */
        				.addGroup(gl_contentPane.createSequentialGroup()
        						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        							.addComponent(btnClose)
        							.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        						.addGap(18))
        				)
        			.addGap(12))
        );
        gl_contentPane.setVerticalGroup(
            	gl_contentPane.createParallelGroup(Alignment.LEADING)
            		.addGroup(gl_contentPane.createSequentialGroup()
            				
        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        .addComponent(lblPharmTitle))
        .addPreferredGap(ComponentPlacement.RELATED)
        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        .addComponent(txtPharmTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            			/*
            			.addPreferredGap(ComponentPlacement.UNRELATED)
            			.addComponent(lblAddress)
            			.addPreferredGap(ComponentPlacement.RELATED)
            			.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)*/
            			
        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        .addComponent(lblPharmAddress))
        .addPreferredGap(ComponentPlacement.RELATED)
        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        .addComponent(txtPharmAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        
        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        .addComponent(lblMedTitle))
        .addPreferredGap(ComponentPlacement.RELATED)
        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        .addComponent(txtMedTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        
        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        .addComponent(lblMedPrice))
        .addPreferredGap(ComponentPlacement.RELATED)
        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        .addComponent(txtMedPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        
          .addGap(22)
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  				.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
  				.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
          .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            		);
        contentPane.setLayout(gl_contentPane);
	}
	
}
