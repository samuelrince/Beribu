package fr.ecp.IS1220.myVelib.client;

import java.util.Arrays;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import fr.ecp.IS1220.myVelib.core.*;
import fr.ecp.IS1220.myVelib.core.exception.BadDateException;

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
				String arg0 = null;
				try {
				arg0 = parseString(arguments[0]);
				}
				catch(ParseException e) {System.err.println("'setup' takes the following "
						+ "types of argument :"+"\n"+"'<String>'");break;}
				try {
					MyVelibNetwork network = new MyVelibNetwork(arg0);
					network.createStations(new RandomLocInSquare(), new Localization(0,0)
							, 4, 10, 3, 10, 75, new double[] {70,30});
				}
				catch(Exception e) {System.err.println(e);}
			}
			else if (arguments.length == 5) {

			}
			else
				System.err.println("'setup' takes 1 or 6 arguments.");
			break;
		}
		
		case "addUser": {
			if (arguments.length == 2) {

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
				SystemDate SD = SystemDate.getInstance();
				try {
					Integer year = Integer.valueOf(arguments[0]);
					Integer month = Integer.valueOf(arguments[1]);
					Integer day = Integer.valueOf(arguments[2]);
					SD.setDay(year, month, day);
				} catch(BadDateException e) {
					System.err.println(e.getMessage());
				} catch(Exception e) {
					System.err.println(e.getMessage() + "from" + e.getClass());
				}
			}
			else
				System.err.println("'date' takes 3 arguments.");
			break;
		}
		case "time": {
			if (arguments.length == 3) {
				SystemDate SD = SystemDate.getInstance();
				try {
					Integer hour = Integer.valueOf(arguments[0]);
					Integer minute = Integer.valueOf(arguments[1]);
					Integer second = Integer.valueOf(arguments[2]);
					SD.setTime(hour, minute, second);	
				} catch(BadDateException e) {
					System.err.println(e.getMessage());
				} catch(Exception e) {
					System.err.println(e.getMessage() + "from" + e.getClass());
				}
			}
			else
				System.err.println("'time' takes 3 arguments.");
			break;
		}
		
		case "offline": {
			if (arguments.length == 1) {

			}
			if (arguments.length == 2) {

			}
			else
				System.err.println("'offline' takes 1 or 2 arguments.");
			break;
		}
		
		case "online": {
			if (arguments.length == 1) {

			}
			if (arguments.length == 2) {

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

			}
			else
				System.err.println("'displayStation' takes 1 argument.");
			break;
		}
		
		case "displayUser": {
			if (arguments.length == 1) {

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

			}
			else
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
