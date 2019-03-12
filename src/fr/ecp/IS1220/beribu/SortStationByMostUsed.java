package fr.ecp.IS1220.beribu;

import java.util.Comparator;

public class SortStationByMostUsed implements Comparator<Station> {

	@Override
	public int compare(Station s1, Station s2) {
		Integer h1 = StationBalance.totalRentCount(s1) + StationBalance.totalReturnCount(s1);
		Integer h2 = StationBalance.totalRentCount(s2) + StationBalance.totalReturnCount(s2);
		return h2.compareTo(h1);
	}

}
