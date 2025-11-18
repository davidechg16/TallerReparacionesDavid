package dao.mysql;

import java.util.ArrayList;

import dao.interfaces.VehiculoDAO;
import entidades.Vehiculo;

public class VehiculoDAOMySQL implements VehiculoDAO{

	@Override
	public int insert(Vehiculo v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Vehiculo v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Vehiculo> findall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehiculo findByMatricula(String matricula) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean borrarPorMatricula(String matricula) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrarPorIdCliente(int idCliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vehiculo findByIdCliente(int idCliente) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
