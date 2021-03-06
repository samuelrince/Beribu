package fr.ecp.IS1220.myVelib.core.bicycle;

import fr.ecp.IS1220.myVelib.core.exception.BadBicycleTypeException;

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
		throw new BadBicycleTypeException ("No such bicycle type. The accepted bicycle types"
				+ " are 'mechanical' and 'electrical'.");
	}
}
