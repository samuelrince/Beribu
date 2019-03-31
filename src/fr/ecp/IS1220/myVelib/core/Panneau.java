package fr.ecp.IS1220.myVelib.core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import sun.applet.Main;

/**
 * This class extends JPanel and is used to draw a map with marked positions.
 * @author Valentin
 *
 */
public class Panneau extends JPanel{
	private static final long serialVersionUID = 1L;
	private ArrayList<Localization> locs;
	private ArrayList<String> labels;
	private int length = 500;
	private int margin = 50;

	/**
	 * Constructor of class Panneau.
	 * @param locs points to represent
	 * @param labels labels of the points
	 */
	public Panneau(ArrayList<Localization> locs, ArrayList<String> labels) {
		super();
		if (locs.size() != labels.size())
			throw new IllegalArgumentException("Localizations and labels sizes don't match.");
		this.locs = locs;
		this.labels = labels;
	}

	/**
	 * Constructor of class Panneau.
	 * @param locs points to represent
	 * @param labels labels of the point
	 * @param length length of the sides of the map (in pixels)
	 * @param margin length of the margins around the map in the main frame (in pixels)
	 */
	public Panneau(ArrayList<Localization> locs, ArrayList<String> labels,
			int length, int margin) {
		super();
		if (locs.size() != labels.size())
			throw new IllegalArgumentException("Localizations and labels sizes don't match.");
		if (length<0 || margin<0)
			throw new IllegalArgumentException("The length and margin should be a number of pixels > 0.");
		this.locs = locs;
		this.labels = labels;
		this.length = length;
		this.margin = margin;
	}
	
	public void paint(Graphics g) {
		this.setBackground(Color.white);
		g.drawRect(margin, margin,length,length);
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
			xRatio = 0.95*length/(xSides[1] + xSides[0]);
		double yRatio;
		if (ySides[1]+ySides[0] == 0)
			yRatio = 1;
		else
			yRatio = 0.95*length/(ySides[1] + ySides[0]);
		int xPixel_bary = (int) (margin + xRatio*xSides[0]);
		int yPixel_bary = (int) (margin + yRatio*ySides[1]);
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
		g.drawString("x="+Math.round(1000*ymin.distanceTo(ymax))/1000.+"km",length+margin-30,length+margin+10);
		g.drawString("x=0, y=0",20,length+margin+10);
	}

}
