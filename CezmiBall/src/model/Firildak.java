package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.RepaintManager;

import main.Test;
/**
 * The Firildak class creates basically a square takoz 
 * that is rotating around its center with a constant angular velocity.
 *
 */
public class Firildak implements Gizmo{
	public int x;
	public int y;
	int height;
	int width;
	private double angle;
	private String type;
	private Rectangle boundingBox;
	private String whichCezmi;
	private boolean rotated;
	
	
	public String getWhichCezmi() {
		return whichCezmi;
	}
	public void setWhichCezmi(String whichCezmi) {
		this.whichCezmi = whichCezmi;
	}
	/**
	 * @return getA method returns the angle of the firildak object
	 */
	public double getAngle() {
		return angle;
	}
/**
 * @return setA method set the angle of the firildak object to a integer angle value which is
 * equal to 2
 * @param angle indicates the angle
 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public void rotateClockwise(){

		rotated = true;

	}
	@Override
	public int getWidth(){
		return width;
	}
	@Override
	public int getHeight(){
		return height;
	}
	
	@Override
	public void setX(int xCoor) {
		// TODO Auto-generated method stub
		this.x = xCoor;
	}
	@Override
	public void setY(int yCoor) {
		// TODO Auto-generated method stub
		this.y = yCoor;
	}
	@Override
	public void setHeight(int l) {
		// TODO Auto-generated method stub
		this.height = l;
	}
	@Override
	public void setWidth(int l) {
		this.width= l;
	}
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
	@Override
	public int getX() {
		return x;
	}
	@Override
	public int getY() {
		return y;
	}
	
	
	

/**
 * Constructor of Firildak class
 * @param x indicates the x position of the firildak where its center is found
 * @param y indicates the y position of the firildak where its center is found
 */
	public Firildak(int x, int y){
		this.x = x;
		this.y = y;
		width = Test.L;
		height = Test.L;
		
		if(x>25*Test.L/2){
			this.whichCezmi = "RightCezmi";
		}else{
			this.whichCezmi = "LeftCezmi";
		}
		
		boundingBox = new Rectangle(x, y, width, height);
	}


	/**method returns the angle for angular rotation
	 * @requires a should be an integer which indicates the radian of the firildak.Firildak rotates around its center with a constant angular velocity
	 * @modifies a
	 * @return a
	 */
	public double angularRotate(){
		angle = angle + 1;		
		return angle;
	}
	
	
	/**
	 * @effects returns the smallest rectangle that completely covers the current position,width and height of the firildak
	 * @return rectangle is the x,y for the upper left corner and then the width and height 
	 */
	@Override
	public Rectangle boundingBox() 
	{
		return boundingBox;
	}
	
	public void setboundingBox(Rectangle boundingBox) 
	{
		this.boundingBox = boundingBox;
	}
	@Override
	public String getClassType() {
		// TODO Auto-generated method stub
		return "Firildak";
	}

}
