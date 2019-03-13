package fr.ecp.IS1220.beribu;


public class Scenario1 {

	public static void main(String[] args) throws Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		new MyVelibNetwork("Paris");
		User user1 = new User("Robert Downey Jr.");
		Vmax vmax1 = new Vmax(user1);
		Station station1 = new Station(new Localization(48.85689405420505,2.31536865234375),false,"Varenne");
		Station station2 = new Station(new Localization(48.85223492920533,2.339315414428711),true, "Odéon");
		Station station3 = new Station(new Localization(48.84890268296977,2.321033477783203),false,"Vaneau");
		MyVelibNetwork.getInstance().addStation(station1);
		MyVelibNetwork.getInstance().addStation(station2);
		MyVelibNetwork.getInstance().addStation(station3);
		new ParkingSlot(station1);
		new ParkingSlot(station1);
		new ParkingSlot(station1);
		new ParkingSlot(station1);
		new ParkingSlot(station2);
		new ParkingSlot(station2);
		new ParkingSlot(station2);
		new ParkingSlot(station3);
		new ParkingSlot(station3);
		new ParkingSlot(station3);
		ElectricalBike eBike1 = new ElectricalBike();
		ElectricalBike eBike2 = new ElectricalBike();
		ElectricalBike eBike3 = new ElectricalBike();
		ElectricalBike eBike4 = new ElectricalBike();
		MechanicalBike mBike1 = new MechanicalBike();
		MechanicalBike mBike2 = new MechanicalBike();
		MechanicalBike mBike3 = new MechanicalBike();
		
		station1.getParkingSlots().get(0).attachBicycle(eBike1);
		station1.getParkingSlots().get(1).attachBicycle(mBike1);
		station1.getParkingSlots().get(2).attachBicycle(eBike2);
		station1.getParkingSlots().get(2).setOffline(true);
		station2.getParkingSlots().get(0).attachBicycle(eBike3);
		station2.getParkingSlots().get(2).attachBicycle(eBike4);
		station3.getParkingSlots().get(1).attachBicycle(mBike2);
		station3.getParkingSlots().get(2).attachBicycle(mBike3);
		station3.getParkingSlots().get(0).setOffline(true);
		
		user1.planRide(new Localization(48.847962637540554,2.3183927431184657), 
				new Localization(48.85979680709707,2.315828448811203));
		user1.getPlannedRide().start();
		user1.newRide(station3);
		SD.setTime(19, 26, 37);
		station1.getParkingSlots().get(3).setOffline(true);
		
		
	}
}
