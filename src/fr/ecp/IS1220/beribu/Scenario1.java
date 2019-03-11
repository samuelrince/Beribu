package fr.ecp.IS1220.beribu;


public class Scenario1 {

	public static void main(String[] args) {
		SystemDate SD = SystemDate.getInstance();
		
		User user1 = new User("Robert Downey Jr.");
		Vmax vmax1 = new Vmax(user1);
		Station station1 = new Station(new Localization(0,0),false);
		Station station2 = new Station(new Localization(0.001,0),true);
		new ParkingSlot(station1);
		new ParkingSlot(station1);
		new ParkingSlot(station1);
		new ParkingSlot(station2);
		ElectricalBike eBike1 = new ElectricalBike();
		MechanicalBike mBike1 = new MechanicalBike();
		ElectricalBike eBike2 = new ElectricalBike();
		station1.getParkingSlots().get(0).setBicycle(eBike1);
		station1.getParkingSlots().get(1).setBicycle(mBike1);
		station1.getParkingSlots().get(2).setBicycle(eBike2);
		station1.getParkingSlots().get(2).setOffline(true);
		
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		
		user1.newRide(station1);
		//user1.newRide(station2);
		
		SD.setTime(19, 27, 54);
		
		//user1.getCurrentRide().end(station2.getParkingSlots().get(0));
		//System.out.println(ride.toString());
		
	}
}
