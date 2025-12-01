package dao.mysql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.interfaces.ClienteDAO;
import entidades.Cliente;

class ClienteDAOMySQLTest {

	@Test
	void testInsert() {
				// 0. Crear Usuario
				Cliente c1 = new Cliente ("davidEchevarria", "da@gmail.com", "4444N", "620304040");
				
				int resul = 0;
				
				// 1. Crear un usuarioDAO
				ClienteDAO cDAO1 = new ClienteDAOMySQL ();
				
				// 2. Llamar al insert del usuarioDAO con el Usuario de 0
				cDAO1.insert(c1);
			
				// 3. Comprobar que ha funcionado o no?
				assertEquals(1, resul);
	}
	


	@Test
	void testUpdate() {
		// 1. Crear cliente
		Cliente c = new Cliente ("juant", "Ass@gmaf", "22324N", "6434232");
		int resul = 0;
		ClienteDAO cDAO = new ClienteDAOMySQL();
		
		// 2. Insertar cliente
		cDAO.insert(c);
		
		// 3. Modificar cliente (ie: c.setNombre(), c.set...
		c.setEmail("juantio@gmail.com");
		c.setTelefono("6452629");
		
		// 4. Update(c)
		cDAO.update(c);
		
		assertEquals(1, resul);
	}

	@Test
	void testDelete() {
		//1.Creamos cliente
		Cliente c1 = new Cliente ("Ale", "Javier@gma", "2223333", "23343434");
		int resul = 0;
		ClienteDAO cDAO = new ClienteDAOMySQL();
		
		cDAO.insert(c1);
		cDAO.delete(c1);
		assertEquals(1, resul);
	}

	@Test
	void testFindall() {
		fail("Not yet implemented");
	}

	@Test
	void testFindByDni() {
		fail("Not yet implemented");
	}

}
