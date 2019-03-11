package fr.ecp.IS1220.beribu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParkingSlotTest {

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
	void setBicycleTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 12, 12);
		SD.setTime(5, 34, 45);
		Station s = new Station(new Localization(0.8, 0.7), false);
		ParkingSlot p = new ParkingSlot(s);
		Bicycle b = new ElectricalBike();
		p.setBicycle(b);
		assertEquals(p.getBicycle(), s.getParkingSlots().get(0).getBicycle());
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
