package dao.interfaces;

import entidades.Reparacion;

public interface ReparacionDAO {
	String estadoReparacion (Reparacion r);
	Reparacion finbyidReparacion (int id);
}
