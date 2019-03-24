package fr.ecp.IS1220.myVelib.client;

import java.util.Scanner;
import fr.ecp.IS1220.myVelib.core.*;

public class CommandLineInterface {
	private static boolean exit = false;
	
	public static void setExit(boolean exit) {
		CommandLineInterface.exit = exit;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 03, 24);
		SD.setTime(15, 45, 0);
		
		Scanner scan = new Scanner(System.in);
		String line;
		// defining the words delimiters for splitting fileContent into words
	    String delims = "[ <>.,?!]+";
	   
	    do {

	    	System.out.print("Entrez une commande : ");
	    	line = scan.nextLine();
	    	System.out.println("Vous avez entré : "+line);
	    	String[] tokens = line.split(delims);	    	
	    	CommandLineInterpreter.interprete(tokens);
	    }while(!CommandLineInterface.exit);
	}
	
}
