package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import model.Cezmi;

public class CezmiPainter {

	
	
	public static void paint(Graphics g,Cezmi c) 
	{
		//System.out.println("Girdimmmmmmmmmmmmmmm");
		Rectangle clipRect = g.getClipBounds();
		int posX = c.getPosX();
		int	posY = c.getPosY();
		int radius = c.getRadius();
		
		
		if (clipRect.intersects(c.boundingBox())) 
		{
			g.setColor(Color.CYAN);
			g.fillOval(posX-3,posY, radius+radius, radius+radius);
			g.setColor(Color.CYAN.darker());
			g.drawOval(posX-3,posY, radius+radius, radius+radius);
			//g.fillArc(posX-3, posY, radius+radius, radius+radius, 0, 180);
		}
	}
	
	
	
}
