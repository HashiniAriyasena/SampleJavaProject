import java.awt.EventQueue;
import java.awt.Image;
import java.sql.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Create database connection :
	
    Connection connection=null;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel imageLabel;
    
    
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection = sqliteConnection.dbConnecter();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(181, 69, 119, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(181, 114, 100, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(310, 69, 111, 34);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton logInButton = new JButton("Log In");
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { 
					   String query ="select * from EmployeeInfo where Username=? and password=?";
					   PreparedStatement pst= connection.prepareStatement(query);
					   pst.setString(1, txtUsername.getText());
					   pst.setString(2, txtPassword.getText());
					   
					   ResultSet rs=pst.executeQuery();
					   int count=0;
					   while(rs.next()) {
						   count=count+1;
						   
					   }
					   if(count==1)
					   {
						   JOptionPane.showMessageDialog(null,"Username and password is correct!");
						   frame.dispose();
						   EmployeeInfo emplInfo = new  EmployeeInfo();
						   emplInfo.setVisible(true);
						   
					   }
					   else if(count>1) {
						   JOptionPane.showMessageDialog(null,"Duplicate Username and Password!");
					   } 
					   else {
						   
						   JOptionPane.showMessageDialog(null,"Username and password is not correct try again..!");
					   }
					   rs.close();
					   pst.close();
				}catch(Exception e1){
					
					JOptionPane.showMessageDialog(null,e);
					
				}
			}
		});
		logInButton.setBounds(220, 164, 132, 40);
		frame.getContentPane().add(logInButton);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(310, 114, 111, 34);
		frame.getContentPane().add(txtPassword);
		
		imageLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		imageLabel.setIcon(new ImageIcon(img));
		imageLabel.setBounds(26, 69, 145, 135);
		frame.getContentPane().add(imageLabel);
	}
}
