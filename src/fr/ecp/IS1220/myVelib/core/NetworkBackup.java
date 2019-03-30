package fr.ecp.IS1220.myVelib.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import fr.ecp.IS1220.myVelib.core.exception.NoSuchBackupExistException;
import fr.ecp.IS1220.myVelib.core.exception.NoSuchNetworkExistException;

/**
 * This class handles the network backup system.
 * It provides Serialization to store a network state in a file.
 * It also provides load methods that implements Deserialization to 
 * read backup files and restore a MyVelibNetwork Object.
 * @author Samuel
 *
 */
public class NetworkBackup {
	private static ArrayList<File> backupDatabase = new ArrayList<File>();
	private static final String directory = "network_save/"; 
	
	/**
	 * This method is used to create a backup file (using serialization) of the
	 * current state of a network.
	 * @param network	the network to save
	 * @throws Exception	occurs when file creation failed.
	 */
	public static void saveNetworkState(MyVelibNetwork network) throws Exception {
		SystemDate SD = SystemDate.getInstance();
		File file = new File(network);
		try {
			FileOutputStream fileOut = new FileOutputStream(directory + file.getFileName());
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(network);
			out.close();
			fileOut.close();
			System.out.println("Network state has been saved in " + directory + file.getFileName());
		} catch(IOException i) {
			throw i;
		}
		backupDatabase.add(file);
	}
	
	/**
	 * This method is used to load a network stored in the database
	 * @param fileName	the backup file name
	 * @return		a MyVelibNetwork that corresponds to the backup expected
	 * @throws IOException	occurs when the file cannot be loaded
	 * @throws ClassNotFoundException 	occurs when the class MyVelibNetwork is not found
	 * @throws NoSuchBackupExistException	occurs when the backup file wanted does not exist
	 */
	public static MyVelibNetwork loadNetworkState(String fileName) throws Exception {
		MyVelibNetwork network = null;
		File file = getFile(fileName);
		SystemDate.delInstance();
		SystemDate SD = SystemDate.getInstance();
		try {
			FileInputStream fileIn = new FileInputStream(directory + fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			network = (MyVelibNetwork) in.readObject();
			in.close();
			fileIn.close();
		} catch(IOException i) {
			throw i;
		} catch(ClassNotFoundException c) {
			System.err.println("MyVelibNetwork class not found");
			throw c;
		}
		SD.setDay(file.getDateOfCreation().getYear(), file.getDateOfCreation().getMonth(), file.getDateOfCreation().getDay()); 
		SD.setTime(file.getDateOfCreation().getHour(), file.getDateOfCreation().getMinute(), file.getDateOfCreation().getSecond());
		System.err.println("Backup loaded");
		return network;
	}
	
	public static MyVelibNetwork loadBackup(String name) throws Exception {
		for(File file: backupDatabase) {
			if (name.equals(file.getFileName())) {
				return loadNetworkState(name);
			} else if (name.equals(file.getNeworkName())) {
				return loadNetworkState(file.fileName);
			}
		}
		throw new NoSuchNetworkExistException("The network or filename " + name + " does not exist in backup database");
	}
	
	public static void display() {
		if (backupDatabase.size() == 0)
			System.out.println("No backup");
		System.out.println("List of available backups :");
		for (File file: backupDatabase) {
			System.out.println("- " + file.fileName + " name: " + file.networkName + " date: " + file.dateOfCreation);
		}
	}
	
	private static File getFile(String fileName) throws NoSuchBackupExistException {
		for (File file: backupDatabase) {
			if (fileName.equals(file.getFileName())) {
				return file;
			}
		}
		throw new NoSuchBackupExistException("This file does not exist");
	}
	
	/**
	 * A nested class that handles file storage system.
	 * A file is represented by a file name, a network name and a date of creation.
	 * @author Samuel
	 *
	 */
	static class File {
		private String fileName;
		private String networkName;
		private Date dateOfCreation;
		
		public File(MyVelibNetwork network) {
			super();
			SystemDate SD = SystemDate.getInstance();
			this.dateOfCreation = new Date();
			this.networkName = network.getName();
			this.fileName = "Network_" + network.getName() + "_" + 
							dateOfCreation.getYear() + "-" +
							dateOfCreation.getMonth() + "-" +
							dateOfCreation.getDay() + "_" +
							dateOfCreation.getHour() + "-" +
							dateOfCreation.getMinute() + "-" +
							dateOfCreation.getSecond() + ".ser";
		}

		public String getFileName() {
			return fileName;
		}

		public Date getDateOfCreation() {
			return dateOfCreation;
		}
		
		public String getNeworkName() {
			return networkName;
		}
	}
}
