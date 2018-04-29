package model;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;

import main.Test;

public class Grid extends  JComponent{
	private int x,y,x2,y2;


	public Grid(int x, int y, int x2, int y2) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}               


	private static  ArrayList<Grid> lines = new ArrayList<Grid>();


	public static ArrayList<Grid> getLines() {
		return lines;
	}



	public void setLines(ArrayList<Grid> lines) {
		this.lines = lines;
	}



	public static void addLine() {

		for(int i = 0; i<500-6*Test.L+1; i=i+Test.L){//yatay cizgi
			Grid a = new Grid(0,i,500,i);
			lines.add(a);
		}

		for(int i = 0; i<500;i=i+Test.L){//dikey
			Grid a = new Grid(i, 0, i, 500-6*Test.L);
			lines.add(a);
		}
	}


	public static void setGrid(){
		addLine();
	}


	public static void clearLines() {
		lines.clear();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

}
