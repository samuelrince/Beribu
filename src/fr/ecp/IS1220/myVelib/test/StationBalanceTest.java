package fr.ecp.IS1220.myVelib.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import fr.ecp.IS1220.myVelib.core.bicycle.MechanicalBike;
import fr.ecp.IS1220.myVelib.core.station.ParkingSlot;
import fr.ecp.IS1220.myVelib.core.station.Station;
import fr.ecp.IS1220.myVelib.core.statistic.StationBalance;
import fr.ecp.IS1220.myVelib.core.system.Date;
import fr.ecp.IS1220.myVelib.core.system.Localization;
import fr.ecp.IS1220.myVelib.core.system.SystemDate;
import fr.ecp.IS1220.myVelib.core.user.User;

/**
 * This class contains Junit tests for StationBalance class
 * @author Samuel
 *
 */
class StationBalanceTest {
	
	//Work In Progress
	
	@AfterEach
	void afterEach() {
		SystemDate.delInstance();
	}
	
	/*
	 * Test total rent count 
	 *	
	@Test
	void totalRentCountTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 20); SD.setTime(1, 0, 0);
		Station s = new Station(new Localization(0.0, 0.0), false);
		s.createParkingSlots(10);
		for (ParkingSlot ps : s.getParkingSlots()) {ps.attachBicycle(new MechanicalBike());}
		ArrayList<User> users = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {users.add(new User());}
		for (User u : users) {
			u.newRide(s);
		}
		assertTrue(StationBalance.totalRentCount(s) == 9);
	}*/
	
	@Test
	void totalReturnCountTest001() throws RuntimeException, Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 20); SD.setTime(1, 0, 0);
		Station s = new Station(new Localization(0.0, 0.0), false);
		Station s1 = new Station(new Localization(0.0, 1.0), false);
		s.createParkingSlots(10);
		s1.createParkingSlots(10);
		for (ParkingSlot ps : s.getParkingSlots()) {ps.attachBicycle(new MechanicalBike());}
		ArrayList<User> users = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {users.add(new User());}
		
		for (int i = 0; i < 6; i++) {
			users.get(i).newRide(s);
			users.get(i).endCurrentRide(s1);
		}
		users.get(9).newRide(s1);
		users.get(9).getCurrentRide().end(s.getFreeParkingSlot());
		assertTrue(StationBalance.totalReturnCount(s1) == 5);
	}
}
