package fr.ecp.IS1220.beribu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {
	
	/*
	 * Test different IDs
	 */
	@Test
	void idTest001() {
		User u1 = new User("Jean");
		User u2 = new User("Paul");
		assertFalse(u1.getId() == u2.getId());
	}
	
	
	/*
	 * Test Equality and Hash Code
	 */
	
	
	/*
	 * Test time credit balance
	 */
	@Test
	void creditBalanceTest001() {
		User u1 = new User("Jean");
		u1.addTimeCreditBalance(10);
		Duration d = new Duration();
		d.add(10);
		assertTrue(d.equals(u1.getTimeCreditBalance()));
	}
	@Test
	void creditBalanceTest002() {
		User u1 = new User("Jean");
		u1.addTimeCreditBalance(10, 30);
		Duration d = new Duration();
		d.add(10, 30);
		assertTrue(d.equals(u1.getTimeCreditBalance()));
	}
	@Test
	void creditBalanceTest003() {
		User u1 = new User("Jean");
		u1.addTimeCreditBalance(1, 10, 30);
		Duration d = new Duration();
		d.add(1, 10, 30);
		assertTrue(d.equals(u1.getTimeCreditBalance()));
	}
	
	/*
	 * Test subscription
	 */
	@Test
	void subscriptionTest001() {
		User u = new User("Jean");
		Card c = new Vlibre(u);
		u.subscribe(c);
		assertTrue(u.getCard() == c);
	}
	@Test 
	void subscriptionTest002() {
		User u1 = new User("Jean");
		User u2 = new User("Paul");
		Card c1 = new Vlibre(u1);
		Card c2 = new Vlibre(u2);
		u1.subscribe(c1);
		assertFalse(u1.getCard() == c2);
	}
	@Test 
	void subscriptionTest003() {
		User u1 = new User("Jean");
		User u2 = new User("Paul");
		Card c2 = new Vlibre(u2);
		assertThrows(RuntimeException.class, () -> {
			u1.subscribe(c2);
		});
	}
	
	/*
	 * Test getCurrentRide method
	 */
	@Test
	void getCurrentRideTest001() {
		User u = new User("Jean");
		assertEquals(null, u.getCurrentRide());
	}
	@Test
	void getCurrentRideTest002() {
		// TODO Test the method when the user already terminates his last ride.
	}
	
	/*
	 * Test ride creation
	 * 		001 - 00x : Test if the ride is created
	 * 		00x - 00x : Test Exceptions
	 */
	@Test
	void newRideTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		Station s = new Station(new Localization(0.0, 0.0), false);
		new ParkingSlot(s);
		MechanicalBike mBike = new MechanicalBike();
		s.getParkingSlots().get(0).setBicycle(mBike);
		User u = new User("Jean");
		assertDoesNotThrow(() -> {
			u.newRide(s);
		});
	}
	@Test
	void newRideTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		Station s = new Station(new Localization(0.0, 0.0), false);
		new ParkingSlot(s);
		ElectricalBike eBike = new ElectricalBike();
		s.getParkingSlots().get(0).setBicycle(eBike);
		User u = new User("Jean");
		assertDoesNotThrow(() -> {
			u.newRide(s);
		});
	}
	@Test
	void newRideTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		Station s = new Station(new Localization(0.0, 0.0), false);
		new ParkingSlot(s);
		ElectricalBike eBike = new ElectricalBike();
		s.getParkingSlots().get(0).setBicycle(eBike);
		User u = new User("Jean");
		assertDoesNotThrow(() -> {
			u.newRide(s, "Electrical");
			assertEquals(u.getCurrentRide().getBicycle().getType(), "ELECTRICAL");
		});
	}
	@Test
	void newRideTest004() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		Station s = new Station(new Localization(0.0, 0.0), false);
		new ParkingSlot(s);
		MechanicalBike mBike = new MechanicalBike();
		s.getParkingSlots().get(0).setBicycle(mBike);
		User u = new User("Jean");
		assertDoesNotThrow(() -> {
			u.newRide(s, "Mechanical");
			assertEquals(u.getCurrentRide().getBicycle().getType(), "MECHANICAL");
		});
	}
	@Test
	void newRideTest005() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		Station s = new Station(new Localization(0.0, 0.0), false);
		new ParkingSlot(s);
		MechanicalBike mBike = new MechanicalBike();
		s.getParkingSlots().get(0).setBicycle(mBike);
		User u = new User("Jean");
		assertDoesNotThrow(() -> {
			u.newRide(s, "eleCtrical");
			assertEquals(null, u.getCurrentRide());
		});
	}
	@Test
	void newRideTest006() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		Station s = new Station(new Localization(0.0, 0.0), false);
		new ParkingSlot(s);
		ElectricalBike eBike = new ElectricalBike();
		s.getParkingSlots().get(0).setBicycle(eBike);
		User u = new User("Jean");
		assertDoesNotThrow(() -> {
			u.newRide(s, "mechanicaL");
			assertEquals(null, u.getCurrentRide());
		});
	}
	@Test
	void newRideTest007() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		Station s = new Station(new Localization(0.0, 0.0), false);
		new ParkingSlot(s);
		new ParkingSlot(s);
		MechanicalBike mBike1 = new MechanicalBike();
		MechanicalBike mBike2 = new MechanicalBike();
		s.getParkingSlots().get(0).setBicycle(mBike1);
		s.getParkingSlots().get(0).setBicycle(mBike2);
		User u = new User("Jean");
		u.newRide(s);
		assertThrows(RuntimeException.class, () -> {
			u.newRide(s);
		});
	}
	@Test
	void newRideTest008() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		Station s = new Station(new Localization(0.0, 0.0), false);
		new ParkingSlot(s);
		User u = new User("Jean");
		u.newRide(s);
		assertEquals(null, u.getCurrentRide());
	}
	@Test
	void newRideTest009() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		Station s = new Station(new Localization(0.0, 0.0), false);
		new ParkingSlot(s);
		ElectricalBike eBike = new ElectricalBike();
		s.getParkingSlots().get(0).setBicycle(eBike);
		User u = new User("Jean");
		assertDoesNotThrow(() -> {
			u.newRide(s, "Nothing");
			assertEquals(null, u.getCurrentRide());
		});
	}
}
