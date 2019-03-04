package fr.ecp.IS1220.beribu;


public interface PathStrategy {

	public Ride getRide(Localization source, Localization destination,
			String BicycleType);
}
