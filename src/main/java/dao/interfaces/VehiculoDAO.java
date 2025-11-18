package dao.interfaces;

import java.util.ArrayList;

import entidades.Usuario;
import entidades.Vehiculo;

public interface VehiculoDAO {
	int insert(Vehiculo v);
	int update (Vehiculo v);
	ArrayList<Vehiculo> findall();
	
	boolean borrarPorMatricula(String matricula);
	Vehiculo findByMatricula (String matricula);
	
	boolean borrarPorIdCliente(int idCliente);
	Vehiculo findByIdCliente(int idCliente);
}
