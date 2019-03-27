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
		return new Localization(center.getLatitude() + y/Localization.rayonTerre*180/Math.PI,
				center.getLongitude() + x/(Math.cos(center.getLatitude())
						*Localization.rayonTerre)*180/Math.PI);
	}
	
	public static void main(String[] args) {
		RandomLocInSquare generator = new RandomLocInSquare();
		Localization center = new Localization(0,0);
		for (int i = 0; i<10; i++) {
			Localization loc = generator.generate(center, 10);
			System.out.println("deltaLAT: "+(loc.getLatitude()-center.getLatitude()));
			System.out.println("deltaLONG: "+(loc.getLongitude()-center.getLongitude()));
			System.out.println("distance: "+loc.distanceTo(center));
		}
	}

}
