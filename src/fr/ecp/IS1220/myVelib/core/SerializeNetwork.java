package fr.ecp.IS1220.myVelib.core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeNetwork {
	
	public static void saveNetworkSate(MyVelibNetwork network) {
		SystemDate SD = SystemDate.getInstance();
		try {
			String fileName = network.getName() + "_" + SD.getYear() + "-" + SD.getMonth() + "-" + SD.getDay() 
								+ "_" + SD.getHour() + "-" + SD.getMinute() + "-" + SD.getSecond();
			FileOutputStream fileOut = new FileOutputStream("network_save_state/" + fileName + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(network);
			out.close();
			fileOut.close();
			System.out.println("Network state has been saved in network_save_state/" + fileName + ".ser");
		} catch(IOException i) {
			i.printStackTrace();
		}
	}
}
