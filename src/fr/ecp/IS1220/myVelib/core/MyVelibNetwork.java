package fr.ecp.IS1220.myVelib.core;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import fr.ecp.IS1220.myVelib.core.exception.NoSuchNetworkExistException;

/**
 * This class represents a MyVelib network. It is a singleton.
 * @author Valentin
 *
 */
public class MyVelibNetwork {
	private static ArrayList<MyVelibNetwork> listOfNetworks = new ArrayList<MyVelibNetwork>();
	private static MyVelibNetwork instance = null;
	private String name;
	private ArrayList<Station> stationDatabase = new ArrayList<Station>();
	private ArrayList<User> userDatabase = new ArrayList<User>();
	private BicycleFactory bicycleFactory = new BicycleFactory();
	
	/**
	 * Constructor of class MyVelibNetwork.
	 * @param name name of the network
	 * @throws RuntimeException	occurs when the network already exists
	 */
	public MyVelibNetwork(String name) {		
		for (MyVelibNetwork network:listOfNetworks)
			if (network.getName().equals(name)) {
				throw new IllegalArgumentException("A MyVelib network with this name"
						+ " already exists.");
			}		
		this.name = name;
		listOfNetworks.add(this);
		System.out.println("New MyVelib network : "+this);
		switchNetwork(this);
	}

	/**
	 * This method should be used when trying to access the MyVelib network.
	 * If the network has not been instantiated, throws a RuntimeException.
	 * @return the unique instance of MyVelibNetwork
	 * @throws RuntimeException	occurs when there is no instance of the network
	 */
	public static synchronized MyVelibNetwork getInstance() throws RuntimeException {
		if (instance==null) {
			throw new NoSuchNetworkExistException("No MyVelib network has been created.");
		}
		else {
			return instance;
		}
	}
	
	public static void switchNetwork(MyVelibNetwork network) {
		if (listOfNetworks.contains(network)) {
			instance = network;
			System.out.println("Switched to network"+network.name);
		}
		else
			throw new NoSuchNetworkExistException("This MyVelib network does not exist.");
	}
	
	public static void switchNetwork(String networkName) {
		for (MyVelibNetwork network:listOfNetworks) {
			if (network.getName().equals(networkName)) {
			instance = network;
			System.out.println("Switched to MyVelib network "+network.name+".");
			return;
			}
		}
		throw new NoSuchNetworkExistException("This MyVelib network does not exist.");
	}
	
	/**
	 * Add a new empty station with a number of slots to the network.
	 * @param localization localization of the station
	 * @param isPlus type of station (true for Plus, false for Standard)
	 * @param numberOfSlots number of parking slots
	 */
	public void createEmptyStation(Localization localization, Boolean isPlus,
			int numberOfSlots) {
		this.stationDatabase.add(new Station(localization, isPlus, numberOfSlots));
	}
	
	/**
	 * Adds a new station with a number of slots to the network. This station is 
	 * populated with the number of bicycles specified, one number for each type.
	 * The numbers of bicycles of each type should be listed in an array giving the
	 * number of bicycles for each type in order of appearance in the bicycle type
	 * dictionary (see Bicycle.getTypeDict()).
	 * @param localization localization of the station
	 * @param isPlus type of station (true for Plus, false for Standard)
	 * @param numberOfSlots number of parking slots
	 * @param numberOfBicycles number of bicycles of each type
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
		ArrayList<Bicycle> bList = new ArrayList<Bicycle>();
		for (int i = 0; i < typeDict.size(); i++) {
			for (int j = 0; j < numberOfBicycles[i]; j++)
				bList.add(this.bicycleFactory.newBicycle(typeDict.get(i)));
		}
		station.populate(bList);
		this.stationDatabase.add(station);
	}
	
	public void createPopStation(Localization localization, Boolean isPlus, 
			int numberOfSlots, int[] numberOfBicycles, String name) {
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
		Station station = new Station(localization, isPlus,name,numberOfSlots);
		ArrayList<Bicycle> bList = new ArrayList<Bicycle>();
		for (int i = 0; i < typeDict.size(); i++) {
			for (int j = 0; j < numberOfBicycles[i]; j++)
				bList.add(this.bicycleFactory.newBicycle(typeDict.get(i)));
		}
		station.populate(bList);
		this.stationDatabase.add(station);
	}
	
	/**
	 * This method creates a number of stations within a circular perimeter 
	 * defined by a center and a radius. The stations contain a number of parking
	 * slots, which can be populated with bicycles.
	 * @param center center of the covered area 
	 * @param radius radius of the covered area (in km)
	 * @param number number of stations
	 * @param plusNumber number of Plus stations
	 * @param numberOfSlots number of parking slots in each station
	 * @param populationPercentage percentage of parking slots which should
	 * be populated with bicycles
	 * @param typePercentage portion of each type of bicycle relatively to the
	 * total amount of bicycles
	 * @throws IllegalArgumentException	occurs when a wrong type of parameter
	 * is passed to the method
	 */
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
		
		RandomLocGenerator randomLocGenerator = new RandomLocInCircle();
		int n = number*numberOfSlots;
		int numberOfBicycles = (int) Math.round(n*populationPercentage/100);
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
			numberOfTypes[i] = (int) Math.round(typePercentage[i]/100*numberOfBicycles);
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
			Localization randomLoc = randomLocGenerator.generate(center,radius);
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
		
	/**
	 * This method creates a number of stations within an area defined by the random localization
	 * generator and its parameters given in argument. The stations contain a number of parking
	 * slots, which can be populated with bicycles.
	 * @param randomLocGenerator random localization generator for the desired shape
	 * @param center center of the covered area 
	 * @param param parameter (can be radius, side length...) of the covered area (in km)
	 * @param number number of stations
	 * @param plusNumber number of Plus stations
	 * @param numberOfSlots number of parking slots in each station
	 * @param populationPercentage percentage of parking slots which should
	 * be populated with bicycles
	 * @param typePercentage portion of each type of bicycle relatively to the
	 * total amount of bicycles
	 * @throws IllegalArgumentException	occurs when a wrong type of parameter
	 * is passed to the method
	 */
	public void createStations(RandomLocGenerator randomLocGenerator,
			Localization center, double param, int number,
			int plusNumber, int numberOfSlots, double populationPercentage, 
			double[] typePercentage) throws IllegalArgumentException {
		if (param < 0) {
			throw new IllegalArgumentException("The parameter should be > 0.");
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
		int numberOfBicycles = (int) Math.round(n*populationPercentage/100);
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
			numberOfTypes[i] = (int) Math.round(typePercentage[i]/100*numberOfBicycles);
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
			Localization randomLoc = randomLocGenerator.generate(center,param);
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
	/**
	 * Adds a given station to the network.
	 * @param station station to add
	 */
	public void addStation(Station station) {
		this.stationDatabase.add(station);
	}
	
	/**
	 * Adds a number of new Standard users to the network.
	 * @param number number of users
	 */
	public void createUsers(int number) {
		for (int i = 0; i < number; i++)
			this.userDatabase.add(new User());
	}
	
	/**
	 * Adds a number of new users with the specified type of subscription to
	 * the network.
	 * @param number number of users
	 * @param subType type of subscription
	 * @throws Exception 
	 */
	public void createSubscribers(int number,String subType) throws Exception {
		for (int i = 0; i < number; i++) {
			User user = new User();
			if (subType != "Standard")
				user.subscribe(subType);
			this.userDatabase.add(user);
		}
	}
	
	/**
	 * Adds a given user to the network.
	 * @param user user to add
	 */
	public void addUser(User user) {
		this.userDatabase.add(user);
	}
	
	/**
	 * 
	 * @return name of the network
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return list of stations of the network
	 */
	public ArrayList<Station> getStationDatabase() {
		return stationDatabase;
	}

	/**
	 * 
	 * @return list of users of the network
	 */
	public ArrayList<User> getUserDatabase() {
		return userDatabase;
	}
	
	/**
	 * 
	 * @param index index of user
	 * @return the user at specified index in the network user data base
	 */
	public User user(int index) {
		return this.userDatabase.get(index);
	}
	
	/**
	 * 
	 * @param index index of the station
	 * @return the station at specified index in the network station data base
	 */
	public Station station(int index) {
		return this.stationDatabase.get(index);
	}
	/**
	 * Returns a representation of the list of stations of the network and their
	 * current state, ie. the most recent status of all their parking slots 
	 * (online/offline and free/occupied).
	 * @return a representation of the list of stations of the network and their
	 * current state
	 */
	public String stationDatabaseState() {
		String res = "----------------------"+"\n"+this.toString() +"\n"+"State of stations :"+"\n";
		for (int i = 0; i < this.stationDatabase.size(); i++) {	
			res += "\n"+this.stationDatabase.get(i).toString()+" : "+
					this.stationDatabase.get(i).getCurrentState().toString();
		}
		return res+"\n"+"----------------------";
	}
	
	/**
	 * 
	 * @return a representation of the list of users of the network
	 */
	public String userDatabaseRepresentation() {
		String res = "----------------------"+"\n"+this.toString() +"\n"+"List of users :"+"\n";
		for (int i = 0; i < this.userDatabase.size(); i++) {
			res += "\n"+this.userDatabase.get(i).toString()+", "
		+this.userDatabase.get(i).getCard().getType();
		}
		return res+"\n"+"----------------------";
	}

	/**
	 * This method displays a new Jframe window showing the positions of all the stations of
	 * the network.
	 */
	public void visual2D() {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(700,750));
		ArrayList<Localization> points=new ArrayList<Localization>();
		ArrayList<String> labels=new ArrayList<String>();
		for (Station s:this.stationDatabase) {
			points.add(s.getLocalization());
			labels.add(s.getName());
		}
		
		System.out.println("bary "+Localization.barycenter(points));
		for (Station s:this.stationDatabase) {
			System.out.println(s.getName()+" : "+s.getLocalization());	
			System.out.println("distance to bary : "+s.getLocalization().distanceTo(Localization.barycenter(points)));
		}
		
		Panneau p=new Panneau(points,labels);
		frame.setContentPane(p);
		frame.setVisible(true);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\\MyVelib "+this.name+"/";
	}
	
	public static void main(String[] args) {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019,1,1);SD.setTime(12,0,0);
		MyVelibNetwork network = new MyVelibNetwork("Paris");
		network.createStations(new RandomLocInCircle(), new Localization(45,45), 10, 
				10, 0, 10, 70, new double[] {70,30});
		network.visual2D();
	}
}
