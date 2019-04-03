package fr.ecp.IS1220.myVelib.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.SwingUtilities;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import fr.ecp.IS1220.myVelib.core.*;
import fr.ecp.IS1220.myVelib.core.exception.BadDateException;
import fr.ecp.IS1220.myVelib.core.exception.NoBicycleAvailableException;
import fr.ecp.IS1220.myVelib.core.exception.NoNewRideException;
import fr.ecp.IS1220.myVelib.core.exception.NoSuchBackupExistException;
import fr.ecp.IS1220.myVelib.core.exception.NoSuchNetworkExistException;
import fr.ecp.IS1220.myVelib.core.exception.NoSuchStationExistException;
import fr.ecp.IS1220.myVelib.core.exception.NoSuchUserExistException;
import fr.ecp.IS1220.myVelib.core.exception.SuchStationIsFullException;
import fr.ecp.IS1220.myVelib.core.exception.SuchStationIsOfflineException;
import fr.ecp.IS1220.myVelib.core.exception.SuchUserAlreadyExistException;

public class CommandLineInterpreter {

	public static String parseString(String word) throws ParseException {
		
		char[] chars = word.toCharArray();
		if (chars[0]==39 && chars[chars.length-1]==39) {
			char[] stringContent = Arrays.copyOfRange(chars,1,chars.length-1);
			return new String(stringContent);
		} 
		else
			throw new ParseException();
	}
	
	public static void interprete(String[] tokens) {
		String command = tokens[0];
		String arguments[] = Arrays.copyOfRange(tokens, 1, tokens.length);
		switch (command) {
		case "exit": {
			if (arguments.length == 0) {
				CommandLineInterface.setExit(true);
				return;
			}
			System.err.println("'exit' takes no argument.");
			break;
		}
		case "currentSD": {
			if (arguments.length == 0) {
				System.out.println(SystemDate.getInstance().toString());
				return;
			}
			System.err.println("'currentSD' takes no argument.");
			break;
		}	
		case "delete": {
			if (arguments.length == 1) {
				if (arguments[0].equalsIgnoreCase("all")) {
					MyVelibNetwork.deleteAll();
				}
				else
					System.err.println("Type 'delete all' to delete all the database,"
							+ " including networks, users and stations.");
				return;
			}
			System.err.println("'delete' takes 1 argument.");
			break;
		}	
		case "setup": {
			if (arguments.length == 1) {
				String networkName = null;
				try {
				networkName = parseString(arguments[0]);
				}
				catch(ParseException e) {System.err.println("'setup' takes the following "
					+ "types of argument :"+"\n"+"'<String>'");return;}
				try {
					MyVelibNetwork network = new MyVelibNetwork(networkName);
					network.createStations(new RandomLocInSquare(), new Localization(0,0)
						, 4, 10, 3, 10, 75, new double[] {70,30});
				}
				catch(Exception e) {System.err.println(e.getMessage());}
				return;
			}
			if (arguments.length == 9+Bicycle.getTypeDict().size()) {
				String networkName = null;
				int nstations = 0;
				int nplus = 0;
				int nslots = 0;
				String shape = null;
				double centerLat = 0;
				double centerLong = 0;
				double param = 0;
				double populationPercent = 0;
				double[] typePercent = new double[Bicycle.getTypeDict().size()];
				try {
					networkName = parseString(arguments[0]);
					nstations = Integer.parseInt(arguments[1]);
					nplus = Integer.parseInt(arguments[2]);
					nslots = Integer.parseInt(arguments[3]);
					shape = parseString(arguments[4]);
					centerLat = Double.parseDouble(arguments[5]);
					centerLong = Double.parseDouble(arguments[6]);
					param = Double.parseDouble(arguments[7]);
					populationPercent = Double.parseDouble(arguments[8]);
					for (int i = 0; i < typePercent.length; i++)
						typePercent[i] = Double.parseDouble(arguments[9+i]);
				}
				catch (ParseException e) {
					String argList = "'<String>' <int> <int> <int> '<String>'"
							+ " <double> <double> <double>";
					for (int i = 0; i<Bicycle.getTypeDict().size(); i++)
						argList += " <double>";
					System.err.println("'setup' takes the following "
							+ "types of argument :"+"\n"+argList);return;
				}
				catch (NumberFormatException e) {
					String argList = "'<String>' <int> <int> <int> '<String>'"
							+ " <double> <double> <double>";
					for (int i = 0; i<Bicycle.getTypeDict().size(); i++)
						argList += " <double>";
					System.err.println("'setup' takes the following "
							+ "types of argument :"+"\n"+argList);return;
				}				
				try {
					MyVelibNetwork network = new MyVelibNetwork(networkName);
					RandomLocGeneratorFactory rlgFactory = new RandomLocGeneratorFactory();
					RandomLocGenerator rlg = rlgFactory.newRandomLocGenerator(shape);
					Localization center = new Localization(centerLat,centerLong);
					
					network.createStations(rlg,center,param,nstations,nplus,nslots, 
							populationPercent, typePercent);
				}
				catch(Exception e) {System.err.println(e);}
				return;
			}
			System.err.println("'setup' takes 1 or "+(9+Bicycle.getTypeDict().size())
					+" arguments.");
			break;
		}
		
		case "createBackup": {
			if (arguments.length == 0) {		
				try {
					MyVelibNetwork network = MyVelibNetwork.getInstance();
					NetworkBackup.saveNetworkState(network);
				} catch (NoSuchNetworkExistException e) {
					System.err.println("Unable to find the current network");
				} catch (IOException e) {
					System.err.println("Unable to create backup file");
				} catch (Exception e) {
					System.err.println("Unexpected error");
					e.printStackTrace();
				}
				return;
			}	
			System.err.println("'createBackup' takes no argument.");
			break;
		}
		
		case "loadBackup": {
			if (arguments.length == 1) {		
				String arg = null;
				try {
					arg = parseString(arguments[0]);
				} catch(ParseException e) {System.err.println("'loadBackup' takes the following "
						+ "type of argument :"+"\n"+"'<String>'");return;}
				MyVelibNetwork.deleteAll();
				try {	
					MyVelibNetwork.loadNetworkFromBackup(NetworkBackup.loadBackup(arg));
				} catch(IOException i) {
					System.err.println("Failed to load the backup file");
				} catch(ClassNotFoundException c) {
					System.err.println("MyVelibNetwork class not found");
				} catch(NoSuchBackupExistException b) {
					System.err.println("The expected backup file (" + arg + ") does not exist");
				} catch(NoSuchNetworkExistException n) {
					System.err.println("The expected network backup (" + arg + ") does not exist");
				} catch(Exception e) {
					System.err.println("Unexpected error");
					e.printStackTrace();
				}
				return;
			}	
			System.err.println("'loadBackup' takes 1 argument.");
			break;
		}
		
		case "listBackup" : {
			if (arguments.length == 0) {
				NetworkBackup.display();
				return;
			}
			System.err.println("'createBackup' takes no argument.");
			break;
		}
		
		case "addUser": {
			if (arguments.length == 2) {
				String name = null;
				String subType = null;
				try {
				name = parseString(arguments[0]);
				subType = parseString(arguments[1]);
				}
				catch(ParseException e) {System.err.println("'addUser' takes the following "
					+ "types of argument :"+"\n"+"'<String>' '<String>'");return;}
				try {
					MyVelibNetwork network = MyVelibNetwork.getInstance();
					network.newSubscriber(name,subType);
				} catch (SuchUserAlreadyExistException a) {
					System.err.println("This user name already exist, please try another one");
				}
				catch(Exception e) {System.err.println(e);}
				return;
			}
			if (arguments.length == 3) {
				String name = null; 
				String password = null;
				String subType = null;
				try {
					name = parseString(arguments[0]);
					password = parseString(arguments[1]);
					subType = parseString(arguments[2]);
				} catch(ParseException e) {System.err.println("'addUser' takes the following "
						+ "types of argument :"+"\n"+"'<String>' '<String>' '<String>'");return;}
				try {
					MyVelibNetwork network = MyVelibNetwork.getInstance();
					network.newSubscriber(name, password, subType);
				} catch (SuchUserAlreadyExistException a) {
					System.err.println("This user name already exist, please try another one");
				}
				catch(Exception e) {System.err.println(e);}
				return;
			}
			System.err.println("'addUser' takes 2 arguments.");
			break;
		}

		case "switch": {
			if (arguments.length == 1) {
				String networkName = null;
				try {
					networkName = parseString(arguments[0]);
				}
				catch(ParseException e) {System.err.println("'switch' takes the following "
					+ "types of argument :"+"\n"+"'<String>'");return;}
				try {
					MyVelibNetwork.switchNetwork(networkName);
				} catch(NoSuchNetworkExistException e) {
					System.err.println("Network " + networkName + " does not exist");
				} catch(Exception e) {
					System.err.println("Unexpected error");
					System.err.println(e);
				}
				return;
			}
			System.err.println("'switch' takes 1 argument.");
			break;
		}

		case "date": {
			if (arguments.length == 3) {
				SystemDate SD = SystemDate.getInstance();
				Integer year = Integer.valueOf(arguments[0]);
				Integer month = Integer.valueOf(arguments[1]);
				Integer day = Integer.valueOf(arguments[2]);
				try {
					SD.setDay(year, month, day);
				} catch(BadDateException e) {
					System.err.println(e.getMessage() + "From CLUI");
				} catch(Exception e) {
					System.err.println(e.getMessage() + "from" + e.getClass());
				}
				return;
			}
			System.err.println("'date' takes 3 arguments.");
			break;
		}
		case "time": {
			if (arguments.length == 3) {
				SystemDate SD = SystemDate.getInstance();
				Integer hour = Integer.valueOf(arguments[0]);
				Integer minute = Integer.valueOf(arguments[1]);
				Integer second = Integer.valueOf(arguments[2]);
				try {
					SD.setTime(hour, minute, second);	
				} catch(BadDateException e) {
					System.err.println(e.getMessage());
				} catch(Exception e) {
					System.err.println(e.getMessage() + "from" + e.getClass());
				}
				return;
			}
			System.err.println("'time' takes 3 arguments.");
			break;
		}
		
		case "offline": {
			if (arguments.length == 1) {
				long stationID = 0;
				try {
				stationID = Long.parseLong(arguments[0]);
				}
				catch (NumberFormatException e) {System.err.println("'offline' takes the following "
						+ "types of argument :"+"\n"+"<long>");return;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				network.station(stationID).setOffline(true);
				} catch(NoSuchStationExistException e) {
					System.err.println("Station " + stationID + " does not exist");
				} catch(Exception e) {
					System.err.println("Unexpected error");
					System.err.println(e);
				}
				return;
			}
			if (arguments.length == 2) {
				long stationID = 0;
				int slotIndex = 0;
				try {
				stationID = Long.parseLong(arguments[0]);
				slotIndex = Integer.parseInt(arguments[1]);
				}
				catch (NumberFormatException e) {System.err.println("'offline' takes the following "
						+ "types of argument :"+"\n"+"<long> <int>");return;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				network.station(stationID).getParkingSlot(slotIndex).setOffline(true);
				} catch(NoSuchStationExistException e) {
					System.err.println("Station " + stationID + " does not exist");
				} catch(Exception e) {
					System.err.println("Unexpected error");
					System.err.println(e);
				}
				return;
			}
			System.err.println("'offline' takes 1 or 2 arguments.");
			break;
		}
		
		case "online": {
			if (arguments.length == 1) {
				long stationID = 0;
				try {
				stationID = Long.parseLong(arguments[0]);
				}
				catch (NumberFormatException e) {System.err.println("'online' takes the following "
						+ "types of argument :"+"\n"+"<long>");return;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				network.station(stationID).setOffline(false);
				} catch(NoSuchStationExistException e) {
					System.err.println("Station " + stationID + " does not exist");
				} catch(Exception e) {
					System.err.println("Unexpected error");
					System.err.println(e);
				}
				return;
			}
			if (arguments.length == 2) {
				long stationID = 0;
				int slotIndex = 0;
				try {
				stationID = Long.parseLong(arguments[0]);
				slotIndex = Integer.parseInt(arguments[1]);
				}
				catch (NumberFormatException e) {System.err.println("'online' takes the following "
						+ "types of argument :"+"\n"+"<long> <int>");return;}
				try {
					MyVelibNetwork network = MyVelibNetwork.getInstance();
					network.station(stationID).getParkingSlot(slotIndex).setOffline(false);
				} catch(NoSuchStationExistException e) {
					System.err.println("Station " + stationID + " does not exist");
				} catch(Exception e) {
					System.err.println("Unexpected error");
					System.err.println(e);
				}
				return;
			}
			System.err.println("'online' takes 1 or 2 arguments.");
			break;
		}
		
		case "rentBike": {
			if (arguments.length == 2) {
				long userID = 0;
				long stationID = 0;
				try {
				userID = Long.parseLong(arguments[0]);
				stationID = Long.parseLong(arguments[1]);
				}
				catch (NumberFormatException e) {System.err.println("'rentBike' takes the following "
						+ "types of argument :"+"\n"+"<long> <long>");return;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				network.user(userID).newRide(network.station(stationID));;
				} catch (NoSuchStationExistException n) {
					System.err.println("The station " + stationID + " does not exist");
				} catch (SuchStationIsOfflineException o) {
					System.err.println("The station " + stationID + " is offline");
				} catch(NoBicycleAvailableException b) {
					System.err.println("The station " + stationID + " does not have any bike");
				} catch(NoNewRideException r) {
					System.err.println("User " + userID + " is already on a ride");
				}
				catch(Exception e) {System.err.println(e);}
				return;
			}
			if (arguments.length == 3) {
				long userID = 0;
				long stationID = 0;
				String bicycleType = null;
				try {
				userID = Long.parseLong(arguments[0]);
				stationID = Long.parseLong(arguments[1]);
				bicycleType = parseString(arguments[2]);
				}
				catch (NumberFormatException e) {System.err.println("'rentBike' takes the following "
						+ "types of argument :"+"\n"+"<long> <long> '<String>'");return;}
				catch (ParseException e) {System.err.println("'rentBike' takes the following "
						+ "types of argument :"+"\n"+"<long> <long> '<String>'");return;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				network.user(userID).newRide(network.station(stationID),bicycleType);;
				} catch (NoSuchStationExistException n) {
					System.err.println("The station " + stationID + " does not exist");
				} catch (SuchStationIsOfflineException o) {
					System.err.println("The station " + stationID + " is offline");
				} catch(NoBicycleAvailableException b) {
					System.err.println("The station " + stationID + " does not have any bike of type " + bicycleType);
				} catch(NoNewRideException r) {
					System.err.println("User " + userID + " is already on a ride");
				}
				catch(Exception e) {System.err.println(e);}
				return;
			}
			System.err.println("'rentBike' takes 2 or 3 arguments.");
			break;
		}
		
		case "returnBike": {
			if (arguments.length == 2) {
				long userID = 0;
				long stationID = 0;
				try {
				userID = Long.parseLong(arguments[0]);
				stationID = Long.parseLong(arguments[1]);
				}
				catch (NumberFormatException e) {System.err.println("'returnBike' takes the following "
						+ "types of argument :"+"\n"+"<long> <long>");return;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				network.user(userID).endCurrentRide(network.station(stationID));;
				} catch (SuchStationIsFullException f) {
					System.err.println("The station " + stationID + " is full" );
				} catch (NoSuchStationExistException n) {
					System.err.println("The station " + stationID + " does not exist");
				} catch (SuchStationIsOfflineException o) {
					System.err.println("The station " + stationID + " is offline");
				}
				catch(Exception e) {System.err.println(e);}
				return;
			}
			System.err.println("'returnBike' takes 2 arguments.");
			break;
		}
		
		case "notifyUser": {
			if (arguments.length == 2) {
				long userID = 0;
				String message = null;
				try {
				userID = Long.parseLong(arguments[0]);
				message = parseString(arguments[1]);
				}
				catch (NumberFormatException e) {System.err.println("'notifyUser' takes the following "
						+ "types of argument :"+"\n"+"<long> '<String>'");return;}
				catch (ParseException e) {System.err.println("'notifyUser' takes the following "
						+ "types of argument :"+"\n"+"<long> '<String>'");return;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				network.user(userID).notifyUser(message);;
				}
				catch(Exception e) {System.err.println(e);}
				return;
			}
			System.err.println("'notifyUser' takes 2 arguments.");
			break;
		}
		
		case "locateUser": {
			if (arguments.length == 3) {
				long userID = 0;
				double latitude = 0;
				double longitude = 0;
				try {
				userID = Long.parseLong(arguments[0]);
				latitude = Double.parseDouble(arguments[1]);
				longitude = Double.parseDouble(arguments[2]);
				}
				catch (NumberFormatException e) {System.err.println("'locateUser' takes the following "
						+ "types of argument :"+"\n"+"<long> <double> <double>");return;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				network.user(userID).setLocalisation(new Localization(latitude,longitude));
				}
				catch(Exception e) {System.err.println(e);}
				return;
			}
			System.err.println("locateUser' takes 3 arguments.");
			break;
		}
		
		case "startStationGUI" : {
			if (arguments.length == 1) {
				long stationID = 0;
				try {
				stationID = Long.parseLong(arguments[0]);
				}
				catch (NumberFormatException e) {System.err.println("'startStationGUI' takes the following "
						+ "types of argument :"+"\n"+"<long>");return;}
				try {
					MyVelibNetwork network = MyVelibNetwork.getInstance();
					Station station = network.station(stationID);
					SwingUtilities.invokeLater (new Runnable (){public void run () {new StationGUI(network, station);}});
				} catch(NoSuchStationExistException e) {
					System.err.println("Station " + stationID + " does not exist");
				} catch(Exception e) {
					System.err.println("Unexpected error");
					e.printStackTrace();
				}
				return;
			}
			System.err.println("'startStationGUI' takes 1 argument.");
			break;
		}
		
		case "startUserGUI" : {
			if (arguments.length == 1) {
				long userID = 0;
				try {
					userID = Long.valueOf(arguments[0]);
				} catch (NumberFormatException e) {System.err.println("'startUserGui' takes the following "
						+ "types of argument :"+"\n"+"<long>");return;}
				try {
					MyVelibNetwork network = MyVelibNetwork.getInstance();
					User user = network.user(userID);
					SwingUtilities.invokeLater (new Runnable (){public void run () {new UserGUI(user);}});
				} catch(NoSuchUserExistException e) {
					System.err.println("User " + userID + " does not exist");
				} catch(Exception e) {
					System.err.println("Unexpected error");
					e.printStackTrace();
				}
				return;
			}
			System.err.println("'startUserGUI' takes 1 argument.");
			break;
		}
		
		case "displayStation": {
			if (arguments.length == 1) {
				long stationID = 0;
				try {
					stationID = Long.parseLong(arguments[0]);
				}
				catch (NumberFormatException e) {System.err.println("'displayStation' takes the following "
						+ "types of argument :"+"\n"+"<long>");return;}
				try {
					MyVelibNetwork network = MyVelibNetwork.getInstance();
					StationBalance.display(network.station(stationID));
				} catch(NoSuchStationExistException e) {
					System.err.println("Station " + stationID + " does not exist");
				} catch(Exception e) {
					System.err.println("Unexpected error");
					System.err.println(e);
				}
				return;
			}
			System.err.println("'displayStation' takes 1 argument.");
			break;
		}
		
		case "displayUser": {
			if (arguments.length == 1) {
				long userID = 0;
				try {
				userID = Long.parseLong(arguments[0]);
				}
				catch (NumberFormatException e) {System.err.println("'displayUser' takes the following "
						+ "types of argument :"+"\n"+"<long>");return;}
				try {
					MyVelibNetwork network = MyVelibNetwork.getInstance();
					UserBalance.display(network.user(userID));
				} catch(NoSuchUserExistException e) {
					System.err.println("User " + userID + " does not exist");
				} catch(Exception e) {
					System.err.println("Unexpected error");
					System.err.println(e);
				}
				return;
			}
			System.err.println("'displayUser' takes 1 argument.");
			break;
		}
		
		case "sortStation": {
			if (arguments.length == 1) {
				String sortPolicy = null;
				try {
				sortPolicy = parseString(arguments[0]);
				}
				catch(ParseException e) {System.err.println("'sortStation' takes the following "
						+ "types of argument :"+"\n"+"'<String>'");return;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				ComparatorFactory comparatorFactory = new ComparatorFactory();
				Comparator<Station> c = comparatorFactory.newComparator(sortPolicy);
				String rep = " == Stations sorted by "+sortPolicy+" == ";
				System.out.println(network+"\n"+rep+"\n"+network.sortStation(c));
				}
				catch(Exception e) {System.err.println(e);}
				return;
			}
			System.err.println("'sortStation' takes 1 argument.");
			break;
		}
		
		case "display": {
			if (arguments.length == 1) {
				String networkName = null;
				try {
				networkName = parseString(arguments[0]);
				}
				catch(ParseException e) {System.err.println("'display' takes the following "
						+ "types of argument :"+"\n"+"'<String>'");return;}
				try {
					MyVelibNetwork.switchNetwork(networkName);
					MyVelibNetwork network = MyVelibNetwork.getInstance();
					System.out.println(network.stationDatabaseState());
					System.out.println(network.userDatabaseRepresentation());
					network.visual2D();
				} catch(NoSuchNetworkExistException e) {
					System.err.println("Network " + networkName + " does not exist.");
					e.printStackTrace();
				} catch(Exception e) {
					System.err.println("Unexpected error");
					System.err.println(e);
				}
				return;
			}
			System.err.println("'display' takes 1 argument.");
			break;
		}
		
		case "runTest": {
			if (arguments.length == 1) {
				try {
					TextFileInterpreter.textFileInterpreter(arguments[0]);
				} catch (Exception e) {
					System.err.println("Failed to load or interprete the scenario <" + arguments[0] + ">");
				}
				return;
			}
			System.err.println("'runTest' takes 1 argument.");
			break;
		}
		
		case "help": {
			if (arguments.length == 0) {		
				System.err.println("\n"+"exit"+"\n"
						+"setup <velibnetworkName>"+"\n"
						+"setup <velibnetworkName> <nstations> <nslots> <radius> <nbikes>"+"\n"
						+"createBackup"+"\n"
						+"listBackup"+"\n"
						+"loadBackup <velibnetworkName>"+"\n"
						+"loadBackup <networkBackupFileName>"+"\n"
						+"addUser <userName> <cardType>"+"\n"
						+"addUser <userName> <password> <cardType>"+"\n"
						+"switch <velibnetworkName>"+"\n"
						+"time <hour> <min> <sec>"+"\n"
						+"date <year> <month> <day>"+"\n"
						+"offline <stationID>"+"\n"
						+"online <stationID>"+"\n"
						+"rentBike <userID> <stationID>"+"\n"
						+"rentBike <userID> <stationID> <bicycleType>"+"\n"
						+"returnBike <userID> <stationID>"+"\n"
						+"displayStation <stationID>"+"\n"
						+"displayUser <userID>"+"\n"
						+"sortStation <sortpolicy>"+"\n"
						+"display <velibnetworkName>"+"\n"
						+"runTest <testScenarioN.txt>"+"\n"
						+"startStationGUI <stationID>"+"\n"
						+"startUserGUI <userID>"+"\n");
				return;
			}
			System.err.println("'help' takes no argument.");
			break;
		}
		default: System.err.println("Invalid command. Type 'help' for a list of commands.");
		}

		
	}
}
