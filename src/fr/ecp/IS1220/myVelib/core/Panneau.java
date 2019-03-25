package fr.ecp.IS1220.myVelib.core;

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
		g.drawRect(50, 50, 600, 600);
		Localization barycenter = Localization.barycenter(locs);
		double[] xSides = new double[] {0,0};
		double[] ySides = new double[] {0,0};
		for (Localization loc:locs) {
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
		int i=0;
		for (Localization loc:locs) {
			int xPixel = (int) (350 + (loc.getLongitude()-barycenter.getLongitude())*xRatio);
			int yPixel = (int) (350 + (loc.getLatitude()-barycenter.getLatitude())*yRatio);
			g.fillOval(xPixel, yPixel, 5, 5);
			g.drawString("Station id."+i,xPixel,yPixel);
			i++;
		}
	}

}
