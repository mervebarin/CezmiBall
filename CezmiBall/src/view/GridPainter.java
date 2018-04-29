package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Test;
import model.Cezerye;
import model.Grid;

public class GridPainter {

	public static void paint(Graphics g,Grid grid) 
	{
		Rectangle clipRect = g.getClipBounds();
		int x = grid.getX();
		int y = grid.getY();
		int x2 = grid.getX2();
		int y2 = grid.getY2();

		g.setColor(Color.YELLOW);
		g.drawLine(x, y, x2, y2);


	}

}
