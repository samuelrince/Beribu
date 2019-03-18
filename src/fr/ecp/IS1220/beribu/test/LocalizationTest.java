package fr.ecp.IS1220.beribu;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LocalizationTest {
	private static final double DELTA = 1e-14;

	/*
	 * Test getters 
	 */
	@Test
	void test001() {
		Localization loc = new Localization(1.0, 1.0);
		assertTrue(loc.getLatitude() == 1.0);
	}
	
	@Test
	void test002() {
		Localization loc = new Localization(1.0, 1.0);
		assertTrue(loc.getLongitude() == 1.0);
	}
	/*
	 * Test setters
	 */
	@Test
	void test003() {
		Localization loc = new Localization(1.0, 1.0);
		loc.setLatitude(1.2);
		assertTrue(loc.getLatitude() == 1.2);
	}
	@Test
	void test004() {
		Localization loc = new Localization(1.0, 1.0);
		loc.setLongitude(1.2);
		assertTrue(loc.getLongitude() == 1.2);
	}
	/*
	 * Test Equality
	 */
	@Test
	void test005() {
		Localization loc1 = new Localization(1.0, 2.0);
		Localization loc2 = new Localization(1.0, 2.0);
		assertTrue(loc1.equals(loc2));
	}
	@Test
	void test006() {
		Localization loc1 = new Localization(1.0, 2.0);
		Localization loc2 = new Localization(1.0, 1.0);
		assertFalse(loc1.equals(loc2));
	}
	@Test
	void test007() {
		Localization loc1 = new Localization(2.0, 1.0);
		Localization loc2 = new Localization(1.0, 1.0);
		assertFalse(loc1.equals(loc2));
	}
	@Test
	void test008() {
		Localization loc1 = new Localization(2.0, 2.0);
		Localization loc2 = new Localization(1.0, 1.0);
		assertFalse(loc1.equals(loc2));
	}
	@Test
	void test009() {
		Localization loc1 = new Localization(1.0, 2.0);
		Localization loc2 = new Localization(1.0, 2.0);
		assertTrue(loc1.hashCode() == loc2.hashCode());
	}
	@Test
	void test010() {
		Localization loc1 = new Localization(1.0, 2.0);
		Localization loc2 = new Localization(1.0, 1.0);
		assertFalse(loc1.hashCode() == loc2.hashCode());
	}
	@Test
	void test011() {
		Localization loc1 = new Localization(2.0, 1.0);
		Localization loc2 = new Localization(1.0, 1.0);
		assertFalse(loc1.hashCode() == loc2.hashCode());
	}
	@Test
	void test012() {
		Localization loc1 = new Localization(2.0, 2.0);
		Localization loc2 = new Localization(1.0, 1.0);
		assertFalse(loc1.hashCode() == loc2.hashCode());
	}
	/*
	 * Test distanceTo method
	 
	@Test
	void test013() {
		Localization loc1 = new Localization(34.6, 8.1);
		Localization loc2 = new Localization(1.4, 10.89);
		assertEquals(loc1.distanceTo(loc2), 3703005.3008081787, DELTA);
	}
	@Test
	void test014() {
		Localization loc1 = new Localization(8.6, 42.789);
		Localization loc2 = new Localization(31.53, 77.89);
		assertEquals(loc1.distanceTo(loc2), 4431235.555906715, DELTA);
	}
	@Test
	void test015() {
		Localization loc1 = new Localization(86.9898379287, 78.78975289089);
		Localization loc2 = new Localization(7393.49387473, 89742.30809320);
		assertEquals(loc1.distanceTo(loc2), 11814541.6894044, DELTA);
	}
	@Test
	void test016() {
		Localization loc1 = new Localization(78.90, 78.45);
		Localization loc2 = new Localization(123.56, 124.8);
		assertEquals(loc1.distanceTo(loc2), 4659594.245561138, DELTA);
	}
	*/
}
