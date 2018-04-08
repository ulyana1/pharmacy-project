package com.medicine.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.pharmacy.entities.Medicine;

public class MedicineBalanceWindow extends JFrame {

	private JPanel contentPane;
    private JTextField txtTitle;
    private JTextField txtId;
    private JTextField txtProducer;
    private JTextField txtBoxPrice;
    private JTextField txtQuantityPerBox;
    
    public MedicineBalanceWindow(Medicine medicine) {
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
        	MedicineBalanceWindow.this.setVisible(false);
        	MedicineBalanceWindow.this.dispose();
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
    					.addGap(18)
    					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
    						.addComponent(btnClose, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
    					.addPreferredGap(ComponentPlacement.RELATED)))
    			.addGap(12))
    );
    
  }
	
}
