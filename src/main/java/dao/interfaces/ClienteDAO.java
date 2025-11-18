package dao.interfaces;

import java.util.ArrayList;

import entidades.Cliente;

public interface ClienteDAO {
	int insert(Cliente c);
	int update (Cliente c);
	void delete(Cliente c);
	ArrayList<Cliente> findall();
	Cliente findByDni (String dni);
}
