package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class MyVelibNetwork {
	private static MyVelibNetwork instance = null;
	private String name;
	private ArrayList<Station> stationDatabase = new ArrayList<Station>();
	private ArrayList<User> userDatabase = new ArrayList<User>();
	private BicycleFactory bicycleFactory = new BicycleFactory();
	private CardFactory cardFactory = new CardFactory();
	
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
		int sum = 0;
		for (int i = 0; i < numberOfBicycles.length; i++) {
			if (numberOfBicycles[i] < 0)
				throw new IllegalArgumentException("The numbers of bicycles should be"
						+ " positive.");
			sum += numberOfBicycles[i];
		}
		if (sum > numberOfSlots)
			throw new IllegalArgumentException("The total number of bicycles should"
					+ " not exceed the number of parking slots created.");
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
	
	public void createStations(Localization center, double radius, int number,
			int plusNumber, int numberOfSlots, double populationPercentage, 
			double[] typePercentage) throws IllegalArgumentException {
		if (radius < 0) {
			throw new IllegalArgumentException("The radius should be > 0.");
		}
		if (number < 0 || plusNumber < 0) {
			throw new IllegalArgumentException("The number of stations and Plus stations"
					+ " should be positive.");
		}
		if (populationPercentage < 0 || populationPercentage > 100) {
			throw new IllegalArgumentException("The population percentage should range"
					+ " between 0 and 100");
		}
		if (plusNumber > number) {
			throw new IllegalArgumentException("The number of Plus stations should"
					+ " not exceed the total number of stations created.");
		}
		ArrayList<String> typeDict = Bicycle.getTypeDict();
		double sum = 0;
		if (typePercentage.length != typeDict.size())
			throw new IllegalArgumentException(typeDict.size()+" numbers of bicycles should be given, "
					+ "one for each type in the order of appearance in Bicycle.getTypeDict().");
		for (int i = 0; i < typePercentage.length; i++) {
			if (typePercentage[i] < 0 || typePercentage[i] > 100)
				throw new IllegalArgumentException("The percentage of each bicycle"
						+ " type should range between 0 and 100");
			sum += typePercentage[i];
		}
		if (sum != 100 )
			throw new IllegalArgumentException("The sum of percentages for all bicycle"
					+ " types should be equal to 100.");
		
		int n = number*numberOfSlots;
		int numberOfBicycles = (int) Math.round(n*populationPercentage);
		int[] numberOfBicyclesPerStation = new int[number];
		int n_div = numberOfBicycles/number;
		int n_mod = numberOfBicycles%number;
		for (int i = 0; i < n_mod ; i++)
			numberOfBicyclesPerStation[i] = n_div + 1;
		for (int i = 0; i < number-n_mod ; i++)
			numberOfBicyclesPerStation[i] = n_div;
		int[] numberOfTypes = new int[typePercentage.length];
		int numberCheck = 0;
		for (int i = 0; i < numberOfTypes.length-1; i++) {
			numberOfTypes[i] = (int) Math.round(typePercentage[i]*numberOfBicycles);
			numberCheck += numberOfTypes[i];
		}
		numberOfTypes[numberOfTypes.length-1] = numberOfBicycles - numberCheck;
		int[] numberOfTypes_div = new int[numberOfTypes.length];
		int[] numberOfTypes_mod = new int[numberOfTypes.length];
		for (int i = 0; i < numberOfTypes.length ; i++) {
			numberOfTypes_div[i] = numberOfTypes[i]/number;
			numberOfTypes_mod[i] = numberOfTypes[i]%number;
		}
		ArrayList<Bicycle> remainderOfBicycles = new ArrayList<Bicycle>();
		for (int i = 0; i < numberOfTypes.length ; i++) {
			for (int j = 0; j < numberOfTypes_mod[i]; j++)
				remainderOfBicycles.add(this.bicycleFactory.newBicycle(typeDict.get(i)));
		}
		int j = 0;
		for (int i = 0; i < number; i++, j++) {
			boolean isPlus;
			if (j < plusNumber)
				isPlus = true;
			else
				isPlus = false;
			Localization randomLoc = center.generateLocInRadius(radius);
			ArrayList<Bicycle> stationPopulation = new ArrayList<Bicycle>();
			for (int k = 0; k < typeDict.size(); k++) {
				for (int l = 0; l < numberOfTypes_div[k]; l++) {
					stationPopulation.add(this.bicycleFactory.newBicycle(typeDict.get(k)));
				}
			}
			for (int k = 0; k < numberOfBicyclesPerStation[i] - stationPopulation.size(); k++) {
				stationPopulation.add(remainderOfBicycles.get(0));
				remainderOfBicycles.remove(0);
			}
			this.createEmptyStation(randomLoc, isPlus, numberOfSlots);
			this.stationDatabase.get(stationDatabase.size()-1).populate(stationPopulation);
		}
	}
	
	public void addStation(Station station) {
		this.stationDatabase.add(station);
	}
	
	public void createUsers(int number) {
		for (int i = 0; i < number; i++)
			this.userDatabase.add(new User());
	}
	
	public void createSubscribers(int number,String subType) {
		for (int i = 0; i < number; i++) {
			User user = new User();
			user.subscribe(subType);
			this.userDatabase.add(user);
		}
	}
	
	public void addUser(User user) {
		this.userDatabase.add(user);
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
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		MyVelibNetwork network = new MyVelibNetwork("Paris");
		System.out.println(MyVelibNetwork.getInstance().getName());
		network.createPopStation(new Localization(0,0), false,
				10, new int[] {5,2});
		System.out.println(network.stationDatabase);
	}
}
