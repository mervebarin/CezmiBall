package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Test;
import physics.Circle;
import physics.Vect;
//denemeeeee

public class Cezmi {

	private int radius;
	private int posX;
	private int posY;
	private int velX;
	private Vect vect1 = new Vect(posX+radius, posY+radius);
	private Circle circle = new Circle(vect1, radius);
	private int score;
	private static boolean isLevel2 = false;


	public static boolean isLevel2() {
		return isLevel2;
	}
	public static void setLevel2(boolean isLevel2) {
		Cezmi.isLevel2 = isLevel2;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Vect getVect1() {
		return vect1;
	}
	public void setVect1(Vect vect1) {
		this.vect1 = vect1;
	}
	public Circle getCircle() {
		return circle;
	}
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int xCoor) {
		this.posX = xCoor;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int yCoor) {
		this.posY = yCoor;
	}
	public int getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}

	public Cezmi(int x){
		setRadius(Test.L);
		setPosX(x);
		setPosY(24*Test.L);
		setVelX(10);
	}

	public Rectangle boundingBox() 
	{
		return new Rectangle((int)posX, (int)posY, radius+radius, radius+radius);
	}

	public void setCenter(int i, int j){
		this.setPosX(i);
		this.setPosY(j);
	}
	public void moveLeftCezmi(){


		if(!isLevel2){

			if(posX <= 0)
			{
				setPosX(posX);
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);
			}else{
				setPosX(posX-velX);	
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);
			}
		}else{

			if(posX <= -20 && posY >= 500-6*Test.L)
			{
				setPosX(posX);
				setPosY(posY-velX);
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);
			}else if(posX >= -20 && posY >= 500-6*Test.L){
				setPosX(posX-velX);	
				setPosY(posY);
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);
			}
		}
	}
	public void moveRightCezmi(){

		if(!isLevel2){

			if(posX >= 200 && posY >= 500-6*Test.L)
			{
				setPosX(posX);
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);

			}else if(posX <= 200 && posY >= 500-6*Test.L){

				setPosX(posX+velX);	
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);
			}
		}else{

			if(posX >= 200 && posY >= 500-6*Test.L)
			{
				setPosX(posX);
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);

			}else if(posX <= 200){

				if(posX == -20 && posY != 500-radius)
				{
					setPosX(posX);
					setPosY(posY + velX);

				}else if(posX >= -20 && posY == 500-radius){
					setPosX(posX+velX);
				}

				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);
			}
		}

	}
	public void moveLeftCezmi2(){

		if(!isLevel2){
			if(posX <= 250)
			{
				setPosX(posX);
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);
			}else if(posX >= 250 && posY >= 500-6*Test.L){
				setPosX(posX-velX);	
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);
			}
		}else{
			if(posX <= 250 && posY >= 500-6*Test.L)
			{
				setPosX(posX);
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);

			}else if(posX >= 250){

				if(posX == 480 && posY != 500-radius)
				{
					setPosX(posX);	
					setPosY(posY+velX);

				}else if(posX <= 480 && posY == 500-radius){

					setPosX(posX-velX);	
				}
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);
			}
		}
	}
	public void moveRightCezmi2(){

		if(!isLevel2){

			if(posX >= 460)
			{
				setPosX(posX);
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);
			}else{
				setPosX(posX+velX);	
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);
			}
		}else{

			if(posX >= 480 && posY >= 500-6*Test.L)
			{
				setPosX(posX);
				setPosY(posY-velX);
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);
			}else if(posX <= 480 && posY >= 500-6*Test.L){
				setPosX(posX+velX);	
				vect1 = new Vect(posX + radius, posY+ radius);
				circle = new Circle(vect1, radius);
			}
		}
	}

	public void stopCezmi(){
		this.setVelX(0);
	}
	
	public void startCezmi(){
		this.setVelX(10);
	}
	public Vect getCenter() {
		Vect cezmiVect = new Vect(this.posX, this.posY);
		return cezmiVect;
	}

}

