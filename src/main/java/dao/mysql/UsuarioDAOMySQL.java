package dao.mysql;

import java.util.ArrayList;

public class UsuarioDAOMySQL implements dao.interfaces.UsuarioDAO{
	boolean login(String dni, String password);
	int insert(Usuario u);
	ArrayList<Usuario> findall();
	Usuario findByNombre(String nombre);
}
