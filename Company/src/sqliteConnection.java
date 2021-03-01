import java.sql.*;
import javax.swing.*;
public class sqliteConnection {
	
	Connection conn=null;
	public static Connection dbConnecter()
	{
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\UARIYTH\\Desktop\\MYDatabase\\EmployeeData.db");
			//Connection conn=DriverManager.getConnection("jdbc:sqlite:Database\\EmployeeData.db");
			JOptionPane.showMessageDialog(null,"Connection Successfull !");
			return conn;
			
			
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
		
		
		
	}
	
	
	
	
	
	

}
