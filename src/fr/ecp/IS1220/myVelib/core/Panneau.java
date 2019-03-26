package fr.ecp.IS1220.myVelib.core;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import sun.applet.Main;

public class Panneau extends JPanel{
	private static final long serialVersionUID = 1L;
	private ArrayList<Localization> locs;
	private ArrayList<String> labels;

	public Panneau(ArrayList<Localization> locs, ArrayList<String> labels) {
		super();
		if (locs.size() != labels.size())
			throw new IllegalArgumentException("Localizations and labels sizes don't match.");
		this.locs = locs;
		this.labels = labels;
	}

	public void paint(Graphics g) {
		g.drawRect(50, 50, 600, 600);
		Localization barycenter = Localization.barycenter(this.locs);
		double[] xSides = new double[] {0,0};
		double[] ySides = new double[] {0,0};
		for (Localization loc:this.locs) {
			if (loc.getLatitude()-barycenter.getLatitude()>=ySides[1])
				ySides[1] = loc.getLatitude()-barycenter.getLatitude();
			if (barycenter.getLatitude()-loc.getLatitude()>=ySides[0])
				ySides[0] = barycenter.getLatitude()-loc.getLatitude();
			if (loc.getLongitude()-barycenter.getLongitude()>=xSides[1])
				xSides[1] = loc.getLongitude()-barycenter.getLongitude();
			if (barycenter.getLongitude()-loc.getLongitude()>=xSides[0])
				xSides[0] = barycenter.getLongitude()-loc.getLongitude();
		}
		double xRatio;
		if (xSides[1]+xSides[0] == 0)
			xRatio = 1;
		else
			xRatio = 500/(xSides[1] + xSides[0]);
		double yRatio;
		if (ySides[1]+ySides[0] == 0)
			yRatio = 1;
		else
			yRatio = 500/(ySides[1] + ySides[0]);
		int xPixel_bary = (int) (350 + xRatio*(xSides[1]-xSides[0])/2);
		int yPixel_bary = (int) (350 - yRatio*(ySides[1]-ySides[0])/2);
		for (int i = 0; i < locs.size(); i++) {
			Localization loc = locs.get(i);
			int xPixel = (int) (xPixel_bary + xRatio*(loc.getLongitude()-barycenter.getLongitude()));
			int yPixel = (int) (yPixel_bary - yRatio*(loc.getLatitude()-barycenter.getLatitude()));
			g.fillOval(xPixel, yPixel, 5, 5);
			g.drawString(this.labels.get(i),xPixel,yPixel);
		}
		Localization xmin = new Localization(barycenter.getLatitude(),barycenter.getLongitude()-xSides[0]);
		Localization xmax = new Localization(barycenter.getLatitude(),barycenter.getLongitude()+xSides[1]);
		Localization ymin = new Localization(barycenter.getLatitude()-ySides[0],barycenter.getLongitude());
		Localization ymax = new Localization(barycenter.getLatitude()+ySides[1],barycenter.getLongitude());
		g.drawString("y="+Math.round(1000*xmin.distanceTo(xmax))/1000.+"km",20,50);
		g.drawString("x="+Math.round(1000*ymin.distanceTo(ymax))/1000.+"km",620,660);
		g.drawString("x=0,y=0",20,660);
	}

}
