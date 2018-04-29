package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import main.Test;
import model.Takoz;

public class TakozPainter {


	/**
	 * @modifies the Graphics object <g>
	 * @effects paints either a triangular or square on <g> depending on its type
	 * @param g
	 */

	public static void paint(Graphics g,Takoz t) 
	{
		Rectangle clipRect = g.getClipBounds();

		Graphics2D g2d = (Graphics2D)g.create();

		int x = t.getX();
		int y = t.getY();
		int width = t.getWidth();
		int height = t.getHeight();
		String type = t.getTakozType();
		int orientation = t.getOrientation();

		if(type.equals("Square")){
			g2d.setColor(Color.WHITE);
			g2d.drawRect(x, y, width, height);
			g2d.fillRect(x, y, width, height);
		}

		else if(type.equals("Triangular")){

			Polygon poly = new Polygon();
			poly.addPoint(x, y);
			poly.addPoint(x  , y + width);
			poly.addPoint(x + height, y + width);

			int rotateCounter = orientation/90;

			if(t.rotated && rotateCounter%4 == 1){

				//System.out.println("1: " + orientation);
				poly = new Polygon();
				poly.addPoint(x, y);
				poly.addPoint(x-height,y);
				poly.addPoint(x-height, y+width);
				t.setBoundingBox(poly.getBounds());
				
			}else if(t.rotated && rotateCounter%4 == 2){
				//System.out.println("2: " + orientation);
				poly = new Polygon();
				poly.addPoint(x, y);
				poly.addPoint(x,y-width);
				poly.addPoint(x-height, y-width);
				t.setBoundingBox(poly.getBounds());

			}else if(t.rotated && rotateCounter%4 == 3){
				
				//System.out.println("3: " + orientation);
				poly = new Polygon();
				poly.addPoint(x, y);
				poly.addPoint(x+ height,y);
				poly.addPoint(x+height, y-width);
				t.setBoundingBox(poly.getBounds());
			}else if(t.rotated && rotateCounter%4 == 0){
				
				//System.out.println("4: " + orientation);
				poly = new Polygon();
				poly.addPoint(x, y);
				poly.addPoint(x  , y + width);
				poly.addPoint(x + height, y +width);
				t.setBoundingBox(poly.getBounds());
			}

			g2d.setColor(Color.RED);
			g2d.drawPolygon(poly);
			g2d.fillPolygon(poly);
		}


	}


}
