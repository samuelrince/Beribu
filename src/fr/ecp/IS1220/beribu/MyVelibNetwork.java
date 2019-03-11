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
	
	public void addStation(Localization localization, Boolean isPlus) {
		this.stationDatabase.add(new Station(localization, isPlus));
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
		new MyVelibNetwork("Lyon");
		System.out.println(MyVelibNetwork.getInstance().getName());
		new MyVelibNetwork("Paris");
	}
}
