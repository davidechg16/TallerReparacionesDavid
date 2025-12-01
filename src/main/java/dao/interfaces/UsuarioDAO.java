package dao.interfaces;

import java.util.ArrayList;
import entidades.Usuario;

public interface UsuarioDAO {
	boolean login(String nombreUsuario, String password);
	int update (Usuario u);
	int insert(Usuario u);
	int delete (Usuario u);
	Usuario findByNombre(String nombre);
	ArrayList<Usuario> findall();

}
