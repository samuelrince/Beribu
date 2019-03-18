package fr.ecp.IS1220.beribu.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.ecp.IS1220.beribu.Bicycle;
import fr.ecp.IS1220.beribu.Date;
import fr.ecp.IS1220.beribu.Duration;
import fr.ecp.IS1220.beribu.ElectricalBike;
import fr.ecp.IS1220.beribu.Localization;
import fr.ecp.IS1220.beribu.MechanicalBike;
import fr.ecp.IS1220.beribu.ParkingSlot;
import fr.ecp.IS1220.beribu.Ride;
import fr.ecp.IS1220.beribu.Station;
import fr.ecp.IS1220.beribu.SystemDate;
import fr.ecp.IS1220.beribu.User;
import fr.ecp.IS1220.beribu.Vlibre;
import fr.ecp.IS1220.beribu.Vmax;

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
		try {
			s1.getParkingSlots().get(0).attachBicycle(b);
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
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
	 * Test price calculation
	 */
	@Test
	void endRideTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new MechanicalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(16, 13, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		// The duration is 3h 
		// Its a mechanical bicycle
		// The user did not subscribe 
		// The user time credit balance is null
		// => price should be 3.0
		assertTrue(u.getListOfRides().get(0).getPrice() == 3.0);
	}
	@Test
	void endRideTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		u.subscribe(new Vlibre(u));
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new MechanicalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(16, 13, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		// The duration is 3h 
		// Its a mechanical bicycle
		// The user did subscribe to Vlibre 
		// The user time credit balance is null
		// => price should be 2.0
		assertTrue(u.getListOfRides().get(0).getPrice() == 2.0);
	}
	@Test
	void endRideTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		u.subscribe(new Vmax(u));
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new MechanicalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(16, 13, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		// The duration is 3h 
		// Its a mechanical bicycle
		// The user did subscribe to Vmax 
		// The user time credit balance is null
		// => price should be 2.0
		assertTrue(u.getListOfRides().get(0).getPrice() == 2.0);
	}
	@Test
	void endRideTest004() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 13, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		// The duration is 4h 
		// Its a electrical bicycle
		// The user did not subscribe 
		// The user time credit balance is null
		// => price should be 8.0
		assertTrue(u.getListOfRides().get(0).getPrice() == 8.0);
	}
	@Test
	void endRideTest005() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		u.subscribe(new Vlibre(u));
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 13, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		// The duration is 4h 
		// Its a electrical bicycle
		// The user did subscribe to a Vlibre card
		// The user time credit balance is null
		// => price should be 7.0
		assertTrue(u.getListOfRides().get(0).getPrice() == 7.0);
	}
	@Test
	void endRideTest006() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 13, 13);
		User u = new User("Jean");
		u.subscribe(new Vmax(u));
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 13, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		// The duration is 4h 
		// Its a electrical bicycle
		// The user did subscribe to a Vmax card
		// The user time credit balance is null
		// => price should be 3.0
		assertTrue(u.getListOfRides().get(0).getPrice() == 3.0);
	}
	@Test
	void endRideTest007() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		u.subscribe(new Vlibre(u));
		u.addTimeCreditBalance(45);
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 15, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		// The duration is 4h 
		// Its a mechanical bicycle
		// The user did subscribe to a Vlibre card
		// The user time credit balance is 45minutes
		// => price should be 3.0
		assertTrue(u.getListOfRides().get(0).getPrice() == 3.0);
	}
	@Test
	void endRideTest008() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		u.subscribe(new Vmax(u));
		u.addTimeCreditBalance(75);
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 15, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		// The duration is 4h 
		// Its a mechanical bicycle
		// The user did subscribe to a Vmax card
		// The user time credit balance is 75minutes
		// => price should be 2.0
		assertTrue(u.getListOfRides().get(0).getPrice() == 2.0);
	}
	@Test
	void endRideTest009() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		u.subscribe(new Vlibre(u));
		u.addTimeCreditBalance(45);
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 15, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		// The duration is 4h 
		// Its a electrical bicycle
		// The user did subscribe to a Vlibre card
		// The user time credit balance is 45minutes
		// => price should be 7.0
		assertTrue(u.getListOfRides().get(0).getPrice() == 7.0);
	}
	@Test
	void endRideTest0010() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		u.subscribe(new Vmax(u));
		u.addTimeCreditBalance(45);
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 15, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		// The duration is 4h 
		// Its a electrical bicycle
		// The user did subscribe to a Vmax card
		// The user time credit balance is 45minutes
		// => price should be 3.0
		assertTrue(u.getListOfRides().get(0).getPrice() == 3.0);
	}
	/*
	 * Test duration of ride
	 */
	@Test
	void endRideTest0011() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		u.subscribe(new Vmax(u));
		u.addTimeCreditBalance(45);
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 2, 8);
		SD.setTime(17, 15, 13);
		assertThrows(RuntimeException.class, () -> {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		});
	}
	@Test
	void endRideTest0012() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		u.subscribe(new Vmax(u));
		u.addTimeCreditBalance(45);
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 20, 33);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		Duration d = new Duration();
		d.add(4, 5, 20);
		assertEquals(d, u.getListOfRides().get(0).getDuration());
	}
	
	/*
	 * Test time credit affectation
	 */
	@Test
	void endRideTest0011b() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), false);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 15, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		assertEquals(new Duration(), u.getTimeCreditBalance());
	}
	@Test
	void endRideTest0012b() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 15, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		assertEquals(new Duration(), u.getTimeCreditBalance());
	}
	@Test
	void endRideTest0013b() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		u.subscribe(new Vlibre(u));
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), false);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 15, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		assertEquals(new Duration(), u.getTimeCreditBalance());
	}
	@Test
	void endRideTest0014b() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		u.subscribe(new Vlibre(u));
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 15, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		Duration d = new Duration();
		d.setDuration(5*60);
		assertEquals(d, u.getTimeCreditBalance());
	}
	@Test
	void endRideTest0015b() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		u.subscribe(new Vmax(u));
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), false);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 15, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		assertEquals(new Duration(), u.getTimeCreditBalance());
	}
	@Test
	void endRideTest0016b() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		u.subscribe(new Vmax(u));
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 15, 13);
		try {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		} catch (Exception e) {
			fail("Failed to find a free parking slot");
		}
		Duration d = new Duration();
		d.setDuration(5*60);
		assertEquals(d, u.getTimeCreditBalance());
	}
	
	/*
	 * Test no parking slot available
	 */
	@Test
	void endRideTest17() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		u.subscribe(new Vmax(u));
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 15, 13);
		assertThrows(RuntimeException.class, () -> {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		});
	}
	@Test
	void endRideTest18() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		u.subscribe(new Vmax(u));
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		try {
			s2.getParkingSlots().get(0).attachBicycle(new MechanicalBike());
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 15, 13);
		assertThrows(RuntimeException.class, () -> {
			u.getCurrentRide().end(s2.getFreeParkingSlot());
		});
	}
	
	/*
	 * Test start and end at the same station
	 */
	@Test
	void endRideTest19() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 8);
		SD.setTime(13, 15, 13);
		User u = new User("Jean");
		Station s1 = new Station(new Localization(2.0, 3.0), true);
		new ParkingSlot(s1);
		try {
			s1.getParkingSlots().get(0).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		u.newRide(s1);
		SD.setDay(2019, 3, 8);
		SD.setTime(17, 15, 13);
		assertDoesNotThrow(() -> {
			u.getCurrentRide().end(s1.getFreeParkingSlot());
		});
	}
}
