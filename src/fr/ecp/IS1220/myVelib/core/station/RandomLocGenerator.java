package fr.ecp.IS1220.myVelib.core.station;

import fr.ecp.IS1220.myVelib.core.system.Localization;

/**
 * This interface represents a random localization generator in a specified area.
 * @author Valentin
 *
 */
public interface RandomLocGenerator {

	public Localization generate(Localization center, double param);
	
}
