package fr.ecp.IS1220.beribu;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import sun.applet.Main;

public class Panneau extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Localization> locs;

	public Panneau(ArrayList<Localization> locs) {
		super();
		this.locs=locs;
	}

	public void paint(Graphics g) {
		g.drawRect(50, 50, 500, 500);
		int i=0;
		for (Localization loc:locs) {
			g.fillOval(50+(int)(loc.getLongitude()), 550-(int)(loc.getLatitude()), 5, 5);
			g.drawString(""+i,50+(int)(loc.getLongitude()), 550-(int)(loc.getLatitude()));
			i++;
		}
	}

}
