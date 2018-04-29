package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import controller.AnimationWindowController;
import controller.ApplicationWindowController;
import main.Test;
import physics.Circle;
import physics.Geometry;
import physics.Vect;
import view.ApplicationWindow;
import view.DesignAnimationWindow;
import view.XmlAnimationWindow;
/**
 * The BouncingBall class implements a ball for our HadiCezmi game.
 * simply creates a ball with x and y coordinates, radius and velocity
 *
 */
public class BouncingBall {

	private  int x = (int)((Math.random() * 100.0) + 100.0);
	private int y = (int)((Math.random() * 100.0) + 100.0);
	private  double vx = 5;
	private  double vy = 5;
	private static int radius = Test.L/2;
	private long initialSysTime = System.currentTimeMillis();
	private long time;
	public static boolean shrinked = false;
	private double mu = 0.025;
	private double mu2 = 0.025;
	private static boolean isLevel2 = false;
	double offset = 1.0;

	private Color color = new Color(255, 0, 0);

	private static ArrayList<Takoz> bbtakozList;
	private static ArrayList<Tokat> bbtokatList;
	private static ArrayList<Firildak> bbfirildakList;
	private static  Cezerye bbcezerye;
	private static ArrayList<Cezmi> bbcezmiList;
	private static ArrayList<BouncingBall> bbballList;


	private  Vect ballVelocityVector = new Vect(vx,vy); 
	private  Vect ballPositionVector = new Vect(x,y);

	private Walls engel = new Walls(240,440,(int)(0.25 * Test.L),3*Test.L);
	private String whoHitCezerye = "";
	private String lasthit = "";
	private Circle circle1 = new Circle(ballPositionVector, radius);
	public static boolean hitCezerye = false;
	public boolean tc =false;
	public boolean ec = false;



	public double getMu() {
		return mu;
	}

	public void setMu(double mu) {
		this.mu = mu;
	}

	public double getMu2() {
		return mu2;
	}

	public void setMu2(double mu2) {
		this.mu2 = mu2;
	}

	public  Vect getVelocityVector(){
		return ballVelocityVector;
	}

	public  Vect getPositionVector(){
		return ballPositionVector;
	}

	public static ArrayList<BouncingBall> getBallList() {
		return bbballList;
	}



	public static void setBallList(ArrayList<BouncingBall> ballList) {
		bbballList = ballList;
	}



	public static ArrayList<Cezmi> getCezmiList() {
		return bbcezmiList;
	}



	public static void setCezmiList(ArrayList<Cezmi> cezmiList) {
		bbcezmiList = cezmiList;
	}



	public ArrayList<Takoz> getTakozList() {
		return bbtakozList;
	}



	public static void setTakozList(ArrayList<Takoz> takozList) {
		bbtakozList = takozList;
	}



	public ArrayList<Tokat> getTokatList() {
		return bbtokatList;
	}



	public static void setTokatList(ArrayList<Tokat> tokatList) {
		bbtokatList = tokatList;
	}



	public ArrayList<Firildak> getFirildakList() {
		return bbfirildakList;
	}



	public static void setFirildakList(ArrayList<Firildak> firildakList) {
		bbfirildakList = firildakList;
	}



	public Cezerye getCezerye() {
		return bbcezerye;
	}



	public static  void setCezerye(Cezerye cezerye) {
		bbcezerye = cezerye;
	}



	public boolean isLevel2() {
		return isLevel2;
	}

	public static void setLevel2(boolean isLevel) {
		isLevel2 = isLevel;
	}

	public BouncingBall(int x, int y, double vx,double vy){
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}



	/**Returns radius of the ball
	 * @return radius
	 */
	public int getRadius() {
		return radius;
	}


	/**Sets radius to radius-(L/10).  
	 * @requires L != 0 && radius != 0 && radius > L*(0.2)
	 * @modifies this.radius
	 * @effects sets radius to radius-(L/10)
	 */
	public void setRadius() {
		if(shrinked == false){
			if(this.radius > Test.L*(0.2)){

				if(ApplicationWindowController.getTotalScore()%2 == 0 && ApplicationWindowController.getTotalScore() != 0){
					System.err.println("Kuculdu");
					this.radius = this.radius-Test.L/10;
				}
			}
		}
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}



	public double getVy() {
		return vy;
	}



	public void setVy(double vy) {
		this.vy = vy;
	}



	public Vect getBallVelocityVector(){
		return ballVelocityVector;
	}

	public void setBallVelocityVector (Vect ballVelocityVector){
		this.ballVelocityVector=ballVelocityVector;
	}

	public Vect getBallPositionVector() {
		return ballPositionVector;
	}

	public void setBallPositionVector(Vect ballPositionVector) {
		this.ballPositionVector = ballPositionVector;
	}

	public Circle getCircle1() {
		return circle1;
	}

	public void setCircle1(Circle circle1) {
		this.circle1 = circle1;
	}


	/**Moves the ball
	 * @requires gizmoList != null && shrink == true 
	 * @modifies vy += calculateGravity().
	 */
	public void move() 
	{
		AnimationWindowController.setLists();

		//applyFrictionAndGravity();
		setRadius();
		shrinked=true;

		//Hit nothing or walls
		x += vx;
		if (x <= radius) { x = radius; vx = -vx; }
		if (x >= 25*Test.L-radius) { x = 25*Test.L-radius; vx = -vx;}

		//Hit nothing or walls
		y += vy;
		if (y <= radius) { y = radius; vy = -vy; }
		if (y >= 25*Test.L-radius + 5) 
		{
			y = 240;
			vy = -vy;
				//System.err.println("HELLO BACIM");
			if(x < 240){
				if(tc){
					System.out.println("Score 3");
					ApplicationWindow.getScore2().setText(String.valueOf(ApplicationWindowController.getScorePlayer2()+offset+1));
					tc = false;
				}else if(ec){
					ApplicationWindow.getScore1().setText(String.valueOf(ApplicationWindowController.getScorePlayer1()-0.5));
					ec = false;
				}
				else{
					System.err.println("only one");
					ApplicationWindow.getScore2().setText(String.valueOf(ApplicationWindowController.getScorePlayer2()+offset));
				}
			}else{
				if(tc){
					System.out.println("Score 3");
					ApplicationWindow.getScore1().setText(String.valueOf(ApplicationWindowController.getScorePlayer1()+offset+1));
					tc = false;
				}else if(ec){
					ApplicationWindow.getScore2().setText(String.valueOf(ApplicationWindowController.getScorePlayer2()-0.5));
					ec=false;
				}else{
					System.out.println("only one");
					ApplicationWindow.getScore1().setText(String.valueOf(ApplicationWindowController.getScorePlayer1()+offset));
				}
			}
		}

		if(this.boundingBox().intersects(engel.boundingBox()))
		{
			tc = false;
			ec = true;
			if(boundingBox().intersection(engel.boundingBox()).getWidth() >= boundingBox().intersection(engel.boundingBox()).getHeight()){
				vy = -vy; 
			} 
			if(boundingBox().intersection(engel.boundingBox()).getHeight() > boundingBox().intersection(engel.boundingBox()).getWidth()){
				vx = -vx; 
			}  
		}

		if(this.boundingBox().intersects(bbcezerye.boundingBox()))
		{
			tc = false;
			ec = false;
			if(whoHitCezerye.equals("cezmi2")){
				bbcezerye.specialEffect(bbcezmiList.get(0),bbcezmiList.get(1),bbtakozList ,bbtokatList, bbfirildakList);
			}
			else if(whoHitCezerye.equals("cezmi")){
				bbcezerye.specialEffect(bbcezmiList.get(1),bbcezmiList.get(0),bbtakozList ,bbtokatList, bbfirildakList);
			}
			else{
				System.out.println("HOPPI");
			}
			BouncingBall.hitCezerye = true;
		}

		for(int i=0;i<bbfirildakList.size();i++){

			Shape fi = new Rectangle2D.Float(bbfirildakList.get(i).x, bbfirildakList.get(i).y, bbfirildakList.get(i).width, bbfirildakList.get(i).height);
			Area areA = new Area(fi);
			Shape bi = boundingBox();
			Area areB = new Area(bi);
			areA.intersect(areB);

			if(!areA.isEmpty())
			{
				tc = false;
				ec=false;
				initialSysTime = System.currentTimeMillis();
				if(boundingBox().intersection(bbfirildakList.get(i).boundingBox()).getWidth() >= boundingBox().intersection(bbfirildakList.get(i).boundingBox()).getHeight()){
					vy = -vy;
				} 
				if(boundingBox().intersection(bbfirildakList.get(i).boundingBox()).getHeight() > boundingBox().intersection(bbfirildakList.get(i).boundingBox()).getWidth()){
					vx = -vx; 
				}  
			}
		}
		for(int i =0;i<bbtokatList.size();i++){
			if(this.boundingBox().intersects(bbtokatList.get(i).boundingBox()))
			{
				tc= true;
				ec=false;
				if(boundingBox().intersection(bbtokatList.get(i).boundingBox()).getWidth() >= boundingBox().intersection(bbtokatList.get(i).boundingBox()).getHeight()){
					vy = -vy; 
				} 

				if(boundingBox().intersection(bbtokatList.get(i).boundingBox()).getHeight() > boundingBox().intersection(bbtokatList.get(i).boundingBox()).getWidth()){
					vx = -vx;
				}  
			}
		}

		for(int i =0;i<bbtakozList.size();i++){

			if(this.boundingBox().intersects(bbtakozList.get(i).boundingBox()))
			{
				tc= false;
				ec =false;
				if(boundingBox().intersection(bbtakozList.get(i).boundingBox()).getWidth() >= boundingBox().intersection(bbtakozList.get(i).boundingBox()).getHeight()){
					vy = -vy; 
				} 

				if(boundingBox().intersection(bbtakozList.get(i).boundingBox()).getHeight() > boundingBox().intersection(bbtakozList.get(i).boundingBox()).getWidth()){
					vx = -vx;
				}  
			}
		}


		
		for(int i=0;i<bbballList.size()-1;i++){

			if(Geometry.timeUntilCircleCollision(bbballList.get(0).getCircle1(), bbballList.get(1).getCircle1(), ballVelocityVector) == 0){
				Vect vect3 = Geometry.reflectCircle(bbballList.get(0).getCircle1().getCenter(), bbballList.get(0).getBallPositionVector(),bbballList.get(0).getBallVelocityVector());
				Vect vect4 = Geometry.reflectCircle(bbballList.get(1).getCircle1().getCenter(),bbballList.get(1).getBallPositionVector(),bbballList.get(1).getBallVelocityVector());
				tc = false;
				ec =false;
				int newvx = (int) vect3.x();
				int newvy = (int) vect3.y();
				bbballList.get(0).vx = newvx;
				bbballList.get(0).vy = newvy;

				int newvx2 = (int) vect4.x();
				int newvy2 = (int) vect4.y();
				bbballList.get(1).vx = newvx2;
				bbballList.get(1).vy = newvy2;
				System.out.println("Collision");

			}
		}
		 
		for(int i=0;i<bbcezmiList.size()-1;i++){

			if(Geometry.timeUntilCircleCollision(bbcezmiList.get(0).getCircle(), circle1, ballVelocityVector) == 0){
				tc = false;
				ec =false;
				Vect vect3 = Geometry.reflectCircle(bbcezmiList.get(0).getCircle().getCenter(), ballPositionVector, ballVelocityVector);
				int newvx = (int) vect3.x();
				int newvy = (int) vect3.y();
				vx = newvx;
				vy = newvy;
				System.out.println("Collision");
				lasthit = "cezmi";
				whoHitCezerye = "cezmi";
			}

			if(Geometry.timeUntilCircleCollision(bbcezmiList.get(1).getCircle(), circle1, ballVelocityVector) == 0){
				tc = false;
				ec=false;
				Vect vect3 = Geometry.reflectCircle(bbcezmiList.get(1).getCircle().getCenter(), ballPositionVector, ballVelocityVector);
				int newvx = (int) vect3.x();
				int newvy = (int) vect3.y();
				vx = newvx;
				vy = newvy;
				System.out.println("Collision");
				lasthit = "cezmi2";
				whoHitCezerye = "cezmi2";

			}
		}
		ballPositionVector = new Vect(x,y);
		setCircle1(new Circle(ballPositionVector, radius));
	}


	/**Ball bumps randomly
	 * @modifies this.vx and this.vy
	 */
	public void randomBump() 
	{
		vx += (int)((Math.random() * 10.0) - 5.0);
		vx = -vx;
		vy += (int)((Math.random() * 10.0) - 5.0);
		vy = -vy;
	}


	/**
	 * @effects  Returns the smallest rectangle that completely covers the
	             current position of the ball.
	 * @return a Rectangle is the x,y for the upper left corner and then the
	           width and height
	 * radius indicates the radius of the ball where  x,y is the center of the ball
	 */
	public Rectangle boundingBox() 
	{
		return new Rectangle(x-radius, y-radius, radius+radius+1, radius+radius+1);
	}

	/**Returns the current position of the ball
	 * @return ballVect
	 */
	public Vect getPosition() {
		// TODO Auto-generated method stub
		Vect ballVect = new Vect(this.x,this.y); 
		return ballVect;
	}

	public boolean getCezeryeHit(){
		return hitCezerye;
	}

	public void setCezeryeHit(boolean m){
		hitCezerye = m;
	}

	/**Calculates time and returns gravity
	 * @modifies this.time
	 * @return gravity
	 */
	public double calculateGravity(){
		double timedelta = 0.004;
		double gravity = 25*Test.L*(timedelta);
		return gravity;

	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void setX(double xCoor) {
		// TODO Auto-generated method stub
		this.x = (int) xCoor;

	}
	public void setY(double yCoor) {
		// TODO Auto-generated method stub
		this.y = (int) yCoor;

	}

	public void applyFrictionAndGravity() {

		double gravity = calculateGravity();
		double timedelta = 0.02;
		double friction = 1 - mu * timedelta - mu2 * Math.abs(Math.hypot(vx, vy)) * timedelta;
		vx = vx*friction;
		vy = vy*friction+gravity*timedelta;

	}	

	private void setVelocity(double vx,double vy){
		this.vx = (int) vx;
		this.vy = (int) vy;
	}
}
