package fr.ecp.IS1220.myVelib.core;

/**
 * A path strategy factory
 * @author Valentin
 *
 */
public class PathStrategyFactory {

	public PathStrategy newPathStrategy(String pathStrategy) {
		if (pathStrategy.equalsIgnoreCase("minimal walking"))
			return new MinimalWalking();
		if (pathStrategy.equalsIgnoreCase("fastest path"))
			return new FastestPath();
		if (pathStrategy.equalsIgnoreCase("prefer plus"))
			return new PreferPlus();
		if (pathStrategy.equalsIgnoreCase("avoid plus"))
			return new AvoidPlus();
		if (pathStrategy.equalsIgnoreCase("preserve distribution"))
			return new PreserveDistribution();
		
		else
			throw new IllegalArgumentException("No such path strategy. The accepted path strategies "
					+ "are 'minimal walking', 'fastest path', 'prefer plus', "
					+ "'avoid plus' and 'preserve distribution'.");
	}
	
}
