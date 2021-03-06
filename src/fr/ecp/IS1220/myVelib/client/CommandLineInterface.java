package fr.ecp.IS1220.myVelib.client;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Scanner;
import fr.ecp.IS1220.myVelib.core.*;
import fr.ecp.IS1220.myVelib.core.system.CommandLineInterpreter;
import fr.ecp.IS1220.myVelib.core.system.NetworkBackup;
import fr.ecp.IS1220.myVelib.core.system.SystemDate;
import fr.ecp.IS1220.myVelib.core.system.TextFileInterpreter;

/**
 * This <code>CommandLineInterface</code> class handles the CLUI feature.
 * You have to launch this class to access the Command Line User Interface.
 * Look at the documentation part in the report to find more details on 
 * available commands.
 */
public class CommandLineInterface {
	private static boolean exit = false;
	
	public static void setExit(boolean exit) {
		CommandLineInterface.exit = exit;
	}
	
	public static boolean check(String[] tokens) {
		for (String word : tokens) {
			char[] chars = word.toCharArray();
			if (chars.length == 0) {
				System.err.println("Invalid space. Put a space between each word only.");
				return false;
			}
			if ((chars.length == 2 && chars[0]==39 && chars[1]==39)) {
				System.err.println("Empty String argument detected.");
				return false;
			}
			for(int i = 0; i < chars.length; i++){
				char c = chars[i];
				if(!(c>=65 && c<=90)&&!(c>=97 && c<=122) && !(c>=48 && c<=57) && !(c==39) && !(c==46)
						&& !(c==45) && !(c==95)) {
					System.err.println("Invalid characters. Only letters of the "
							+ "alphabet, numbers, ., -, _, and string delimitors ' are accepted.");
					return false;
				}
				if ((c==39 && !(i==0 || i==chars.length-1))
						||(c==39 && chars.length==1)) {
					System.err.println("Invalid use of '.Use ' to delimitate String arguments."
							+"\n"+"Put a space between each argument and no space inside ' '.");
					return false;
				}				
			}
		}
		return true;
	}
	
	public static String[] reconstructString(String[] tokens) {
		ArrayList<String> reconstructedTokens = new ArrayList<String>();
		String word;
		boolean reading = false;
		String reconstructedString = "";
		int startIndex = 0;
		for (int i = 0; i < tokens.length; i++) {
			word = tokens[i];
			char[] chars = word.toCharArray();
			
			if (chars[0] == 39) {
				if (reading)
					throw new RuntimeException();
				reconstructedString = tokens[i];
				reading = true;
				startIndex = i;
			}			
			if (chars[chars.length-1] == 39) {
				if (!reading)
					throw new RuntimeException();
				for (int j = startIndex+1; j <= i; j++)
					reconstructedString +=" "+tokens[j];
				reconstructedTokens.add(reconstructedString);
				reading = false;
			}
			else
				if (!reading)
					reconstructedTokens.add(tokens[i]);
			
		}
		if (reading)
			throw new RuntimeException();
		return reconstructedTokens.toArray(new String[reconstructedTokens.size()]);
	}
	
	public static void main(String[] args) {
		// Initialization
		SystemDate SD 	 = SystemDate.getInstance();
		TextFileInterpreter.textFileInterpreter("my_velib.ini");
		System.err.println("The 'Default' network has been loaded.");
		try {
			NetworkBackup.scanBackup();
		} catch(Exception e) {
			System.err.println("No backup file loaded");
		}	
		
		System.out.println("\n"+"Welcome to the MyVelib command line user interface."+"\n"+"Type 'help'"
				+ " for a list of commands, or start entering your command lines now!");

		Scanner scan = new Scanner(System.in);
		String line;
		// defining the words delimiters for splitting fileContent into words
	    String delims = " ";
		
	    do {
	    	System.out.print(">");
	    	line = scan.nextLine();
	    	
	    	String[] tokens = line.split(delims);
	    	if (!check(tokens))
	    		continue;
	    	try {
	    		tokens = reconstructString(tokens);
	    	}
	    	catch(RuntimeException e) {
	    		System.err.println("Invalid use of '.Use ' to delimitate String arguments."
	    				+"\n"+"Put a space between each argument and no space inside ' '.");
	    		continue;
	    	}
	    	/*
	    	for (int i = 0; i <tokens.length; i++)
	    		System.out.println(tokens[i]);
	    	 */
	    	
	    	CommandLineInterpreter.interprete(tokens);
	    }while(!CommandLineInterface.exit);
	    Frame[] frames = Frame.getFrames();
	    for (Frame frame:frames)
	    	frame.dispose();
	    System.err.println("CLUI terminated.");
	}
	
}
