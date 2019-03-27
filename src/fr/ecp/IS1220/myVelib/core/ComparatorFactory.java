package fr.ecp.IS1220.myVelib.core;

import java.util.Comparator;

import fr.ecp.IS1220.myVelib.core.exception.BadSortPolicyException;

/**
 * A comparator factory used for the creation of station comparators from the network.
 * @author Valentin
 *
 */
public class ComparatorFactory {

	public Comparator<Station> newComparator(String sortPolicy) {
		if (sortPolicy.equalsIgnoreCase("most used"))
			return new SortStationByMostUsed();
		if (sortPolicy.equalsIgnoreCase("least occupied"))
			return new SortStationByLeastOccupied();
		else
			throw new BadSortPolicyException("No such sort policy. The accepted sort policies"
					+ " are 'most used' and 'least occupied'.");
	}
}
