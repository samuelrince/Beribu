package fr.ecp.IS1220.myVelib.client;

import java.util.Arrays;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import fr.ecp.IS1220.myVelib.core.*;

public class CommandLineInterpreter {

	public static String parseString(String word) throws ParseException {
		
		char[] chars = word.toCharArray();
		if (chars[0]==39 && chars[chars.length-1]==39) {
			char[] stringContent = Arrays.copyOfRange(chars,1,chars.length-1);
			return new String(stringContent);
		} 
		throw new ParseException();
	}
	
	public static void interprete(String[] tokens) {
		String command = tokens[0];
		String arguments[] = Arrays.copyOfRange(tokens, 1, tokens.length);
		switch (command) {
		case "exit": {
			if (arguments.length == 0)
				CommandLineInterface.setExit(true);
			else
				System.err.println("'exit' takes no argument.");
			break;
		}
		case "setup": {
			if (arguments.length == 1) {
				String networkName = null;
				try {
				networkName = parseString(arguments[0]);
				}
				catch(ParseException e) {System.err.println("'setup' takes the following "
					+ "types of argument :"+"\n"+"'<String>'");break;}
				try {
				MyVelibNetwork network = new MyVelibNetwork(networkName);
				network.createStations(new RandomLocInSquare(), new Localization(0,0)
						, 4, 10, 3, 10, 75, new double[] {70,30});
				}
				catch(Exception e) {System.err.println(e);}
			}
			if (arguments.length == 5) {

			}
			else
				System.err.println("'setup' takes 1 or 6 arguments.");
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
					+ "types of argument :"+"\n"+"'<String>' '<String>'");break;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				network.newSubscriber(name,subType);
				}
				catch(Exception e) {System.err.println(e);}
			}
			else
				System.err.println("'addUser' takes 2 arguments.");
			break;
		}

		case "switch": {
			if (arguments.length == 1) {
				String arg0 = null;
				try {
				arg0 = parseString(arguments[0]);
				}
				catch(ParseException e) {System.err.println("'switch' takes the following "
					+ "types of argument :"+"\n"+"'<String>'");break;}
				try {
				MyVelibNetwork.switchNetwork(arg0);
				}
				catch(Exception e) {System.err.println(e);}
			}
			else
				System.err.println("'switch' takes 1 argument.");

			break;
		}

		case "date": {
			if (arguments.length == 3) {
				int year;
				int month;
				int day;
				try {
				year = Integer.parseInt(arguments[0]);
				month = Integer.parseInt(arguments[1]);
				day = Integer.parseInt(arguments[2]);
				}
				catch (NumberFormatException e) {System.err.println("'date' takes the following "
					+ "types of argument :"+"\n"+"<int> <int> <int>");break;}
				try {
				SystemDate SD = SystemDate.getInstance();
				SD.setDay(year, month, day);
				}
				catch(Exception e) {System.err.println(e);}
			}
			else
				System.err.println("'date' takes 3 arguments.");
			break;
		}
		case "time": {
			if (arguments.length == 3) {
				int hour;
				int min;
				int sec;
				try {
				hour = Integer.parseInt(arguments[0]);
				min = Integer.parseInt(arguments[1]);
				sec = Integer.parseInt(arguments[2]);
				}
				catch (NumberFormatException e) {System.err.println("'time' takes the following "
					+ "types of argument :"+"\n"+"<int> <int> <int>");break;}
				try {
				SystemDate SD = SystemDate.getInstance();
				SD.setTime(hour, min, sec);
				}
				catch(Exception e) {System.err.println(e);}
			}
			else
				System.err.println("'time' takes 3 arguments.");
			break;
		}
		
		case "offline": {
			if (arguments.length == 1) {
				long stationID;
				try {
				stationID = Long.parseLong(arguments[0]);
				}
				catch (NumberFormatException e) {System.err.println("'offline' takes the following "
						+ "types of argument :"+"\n"+"<long>");break;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				network.station(stationID).setOffline(true);
				}
				catch(Exception e) {System.err.println(e);}
			}
			if (arguments.length == 2) {
				long stationID;
				int slotIndex;
				try {
				stationID = Long.parseLong(arguments[0]);
				slotIndex = Integer.parseInt(arguments[1]);
				}
				catch (NumberFormatException e) {System.err.println("'offline' takes the following "
						+ "types of argument :"+"\n"+"<long> <int>");break;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				network.station(stationID).getParkingSlot(slotIndex).setOffline(true);
				}
				catch(Exception e) {System.err.println(e);}
			}
			else
				System.err.println("'offline' takes 1 or 2 arguments.");
			break;
		}
		
		case "online": {
			if (arguments.length == 1) {
				long stationID;
				try {
				stationID = Long.parseLong(arguments[0]);
				}
				catch (NumberFormatException e) {System.err.println("'online' takes the following "
						+ "types of argument :"+"\n"+"<long>");break;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				network.station(stationID).setOffline(false);
				}
				catch(Exception e) {System.err.println(e);}
			}
			if (arguments.length == 2) {
				long stationID;
				int slotIndex;
				try {
				stationID = Long.parseLong(arguments[0]);
				slotIndex = Integer.parseInt(arguments[1]);
				}
				catch (NumberFormatException e) {System.err.println("'online' takes the following "
						+ "types of argument :"+"\n"+"<long> <int>");break;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				network.station(stationID).getParkingSlot(slotIndex).setOffline(false);
				}
				catch(Exception e) {System.err.println(e);}
			}
			else
				System.err.println("'online' takes 1 or 2 arguments.");
			break;
		}
		
		case "rentBike": {
			if (arguments.length == 2) {

			}
			if (arguments.length == 3) {

			}
			else
				System.err.println("'rentBike' takes 2 or 3 arguments.");
			break;
		}
		
		case "returnBike": {
			if (arguments.length == 2) {

			}
			else
				System.err.println("'returnBike' takes 2 arguments.");
			break;
		}
		
		case "displayStation": {
			if (arguments.length == 1) {
				long stationID;
				try {
				stationID = Long.parseLong(arguments[0]);
				}
				catch (NumberFormatException e) {System.err.println("'displayStation' takes the following "
						+ "types of argument :"+"\n"+"<long>");break;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				StationBalance.display(network.station(stationID));
				}
				catch(Exception e) {System.err.println(e);}
			}
			else
				System.err.println("'displayStation' takes 1 argument.");
			break;
		}
		
		case "displayUser": {
			if (arguments.length == 1) {
				long userID;
				try {
				userID = Long.parseLong(arguments[0]);
				}
				catch (NumberFormatException e) {System.err.println("'displayUser' takes the following "
						+ "types of argument :"+"\n"+"<long>");break;}
				try {
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				UserBalance.display(network.user(userID));
				}
				catch(Exception e) {System.err.println(e);}
			}
			else
				System.err.println("'displayUser' takes 1 argument.");
			break;
		}
		
		case "sortStation": {
			if (arguments.length == 1) {
				
			}
			else
				System.err.println("'sortStation' takes 1 argument.");
			break;
		}
		
		case "display": {
			if (arguments.length == 1) {
				String networkName;
				try {
				networkName = parseString(arguments[0]);
				}
				catch(ParseException e) {System.err.println("'display' takes the following "
						+ "types of argument :"+"\n"+"'<String>'");break;}
				try {
				MyVelibNetwork.switchNetwork(networkName);
				MyVelibNetwork network = MyVelibNetwork.getInstance();
				System.out.println(network.stationDatabaseState());
				System.out.println(network.userDatabaseRepresentation());
				}
				catch(Exception e) {System.err.println(e);}
			}
			else
				System.err.println("'display' takes 1 argument.");
			break;
		}
		
		case "runTest": {
			if (arguments.length == 1) {

			}
			else
				System.err.println("'runTest' takes 1 argument.");
			break;
		}
		
		case "help": {
			if (arguments.length == 0)			
			System.err.println("\n"+"exit"+"\n"+"setup <velibnetworkName>"+"\n"
				+"setup <velibnetworkName> <nstations> <nslots> <radius> <nbikes>"+"\n"
				+"addUser <userName> <cardType>"+"\n"+"switch <velibnetworkName>"+"\n"
				+"time <hour> <min> <sec>"+"\n"+"date <year> <month> <day>"+"\n"+"offline <stationID>"+"\n"
				+"online <stationID>"+"\n"+"rentBike <userID> <stationID>"+"\n"
				+"returnBike <userID> <stationID>"+"\n"+"displayStation <stationID>"+"\n"
				+"displayUser <userID>"+"\n"+"sortStation <sortpolicy>"+"\n"
				+"display <velibnetworkName>"+"\n"+"runTest <testScenarioN.txt>");
			else
				System.err.println("'help' takes no argument.");
			break;
		}
		default: System.err.println("Invalid command. Type 'help' for a list of commands.");
		}

		
	}
}
