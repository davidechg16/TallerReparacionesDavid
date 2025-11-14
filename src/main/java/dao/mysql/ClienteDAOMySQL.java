package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBConnection;
import dao.interfaces.ClienteDAO;
import dwes.pruebamaven.mysql.PasswordUtils;
import entidades.Cliente;

public class ClienteDAOMySQL implements ClienteDAO {
	private Connection conn;
	
	public ClienteDAOMySQL() {
		conn = DBConnection.getInstance().getConnection();
	}
	@Override
	public int insert(Cliente c) {
		int resul = 0;
	
		
		try {
			// PreparedStatement
			//Insertamos Cliente
			String sql = "INSERT INTO cliente (nombre, email, dni, telefono) VALUES (?,?,?,?);";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			
			// Nombre de cliente
			pst.setString(1,c.getNombre());;
			
			//EMAIL
			pst.setString(2, c.getEmail());
			
			// DNI
			pst.setString(3, c.getDni());
			
			// Telefono
			pst.setString(4, c.getTelefono());

			resul = pst.executeUpdate();
			System.out.println("Resultado de insercion: " + resul);
			
		} catch (SQLException e) {
			System.out.println("> NOK: " + e.getMessage());
		}
		return resul;
	}

	@Override
	public int update(Cliente c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String dni) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Cliente> findall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente findByDni(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

}
