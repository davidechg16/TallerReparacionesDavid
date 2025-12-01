package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnection {

	public static DBConnection instance;
	
	Connection conexionMySQL = null;
	
	private DBConnection() {
	 try {
			//Instanciamos un datasource con mysql para que no devuelva una conexion
			MysqlDataSource dataSource = new MysqlDataSource();
			
			//Pasamos propiedades
			dataSource.setServerName("localhost");
			dataSource.setPortNumber(3306);
			dataSource.setDatabaseName("taller");
			dataSource.setUser("root");
			dataSource.setPassword("rootroot");
			
			//Hacemos un FileInputStream para leer datos de un archivo
			Properties props = new Properties();
			FileInputStream file;
			file = new FileInputStream("src\\main\\resources\\conexion.properties");
			
			try {
				props.load(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dataSource.setUrl(props.getProperty("url"));
			dataSource.setUser(props.getProperty("user"));
			dataSource.setPassword(props.getProperty("password"));
			file.close();
			
			conexionMySQL = dataSource.getConnection();
			System.out.println("> Conexion establecida correctamente");
			
			
	    } catch (SQLException|IOException e) {
	    	System.err.println("> Error al conectar con mysql: " + e.getMessage());
	    }	
	}
	
	public static DBConnection getInstance() {
		if(instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	public Connection getConnection() {
		return conexionMySQL;
	}
}