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
				System.err.println("'exit' does not take any argument.");
			break;
		}
		case "setup": {
			if (arguments.length == 1) {
				
			}
			if (arguments.length == 5) {

			}
			else
				System.err.println("'setup' takes 1 or 5 arguments.");
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
				catch(IllegalArgumentException e) {System.err.println(e);}
			}
			else
				System.err.println("'switch' takes 1 argument.");

			break;
		}

		case "date": {

			break;
		}
		case "time": {

			break;
		}
		
		case "offline": {

			break;
		}
		
		case "rentBike": {

			break;
		}
		
		case "returnBike": {

			break;
		}
		
		case "displayStation": {

			break;
		}
		
		case "displayUser": {

			break;
		}
		
		case "sortStation": {

			break;
		}
		
		case "display": {

			break;
		}
		
		case "runTest": {

			break;
		}
		
		case "help": {
			System.err.println("\n"+"exit"+"\n"+"setup <velibnetworkName>"+"\n"
		+"setup <velibnetworkName> <nstations> <nslots> <radius> <nbikes>"+"\n"
		+"addUser <userName> <cardType>"+"\n"+"switch <velibnetworkName>"+"\n"
		+"time <hour> <min> <sec>"+"\n"+"date <year> <month> <day>"+"\n"+"offline <stationID>"+"\n"
		+"online <stationID>"+"\n"+"rentBike <userID> <stationID>"+"\n"
		+"returnBike <userID> <stationID>"+"\n"+"displayStation <stationID>"+"\n"
		+"displayUser <userID>"+"\n"+"sortStation <sortpolicy>"+"\n"
		+"display <velibnetworkName>"+"\n"+"runTest <testScenarioN.txt>");
			break;
		}
		default: System.err.println("Invalid command. Type 'help' for a list of commands.");
		}

		
	}
}
