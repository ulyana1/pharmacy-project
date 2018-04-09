package com.pharmacy.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.medicine.view.UpdatePriceWindow;
import com.pharmacy.data.PharmacyDataContext;
import com.pharmacy.entities.*;
import com.pharmacy.view.elements.CustomTableModel;
import com.pharmacy.view.elements.CustomTableModel.TableSelectionEventHandler;
import java.awt.Button;

public class TestMainFrame extends JFrame {

    public JPanel contentPane;
    
    private PharmacyDataContext pharmacyContext;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    TestMainFrame frame = new TestMainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TestMainFrame() {
    	pharmacyContext = PharmacyDataContext.getInstance();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JButton btnInWork = new JButton("Delivery");
        btnInWork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomTableModel.TableSelectionEventHandler<Delivery> selectionHandler = new TableSelectionEventHandler<Delivery>() {

                    @Override
                    public void handle(Delivery object, int row) {
                        new DeliveryDetailedInfoWindow(object).setVisible(true);
                    }
                    
                };
                List<Delivery> delivery = PharmacyDataContext.getInstance().getDeliveries();
                new TableDataViewWindow<>(Delivery.class, delivery, selectionHandler).setVisible(true);;
            }
        });
        
        JButton btnPharmacy = new JButton("Pharmacies");
        btnPharmacy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomTableModel.TableSelectionEventHandler<Pharmacy> selectionHandler = new TableSelectionEventHandler<Pharmacy>(){

                    @Override
                    public void handle(Pharmacy object, int row) {
                        new PharmacyDetailedInfoWindow(object).setVisible(true);
                    }
                };
                List<Pharmacy> phlist = pharmacyContext.getAllPharmacies();
                new TableDataViewWindow<>(Pharmacy.class, phlist, selectionHandler).setVisible(true);
            }
        });
        
        JButton btnReaderSearch = new JButton("Search pharmacy");
        btnReaderSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PharmacySearchWindow(pharmacyContext.getAllPharmacies()).setVisible(true);
            }
        });
        
        JButton btnNewPharmacy = new JButton("New pharmacy");
        
        btnNewPharmacy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new NewPharmacyWindow().setVisible(true);
			}
		});
        
        JButton btnUpdateMedicine = new JButton("Update medicine price"); 
        btnUpdateMedicine.addActionListener(new ActionListener() {
 		  
     	   @Override
            public void actionPerformed(ActionEvent e) {
                CustomTableModel.TableSelectionEventHandler<Pharmacy> selectionHandler = new TableSelectionEventHandler<Pharmacy>(){

                    @Override
                    public void handle(Pharmacy object, int row) {
                    	new UpdatePriceWindow(object).setVisible(true);
                    }
                };
                List<Pharmacy> phlist = pharmacyContext.getAllPharmacies();
                new TableDataViewWindow<>(Pharmacy.class, phlist, selectionHandler).setVisible(true);
            }
     	   
 		});
        
        JButton btnNewDelivery = new JButton("New delivery");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        				.addComponent(btnInWork, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        				.addComponent(btnPharmacy, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        				.addComponent(btnReaderSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnNewPharmacy, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnNewDelivery, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        				.addComponent(btnUpdateMedicine, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        					)
        			.addGap(0))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(btnInWork)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnPharmacy)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnReaderSearch)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnNewPharmacy)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnUpdateMedicine)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnNewDelivery)
        			.addGap(112))
        ); 
        contentPane.setLayout(gl_contentPane);
    }
}
