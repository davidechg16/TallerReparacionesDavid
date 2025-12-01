package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dao.interfaces.ClienteDAO;
import dao.mysql.ClienteDAOMySQL;
import entidades.Cliente;

class ClienteDAOTest {

	@Test
	void testInsert() {
		
		// 0. Crear Usuario
		//No se comprueba nada, solo se crea un usuario de ejemplo
		Cliente c1 = new Cliente ("davidEchevarria", "da@gmail.com", "4444N", "620304040");
		
		int resul = 0;
		
		// 1. Crear un usuarioDAO
		ClienteDAO cDAO1 = new ClienteDAOMySQL ();
		
		// 2. Llamar al insert del usuarioDAO con el Usuario de 0
		cDAO1.insert(c1);
	
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
