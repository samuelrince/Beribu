package fr.ecp.IS1220.beribu;

public class StationFactory {
	private int uniqID = 0;
	
	public Station createStation(String stationType, Localization localization) throws BadStationCreationType {
		if (stationType.equalsIgnoreCase("PLUS")) {
			return new PlusStation(this.uniqID, localization);
		} else if (stationType.equalsIgnoreCase("STANDARD")) {
			return new StandardStation(this.uniqID, localization);
		} else {
			throw new BadStationCreationType(stationType);
		}
	}
}
