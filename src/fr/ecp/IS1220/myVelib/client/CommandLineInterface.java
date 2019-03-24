package fr.ecp.IS1220.myVelib.client;

import java.util.Scanner;
import fr.ecp.IS1220.myVelib.core.*;

public class CommandLineInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 03, 24);
		SD.setTime(15, 45, 0);
		
		Scanner scan = new Scanner(System.in);
		String line;
		// defining the words delimiters for splitting fileContent into words
	    String delims = "[<>.,?!]+";
	   

		System.out.print("Entrez une commande : ");
		line = scan.next();
		String[] tokens = line.split(delims);
		System.out.println();
		for (String word : tokens) {
			if (word.equalsIgnoreCase("user")) {
				User u1 = new User(tokens[1]);
				System.out.println(u1);
			}
				
		}
		System.out.println(line);
	}

}
