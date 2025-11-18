package dao.interfaces;

import java.util.ArrayList;

import entidades.Reparacion;

public interface ReparacionDAO {
	String estadoReparacion (Reparacion r);
	Reparacion finbyidReparacion (int id);
	ArrayList<Reparacion> findall();
}
