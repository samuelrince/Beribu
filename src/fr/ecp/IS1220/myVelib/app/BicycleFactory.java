package fr.ecp.IS1220.myVelib.app;
/**
 * A bicycle factory used for the creation of bicycles from the network.
 * @author Valentin
 *
 */
public class BicycleFactory {

	public Bicycle newBicycle(String bicycleType) {
		if (bicycleType.equalsIgnoreCase("mechanical"))
			return new MechanicalBike();
		if (bicycleType.equalsIgnoreCase("electrical"))
			return new ElectricalBike();
		throw new IllegalArgumentException("There is no such bicycle type.");
	}
}
