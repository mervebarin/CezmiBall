package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

import main.Test;
import view.TokatPainter;

/**
 * The Takoz class creates a triangle or square shaped fixed, solid bumpers(gizmos).
 */
public class Takoz implements Gizmo{

	private int x;
	private int y;
	private int width;
	private int height;
	private String type;
	private String classType;
	private int orientation;
	private String whichCezmi;
	private Rectangle boundingBox;
	public boolean rotated;


	public String getWhichCezmi() {
		return whichCezmi;
	}
	public void setWhichCezmi(String whichCezmi) {
		this.whichCezmi = whichCezmi;
	}

	public int getOrientation() {
		return orientation;
	}


	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}


	@Override
	public String getClassType() {
		return classType;
	}


	public String getTakozType(){
		return type;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	/**Constructor of Takoz class
	 * 
	 * @param x indicates the x position of takoz's center
	 * @param y indicates the y position of takoz's center
	 * @param type indicates whether takoz is square or triangular,according to selected type a takoz 
	 * object is created
	 */
	public Takoz(int x, int y,String type) {
		this.x = x;
		this.y = y;
		this.orientation = 0;
		this.type = type;
		width = Test.L;
		height = Test.L;
		classType = "Takoz";

		if(x>25*Test.L/2){

			this.whichCezmi = "RightCezmi";

		}else{

			this.whichCezmi = "LeftCezmi";
		}
		boundingBox = new Rectangle(x, y, width, height);				
	}

	@Override
	/**
	 * @effects returns the smallest rectangle that completely covers the current position,width and height of the takoz
	 * @return a rectangle is the x,y for the upper left corner and then the width and height
	 */
	public Rectangle boundingBox() 
	{
		return boundingBox;
	}
	
	
	public void setBoundingBox(Rectangle boundingbox){
		this.boundingBox = boundingbox;
	}


	public void setClassType(String string) {
		// TODO Auto-generated method stub
		this.classType = "Takoz";
	}


	public void setType(String string) {
		// TODO Auto-generated method stub
		this.type = string;
	}


	public void rotateClockwise(){

		rotated = true;

	}

}



