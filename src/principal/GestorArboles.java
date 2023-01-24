package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Scanner;

import java.sql.Statement;

public class GestorArboles {

	private static final String HOST = "localhost";
	private static final String BBDD = "bibioteca";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Scanner scan = new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/eh_garden", "root", "");
		Statement st = con.createStatement();

		final int InsertarArbol = 1;
		final int EliminarArbol = 2;
		final int ModificarArbol = 3;
		final int VisualizarArboles = 4;
		final int SALIR = 0;
		String nombreComun = "";
		String nombreCientifico = "";
		String habitat = "";
		int altura = 0;
		String origen = "";
		String nombreBorrar="";
		

		int opcion_menu;

		do {
			System.out.println("------MENU-------");
			System.out.println(InsertarArbol + ". Inserta un arbol");
			System.out.println(EliminarArbol + ". Elimina un arbol");
			System.out.println(ModificarArbol + ". Modifica la informacion de un arbol");
			System.out.println(VisualizarArboles + ". Visualizar arboles");
			System.out.println(SALIR + ". Salir");
			System.out.println("Elije una de las opciones");
			opcion_menu = Integer.parseInt(scan.nextLine());
			int idModificar=0;

			switch (opcion_menu) {
			case InsertarArbol:

				// rellenar todas las variables
				System.out.println("Insertar Arbol");
				System.out.println("Introduce el nombre comun del arbol");
				nombreComun = scan.nextLine();
				System.out.println("Introduce el nombre cientifico del arbol");
				nombreCientifico = scan.nextLine();
				System.out.println("Introduce su habitat");
				habitat = scan.nextLine();
				System.out.println("Introduce la altura del arbol");
				altura = Integer.parseInt(scan.nextLine());
				System.out.println("Introduce el origen del arbol");
				origen = scan.nextLine();

				// insertar todos los valores
				PreparedStatement preparedSt = con.prepareStatement("INSERT INTO arboles (nombre_Comun, nombre_Cientifico, habitat, altura, origen ) VALUES (?,?,?,?,?)");
				preparedSt.setString(1 , nombreComun);
				preparedSt.setString(2 , nombreCientifico);
				preparedSt.setString(3 , habitat);
				preparedSt.setInt(4 , altura);
				preparedSt.setString(5 , origen);
				preparedSt.execute();
				break;
				
			case EliminarArbol:
				System.out.println("Eliminar Arbol");
				System.out.println("Cual es su nombre comun");
				nombreBorrar = scan.nextLine();
				preparedSt = con.prepareStatement("DELETE FROM arboles WHERE Nombre_Comun = ?");
				preparedSt.setString(1, nombreBorrar);
				preparedSt.execute();
				break;
			case ModificarArbol:
				
				System.out.println("Selecciona un arbol para modificar");
				System.out.println("Escribe la id del arbol a modificar");
				idModificar = Integer.parseInt(scan.nextLine());
				
				System.out.println("nombre comun");
				nombreComun = scan.nextLine();
				preparedSt = con.prepareStatement("UPDATE arboles SET nombre_Comun=? WHERE id = ?");
				preparedSt.setString(1, nombreComun);
				preparedSt.setInt(2, idModificar);
				preparedSt.executeUpdate();				
				
				System.out.println("Nombre cientifico");
				nombreCientifico = scan.nextLine();
				preparedSt = con.prepareStatement("UPDATE arboles SET nombre_Cientifico=? WHERE id = ?");
				preparedSt.setString(1, nombreCientifico);
				preparedSt.setInt(2, idModificar);
				preparedSt.executeUpdate();								
				
				System.out.println("Habitat");
				habitat = scan.nextLine();
				preparedSt = con.prepareStatement("UPDATE arboles SET Habitat=? WHERE id = ?");
				preparedSt.setString(1, habitat);
				preparedSt.setInt(2, idModificar);
				preparedSt.executeUpdate();								
				
				System.out.println("Altura");
				altura = Integer.parseInt(scan.nextLine());
				preparedSt = con.prepareStatement("UPDATE arboles SET Altura=? WHERE id = ?");
				preparedSt.setInt(1, altura);
				preparedSt.setInt(2, idModificar);
				preparedSt.executeUpdate();				
				
				System.out.println("Origen");
				origen = scan.nextLine();
				preparedSt = con.prepareStatement("UPDATE arboles SET Origen=? WHERE id = ?");
				preparedSt.setString(1, origen);
				preparedSt.setInt(2, idModificar);
				preparedSt.executeUpdate();				
				
				break;
			case VisualizarArboles:

				String sentenciaSelect = "SELECT * FROM arboles";
				Statement statement = con.createStatement();
				ResultSet resultado = statement.executeQuery(sentenciaSelect);	
				
				while(resultado.next()) {
					System.out.println(resultado.getInt(1) + " - " + resultado.getString(2) + " - " + resultado.getString(3) + " - " +  resultado.getString(4) + " - " +  resultado.getString(5));
				}
				
				break;
			case SALIR:
				System.out.println("ADIOS");
				break;
			default:
				System.out.println("Opcion incorrecta!");
			}

		} while (opcion_menu != SALIR);
		scan.close();


		con.close();
	}

}
