package fr.ecp.IS1220.myVelib.client;

import fr.ecp.IS1220.myVelib.core.*;

public class CommandLineInterpreter {

	public static void interprete(String[] tokens) {
		if (tokens.length == 1 && tokens[0].equals("exit"))
			CommandLineInterface.setExit(true);
		for (String word : tokens) {
    		if (word.equalsIgnoreCase("user")) {
    			User u1 = new User(tokens[1]);
    			System.out.println(u1);
    			break;
    		}	
    	}
	}
}
