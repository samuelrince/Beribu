
public class Scenario1 {

	public static void main(String[] args) {
		User user1 = new User("Robert Downey Jr.");
		Vmax vmax1 = new Vmax(user1);
		Station station1 = new Station(new Localization(0,0),false);
		new ParkingSlot(station1);
		new ParkingSlot(station1);
		new ParkingSlot(station1);
		ElectricalBike eBike1 = new ElectricalBike();
		MechanicalBike mBike1 = new MechanicalBike();
		ElectricalBike eBike2 = new ElectricalBike();
		station1.getParkingSlots().get(0).setBicycle(eBike1);
		station1.getParkingSlots().get(1).setBicycle(mBike1);
		station1.getParkingSlots().get(2).setBicycle(eBike2);
		station1.getParkingSlots().get(2).setOffline(true);
		System.out.println(user1.getCard());
		user1.newRide(station1);
	}
}
