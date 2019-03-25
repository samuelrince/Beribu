package fr.ecp.IS1220.myVelib.core;

/**
 * This class allows to generate a random localization within a circular area. 
 * @author Valentin
 *
 */
public class RandomLocInCircle implements RandomLocGenerator {

	/**
	 * This method generates a random localization within a circular area of given center and radius.
	 * @param center the center of the circular area
	 * @param radius the radius of the circular area
	 */
	@Override
	public Localization generate(Localization center, double radius)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (radius < 0) {
			throw new IllegalArgumentException("The radius should be > 0.");
		}
		double r = Math.sqrt(Math.random())*radius;
		double angle = Math.random()*2*Math.PI;
		return new Localization(center.getLatitude() + Math.cos(angle)*r/Localization.rayonTerre,
				center.getLongitude() + Math.sin(angle)*r/(Math.cos(center.getLatitude())
						*Localization.rayonTerre));
	}

}
