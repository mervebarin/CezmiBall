package model;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * The Walls object creates a wall separating two sides of the board, 
 * just like a volleyball net.
 *
 */

public class Walls {
int x,y,width,height;

/**Constructor of Walls class.
 * 
 * @param x
 * @param y
 * @param width
 * @param height
 * 
 * @modifies this.x, this.y, this.height, this.width
 */
	
	public Walls(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	
	public int getX(){
		return x;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getY(){
		return y;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getWidth(){
		return width;
	}
	public void setWidth(int width){
		this.width = width;
	}
	public int getHeight(){
		return height;
	}
	public void setHeight(int height){
		this.height = height;
	}
	
	
/**
 * @effects  Returns the smallest rectangle that completely covers the
             current position of the ball.
 * @return a Rectangle is the x,y for the upper left corner and then the
           width and height
 */
public Rectangle boundingBox() {
    // effect: Returns the smallest rectangle that completely covers the
    //         current position of the ball.

    // a Rectangle is the x,y for the upper left corner and then the
    // width and height
    return new Rectangle(x, y,width ,height);
  }

}
