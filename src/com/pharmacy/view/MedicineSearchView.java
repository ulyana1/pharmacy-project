package com.pharmacy.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class MedicineSearchView extends JFrame {

    private JPanel contentPane;
    private JTextField txtReaderName;
    private List<Medicine> list; 
    private NewDeliveryWindow parent;
    private JSpinner mCount;
    private DefaultListModel<Medicine> model;

    /**
     * Create the frame.
     */
    public MedicineSearchView(List<Medicine> phlist, NewDeliveryWindow parent) {
    	this.parent = parent;
    	list = phlist;
        init();
    }
    
    private void init(){
    	setTitle("Search pharmacy");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 427, 238);
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
            	MedicineSearchView.this.setVisible(false);
            	MedicineSearchView.this.dispose();
            }
        });

        JList<Medicine> lstReadersResult = new JList<Medicine>();
        //lstReadersResult.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        model = new DefaultListModel<Medicine>();
        for(Medicine p : list){
            model.addElement(p);
        }
        lstReadersResult.setModel(model);
        
        btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String query = txtReaderName.getText();
				List<Medicine> result = new ArrayList<Medicine>();
				for(Medicine m : list){
					if(m.getTitle().contains(query) || "".equals(query))
						result.add(m);
				}
				
				model = new DefaultListModel<Medicine>();
		        for(Medicine p : result){
		            model.addElement(p);
		        }
		        lstReadersResult.removeAll();
		        lstReadersResult.setModel(model);
			}
		});
        
        JButton button = new JButton("Select");
        for(Medicine p : list){
        	model.addElement(p);
        }
        
        button.addActionListener(new ActionListener() {
			
        	@Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Medicine p = lstReadersResult.getSelectedValue();
                if(parent !=null){
                    int val = (int) mCount.getValue();
                    System.out.println(val+" " + p);
                    parent.addMedicineToList(p,val);
                    MedicineSearchView.this.dispose();
                }
            }
		});
        
        mCount = new JSpinner();
        mCount.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
        
        JLabel lblCount = new JLabel("Count");
        lblCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane();

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblCount)
        					.addGap(18)
        					.addComponent(mCount, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(button)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnNewButton))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(txtReaderName, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnSearch)))
        			.addContainerGap(24, Short.MAX_VALUE))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtReaderName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnSearch))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
        			.addGap(12)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblCount)
        				.addComponent(mCount, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnNewButton)
        				.addComponent(button))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        scrollPane.setViewportView(lstReadersResult);
        contentPane.setLayout(gl_contentPane);
    }
}
