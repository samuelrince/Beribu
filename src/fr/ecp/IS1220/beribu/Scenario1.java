package fr.ecp.IS1220.beribu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Scenario1 {

	public static void main(String[] args) {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1); SD.setTime(1, 0, 0);
		// Stations
		Station s0 = new Station(new Localization(2.1, 3.1), false);
		Station s1 = new Station(new Localization(2.1, 3.1), false);
		Station s2 = new Station(new Localization(2.1, 3.1), true);
		//Station s3 = new Station(new Localization(3.4, 5.2), false);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s2); stations.add(s0); stations.add(s1); //stations.add(s3);
		// Parking slots
		new ParkingSlot(s0);
		new ParkingSlot(s0);
		new ParkingSlot(s0);
		new ParkingSlot(s0);
		new ParkingSlot(s0);
		new ParkingSlot(s1);
		new ParkingSlot(s1);
		new ParkingSlot(s1);
		new ParkingSlot(s2);
		new ParkingSlot(s2);
		new ParkingSlot(s2);
		new ParkingSlot(s2);
		new ParkingSlot(s2);
		//new ParkingSlot(s3);
		//new ParkingSlot(s3);
		s0.getParkingSlots().get(3).setOffline(true);
		// Bicycles
		s0.getParkingSlots().get(0).setBicycle(new MechanicalBike());
		s0.getParkingSlots().get(1).setBicycle(new ElectricalBike());
		s0.getParkingSlots().get(3).setBicycle(new MechanicalBike());	
		s1.getParkingSlots().get(1).setBicycle(new ElectricalBike());
		s2.getParkingSlots().get(1).setBicycle(new MechanicalBike());
		s2.getParkingSlots().get(2).setBicycle(new MechanicalBike());
		s2.getParkingSlots().get(0).setBicycle(new MechanicalBike());
		s2.getParkingSlots().get(3).setBicycle(new MechanicalBike());
		// Users
		User u1 = new User("Jean");
		User u2 = new User("Paul");
		u2.subscribe(new Vlibre(u2));
		User u3 = new User("John");
		u3.subscribe(new Vlibre(u3));
		// Rides
			// Day 1
		SD.setDay(2019, 1, 1); SD.setTime(8, 13, 27);
		u1.newRide(s0);
		SD.setDay(2019, 1, 1); SD.setTime(8, 55, 13);
		u2.newRide(s0);
		SD.setDay(2019, 1, 1); SD.setTime(9, 16, 27);
		u2.getCurrentRide().end(s1.getFreeParkingSlot());
		SD.setDay(2019, 1, 1); SD.setTime(9, 45, 27);
		u3.newRide(s2);
		SD.setDay(2019, 1, 1); SD.setTime(9, 54, 27);
		u1.getCurrentRide().end(s2.getFreeParkingSlot());
		SD.setDay(2019, 1, 1); SD.setTime(10, 13, 27);
		u3.getCurrentRide().end(s1.getFreeParkingSlot());
			// Day 2
		SD.setDay(2019, 1, 2); SD.setTime(8, 10, 34);
		u1.newRide(s1);
		SD.setDay(2019, 1, 2); SD.setTime(10, 30, 27);
		u2.newRide(s2);
		SD.setDay(2019, 1, 2); SD.setTime(10, 35, 27);
		u1.getCurrentRide().end(s2.getFreeParkingSlot());
		SD.setDay(2019, 1, 2); SD.setTime(11, 13, 27);
		u2.getCurrentRide().end(s0.getFreeParkingSlot());
			// Day 3
		SD.setDay(2019, 1, 3); SD.setTime(10, 15, 56);
		u3.newRide(s1);
		SD.setDay(2019, 1, 3); SD.setTime(10, 35, 56);
		u3.getCurrentRide().end(s0.getFreeParkingSlot());
		SD.setDay(2019, 1, 3); SD.setTime(10, 43, 56);
		u2.newRide(s1);
		SD.setDay(2019, 1, 3); SD.setTime(11, 53, 56);
		u2.getCurrentRide().end(s0.getFreeParkingSlot());
			// Day 4
		SD.setDay(2019, 1, 4); SD.setTime(11, 53, 56);
		u1.newRide(s0);
		SD.setDay(2019, 1, 4); SD.setTime(11, 55, 56);
		u2.newRide(s2);
		SD.setDay(2019, 1, 4); SD.setTime(12, 53, 56);
		u1.getCurrentRide().end(s2.getFreeParkingSlot());
		SD.setDay(2019, 1, 4); SD.setTime(13, 30, 56);
		u2.getCurrentRide().end(s0.getFreeParkingSlot());
		
		System.out.println(" == Stations initial order == ");
		System.out.println(stations.toString());
		System.out.println();
		
		Collections.sort(stations);
		System.out.println(" == Stations sorted by ID == ");
		System.out.println(stations.toString());
		System.out.println();
		
		Comparator<Station> c = new SortStationByMostUsed();
		
		Collections.sort(stations, c);
		System.out.println(" == Stations sorted by most used == ");
		System.out.println(stations);
		System.out.println();
		
		System.out.println(StationBalance.occupationRate(s0, s0.getCreatedAt(), new Date()));
		System.out.println(StationBalance.occupationRate(s1, s1.getCreatedAt(), new Date()));
		System.out.println(StationBalance.occupationRate(s2, s2.getCreatedAt(), new Date()));
		//System.out.println(StationBalance.occupationRate(s3, s3.getCreatedAt(), new Date()));
		
		/*
		c = new SortStationByLeastOccupied();
		Collections.sort(stations, c);
		System.out.println(" == Stations sorted by least occupied == ");
		System.out.println(stations);
		System.out.println();
		*/
	}
}
