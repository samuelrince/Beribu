package fr.ecp.IS1220.myVelib.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import fr.ecp.IS1220.myVelib.core.bicycle.Bicycle;
import fr.ecp.IS1220.myVelib.core.bicycle.ElectricalBike;
import fr.ecp.IS1220.myVelib.core.station.ParkingSlot;
import fr.ecp.IS1220.myVelib.core.station.Station;
import fr.ecp.IS1220.myVelib.core.system.Localization;
import fr.ecp.IS1220.myVelib.core.system.SystemDate;

/**
 * This class contains Junit tests for ParkingSlot class
 * @author Samuel
 *
 */
class ParkingSlotTest {

	@AfterEach
	void afterEach() {
		SystemDate.delInstance();
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
		ParkingSlot p = new ParkingSlot(s);
		p.setOffline(true);
		assertTrue(p.isOffline());
	}
	@Test
	void isBicycleTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		ParkingSlot p = new ParkingSlot(s);
		p.setOffline(true);
		p.setOffline(false);
		assertFalse(p.isOffline());
	}
	
	/*
	 * Test set bicycle method
	 */
	@Test
	void attachBicycleTest001() throws Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		ParkingSlot p = new ParkingSlot(s);
		Bicycle b = new ElectricalBike();
		p.attachBicycle(b);
		assertEquals(p.getBicycle(), s.getParkingSlots().get(0).getBicycle());
	}
	
	/*
	 * Test set bicycle method
	 */
	@Test
	void attachBicycleTest002() throws Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s1 = new Station(new Localization(0.8, 0.7), false);
		ParkingSlot p1 = new ParkingSlot(s1);
		ParkingSlot p2 = new ParkingSlot(s1);
		Bicycle b = new ElectricalBike();
		p1.attachBicycle(b);
		assertThrows(IllegalArgumentException.class, () -> {
			p2.attachBicycle(b);
		});
	}
	
	/*
	 * Test set bicycle method
	 */
	@Test
	void attachBicycleTest003() throws Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s1 = new Station(new Localization(0.8, 0.7), false);
		ParkingSlot p1 = new ParkingSlot(s1);
		Bicycle b1 = new ElectricalBike();
		Bicycle b2 = new ElectricalBike();
		p1.attachBicycle(b1);
		assertThrows(RuntimeException.class, () -> {
			p1.attachBicycle(b2);
		});
	}
	
	/*
	 * Test set offline method
	 */
	@Test
	void setOfflineTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		ParkingSlot p = new ParkingSlot(s);
		p.setOffline(true);
		assertEquals(p.isOffline(), s.getParkingSlots().get(0).isOffline());
	}
	
}
