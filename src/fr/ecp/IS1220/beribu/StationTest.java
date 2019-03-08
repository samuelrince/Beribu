package fr.ecp.IS1220.beribu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StationTest {

	/*
	 * Test initialization
	 */
	@Test
	void initTest001() {
		assertDoesNotThrow(() -> {
			new Station(new Localization(0.1, 0.4), true);
		});
	}
	@Test
	void initTest002() {
		assertDoesNotThrow(() -> {
			new Station(new Localization(1.8, 6.0), false);
		});
	}
	
}
