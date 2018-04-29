package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import model.Cezerye;


public class CezeryePainter {

	private static Random r = new Random();
	
	/**Paints rectangle shaped Cezerye.
	 * @param g
	 * @modifies the Graphics object <g>.
	 * @effects paints a circle on <g> reflecting the current position of the ball.
	 */
	
	public static void paint(Graphics g,Cezerye cezerye) 
	{
		Rectangle clipRect = g.getClipBounds();
		int x = cezerye.getX();
		int y = cezerye.getY();
		int width = cezerye.getWidth();
		int height = cezerye.getHeight();

		if (clipRect.intersects(cezerye.boundingBox())) 
		{
			Color c=new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),r.nextInt(256));
			g.setColor(c);
			g.fillRect(x, y, width, height);
			g.setColor(c.darker());
			g.drawRect(x, y, width, height);
		}

		/*
		if(alive){
			if(countFiveSeconds() || AnimationWindow.getBall().getCezeryeHit()){
				g.clearRect(x, y, width, height);
				clearCezerye();
				System.out.println("sildim");
				AnimationWindow.getBall().setCezeryeHit(false);
			}
		}

		if(!alive){
			summonCezerye();
			g.fillRect(x, y, width, height);
		}
		
		*/
		
	}
	
	
	
	
}
