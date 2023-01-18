package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import java.sql.Statement;

public class Main {

	
	private static final String HOST = "localhost";
	private static final String BBDD = "bibioteca";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		Scanner scan = new Scanner(System.in);
	
		System.out.println("Introduce un nombre de un animal");
		String nombreAnimal = scan.nextLine();
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/aaaaa", "root", "");
		
		Statement st = con.createStatement();
		st.execute("INSERT INTO animales (nombre) Values ('"+nombreAnimal+"')");
		
		
		con.close();
		}
	
}
