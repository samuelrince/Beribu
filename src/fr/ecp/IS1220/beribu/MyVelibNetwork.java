package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class MyVelibNetwork {
	private static long uniqId;
	private long id;
	private String name;
	private ArrayList<Station> listOfStations = new ArrayList<Station>();
	private ArrayList<User> userDatabase = new ArrayList<User>();
	
	public MyVelibNetwork(String name) {
		super();
		this.name = name;
		this.id = uniqId++;
	}
	
	
}
