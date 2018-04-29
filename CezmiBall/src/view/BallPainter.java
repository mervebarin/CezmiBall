package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import model.BouncingBall;

public class BallPainter {
	
	/**Paints ball shaped graphical object
	 * @requires x-radius>0 && y-radius>0
	 * @modifies the Graphics object <g>.
	 * @effects paints a circle on <g> reflecting the current position of the ball.
	 * @param g
	 */
	public static void paint(Graphics g,BouncingBall ball) 
	{
		Rectangle clipRect = g.getClipBounds();
		int radius = ball.getRadius();
		int y = ball.getY();
		int x = ball.getX();
	
			g.setColor(Color.RED);
			g.fillOval(x-radius, y-radius, radius+radius, radius+radius);
			g.setColor(Color.RED.darker());
			g.drawOval(x-radius, y-radius, radius+radius, radius+radius);
		
		
		
		
		
	}

	
	
	
	
	
}
