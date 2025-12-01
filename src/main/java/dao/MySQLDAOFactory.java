package dao;

import dao.interfaces.ClienteDAO;
import dao.interfaces.ReparacionDAO;
import dao.interfaces.UsuarioDAO;
import dao.interfaces.VehiculoDAO;
import dao.mysql.ClienteDAOMySQL;
import dao.mysql.ReparacionDAOMySQL;
import dao.mysql.UsuarioDAOMySQL;
import dao.mysql.VehiculoDAOMySQL;

public class MySQLDAOFactory implements DAOFactory {
	
	@Override
	public ClienteDAO getClienteDAO() {
		return new ClienteDAOMySQL();
	}
		
	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOMySQL();
	}

	

	@Override
	public VehiculoDAO getVehiculoDAO() {
		return new VehiculoDAOMySQL();
	}

	@Override
	public ReparacionDAO getReparacionDAO() {
		return new ReparacionDAOMySQL();
	}

}
