package fr.ecp.IS1220.myVelib.client;

import java.util.Scanner;

import fr.ecp.IS1220.myVelib.core.User;

public class CommandLineInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);

		String line;

		System.out.print("Entrez une commande : ");
		line = scan.next();

		if (line.equalsIgnoreCase("User"))
			User u1 = new User("Jean-Marc");
		System.out.println(u1);
		System.out.println(line);
	}

}
