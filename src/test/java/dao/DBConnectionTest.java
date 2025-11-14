package dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DBConnectionTest {

	@Test
	void test() {
		DBConnection c1 = DBConnection.getInstance();
		DBConnection c2 = DBConnection.getInstance();
		assertEquals(c1, c2);
	}

}
