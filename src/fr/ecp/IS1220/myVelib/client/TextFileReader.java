package fr.ecp.IS1220.myVelib.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader {
	public static void readTextFile(String fileName) {
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
				  System.out.println("Line: " + line);
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
	} 
	
	public static void main(String[] args) {
		readTextFile("testScenario1.txt");
	}
}
