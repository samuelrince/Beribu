package fr.ecp.IS1220.beribu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RideTest {

	/*
	 * Test initialization
	 */
	@Test
	void initTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Bicycle b = new ElectricalBike();
		Station s = new Station(new Localization(0.0, 0.0), false);
		assertDoesNotThrow(() -> {
			new Ride(u, b, s);
		});
	}
	@Test
	void initTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Bicycle b = new MechanicalBike();
		Station s = new Station(new Localization(2.0, 3.0), false);
		assertDoesNotThrow(() -> {
			new Ride(u, b, s);
		});
	}
	@Test
	void initTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Bicycle b = new MechanicalBike();
		Station s = new Station(new Localization(2.0, 3.0), true);
		assertDoesNotThrow(() -> {
			new Ride(u, b, s);
		});
	}
	@Test
	void initTest004() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u1 = new User("Jean");
		User u2 = new User("Paul");
		Bicycle b1 = new MechanicalBike();
		Bicycle b2 = new ElectricalBike();
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(0.1, 0.0), false);
		Ride r1 = new Ride(u1, b1, s1);
		Ride r2 = new Ride(u2, b2, s2);
		assertFalse(r1.getId() == r2.getId());
	}
	
	/*
	 * Test end method
	 */
	@Test
	void endTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(5.0, 3.0), true);
		Bicycle b = new MechanicalBike();
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		s1.getParkingSlots().get(0).attachBicycle(b);
		u.newRide(s1);
		SD.setTime(13, 24, 56);
		assertDoesNotThrow(() -> {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		});
	}
	@Test
	void endTest002() throws Exception, RuntimeException {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(5.0, 3.0), true);
		Bicycle b1 = new MechanicalBike();
		Bicycle b2 = new ElectricalBike();
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		s1.getParkingSlots().get(0).attachBicycle(b1);
		s2.getParkingSlots().get(0).attachBicycle(b2);
		u.newRide(s1);
		SD.setTime(13, 24, 56);
		assertThrows(RuntimeException.class, () -> {
			u.getCurrentRide().end(s2.getParkingSlots().get(0));
		});
	}
	@Test
	void endTest003() throws Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(5.0, 3.0), true);
		Bicycle b = new MechanicalBike();
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		s1.getParkingSlots().get(0).attachBicycle(b);
		u.newRide(s1);
		SD.setTime(13, 24, 56);
		u.getCurrentRide().end(s2.getFreeParkingSlot());
		assertEquals(new Duration(new Date(2019, 3, 8, 13, 13, 13), new Date(2019, 3, 8, 13, 24, 56)), u.getListOfRides().get(0).getDuration());
	}
	@Test
	void endTest004() throws Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(5.0, 3.0), true);
		Bicycle b = new MechanicalBike();
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		s1.getParkingSlots().get(0).attachBicycle(b);
		u.newRide(s1);
		SD.setTime(13, 24, 56);
		u.getCurrentRide().end(s2.getFreeParkingSlot());
		System.out.println(u.getListOfRides().get(0).getEndTime());
		assertEquals(new Date(2019, 3, 8, 13, 24, 56), u.getListOfRides().get(0).getEndTime());
	}
	@Test
	void endTest005() throws Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(5.0, 3.0), true);
		Bicycle b = new MechanicalBike();
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		s1.getParkingSlots().get(0).attachBicycle(b);
		u.newRide(s1);
		SD.setTime(13, 24, 56);
		u.getCurrentRide().end(s2.getFreeParkingSlot());
		assertEquals(new Date(2019, 3, 8, 13, 13, 13), u.getListOfRides().get(0).getStartTime());
	}
	@Test
	void endTest006() throws Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(5.0, 3.0), true);
		Bicycle b = new MechanicalBike();
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		s1.getParkingSlots().get(0).attachBicycle(b);
		u.newRide(s1);
		SD.setTime(13, 24, 56);
		u.getCurrentRide().end(s2.getFreeParkingSlot());
		assertEquals(s2, u.getListOfRides().get(0).getEndStation());
	}
	@Test
	void endTest007() throws Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(5.0, 3.0), true);
		Bicycle b = new MechanicalBike();
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		s1.getParkingSlots().get(0).attachBicycle(b);
		u.newRide(s1);
		SD.setTime(13, 24, 56);
		u.getCurrentRide().end(s2.getFreeParkingSlot());
		assertEquals(s1, u.getListOfRides().get(0).getStartStation());
	}
	@Test
	void endTest008() throws RuntimeException, Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(5.0, 3.0), true);
		Bicycle b = new MechanicalBike();
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		s1.getParkingSlots().get(0).attachBicycle(b);
		u.newRide(s1);
		SD.setTime(13, 24, 56);
		u.getCurrentRide().end(s2.getFreeParkingSlot());
		assertEquals(false, u.getListOfRides().get(0).isCurrent());
	}
	@Test
	void endTest009() throws Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(5.0, 3.0), true);
		Bicycle b = new MechanicalBike();
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		s1.getParkingSlots().get(0).attachBicycle(b);
		u.newRide(s1);
		assertEquals(true, u.getCurrentRide().isCurrent());
	}
	
	/*
	 * Test price and time credit balance update
	 */
	
}
