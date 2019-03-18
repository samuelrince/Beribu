package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class MyVelibNetwork {
	private static MyVelibNetwork instance = null;
	private String name;
	private ArrayList<Station> stationDatabase = new ArrayList<Station>();
	private ArrayList<User> userDatabase = new ArrayList<User>();
	
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
	
	public void createEmptyStation(Localization localization, Boolean isPlus) {
		this.stationDatabase.add(new Station(localization, isPlus));
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
