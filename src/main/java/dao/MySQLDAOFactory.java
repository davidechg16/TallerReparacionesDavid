package dao;

import dao.interfaces.ClienteDAO;
import dao.interfaces.ReparacionDAO;
import dao.interfaces.UsuarioDAO;
import dao.interfaces.VehiculoDAO;
import dao.mysql.UsuarioDAOMySQL;

public class MySQLDAOFactory implements DAOFactory {
		
	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOMySQL();
	}

	@Override
	public ClienteDAO getClienteDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehiculoDAO getVehiculoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReparacionDAO getReparacionDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
