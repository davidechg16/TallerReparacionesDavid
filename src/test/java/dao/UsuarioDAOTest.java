package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dao.interfaces.UsuarioDAO;
import dao.mysql.UsuarioDAOMySQL;
import entidades.Usuario;

class UsuarioDAOTest {

	@Test
	void testInsert() {
		
		// 0. Crear Usuario
		//No se comprueba nada, solo se crea un usuario de ejemplo
		Usuario u1 = new Usuario ("davidEchevarria", "password", "mecanico", "58447520N");
		
		int resul = 0;
		
		// 1. Crear un usuarioDAO
		UsuarioDAO uDAO1 = new UsuarioDAOMySQL ();
		
		// 2. Llamar al insert del usuarioDAO con el Usuario de 0
		uDAO1.insert(u1);
	
		// 3. Comprobar que ha funcionado o no?
		assertEquals(1, resul);
	}
	
//	@Test
//	void testLogin() {
//		
//		// 0. Crear Usuario
//		//No se comprueba nada, solo se crea un usuario de ejemplo
//		//Usuario u1 = new Usuario ("12345678A", "password", "David Echevarria", "mecanico");
//		
//		boolean resul = false;
//		// 1. Crear un usuarioDAO
//		
//		// 2. Llamar al login del usuarioDAO
//		resul = usuarioDAO.login();
//		
//		// 3. Comprobar que ha funcionado o no?
//		assertTrue(resul);
//	}

}
