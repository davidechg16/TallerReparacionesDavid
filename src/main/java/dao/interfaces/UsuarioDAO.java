package dao.interfaces;

import java.util.ArrayList;
import entidades.Usuario;

public interface UsuarioDAO {
	boolean login(String dni, String password);
	int update (Usuario u);
	int insert(Usuario u);
	int delete (Usuario u);
	Usuario findByNombre(String nombre);
	
	
	//Esta clase es intermediaria entre UsusarioDAOMySQL y la base de datos.
}
