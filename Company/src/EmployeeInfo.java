import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import javax.swing.*;

public class EmployeeInfo extends JFrame {

	private JPanel contentPane;
	private JPanel panel2;
	private JPanel panel1;
	private JLayeredPane layeredPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInfo frame = new EmployeeInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void switchPnnals(JPanel panel)
	{
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
		
	}
    Connection connection=null;
    
	/**
	 * Create the frame.
	 */
	public EmployeeInfo() {
		
		connection = sqliteConnection.dbConnecter();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 43, 414, 207);
		contentPane.add(layeredPane);
		
		panel1 = new JPanel();
		panel1.setBounds(10, 11, 394, 191);
		layeredPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pannal1");
		lblNewLabel.setBounds(81, 5, 38, 14);
		panel1.add(lblNewLabel);
		
		panel2 = new JPanel();
		panel2.setBounds(10, 11, 394, 191);
		layeredPane.add(panel2);
		panel2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Pannal2");
		lblNewLabel_1.setBounds(78, 5, 38, 14);
		panel2.add(lblNewLabel_1);
		
		JButton loardButton = new JButton("Loard Table");
		loardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query="select * from EmployeeInfo";
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch (Exception e1){
					
					e1.printStackTrace();
				}
				
			}
		});
		loardButton.setBounds(108, 30, 89, 23);
		panel2.add(loardButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 374, 116);
		panel2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Pannal1");
		btnNewButton.setBounds(43, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Pannal2");
		btnNewButton_1.setBounds(129, 11, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switchPnnals(panel2);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switchPnnals(panel1);
				
				
				
			}
		});
	}
}
