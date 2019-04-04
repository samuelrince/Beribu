package fr.ecp.IS1220.myVelib.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import fr.ecp.IS1220.myVelib.client.CommandLineInterface;

public class TextFileInterpreter {
	
	public static String fileName;
	
	/**
	 * This static method reads a (scenario) text file and use 
	 * <code>CommandLineInterpreter</code> to interpret each line.
	 * @param fileName	the file to load
	 */
	public static void textFileInterpreter(String fileName) {
		TextFileInterpreter.fileName = fileName;
		FileReader file = null;
		BufferedReader reader = null;
		try {
			// open input stream pointing at fileName
			file = new FileReader(fileName);
		  
			// open input buffered reader to read file line by line
			reader = new BufferedReader(file);
			String line = "";
		  
			// reading input file line by line
			while ((line = reader.readLine()) != null) {
				TextFileInterpreter.lineInterpretor(line);
			}
		} catch (Exception e) {
		    throw new RuntimeException(e);
		} finally {
			if (file != null) {
				try {
					file.close();
					reader.close();
				} catch (IOException e) {
					System.out.println("File not found: " + fileName);
					// Ignore issues during closing 
				}
			}
		}
		System.out.println("Test Scenario <" + TextFileInterpreter.fileName + "> is correctly loaded");
	}
	
	private static void lineInterpretor(String line) {
		String delims = " ";
		String[] tokens = line.split(delims);
		if (!CommandLineInterface.check(tokens))
    		throw new RuntimeException("Invalid command detected in " + TextFileInterpreter.fileName);
    	try {
    		tokens = CommandLineInterface.reconstructString(tokens);
    	} catch(RuntimeException e) {
    		System.err.println("Invalid use of '.Use ' to delimitate String arguments."
    				+"\n"+"Put a space between each argument and no space inside ' '.");
    		throw new RuntimeException("Invalid command detected in " + TextFileInterpreter.fileName);
    	}
    	CommandLineInterpreter.interprete(tokens);
	}
	
	public static void main(String[] args) {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 03, 24);
		SD.setTime(15, 45, 0);
		textFileInterpreter("testScenario1.txt");
	}
}
