package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import model.Firildak;

public class FirildakPainter {

	/**
	    * @modifies the Graphic object <g>
	    * @return a rectangle is the x,y for the upper left corner and then the width and height
	    * @param g
	    */
	public static void paint(Graphics g,Firildak firildak) 
	{
		 Graphics2D g2d = (Graphics2D)g.create();

		 	int x =  firildak.getX();
		 	int y =  firildak.getY();
		 	int width = firildak.getWidth();
		 	int height = firildak.getHeight();
		 	double t = firildak.angularRotate();
			AffineTransform transform = new AffineTransform();
			
			g2d.setColor(Color.ORANGE);			
			transform.rotate(Math.toRadians(t), x + width/2, y + height/2);
			
			Shape fi = new Rectangle2D.Float(x, y, width, height);
			Shape transformed = transform.createTransformedShape(fi);
			firildak.setboundingBox(transformed.getBounds());
			
			g2d.fill(transformed);
			g2d.draw(transformed);
			g2d.dispose();
			g2d = (Graphics2D)g.create();
	}
	
	
}
