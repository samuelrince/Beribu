package fr.ecp.IS1220.myVelib.core;

/**
 * This class allows to generate a random localization within a square area. 
 * @author Valentin
 *
 */
public class RandomLocInSquare implements RandomLocGenerator {

	/**
	 * This method generates a random localization within a square area of given center and side length.
	 * @param center the center of the square area
	 * @param side side length of the square area
	 */
	@Override
	public Localization generate(Localization center, double side)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (side < 0) {
			throw new IllegalArgumentException("The length of the side should be > 0.");
		}
		double x = Math.random()*side - side/2.;
		double y = Math.random()*side - side/2.;
		return new Localization(center.getLatitude() + x/Localization.rayonTerre,
				center.getLongitude() + y/(Math.cos(center.getLatitude())
						*Localization.rayonTerre));
	}

}
