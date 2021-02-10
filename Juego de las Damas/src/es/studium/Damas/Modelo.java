package es.studium.Damas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Modelo
{
	public Connection connectDB()
	{
		Connection resultado = null;

		String driver = "com.mysql.jdbc.Driver"; // Definimos el driver de conexión
		// Se establece la ubicación de nuestra base de datos.
		String url = "jdbc:mysql://localhost:3306/puntuaciondamas?autoReconnect=true&useSSL=false";
		String loginBD = "root";
		String passwordBD = "1234";

		// Cargar el Driver (Los controladores de la BD)
		try
		{
			Class.forName(driver);
		} catch (ClassNotFoundException e)
		{
			System.out.println("Se ha producido un error al cargar el Driver");
		}

		// Establecer la conexión con la base de datos Sexshop
		try
		{
			resultado = DriverManager.getConnection(url, loginBD, passwordBD);
		} catch (SQLException e)
		{
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}
		return resultado;
	}

	public int insertarRecord(Connection con, String jugador, int puntuacion)
	{
		
		try
		{
			Statement sta = con.createStatement();
			String sentencia = "INSERT INTO records(jugador,puntuacion) values (\"" + jugador + "\"," + puntuacion + ");";
			//String sentencia = "INSERT INTO"+records+"VALUES (null,'"+jugador+"',+puntuacion+");";
			sta.executeUpdate(sentencia);
			sta.close();
			return 0;
		} catch (SQLException ex)
		{
			System.out.println("ERROR:al hacer un Insert");
			ex.printStackTrace();
			return 1;
		}
	}

	public ArrayList<Record> consultarRecord(Connection con)
	{
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Record> records = new ArrayList<Record>();
		try
		{
			String sentencia = "SELECT * FROM records order by puntuacion desc limit 10";
			st = con.createStatement();
			rs = st.executeQuery(sentencia);
			while (rs.next())
			{
				records.add(new Record(rs.getInt("idRecords"), rs.getString("Jugador"), rs.getInt("Puntuacion")));
			}

		} catch (SQLException sql)
		{
			System.out.println("Error 2-" + sql.getMessage());
		}

		try
		{
			rs.close();
			st.close();
		} 
		catch (SQLException e)
		{			
			e.printStackTrace();
		}
		return records;
	}
}
