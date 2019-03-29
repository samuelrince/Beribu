package fr.ecp.IS1220.myVelib.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeNetwork {
	public static void main(String[] args) {
		MyVelibNetwork network = null;
		
		try {
			FileInputStream fileIn = new FileInputStream("network_save_state/Paris_2019-2-17_10-56-3.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			network = (MyVelibNetwork) in.readObject();
			in.close();
			fileIn.close();
		} catch(IOException i) {
			i.printStackTrace();
		} catch(ClassNotFoundException c) {
			System.err.println("MyVelibNetwork class not found");
			c.printStackTrace();
		}
		
	
	}
}
