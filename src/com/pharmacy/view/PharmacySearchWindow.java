package com.pharmacy.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.pharmacy.entities.Medicine;
import com.pharmacy.entities.Pharmacy;

public class PharmacySearchWindow extends JFrame {

    private JPanel contentPane;
    private JTextField txtReaderName;
    private List<Pharmacy> list; 
    private NewDeliveryWindow parent;
    private DefaultListModel<Pharmacy> model;

    /**
     * Create the frame.
     */
    public PharmacySearchWindow(List<Pharmacy> phlist, NewDeliveryWindow p) {
    	list = phlist;
    	parent = p;
    	init();
    	
    }
    
    private void init(){
        setTitle("Search pharmacy");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 415, 238);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        txtReaderName = new JTextField("Search pharmacy");
        txtReaderName.setColumns(10);
        

        JButton btnSearch = new JButton("Search");

        JButton btnNewButton = new JButton("Cancel");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PharmacySearchWindow.this.setVisible(false);
                PharmacySearchWindow.this.dispose();
            }
        });

        JButton button = new JButton("Select");

        JList<Pharmacy> lstReadersResult = new JList<Pharmacy>();
        lstReadersResult.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        model = new DefaultListModel<Pharmacy>();
        for(Pharmacy p : list){
        	model.addElement(p);
        }
        
        btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String query = txtReaderName.getText();
				List<Pharmacy> result = new ArrayList<Pharmacy>();
				for(Pharmacy m : list){
					if(m.getTitle().contains(query) || query.isEmpty() || query == null)
						result.add(m);
				}
				model.removeAllElements();
		        for(Pharmacy p : result){
		            model.addElement(p);
		        }
		        //lstReadersResult.removeAll();
		        //lstReadersResult.setModel(model);
			}
		});
        
        
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pharmacy p = lstReadersResult.getSelectedValue();
				if(parent !=null){
					parent.setPharmacy(p);
					PharmacySearchWindow.this.dispose();
				}
				else
					new PharmacyDetailedInfoWindow(p).setVisible(true);
			}
		});
        
        lstReadersResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstReadersResult.setModel(model);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(txtReaderName, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnSearch)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lstReadersResult, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addContainerGap(176, Short.MAX_VALUE)
                            .addComponent(button)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnNewButton)))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(txtReaderName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch))
                    .addGap(7)
                    .addComponent(lstReadersResult, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnNewButton)
                        .addComponent(button)))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
