package fr.ecp.IS1220.myVelib.client;

import java.util.Arrays;

import fr.ecp.IS1220.myVelib.core.*;

public class CommandLineInterpreter {

	public static void interprete(String[] tokens) {
		String command = tokens[0];
		String arguments[] = Arrays.copyOfRange(tokens, 1, tokens.length);
		switch (command) {
		case "exit": {
			if (tokens.length == 1)
				CommandLineInterface.setExit(true);
			else
				System.err.println("'exit' does not take any argument.");
			break;
		}
		case "setup": {
			
			break;
		}
		
		case "addUser": {
			User u1 = new User(tokens[1]);
			System.out.println(u1);
			break;
		}

		case "switch": {

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
