package dao;

import dao.interfaces.ClienteDAO;
import dao.interfaces.ReparacionDAO;
import dao.interfaces.UsuarioDAO;
import dao.interfaces.VehiculoDAO;

public interface DAOFactory {
	public UsuarioDAO getUsuarioDAO();
	public ClienteDAO getClienteDAO();
	public VehiculoDAO getVehiculoDAO();
	public ReparacionDAO getReparacionDAO();
}
