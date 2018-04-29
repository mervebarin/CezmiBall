package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import main.Test;
import model.Tokat;

public class TokatPainter {


	/**Paints rectangle shaped tokats.
	 * 
	 * @param g
	 * @modifies the Graphics object <g>.
	 * @effects paints a circle on <g> reflecting the current position of the ball.
	 */
	public static void paint(Graphics g,Tokat t) 
	{
		Rectangle clipRect = g.getClipBounds();

		Graphics2D g2d = (Graphics2D)g.create();
		int x = t.getX();
		int y = t.getY();
		int width = t.getWidth();
		int height = t.getHeight();
		int a = t.getRotation();
		int orientation = t.getOrientation();

		int rotateCounter = orientation/90;

		AffineTransform transform = new AffineTransform();



		g2d.setColor(Color.GREEN);
		transform.rotate(Math.toRadians(a), x, y);
		Shape fi = new Rectangle2D.Float(x, y, width, height);
		Shape transformed = transform.createTransformedShape(fi); 
		Rectangle transformedBoundingbox = new Rectangle((int)transformed.getBounds2D().getX(), (int)transformed.getBounds2D().getY(), (int)transformed.getBounds2D().getWidth(), (int)transformed.getBounds2D().getHeight());
		t.setboundingBox(transformedBoundingbox);


		g2d.fill(transformed);
		g2d.draw(transformed);
		g2d.dispose();
		g2d = (Graphics2D)g.create();

		if(t.rotated && rotateCounter%4 == 1){

			t.setX(x-height);
			t.setY(y);
			t.setWidth(Test.L*2);
			t.setHeight(Test.L/4);
			
		}else if(t.rotated && rotateCounter%4 == 2){
			
			t.setX(x+(Test.L*2-Test.L/4));
			t.setY(y-Test.L*2);
			t.setWidth(Test.L/4);
			t.setHeight(Test.L*2);

		}else if(t.rotated && rotateCounter%4 == 3){

			t.setX(x+Test.L/4);
			t.setY(y+(Test.L*2-Test.L/4));
			t.setWidth(Test.L*2);
			t.setHeight(Test.L/4);
			
		}else if(t.rotated && rotateCounter%4 == 0){

			t.setX(x);
			t.setY(y+Test.L/4);
			t.setWidth(Test.L/4);
			t.setHeight(Test.L*2);
		}

	}



}
