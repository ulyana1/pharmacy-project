package com.pharmacy.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.pharmacy.data.MedicineDataContext;
import com.pharmacy.data.PharmacyDataContext;
import com.pharmacy.entities.*;
import com.pharmacy.view.elements.CustomTableModel;
import com.pharmacy.view.elements.CustomTableModel.TableSelectionEventHandler;

public class PharmacyDetailedInfoWindow extends JFrame {

    private JPanel contentPane;
    private JTextField txtTitle;
    private JTextField txtId;
    private JTextField txtAddress;
    
    /**
     * Create the frame.
     */
    public PharmacyDetailedInfoWindow(Pharmacy pharmacy) {
        setResizable(false);
        setTitle("\u0406\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0456\u044F \u043F\u0440\u043E \u0432\u0438\u0434\u0430\u0447\u0443");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 320, 220);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JLabel lblTitle = new JLabel("Title");
        
        txtTitle = new JTextField();
        txtTitle.setColumns(10);
        txtTitle.setText(pharmacy.getTitle());
        
        JLabel lblId = new JLabel("Id");
        
        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setColumns(10);
        txtId.setText(pharmacy.getId() + "");
        
        JLabel lblAddress = new JLabel("Address");
        
        txtAddress = new JTextField();
        txtAddress.setColumns(10);
        txtAddress.setText(pharmacy.getAddress());
        
        JButton btnOpenDrugList = new JButton("Show drugs");
        //btnOpenDrugList.setEnabled(false);
        btnOpenDrugList.addActionListener(new ActionListener() {
        	
        	@Override
			public void actionPerformed(ActionEvent e) {
				CustomTableModel.TableSelectionEventHandler<Medicine> selectionHandler = new TableSelectionEventHandler<Medicine>() {

                    @Override
                    public void handle(Medicine object, int row) {
                    	List<Medicine> medsList = MedicineDataContext.getInstance().getMedBalance(object.getId());
                    	new TableDataViewWindow<>(Medicine.class, medsList, null).setVisible(true);
                    }                    
                };
                List<Medicine> medsList = MedicineDataContext.getInstance().getMedBalance(pharmacy.getId());
            	new TableDataViewWindow<>(Medicine.class, medsList, selectionHandler).setVisible(true);
			}
        });
       
        JButton btnClose = new JButton("\u0417\u0430\u043A\u0440\u0438\u0442\u0438");
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PharmacyDetailedInfoWindow.this.setVisible(false);
                PharmacyDetailedInfoWindow.this.dispose();
            }
        });
        
        JButton btnSave = new JButton("Save");
        
        btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pharmacy p = new Pharmacy(Integer.parseInt(txtId.getText()), txtTitle.getText(),txtAddress.getText());
				boolean res = PharmacyDataContext.getInstance().updatePharmacy(p);
				if(res)
					JOptionPane.showMessageDialog(null, "Pharmacy was updated successfully.");
			}
		});
        
        JButton btnDsf = new JButton("Sell history");
        
        btnDsf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CustomTableModel.TableSelectionEventHandler<Purchase> selectionHandler = new TableSelectionEventHandler<Purchase>() {

                    @Override
                    public void handle(Purchase object, int row) {
                    	List<PurchaseRecord> purchaseRecordList = PharmacyDataContext.getInstance().getPurchaseRecords(object.getId_purch());
                    	new TableDataViewWindow<>(PurchaseRecord.class, purchaseRecordList, null).setVisible(true);
                    }
                    
                };
				
				List<Purchase> purchaseList = PharmacyDataContext.getInstance().getPurchaseHistory(pharmacy.getId());
				new TableDataViewWindow<>(Purchase.class, purchaseList, selectionHandler).setVisible(true);
			}
		});
        
        JButton btnPriceHistory = new JButton("Price History");
        btnPriceHistory.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				CustomTableModel.TableSelectionEventHandler<MedicineChangeRecord> selectionHandler = new TableSelectionEventHandler<MedicineChangeRecord>() {

                    @Override
                    public void handle(MedicineChangeRecord object, int row) {}                    
                };
    
                List<MedicineChangeRecord> medsList = MedicineDataContext.getInstance().getPriceHistory(pharmacy.getId());
                new TableDataViewWindow<>(MedicineChangeRecord.class, medsList, selectionHandler).setVisible(true);
			}
        	
		});
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        		
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        	
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lblAddress, Alignment.LEADING)
        				.addComponent(txtAddress, Alignment.LEADING, 192, 192, 192)
        				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblTitle)
        						.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtId, 0, 0, Short.MAX_VALUE)))
        				
        				.addGroup(gl_contentPane.createSequentialGroup()
        						
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        						.addComponent(btnOpenDrugList)
        						.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						
        							)
        					     
        					.addGap(18)
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        						.addComponent(btnClose, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        						.addComponent(btnDsf, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
        						
        							)
        					.addGap(18)
        					.addComponent(btnPriceHistory, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        			
        					
        					)
        				)
        			
        			.addGap(12))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblTitle)
        				.addComponent(lblId))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(lblAddress)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(22)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnOpenDrugList)
        				.addComponent(btnDsf, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
        					)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnPriceHistory, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        					)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        		   
        		.addGap(22)
        		.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnPriceHistory)
        				.addComponent(btnDsf, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
        					)
        );
        contentPane.setLayout(gl_contentPane);
    }
}
