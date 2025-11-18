package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.DBConnection;
import dao.interfaces.UsuarioDAO;
import dwes.pruebamaven.mysql.PasswordUtils;
import entidades.Usuario;

public class UsuarioDAOMySQL implements dao.interfaces.UsuarioDAO {
	private Connection conn;
	
	public UsuarioDAOMySQL() {
		conn = DBConnection.getInstance().getConnection();
	}
	
	
	@Override
	public boolean login(String dni, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int update(Usuario u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Usuario u) {
		int resul = 0;
		try {
			// PreparedStatement
			// Inserta un usuario de ejemplo
			String sql = "INSERT INTO usuario (nombreUsuario, password, rol, dni) VALUES(?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			// ID No se añade porque es autoincremental
			//pst.setInt(1, 1); // posicion 1, valor 1
			
			// Nombre de usuario
			pst.setString(1, u.getNombreUsuario());;
			
			// Hasheamos la contraseña antes de guardarla
			pst.setString(2, PasswordUtils.hashPassword(u.getPassword()));
			
			// Rol
			pst.setString(3, u.getRol());
			
			// DNI
			pst.setString(4, u.getDni());

			resul = pst.executeUpdate();
			System.out.println("Resultado de insercion: " + resul);
			
		} catch (SQLException e) {
			System.out.println("> NOK: " + e.getMessage());
		}
		return resul;
	}

	@Override
	public void delete(Usuario u) {
		
		String sqlDelete = "DELETE FROM usuario WHERE id = ?;";
		try {
			PreparedStatement pst = conn.prepareStatement(sqlDelete);
			pst.setInt(1, u.getIdUsuario()); // borrar por id
			int filas = pst.executeUpdate();

			if (filas > 0) {
				System.out.println("> OK. Usuario con id " + u.getIdUsuario() + " eliminado correctamente");
			} else {
				System.out.println("> NO OK. Usuario con id " + u.getIdUsuario() + " no se encuentra en la base de datos");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Usuario findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<Usuario> findall() {
		// TODO Auto-generated method stub
		return null;
	}

}
