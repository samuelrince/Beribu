package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class MyVelibNetwork {
	private static MyVelibNetwork instance = null;
	private String name;
	private ArrayList<Station> stationDatabase = new ArrayList<Station>();
	private ArrayList<User> userDatabase = new ArrayList<User>();
	private BicycleFactory bicycleFactory = new BicycleFactory();
	
	public MyVelibNetwork(String name) throws RuntimeException {
		if (instance == null) {
			this.name = name;
			instance = this;
		}
		else {
			throw new RuntimeException("A MyVelib network already exists.");
		}
	}

	public static synchronized MyVelibNetwork getInstance() {
		if (instance==null) {
			throw new RuntimeException("No MyVelib network has been created.");
		}
		else {
			return instance;
		}
	}
	
	public void createEmptyStation(Localization localization, Boolean isPlus,
			double numberOfSlots) {
		this.stationDatabase.add(new Station(localization, isPlus));
	}
	
	/**
	 * The numbers of bicycles of each type should be listed in an array giving the
	 * number of bicycle for each type in order of appearance in the bicycle type
	 * dictionary (see Bicycle.getTypeDict()).
	 * @param localization
	 * @param isPlus
	 * @param numberOfSlots
	 * @param numberOfBicycles
	 */
	public void createPopStation(Localization localization, Boolean isPlus, 
			int numberOfSlots, int[] numberOfBicycles) {
		ArrayList<String> typeDict = Bicycle.getTypeDict();
		if (typeDict.size() != numberOfBicycles.length)
			throw new IllegalArgumentException(typeDict.size()+" numbers of bicycles should be given, "
					+ "one for each type in the order of appearance in Bicycle.getTypeDict().");
		Station station = new Station(localization, isPlus,numberOfSlots);
		for (int i = 0; i < typeDict.size(); i++) {
			ArrayList<Bicycle> bList = new ArrayList<Bicycle>(numberOfBicycles[i]);
			for (int j = 0; j < numberOfBicycles[i]; j++)
				bList.add(this.bicycleFactory.newBicycle(typeDict.get(i)));
			station.populate(bList);
		}
		this.stationDatabase.add(station);
	}
	
	public void createStation(Localization center, double radius, int number,
			int plusNumber, double populationPercentage) throws IllegalArgumentException {
		if (radius < 0) {
			throw new IllegalArgumentException("The radius should be > 0.");
		}
		if (number < 0 || plusNumber < 0) {
			throw new IllegalArgumentException("The number of stations and Plus stations"
					+ " should be positive.");
		}
		if (populationPercentage < 0) {
			throw new IllegalArgumentException("The population percentage should be "
					+ "positive.");
		}
		if (plusNumber > number) {
			throw new IllegalArgumentException("The number of Plus stations should"
					+ " not exceed the total number of stations created.");
		}
		
		
		
		for (int i = 0; i < plusNumber; i++) {
			Localization randomLoc = center.generateLocInRadius(radius);
			this.createEmptyStation(randomLoc, true);
		}
		for (int i = 0; i < number - plusNumber; i++) {
			Localization randomLoc = center.generateLocInRadius(radius);
			this.createEmptyStation(randomLoc, false);
		}
		
	}
	
	public void addStation(Station station) {
		this.stationDatabase.add(station);
	}
	
	public void addUser(String name) {
		this.userDatabase.add(new User(name));
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Station> getStationDatabase() {
		return stationDatabase;
	}

	public ArrayList<User> getUserDatabase() {
		return userDatabase;
	}

	public static void main(String[] args) {
		new MyVelibNetwork("Paris");
		System.out.println(MyVelibNetwork.getInstance().getName());
	}
}
