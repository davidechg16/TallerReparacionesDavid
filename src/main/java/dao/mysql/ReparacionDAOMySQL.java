package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBConnection;
import dao.interfaces.ReparacionDAO;
import entidades.Reparacion;

public class ReparacionDAOMySQL implements ReparacionDAO {
private Connection conn;
	
	public ReparacionDAOMySQL() {
		conn = DBConnection.getInstance().getConnection();
	}

	@Override
	public int insert(Reparacion r) {
		int resul = 0;
		try {
			
			// Inserta una reparacion
			String sql = "INSERT INTO reparacion (idReparacion, descripcion, fechaEntrada, costeEstimado, estado) VALUES(?, ?, ?, ?, ?)";
			
			// PreparedStatement
			PreparedStatement pst = conn.prepareStatement(sql);
			
			
			// descripcion
			pst.setString(1, r.getDescripcion());;
			
			// fechaEntrada
			pst.setDate(2, java.sql.Date.valueOf(r.getFechaEntrada()));
			
			// costeEstimado
			pst.setDouble(3, r.getCosteEstimado());
			
			//Estado
			pst.setString(4, r.getEstado());

			resul = pst.executeUpdate();
			System.out.println("Resultado de insercion: " + resul);
			
		} catch (SQLException e) {
			System.out.println("> NOK: " + e.getMessage());
		}
		return resul;
	}

	@Override
	public int update(Reparacion r) {
		int resul = 0;
        String sql = "UPDATE reparacion SET descripcion = ?, fechaEntrada = ?, costeEstimado = ?, estado = ? " + "WHERE idReparacion = ?;";
        
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, r.getDescripcion());
         
            pst.setDate(2, java.sql.Date.valueOf(r.getFechaEntrada()));
            
            pst.setDouble(3, r.getCosteEstimado());
            pst.setString(4, r.getEstado());   // nuevo dni (si quisieras cambiarlo)
            pst.setInt(5, r.getIdReparacion());   // dni actual (condición del WHERE)
            
            resul = pst.executeUpdate();
            System.out.println("Resultado de update: " + resul);
            
        } catch (SQLException e) {
            System.out.println("> NOK: " + e.getMessage());
        }
        
        return resul;
	}

	@Override
	public int delete(Reparacion r) {
		String sqlDelete = "DELETE FROM reparacion WHERE idReparacion = ?;";
		int filas = 0;
		try {
			PreparedStatement pst = conn.prepareStatement(sqlDelete);
			pst.setInt(1, r.getIdReparacion()); // borrar por idReparacion
			filas = pst.executeUpdate();

			if (filas > 0) {
				System.out.println("> OK. Reparacion con id " + r.getIdReparacion() + " eliminado correctamente");
			} else {
				System.out.println("> NO OK. Reparacion con id " + r.getIdReparacion() + " no se encuentra en la base de datos");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public Reparacion finbyIdVehiculo(int idVehiculo) {
		Reparacion r = null;

	    String sql = "SELECT idReparacion, descripcion, fechaEntrada, costeEstimado, estado FROM reparacion WHERE idVehiculo = ?;";

	    try (PreparedStatement pst = conn.prepareStatement(sql)) {
	        pst.setInt(1, idVehiculo);

	        try (ResultSet resul = pst.executeQuery()) {
	            if (resul.next()) {
	                 	r = new Reparacion(
	                	resul.getInt("idReparacion"),
	                    resul.getString("descripcion"),
	                    resul.getDate("fechaEntrada").toLocalDate(),
	                    resul.getDouble("costeEstimado"),
	                    resul.getString("estado")
	                );
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("> NOK: " + e.getMessage());
	    }
	    return r;
	}

	@Override
	public ArrayList<Reparacion> findall() {
		ArrayList<Reparacion> reparaciones = new ArrayList<>();

	    String sql = "SELECT * FROM reparacion;";

	    try {
	        PreparedStatement pst = conn.prepareStatement(sql);
	        ResultSet resul = pst.executeQuery();

	        while (resul.next()) {
	            Reparacion r = new Reparacion(
	            	resul.getInt("idReparacion"),
	                resul.getString("descripcion"),
	                resul.getDate("fechaEntrada").toLocalDate(),
	                resul.getDouble("costeEstimado"),
	                resul.getString("estado")
	            ); 

	            reparaciones.add(r);
	        }

	    } catch (SQLException e) {
	        System.out.println("> NOK: " + e.getMessage());
	    }

	    return reparaciones;
	}
	
	@Override
	public ArrayList<Reparacion> findByEstado(String estado) {
		
	    ArrayList<Reparacion> lista = new ArrayList<>();

	    String sql = "SELECT idReparacion, descripcion, fechaEntrada, costeEstimado, estado "
	               + "FROM reparacion WHERE estado = ? ORDER BY fechaEntrada;";

	    try (PreparedStatement pst = conn.prepareStatement(sql)) {
	        pst.setString(1, estado);

	        try (ResultSet resul = pst.executeQuery()) {
	            while (resul.next()) {
	                Reparacion r = new Reparacion(
	                    resul.getInt("idReparacion"),
	                    resul.getString("descripcion"),
	                    resul.getDate("fechaEntrada").toLocalDate(),
	                    resul.getDouble("costeEstimado"),
	                    resul.getString("estado")
	                );

	                lista.add(r);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("> NOK: " + e.getMessage());
	    }

	    return lista;
	}

}
