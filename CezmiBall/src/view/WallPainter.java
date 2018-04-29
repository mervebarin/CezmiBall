package view;

import java.awt.Color;
import java.awt.Graphics;

import model.Walls;

public class WallPainter {
	/**
	 * @modifies the Graphics object <g>.
	 * @effects paints a circle on <g> reflecting the current position of the ball.
	 */
public static void paint(Graphics g,Walls w) {
    // modifies: the Graphics object <g>.
    // effects: paints a circle on <g> reflecting the current position
    // of the ball.

    // the "clip rectangle" is the area of the screen that needs to be
    // modified
	
		int x = w.getX();
		int y = w.getY();
		int height = w.getHeight();
		int width = w.getWidth();
    // For this tiny program, testing whether we need to redraw is
    // kind of silly.  But when there are lots of objects all over the
    // screen this is a very important performance optimization
    
    	g.setColor(Color.ORANGE);
    	g.drawRect(x, y, width, height);
    	g.fillRect(x, y, width, height);
    //	g.drawLine(0, 0, 400, 0);
    //	g.drawLine(0, 0, 0, 400);
    //	g.drawLine(400, 400, 0, 400);
    //	g.drawLine(400, 400, 400, 0);
    	
  }
}
