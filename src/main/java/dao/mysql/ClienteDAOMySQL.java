package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBConnection;
import dao.interfaces.ClienteDAO;
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
			System.out.println("Resultado de insercción: " + resul);
			
		} catch (SQLException e) {
			System.out.println("> NOK: " + e.getMessage());
		}
		return resul;
	}

	@Override
	public int update(Cliente c) {
		
		
		int resul = 0;
		String sql = "UPDATE cliente SET nombre = ?, email = ?, telefono = ? WHERE dni = ?;";
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			//SIN ACABAR
			pst.setString(1, c.getNombre());
			pst.setString(2, c.getEmail());
			pst.setString(3, c.getTelefono());
			pst.setString(4, c.getDni());
			
			resul = pst.executeUpdate();
			System.out.println("Resultado update: " + resul);
		} catch(SQLException e) {
			System.out.println("> NOK: " + e.getMessage());
		}
		
		return resul;
	}

	@Override
	public void delete(Cliente c) {
		
		String sqlDelete = "DELETE FROM cliente WHERE dni = ?;";
		try {
			PreparedStatement pst = conn.prepareStatement(sqlDelete);
			pst.setString(1, c.getDni()); // borrar por dni
			int filas = pst.executeUpdate();

			if (filas == 1) {
				System.out.println("> OK. Cliente con dni " + c.getDni() + " eliminado correctamente");
			} else {
				System.out.println("> NO OK. Cliente con dni " + c.getDni() + " no se encuentra en la base de datos");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Cliente> findall() {
		ArrayList<Cliente> clientes = new ArrayList<>();

	    String sql = "SELECT * FROM cliente;";

	    try {
	        PreparedStatement pst = conn.prepareStatement(sql);
	        ResultSet resul = pst.executeQuery();

	        while (resul.next()) {
	            Cliente c = new Cliente(
	                resul.getString("nombre"),
	                resul.getString("email"),
	                resul.getString("dni"),
	                resul.getString("telefono")
	            ); 

	            clientes.add(c);
	        }

	    } catch (SQLException e) {
	        System.out.println("> NOK: " + e.getMessage());
	    }

	    return clientes;
	}

	@Override
	public Cliente findByDni(String dni) {
		
		String sql = "SELECT * FROM cliente WHERE dni = ?;";
	    Cliente c = null;

	    try (PreparedStatement pst = conn.prepareStatement(sql)) {
	        pst.setString(1, dni);

	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            c = new Cliente(
	                rs.getString("nombre"),
	                rs.getString("email"),
	                rs.getString("dni"),
	                rs.getString("telefono")
	            );
	        }

	    } catch (SQLException e) {
	        System.out.println("> NOK: " + e.getMessage());
	    }

	    return c; // si no existe, devuelve null
	}

}
