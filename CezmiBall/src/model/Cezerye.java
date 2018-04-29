package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.RepaintManager;
import javax.swing.SwingWorker;

import main.Test;
import view.DesignAnimationWindow;
import view.XmlAnimationWindow;
/**
 * The Cezerye class creates a square object which initiates special events 
 * when it is hit by the ball. 
 * The special events are described in the detailed requirements.
 *
 */
public class Cezerye{

	private Random r = new Random();
	long start;
	public boolean alive = true;
	int creationTime;
	private long bafTime;
	boolean hitted = false;
	boolean begin = true;
	Cezmi a,b;
	private ArrayList<Takoz> ta;
	private ArrayList<Tokat> toka;
	private ArrayList<Firildak> firil;
	private  int effect = 5;
	private boolean shrinkboolean;
	private boolean growboolean;
	private boolean moveboolean;
	long end;
	boolean hop = false;
	public int getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(int creationTime) {
		this.creationTime = creationTime;
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private int x;
	private int y;
	private int width;
	private int height;

	/**
	 * 
	 * @param x indicates the x position where cezerye will appear
	 * @param y indicates the y position where cezerye will appear
	 */
	public Cezerye(int x, int y) {
		this.setX(x);
		this.setY(y);
		width=Test.L;
		height = Test.L;
		start = System.currentTimeMillis();



	}


	/**Removes Cezerye from the board
	 *@modifies this.width, this.height, this.alive, this.t and this.start
	 *@effects prints the next creation time on the console
	 */
	public void clearCezerye(){
		creationTime = nextCreationTime();
		this.width = 0;
		this.height = 0;
		start = System.currentTimeMillis();
		//	System.out.println("t"  + " " + t);
		alive = false;
	}

	/**adds Cezerye  to the board
	 * @requires System.currentTimeMillis() >= start + t*1000
	 * @modifies this.height, this.width, this.x, this.y
	 * @effects alive field becomes true and currentTimeMillis is equal to the time that Cezerye shows up on the board  
	 */
	public void summonCezerye(){
		if( (System.currentTimeMillis() >= start + creationTime*1000)){
			this.x = r.nextInt(25*Test.L);
			this.y = r.nextInt(25*Test.L);
			this.width = Test.L;
			this.height = Test.L;
			alive = true;
			start = System.currentTimeMillis();
		}

	}

	//the method should be revised!!!! According to requirements given to us:
	//Cezerye:
	//There is one cezerye and appears as a square box.
	//Cezerye  appears for 5 seconds and disappears automatically.
	//Cezerye’s next arrival time is a uniform random value between 5 to 30 seconds
	//If a player hits the cezerye with the ball then the cezerye disappears, and one of the special events (described below) will take place in 2 seconds.
	//The special events are:
	//Make the gizmos on opponent’s side smaller (half size)
	//Make the gizmos on player’s side bigger (twice)
	//Make the opponent’s cezmi freeze.


	/**Identifies how cezerye acts 
	 * 
	 * @param target indicates an object of Cezmi class
	 * @param self indicates an object of Cezmi class
	 */
	public void specialEffect(final Cezmi target,final Cezmi self, final ArrayList<Takoz> takozList, final ArrayList<Tokat> tokatList, final ArrayList<Firildak> firildakList){


		effect = r.nextInt(3);
		hitted = true;
		ta = takozList;
		toka = tokatList;
		firil = firildakList;

		if(BouncingBall.hitCezerye == false){
			if(effect == 0){
				if(begin){
					//Grow self gizmos
					//System.out.println("CASE  O DAYIM");
					long x = System.currentTimeMillis();


					growGizmos(target,self,takozList,tokatList,firildakList);
					/*	try {
				Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					 */
					a = self;
					b = target;

					end = System.currentTimeMillis();
					growboolean = true;
					shrinkboolean = false;
					moveboolean = false;
					begin = false;
				}
				return;

			}
			if(effect == 1){
				if(begin){
					//System.out.println("CASE  1 DAYIM");
					//Small Gizmos 
					a = self;
					b = target;
					shrinkGizmos(target,self,takozList,tokatList,firildakList);
					/*
				try {
				Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					 */
					end = System.currentTimeMillis();
					shrinkboolean = true;
					growboolean = false;
					moveboolean = false;
					begin = false;
				}
				return;
			}

			if(effect == 2){
				if(begin){
					a = target;
					//System.out.println("CASE  2 DAYIM");
					//Freeze opponent cezmi
					target.stopCezmi();
					begin =false;
					/*
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					 */
					moveboolean = true;
					growboolean = false;
					shrinkboolean = false;
					end = System.currentTimeMillis();


				}
				return;
			}
			return;
		}
	}

	/**
	 * @effects  Returns the smallest rectangle that completely covers the
	             current position of the ball.
	 * @return a Rectangle is the x,y for the upper left corner and then the
	           width and height
	 */
	public Rectangle boundingBox() 
	{
		return new Rectangle(x, y, width, height);
	}

	/**Counts 5 seconds starting from the creation time of cezerye
	 * 
	 * @return false 
	 */
	public boolean countFiveSeconds(){

		if((System.currentTimeMillis()-start)/1000  >= 1000){
			return true;
		}
		return false;
	}


	public boolean countBuffTime(){

		if((System.currentTimeMillis()-end)/1000  >= 5){
			return true;
		}
		return false;
	}


	/**Calculates next creation time of Cezerye.
	 * @requires 0<=creationTime<31
	 * @returns creationTime || creationTime+5
	 */
	public int nextCreationTime(){
		int creationTime = r.nextInt(31);
		System.out.println(creationTime);
		if(creationTime > 4){
			return creationTime;
		}
		else{
			return creationTime + 5;
		}
	}



	public void cezeryeMotion() {


		if(alive){
			if(countFiveSeconds()  || hitted || BouncingBall.hitCezerye){
				//		g.clearRect(x, y, width, height);
				clearCezerye();
				//		System.out.println("sildim");
				//	XmlAnimationWindow.getBall().setCezeryeHit(false);
				end = System.currentTimeMillis();
			}
		}

		if(!alive){

			SwingWorker<Boolean,Void> cezeryeWorker = new SwingWorker<Boolean, Void>() {

				@Override
				protected Boolean doInBackground() throws Exception {
					if(countBuffTime()){
						if(growboolean){
							shrinkGizmos(a,b,ta,toka,firil);
							end = System.currentTimeMillis();
							growboolean = false;
						}
						else if(shrinkboolean){
							growGizmos(a,b,ta,toka,firil);
							end = System.currentTimeMillis();
							shrinkboolean = false;
						}
						else if(moveboolean){
							a.startCezmi();
							end = System.currentTimeMillis();
							moveboolean = false;
						}
					}
					return null;
				}

			};
			cezeryeWorker.execute();
			for(int i = 0; i < DesignAnimationWindow.getBallList().size();i++){
				DesignAnimationWindow.getBallList().get(i).setCezeryeHit(false);
			}
			for(int i = 0; i < XmlAnimationWindow.getXballList().size();i++){
				XmlAnimationWindow.getXballList().get(i).setCezeryeHit(false);
			}

			summonCezerye();
			hitted = false;
			begin = true;
			//	g.fillRect(x, y, width, height);
		}



	}

	public void growGizmos(Cezmi target,Cezmi self, ArrayList<Takoz> takozList, ArrayList<Tokat> tokatList, ArrayList<Firildak> firildakList){

		if(self.getPosX() >= 200){
			for(int i =0;i<takozList.size();i++){
				if(takozList.get(i).getWidth() <= Test.L && takozList.get(i).getWhichCezmi() == "RightCezmi"){
					takozList.get(i).setWidth(2*takozList.get(i).getWidth());
					takozList.get(i).setHeight(2*takozList.get(i).getHeight());
				}
			}
			for(int i =0;i<tokatList.size();i++){
				if(tokatList.get(i).getWidth() <= Test.L / 4 && tokatList.get(i).getWhichCezmi() == "RightCezmi"){
					tokatList.get(i).setWidth(2*tokatList.get(i).getWidth());
					tokatList.get(i).setHeight(2*tokatList.get(i).getHeight());
				}
			}
			for(int i =0;i<firildakList.size();i++){
				if(firildakList.get(i).getWidth() <= Test.L && firildakList.get(i).getWhichCezmi() == "RightCezmi"){
					firildakList.get(i).setWidth(2*firildakList.get(i).getWidth());
					firildakList.get(i).setHeight(2*firildakList.get(i).getHeight());
				}
			}
		}else{
			for(int i =0;i<takozList.size();i++){
				if(takozList.get(i).getWidth() <= Test.L && takozList.get(i).getWhichCezmi() == "LeftCezmi"){
					takozList.get(i).setWidth(2*takozList.get(i).getWidth());
					takozList.get(i).setHeight(2*takozList.get(i).getHeight());
				}
			}
			for(int i =0;i<tokatList.size();i++){
				if(tokatList.get(i).getWidth() <= Test.L / 4 && tokatList.get(i).getWhichCezmi() == "LeftCezmi"){
					tokatList.get(i).setWidth(2*tokatList.get(i).getWidth());
					tokatList.get(i).setHeight(2*tokatList.get(i).getHeight());
				}
			}
			for(int i =0;i<firildakList.size();i++){
				if(firildakList.get(i).getWidth() <= Test.L && firildakList.get(i).getWhichCezmi() == "LeftCezmi"){
					firildakList.get(i).setWidth(2*firildakList.get(i).getWidth());
					firildakList.get(i).setHeight(2*firildakList.get(i).getHeight());
				}
			}
		}
	}

	public void shrinkGizmos(Cezmi target,Cezmi self, ArrayList<Takoz> takozList, ArrayList<Tokat> tokatList, ArrayList<Firildak> firildakList){
		if(self.getPosX() >= 200){
			for(int i =0;i<takozList.size();i++){
				if(takozList.get(i).getWidth() >= Test.L && takozList.get(i).getWhichCezmi() == "LeftCezmi"){
					takozList.get(i).setWidth(takozList.get(i).getWidth()/2);
					takozList.get(i).setHeight(takozList.get(i).getHeight()/2);
				}
			}
			for(int i =0;i<tokatList.size();i++){
				if(tokatList.get(i).getWidth() >= Test.L / 4 && tokatList.get(i).getWhichCezmi() == "LeftCezmi"){
					tokatList.get(i).setWidth(tokatList.get(i).getWidth()/2);
					tokatList.get(i).setHeight(tokatList.get(i).getHeight()/2);
				}
			}
			for(int i =0;i<firildakList.size();i++){
				if(firildakList.get(i).getWidth() >= Test.L && firildakList.get(i).getWhichCezmi() == "LeftCezmi"){
					firildakList.get(i).setWidth(firildakList.get(i).getWidth()/2);
					firildakList.get(i).setHeight(firildakList.get(i).getHeight()/2);
				}
			}
		}else{
			for(int i =0;i<takozList.size();i++){
				if(takozList.get(i).getWidth() >= Test.L && takozList.get(i).getWhichCezmi() == "RightCezmi"){
					takozList.get(i).setWidth(takozList.get(i).getWidth()/2);
					takozList.get(i).setHeight(takozList.get(i).getHeight()/2);
				}
			}
			for(int i =0;i<tokatList.size();i++){
				if(tokatList.get(i).getWidth() >= Test.L / 4 && tokatList.get(i).getWhichCezmi() == "RightCezmi"){
					tokatList.get(i).setWidth(tokatList.get(i).getWidth()/2);
					tokatList.get(i).setHeight(tokatList.get(i).getHeight()/2);
				}
			}
			for(int i =0;i<firildakList.size();i++){
				if(firildakList.get(i).getWidth() >= Test.L && firildakList.get(i).getWhichCezmi() == "RightCezmi"){
					firildakList.get(i).setWidth(firildakList.get(i).getWidth()/2);
					firildakList.get(i).setHeight(firildakList.get(i).getHeight()/2);
				}
			}
		}
	}

}
