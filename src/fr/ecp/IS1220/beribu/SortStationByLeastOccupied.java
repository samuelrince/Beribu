package fr.ecp.IS1220.beribu;

import java.util.Comparator;

public class SortStationByLeastOccupied implements Comparator<Station>{

	@Override
	public int compare(Station s1, Station s2) {
		SystemDate SD = SystemDate.getInstance();
		Double ot1 = StationBalance.occupationRate(s1, s1.getCreatedAt(), new Date());
		Double ot2 = StationBalance.occupationRate(s2, s2.getCreatedAt(), new Date());
		return ot2.compareTo(ot1);
	}

}
