package com.pharmacy.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pharmacy.views.PatientFrame;
import com.pharmacy.views.TableDataViewWindow;
import com.pharmacy.view.elements.CustomTableModel;
import com.pharmacy.view.elements.CustomTableModel.TableSelectionEventHandler;
import com.pharmacy.data.PatientData;
import com.pharmacy.entities.Patient;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Color;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	public PatientData patientContext;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		patientContext = PatientData.getInstance();
		
		setForeground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 463);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u0406\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0456\u044F");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u041F\u0435\u0440\u0435\u0439\u0442\u0438 \u043D\u0430 \u0433\u043E\u043B\u043E\u0432\u043D\u0443");
		menu.add(menuItem);
		contentPane = new JPanel();
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton pharmacyBtn = new JButton("\u0410\u043F\u0442\u0435\u043A\u0438");
		pharmacyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pharmacyBtn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		pharmacyBtn.setBounds(52, 10, 300, 110);
		contentPane.add(pharmacyBtn);
		
		JButton medicineBtn = new JButton("\u041B\u0456\u043A\u0438");
		medicineBtn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		medicineBtn.setBounds(387, 10, 300, 110);
		contentPane.add(medicineBtn);
		
		JButton doctorBtn = new JButton("\u041B\u0456\u043A\u0430\u0440\u0456");
		doctorBtn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		doctorBtn.setBounds(52, 131, 300, 110);
		contentPane.add(doctorBtn);
		
		JButton patientBtn = new JButton("\u041F\u0430\u0446\u0456\u0454\u043D\u0442\u0438");
		patientBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				dispose();
				PatientFrame patientFrame = new PatientFrame();
				patientFrame.setVisible(true);
				*/
				dispose();
				CustomTableModel.TableSelectionEventHandler<Patient> selectionHandler = new TableSelectionEventHandler<Patient>(){

                    @Override
                    public void handle(Patient object, int row) {
                        new CurrentPatientFrame(object).setVisible(true);
                    }
                    
                };
                List<Patient> ptlist = patientContext.getAllPatients();
                new TableDataViewWindow<>(Patient.class, ptlist, selectionHandler).setVisible(true);
			}
		});
		
		patientBtn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		patientBtn.setBounds(387, 131, 300, 110);
		contentPane.add(patientBtn);
		
		JButton accountingBtn = new JButton("\u0411\u0443\u0445\u0433\u0430\u043B\u0442\u0435\u0440\u0456\u044F");
		accountingBtn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		accountingBtn.setBounds(220, 252, 300, 110);
		contentPane.add(accountingBtn);
		
		
		
	}
}
