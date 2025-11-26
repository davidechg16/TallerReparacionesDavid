package dao.interfaces;

import java.util.ArrayList;

import entidades.Usuario;
import entidades.Vehiculo;

public interface VehiculoDAO {
	int insert(Vehiculo v);
	ArrayList<Vehiculo> findall();
	int delete(Vehiculo v);
	Vehiculo findByMatricula (String matricula);

}
