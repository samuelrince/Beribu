package fr.ecp.IS1220.myVelib.client;

import fr.ecp.IS1220.myVelib.core.*;

public class CommandLineInterpreter {

	public static void interprete(String[] tokens) {
		String command = tokens[0];
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
		}

		
	}
}
