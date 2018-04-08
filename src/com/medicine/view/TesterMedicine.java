package com.medicine.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.pharmacy.data.MedicineDataContext;
import com.pharmacy.data.PharmacyDataContext;
import com.pharmacy.entities.Medicine;
import com.pharmacy.entities.Pharmacy;
import com.pharmacy.view.NewPharmacyWindow;
import com.pharmacy.view.PharmacyDetailedInfoWindow;
import com.pharmacy.view.PharmacySearchWindow;
import com.pharmacy.view.TableDataViewWindow;
import com.pharmacy.view.elements.CustomTableModel;
import com.pharmacy.view.elements.CustomTableModel.TableSelectionEventHandler;

public class TesterMedicine extends JFrame{

    public JPanel contentPane;
    
    private MedicineDataContext medicineContext;
    private PharmacyDataContext pharmacyContext;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                	TesterMedicine frame = new TesterMedicine();
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
    public TesterMedicine() {
    	medicineContext = MedicineDataContext.getInstance();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        
        JButton btnMedicine = new JButton("Medicine");
        btnMedicine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomTableModel.TableSelectionEventHandler<Medicine> selectionHandler = new TableSelectionEventHandler<Medicine>(){

                    @Override
                    public void handle(Medicine object, int row) {
                        new MedicineDetailedInfoWindow(object).setVisible(true);
                    }
                    
                };
                List<Medicine> meds = medicineContext.getAllMedicine();
                new TableDataViewWindow<>(Medicine.class, meds, selectionHandler).setVisible(true);
            }
        });        
        
        JButton btnReaderSearch = new JButton("Search medicine");
        btnReaderSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MedicineSearchWindow(medicineContext.getAllMedicine()).setVisible(true);
            }
        });
        
       JButton btnNewMedicine = new JButton("New medicine"); 
       btnNewMedicine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new NewMedicineWindow().setVisible(true);
			}
		});        
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        				.addComponent(btnMedicine, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        				.addComponent(btnReaderSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnNewMedicine, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			     )
        			.addGap(0))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(btnMedicine)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnReaderSearch)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnNewMedicine)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGap(112))
        );  
        contentPane.setLayout(gl_contentPane);
    }
	
}

