package dao.mysql;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dao.interfaces.ReparacionDAO;
import entidades.Reparacion;

class ReparacionDAOMySQLTest {

	@Test
	void testInsert() {
		// 0. Crear reparacion
		Reparacion r1 = new Reparacion("Vehiculo siniestrado", LocalDate.of(2025, 11, 19), 222.33, "pendiente");
		
		int resul = 0;
		
		// 1. Crear un reparacionDAO
		ReparacionDAO rDAO1 = new ReparacionDAOMySQL ();
		
		// 2. Llamar al insert del reparacionDAO
		resul = rDAO1.insert(r1);
	
		// 3. Comprobar que ha funcionado o no?
		assertEquals(1, resul);
	}

//	@Test
//	void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDelete() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFinbyIdVehiculo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindall() {
//		fail("Not yet implemented");
//	}

}
