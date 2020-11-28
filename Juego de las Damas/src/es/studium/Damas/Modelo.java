package es.studium.Damas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Modelo 
{
	public Connection connectDB() 
	{
		Connection resultado = null;

		String driver = "com.mysql.jdbc.Driver"; //Definimos el driver de conexión
		//Se establece la ubicación de nuestra base de datos.
		String url = "jdbc:mysql://localhost:3306/PuntuacionDamas?autoReconnect=true&useSSL=false"; 
		String loginBD = "root";
		String passwordBD = "1234";

		//Cargar el Driver (Los controladores de la BD)
		try 
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e) 
		{
			System.out.println("Se ha producido un error al cargar el Driver");
		}
		
		//Establecer la conexión con la base de datos Sexshop
		try 
		{
			resultado = DriverManager.getConnection(url, loginBD, passwordBD);
		}
		catch(SQLException e) 
		{
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}
		return resultado;
	}
}
