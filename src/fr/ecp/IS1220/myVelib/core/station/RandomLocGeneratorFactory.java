package fr.ecp.IS1220.myVelib.core.station;

import fr.ecp.IS1220.myVelib.core.exception.BadShapeException;

/**
 * A RandomLocGenerator factory.
 * @author Valentin
 *
 */
public class RandomLocGeneratorFactory {
	
	public RandomLocGenerator newRandomLocGenerator(String shape) {
		if (shape.equalsIgnoreCase("circle"))
			return new RandomLocInCircle();
		if (shape.equalsIgnoreCase("square"))
			return new RandomLocInSquare();
		throw new BadShapeException ("No localization generator for such an area shape. "
				+ "The accepted shapes are 'square' and 'circle'.");
	}
}
