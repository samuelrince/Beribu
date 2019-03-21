package fr.ecp.IS1220.beribu.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import fr.ecp.IS1220.beribu.Date;
import fr.ecp.IS1220.beribu.Localization;
import fr.ecp.IS1220.beribu.MechanicalBike;
import fr.ecp.IS1220.beribu.ParkingSlot;
import fr.ecp.IS1220.beribu.Station;
import fr.ecp.IS1220.beribu.StationBalance;
import fr.ecp.IS1220.beribu.SystemDate;
import fr.ecp.IS1220.beribu.User;

class StationBalanceTest {
	
	/*
	 * Test total rent count 
	 */
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
		//System.out.println(StationBalance.totalRentCount(s));
		//System.out.println(StationBalance.totalReturnCount(s));
		//assertTrue(StationBalance.totalRentCount(s) == 10);
	}
	
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
			users.get(i).getCurrentRide().end(s1.getFreeParkingSlot());
		}
		users.get(9).newRide(s1);
		users.get(9).getCurrentRide().end(s.getFreeParkingSlot());
		System.out.println("Rent of s: " + StationBalance.totalRentCount(s));
		System.out.println("Return of s: " + StationBalance.totalReturnCount(s));
		System.out.println("Rent of s1: " + StationBalance.totalRentCount(s1));
		System.out.println("Return of s1: " + StationBalance.totalReturnCount(s1));
		//assertTrue(StationBalance.totalRentCount(s) == 10);
	}

}
