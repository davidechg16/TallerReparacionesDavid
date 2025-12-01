package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.DBConnection;
import dao.interfaces.UsuarioDAO;
import dwes.pruebamaven.mysql.PasswordUtils;
import entidades.Cliente;
import entidades.Usuario;

public class UsuarioDAOMySQL implements UsuarioDAO {
	private Connection conn;
	
	public UsuarioDAOMySQL() {
		conn = DBConnection.getInstance().getConnection();
	}
	
	
	@Override
	public boolean login(String nombreUsuario, String password) {
		boolean usuarioRegistrado = false;
        
        String sql = "SELECT nombreUsuario, password, rol FROM usuario WHERE nombreUsuario = ?;";
        
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nombreUsuario);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                	// Rellenas el Usuario con los datos del SELECT *
                    String passwordHashedDB = rs.getString("password");
      
                    // Comprobamos la contraseña recibida con el hash de la BD
                    usuarioRegistrado = PasswordUtils.verifyPassword(password, passwordHashedDB);
                }
            }
        } catch (SQLException e) {
            System.out.println("> NOK: " + e.getMessage());
        }
        
        return usuarioRegistrado;
	}

	@Override
	public int update(Usuario u) {
		int resul = 0;
        String sql = "UPDATE usuario " + "SET nombreUsuario = ?, password = ?, rol = ?, dni = ? " + "WHERE dni = ?;";
        
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, u.getNombreUsuario());
            
            // Volvemos a hashear la contraseña antes de guardarla
            pst.setString(2, PasswordUtils.hashPassword(u.getPassword()));
            
            pst.setString(3, u.getRol());
            pst.setString(4, u.getDni());   // nuevo dni (si quisieras cambiarlo)
            pst.setString(5, u.getDni());   // dni actual (condición del WHERE)
            
            resul = pst.executeUpdate();
            System.out.println("Resultado de update: " + resul);
            
        } catch (SQLException e) {
            System.out.println("> NOK: " + e.getMessage());
        }
        
        return resul;
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
	public int delete(Usuario u) {
		
		String sqlDelete = "DELETE FROM usuario WHERE id = ?;";
		int filas = 0;
		try {
			PreparedStatement pst = conn.prepareStatement(sqlDelete);
			pst.setInt(1, u.getIdUsuario()); // borrar por id
			filas = pst.executeUpdate();

			if (filas > 0) {
				System.out.println("> OK. Usuario con id " + u.getIdUsuario() + " eliminado correctamente");
			} else {
				System.out.println("> NO OK. Usuario con id " + u.getIdUsuario() + " no se encuentra en la base de datos");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
		
	}

	@Override
	public Usuario findByNombre(String nombre) {
		Usuario u = null;

	    String sql = "SELECT nombreUsuario, password, rol, dni FROM usuario WHERE nombreUsuario = ?;";

	    try (PreparedStatement pst = conn.prepareStatement(sql)) {
	        pst.setString(1, nombre);

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	                u = new Usuario(
	                    rs.getString("nombreUsuario"),
	                    rs.getString("password"),
	                    rs.getString("rol"),
	                    rs.getString("dni")
	                );
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("> NOK: " + e.getMessage());
	    }

	    return u;
	}


	@Override
	public ArrayList<Usuario> findall() {
		ArrayList<Usuario> usuarios = new ArrayList<>();

	    String sql = "SELECT * FROM usuario;";

	    try {
	        PreparedStatement pst = conn.prepareStatement(sql);
	        ResultSet resul = pst.executeQuery();

	        while (resul.next()) {
	            Usuario u = new Usuario(
	                resul.getString("nombreUsuario"),
	                resul.getString("password"),
	                resul.getString("rol"),
	                resul.getString("dni")
	            ); 

	            usuarios.add(u);
	        }

	    } catch (SQLException e) {
	        System.out.println("> NOK: " + e.getMessage());
	    }

	    return usuarios;
	}
   }


