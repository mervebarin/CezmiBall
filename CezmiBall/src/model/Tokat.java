package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.Test;

/**
 * The Tokat class creates A flipper gizmo that has a trigger. 
 * Flip direction can be clockwise or counter-clockwise (left and right tokat)
 *
 */
public class Tokat implements Gizmo {

	int height;
	int width;
	int x;
	int y;
	public int angle = 0;
	private String type;
	private String classType;
	private Rectangle boundingBox;
	public int orientation;
	private String whichCezmi;
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

	public int getRotation(){
		return angle;
	}

	public void setRotation(int a){
		this.angle = a;
	}

	@Override
	public int getX() {
		return x;
	}

	public String getType(){
		return type;
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

	/**Constructor of Tokat class.
	 * 
	 * @param x is the x position of its center
	 * @param y is the y position of its center 
	 * @param type indicates whether it is a left tokat or a right tokat
	 * 
	 * @modifies this.x, this.y, this.width, this.height and this.type
	 */
	public Tokat(int x, int y,String type){
		width = Test.L / 4;
		height = Test.L * 2;
		this.x = x;
		this.y = y;
		this.type = type;
		classType = "Tokat";
		if(x>25*Test.L/2){
			this.whichCezmi = "RightCezmi";
		}else{
			this.whichCezmi = "LeftCezmi";
		}
		
		boundingBox =  new Rectangle(x, y, width, height);

	}

	@Override
	/**
	 * @effects  Returns the smallest rectangle that completely covers the
	             current position of the ball.
	 * @return a Rectangle is the x,y for the upper left corner and then the
	           width and height
	 */
	public Rectangle boundingBox() 
	{
		return boundingBox;
	}


	public void setboundingBox(Rectangle boundingBox) 
	{
		this.boundingBox = boundingBox;
	}
	
	public void rotateClockwise(){

		rotated = true;

	}

	/**Rotates right-tokat upwards.
	 * @modifies a
	 */
	public void rotate90(){
		angle = angle + 1;
	
	}
	/**Rotates right-tokat downwards.
	 * @modifies a
	 */
	public void rotateM90(){
		
		angle = angle - 1;

	}
	/**Rotates left-tokat upwards.
	 * @modifies a
	 */
	public void rotateL90(){

		angle = angle -1;
	

	}
	/**Rotates left-tokat downwards.
	 * @modifies a
	 */
	public void rotateML90(){
		angle = angle + 1;


	}


	@Override
	public String getClassType() {
		return classType;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	public void setClassType(String string) {
		// TODO Auto-generated method stub

		this.classType = "Tokat";

	}

	public void setType(String string) {
		// TODO Auto-generated method stub
		this.type = string;
	}

}
