package com.medicine.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import com.pharmacy.entities.Purchase;
import com.pharmacy.entities.PurchaseRecord;
import com.pharmacy.view.PharmacyDetailedInfoWindow;
import com.pharmacy.view.TableDataViewWindow;
import com.pharmacy.view.elements.CustomTableModel;
import com.pharmacy.view.elements.CustomTableModel.TableSelectionEventHandler;

public class MedicineDetailedInfoWindow extends JFrame{
	
	private JPanel contentPane;
    private JTextField txtTitle;
    private JTextField txtId;
    private JTextField txtProducer;
    private JTextField txtBoxPrice;
    private JTextField txtQuantityPerBox;
    
    /**
     * Create the frame.
     */
    public MedicineDetailedInfoWindow(Medicine medicine) {
        setResizable(false);
        setTitle("\u0406\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0456\u044F \u043F\u0440\u043E \u0432\u0438\u0434\u0430\u0447\u0443");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 220, 220);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JLabel lblTitle = new JLabel("Title");
        
        txtTitle = new JTextField();
        txtTitle.setColumns(10);
        txtTitle.setText(medicine.getTitle());
        
        JLabel lblId = new JLabel("Id");
        
        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setColumns(10);
        txtId.setText(medicine.getId() + "");
        
        JLabel lblProducer = new JLabel("Producer");
        
        txtProducer = new JTextField();
        txtProducer.setColumns(10);
        txtProducer.setText(medicine.getProducer());
        
        JLabel lblBoxPrice = new JLabel("BoxPrice");
        
        txtBoxPrice = new JTextField();
        txtBoxPrice.setColumns(10);
        txtBoxPrice.setText(medicine.getBoxPrice()+"");
        
        JLabel lblQuantityPerBox = new JLabel("Quantity per box");
        
        txtQuantityPerBox = new JTextField();
        txtQuantityPerBox.setColumns(10);
        txtQuantityPerBox.setText(medicine.getQuantityPerBox()+"");
        
       
        JButton btnClose = new JButton("\u0417\u0430\u043A\u0440\u0438\u0442\u0438");
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MedicineDetailedInfoWindow.this.setVisible(false);
            	MedicineDetailedInfoWindow.this.dispose();
            }
        });
        
      JButton btnSave = new JButton("Save");
        
        btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Medicine med = new Medicine(Integer.parseInt(txtId.getText()), txtTitle.getText(),txtProducer.getText(),Double.parseDouble(txtBoxPrice.getText()),Integer.parseInt(txtQuantityPerBox.getText()));
				boolean res = MedicineDataContext.getInstance().updateMedicine(med);
				if(res)
					JOptionPane.showMessageDialog(null, "Medicine was updated successfully.");
			}
		});
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup (  gl_contentPane.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lblProducer, Alignment.LEADING)
        				.addComponent(txtProducer, Alignment.LEADING, 192, 192, 192)
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
        						.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addGap(18)
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        						.addComponent(btnClose, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED)))
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
        			.addComponent(lblProducer)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtProducer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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

