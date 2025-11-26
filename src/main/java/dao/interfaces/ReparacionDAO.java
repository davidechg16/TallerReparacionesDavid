package dao.interfaces;

import java.util.ArrayList;

import entidades.Reparacion;

public interface ReparacionDAO {
	int insert(Reparacion r);
	int update(Reparacion r);
	int delete(Reparacion r);
	Reparacion finbyIdVehiculo(int IdVehiculo);
	ArrayList<Reparacion> findall();
}
