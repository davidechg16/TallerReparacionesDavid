package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBConnection;
import dao.interfaces.VehiculoDAO;
import dwes.pruebamaven.mysql.PasswordUtils;
import entidades.Usuario;
import entidades.Vehiculo;

public class VehiculoDAOMySQL implements VehiculoDAO{
	private Connection conn;
	
	public VehiculoDAOMySQL() {
		conn = DBConnection.getInstance().getConnection();
	}

	@Override
	public int insert(Vehiculo v) {
		int resul = 0;
		try {
			// PreparedStatement
			// Inserta un usuario de ejemplo
			String sql = "INSERT INTO vehiculo (matricula, marca, modelo) VALUES(?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			
			// Matricula
			pst.setString(1, v.getMatricula());;
			
			// Marca
			pst.setString(2, v.getMarca());
			
			// Modelo
			pst.setString(3, v.getModelo());
			

			resul = pst.executeUpdate();
			System.out.println("Resultado de insercion: " + resul);
			
		} catch (SQLException e) {
			System.out.println("> NOK: " + e.getMessage());
		}
		return resul;
	}

	

	@Override
	public ArrayList<Vehiculo> findall() {
		ArrayList<Vehiculo> vehiculos = new ArrayList<>();

	    String sql = "SELECT * FROM vehiculo;";

	    try {
	        PreparedStatement pst = conn.prepareStatement(sql);
	        ResultSet resul = pst.executeQuery();

	        while (resul.next()) {
	            Vehiculo v = new Vehiculo(
	                resul.getString("matricula"),
	                resul.getString("marca"),
	                resul.getString("modelo")
	            ); 

	            vehiculos.add(v);
	        }

	    } catch (SQLException e) {
	        System.out.println("> NOK: " + e.getMessage());
	    }

	    return vehiculos;
	}

	@Override
	public Vehiculo findByMatricula(String matricula) {
		Vehiculo v = null;

	    String sql = "SELECT matricula, marca, modelo FROM vehiculo WHERE matricula = ?;";

	    try (PreparedStatement pst = conn.prepareStatement(sql)) {
	        pst.setString(1, matricula);

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	                v = new Vehiculo(
	                    rs.getString("matricula"),
	                    rs.getString("marca"),
	                    rs.getString("modelo")
	                );
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("> NOK: " + e.getMessage());
	    }
	    return v;
	}

	@Override
	public int delete(Vehiculo v) {
		String sqlDelete = "DELETE FROM vehiculo WHERE matricula = ?;";
		int filas = 0;
		try {
			PreparedStatement pst = conn.prepareStatement(sqlDelete);
			pst.setString(1, v.getMatricula()); // borrar por matricula
			filas = pst.executeUpdate();

			if (filas > 0) {
				System.out.println("> OK. Vehiculo con matricula " + v.getMatricula() + " eliminado correctamente");
			} else {
				System.out.println("> NO OK. Vehiculo con matricula " + v.getMatricula() + " no se encuentra en la base de datos");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}	
}
