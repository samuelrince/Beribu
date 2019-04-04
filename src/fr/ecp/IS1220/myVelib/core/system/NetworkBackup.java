package fr.ecp.IS1220.myVelib.core.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private static ArrayList<BackupFile> backupDatabase = new ArrayList<BackupFile>();
	private static final String directory = "network_save/"; 
	
	/**
	 * This method is used to create a backup file (using serialization) of the
	 * current state of a network.
	 * @param network	the network to save
	 * @throws Exception	occurs when file creation failed.
	 */
	public static void saveNetworkState(MyVelibNetwork network) throws Exception {
		SystemDate SD = SystemDate.getInstance();
		BackupFile file = new BackupFile(network);
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
		BackupFile file = getFile(fileName);
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
		for(BackupFile file: backupDatabase) {
			System.out.println(file.getFileName());
			if (name.equals(file.getFileName())) {
				return loadNetworkState(name);
			} else if (name.equals(file.getNeworkName())) {
				return loadNetworkState(file.fileName);
			}
		}
		throw new NoSuchNetworkExistException("The network or filename " + name + " does not exist in backup database");
	}
	
	public static void display() {
		if (backupDatabase.size() == 0) {
			System.out.println("No backup");
			return;
		}
		System.out.println("List of available backups :");
		for (BackupFile file: backupDatabase) {
			System.out.println("- Network: " + file.networkName + " date: " + file.dateOfCreation.toString());
		}
	}
	
	private static BackupFile getFile(String fileName) throws NoSuchBackupExistException {
		for (BackupFile file: backupDatabase) {
			if (fileName.equals(file.getFileName())) {
				return file;
			}
		}
		throw new NoSuchBackupExistException("This file does not exist");
	}
	
	private static void scanDirectory(File dir) throws IOException {
	    File[] files = dir.listFiles();
	    for (File file : files) {
	        System.out.println(file.getName());
	        if (file.listFiles() != null)
	            scanDirectory(file);        
	    } 
	} 
	
	public static void scanBackup() {
		File dir = new File(directory);
		File[] files = dir.listFiles();
		for (File file: files) {
			BackupFile bf = new BackupFile(file.getName());
			backupDatabase.add(bf);
		}
	}
	
	
	
	/**
	 * A nested class that handles file storage system.
	 * A file is represented by a file name, a network name and a date of creation.
	 * @author Samuel
	 *
	 */
	static class BackupFile {
		private String fileName;
		private String networkName;
		private Date dateOfCreation;
		
		public BackupFile(MyVelibNetwork network) {
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
		
		public BackupFile(String fileName) {
			super();
			SystemDate SD = SystemDate.getInstance();
			
			Pattern p = Pattern.compile("Network_([\\w]+)_(\\d{4})-(\\d{1,2})-(\\d{1,2})_(\\d{1,2})-(\\d{1,2})-(\\d{1,2}).ser");
			Matcher m = p.matcher(fileName);
			m.matches();
			
			this.dateOfCreation = new Date(Integer.valueOf(m.group(2)), Integer.valueOf(m.group(3)), Integer.valueOf(m.group(4)), 
					Integer.valueOf(m.group(5)), Integer.valueOf(m.group(6)), Integer.valueOf(m.group(7)));
			this.networkName = m.group(1);
			this.fileName = m.group(0);
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
