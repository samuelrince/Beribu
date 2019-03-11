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
	
	/*
	 * Test number of bicycle method
	 */
	@Test
	void numberOfBicycleTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(1).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(2).setBicycle(new MechanicalBike());
		assertTrue(s.numberOfBicycles() == 3);
	}
	@Test
	void numberOfBicycleTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(1).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(2).setBicycle(new MechanicalBike());
		assertTrue(s.numberOfBicycles("Electrical") == 2);
	}
	@Test
	void numberOfBicycleTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(1).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(2).setBicycle(new MechanicalBike());
		assertTrue(s.numberOfBicycles("Mechanical") == 1);
	}
	@Test
	void numberOfBicycleTest004() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		assertTrue(s.numberOfBicycles() == 0);
	}
	@Test
	void numberOfBicycleTest005() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		assertTrue(s.numberOfBicycles("Mechanical") == 0);
	}
	@Test
	void numberOfBicycleTest006() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		assertTrue(s.numberOfBicycles("Electrical") == 0);
	}
	@Test
	void numberOfBicycleTest007() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(1).setBicycle(new ElectricalBike());
		assertTrue(s.numberOfBicycles("Mechanical") == 0);
	}
	@Test
	void numberOfBicycleTest008() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(3).setBicycle(new MechanicalBike());
		assertTrue(s.numberOfBicycles("Electrical") == 0);
	}
	
	/*
	 * Test number of free slots methods
	 */
	@Test
	void numberOfFreeSlotsTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(1).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(2).setBicycle(new MechanicalBike());
		assertTrue(s.numberOfFreeSlots() == 1);
	}
	@Test
	void numberOfFreeSlotsTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(1).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(2).setBicycle(new MechanicalBike());
		assertTrue(s.numberOfFreeSlots() == 2);
	}
	@Test
	void numberOfFreeSlotsTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		assertTrue(s.numberOfFreeSlots() == 4);
	}
	@Test
	void numberOfFreeSlotsTest004() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(1).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(2).setBicycle(new MechanicalBike());
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(3).setBicycle(new MechanicalBike());
		assertTrue(s.numberOfFreeSlots() == 0);
	}
	
	/*
	 * Test get free parking slot method
	 */
	@Test
	void getFreeParkingSlotTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		ParkingSlot p1 = new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(1).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(2).setBicycle(new MechanicalBike());
		assertEquals(p1, s.getFreeParkingSlot());
	}
	@Test
	void getFreeParkingSlotTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		ParkingSlot p1 = new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		assertEquals(p1, s.getFreeParkingSlot());
	}
	@Test
	void getFreeParkingSlotTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(1).setBicycle(new MechanicalBike());
		s.getParkingSlots().get(2).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(3).setBicycle(new MechanicalBike());
		assertThrows(RuntimeException.class, () -> {
			s.getFreeParkingSlot();
		});
	}
	
	/*
	 * Test is full method
	 */
	@Test
	void isFullTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(1).setBicycle(new MechanicalBike());
		s.getParkingSlots().get(2).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(3).setBicycle(new MechanicalBike());
		assertTrue(s.isFull() == false);
	}
	@Test
	void isFullTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(1).setBicycle(new MechanicalBike());
		s.getParkingSlots().get(2).setBicycle(new ElectricalBike());
		assertTrue(s.isFull() == true);
	}
	
	/*
	 * Test get bicycle method
	 */
	@Test
	void getBicycleTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		Bicycle b = new ElectricalBike();
		s.getParkingSlots().get(0).setBicycle(b);
		s.getParkingSlots().get(1).setBicycle(new MechanicalBike());
		s.getParkingSlots().get(2).setBicycle(new ElectricalBike());
		assertEquals(s.getBicycle(), b);
	}
	@Test
	void getBicycleTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		Bicycle b = new MechanicalBike();
		s.getParkingSlots().get(0).setBicycle(b);
		s.getParkingSlots().get(1).setBicycle(new MechanicalBike());
		s.getParkingSlots().get(2).setBicycle(new ElectricalBike());
		assertEquals(s.getBicycle(), b);
	}
	@Test
	void getBicycleTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		Bicycle b = new MechanicalBike();
		s.getParkingSlots().get(0).setBicycle(new MechanicalBike());
		s.getParkingSlots().get(1).setBicycle(b);
		s.getParkingSlots().get(2).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(0).setOffline(true);
		assertEquals(s.getBicycle(), b);
	}
	@Test
	void getBicycleTest004() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		assertThrows(RuntimeException.class, () -> {
			s.getBicycle();
		});
	}
	@Test
	void getBicycleTest005() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(0).setOffline(true);
		assertThrows(RuntimeException.class, () -> {
			s.getBicycle();
		});
	}
	@Test
	void getBicycleTest006() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		Bicycle b = new ElectricalBike();
		s.getParkingSlots().get(0).setBicycle(new MechanicalBike());
		s.getParkingSlots().get(1).setBicycle(b);
		assertEquals(b, s.getBicycle("Electrical"));
	}
	@Test
	void getBicycleTest007() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		Bicycle b = new MechanicalBike();
		s.getParkingSlots().get(1).setBicycle(b);
		assertEquals(b, s.getBicycle("Mechanical"));
	}
	@Test
	void getBicycleTest008() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		new ParkingSlot(s);
		Bicycle b = new MechanicalBike();
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(1).setBicycle(b);
		s.getParkingSlots().get(1).setOffline(true);
		assertThrows(RuntimeException.class, () -> {
			s.getBicycle("Mechanical");
		});
	}
	@Test
	void getBicycleTest010() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(1).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(0).setOffline(true);
		s.getParkingSlots().get(1).setOffline(true);
		assertThrows(RuntimeException.class, () -> {
			s.getBicycle("Eletrical");
		});
	}
	
	/*
	 * Test is bicycle method
	 */
	@Test
	void isBicycleTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		assertFalse(s.isBicycle());
	}
	@Test
	void isBicycleTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		assertTrue(s.isBicycle());
	}
	@Test
	void isBicycleTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new MechanicalBike());
		assertTrue(s.isBicycle());
	}
	@Test
	void isBicycleTest004() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new MechanicalBike());
		s.getParkingSlots().get(0).setOffline(true);
		assertFalse(s.isBicycle());
	}
	@Test
	void isBicycleTest005() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new MechanicalBike());
		s.getParkingSlots().get(1).setBicycle(new MechanicalBike());
		assertTrue(s.isBicycle("Mechanical"));
	}
	@Test
	void isBicycleTest006() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		assertFalse(s.isBicycle("Mechanical"));
	}
	@Test
	void isBicycleTest007() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(0).setOffline(true);
		assertFalse(s.isBicycle("Electrical"));
	}
	
	/*
	 * Test set offline
	 */
	@Test
	void setOfflineTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.getParkingSlots().get(0).setBicycle(new ElectricalBike());
		s.getParkingSlots().get(0).setOffline(true);
		s.setOffline(true);
		assertTrue(s.getParkingSlots().get(0).isOffline() && s.getParkingSlots().get(1).isOffline());
	}
	@Test
	void setOfflineTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		s.setOffline(true);
		assertTrue(s.getParkingSlots().get(0).isOffline() && s.getParkingSlots().get(1).isOffline());
	}
	@Test
	void setOfflineTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		assertDoesNotThrow(() -> {
			s.setOffline(true);
		});
	}
}
