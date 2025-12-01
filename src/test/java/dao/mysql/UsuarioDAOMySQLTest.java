package dao.mysql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.interfaces.UsuarioDAO;
import entidades.Usuario;

class UsuarioDAOMySQLTest {

		@Test
		void testLogin() {
			
		// 0. Crear Usuario
		//No se comprueba nada, solo se crea un usuario de ejemplo
		Usuario u1 = new Usuario ("david", "david", "mecanico", "58447520N");
		
		boolean resul = false;
		// 1. Crear un usuarioDAO
		UsuarioDAO uDAO = new UsuarioDAOMySQL();
		// 2. Llamar al login del usuarioDAO
		resul = uDAO.login("david", "david");
		
		// 3. Comprobar que ha funcionado o no?
		assertTrue(resul);
	}
//
//	@Test
//	void testUpdate() {
//		fail("Not yet implemented");
//	}

	@Test
	void testInsert() {
		
		// 0. Crear Usuario
		Usuario u1 = new Usuario ("davidEchevarria", "password", "administrador", "58447520N");
		
		int resul = 0;
		
		// 1. Crear un usuarioDAO
		UsuarioDAO uDAO1 = new UsuarioDAOMySQL ();
		
		// 2. Llamar al insert del usuarioDAO con el Usuario de 0
		resul = uDAO1.insert(u1);
	
		// 3. Comprobar que ha funcionado o no?
		assertEquals(1, resul);
	}

	@Test
	void testDelete() {
	// 0. Crear Usuario
			Usuario u2 = new Usuario ("davidEchevarria", "password", "administrador", "58447520N");
			
			int resul = 0;
			
			// 1. Crear un usuarioDAO
			UsuarioDAO uDAO2 = new UsuarioDAOMySQL ();
			
			// 2. Llamar al insert del usuarioDAO con el Usuario de 0
			resul = uDAO2.delete(u2);
		
			// 3. Comprobar que ha funcionado o no?
			assertEquals(1, resul);
	}
//
//	@Test
//	void testFindByNombre() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindall() {
//		fail("Not yet implemented");
//	}

}
