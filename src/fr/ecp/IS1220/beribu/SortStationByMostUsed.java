package fr.ecp.IS1220.beribu;

import java.util.Comparator;

public class SortStationByMostUsed implements Comparator<Station> {

	@Override
	public int compare(Station s1, Station s2) {
		Integer h1 = s1.getRentCount() + s1.getReturnCount();
		Integer h2 = s2.getRentCount() + s2.getReturnCount();
		return h2.compareTo(h1);
	}

}
