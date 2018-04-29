package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.AnimationWindowController;
import controller.ApplicationWindowController;
import controller.Menu;
import main.Test;
import model.BouncingBall;
import model.Cezerye;
import model.Cezmi;
import model.Firildak;
import model.Gizmo;
import model.Grid;
import model.Takoz;
import model.Tokat;
import model.Walls;
import view.XmlAnimationWindow.AnimationEventListener;


public class DesignAnimationWindow extends JPanel{
	// overview: an AnimationWindow is an area on the screen in which a
	// bouncing ball animation occurs.  AnimationWindows have two modes:
	// on and off.  During the on mode the ball moves, during the off
	// mode the ball doesn't move.

	private AnimationEventListener eventListener;
	private static BouncingBall ball;
	private Cezmi cezmi;
	private Cezmi cezmi2;
	private static ArrayList<BouncingBall> ballList;
	private static ArrayList<Cezmi> cezmiList;
	private static ArrayList<Tokat> tokatList;
	private static ArrayList<Takoz> takozList;
	private static ArrayList<Firildak> firildakList;
	private static ArrayList<String> keyList;
	private Timer timer;
	private boolean mode;
	private boolean editmode;
	private Walls engel;
	private Walls leftwall;
	private Walls rightwall;
	private Walls topwall;
	private Walls bottomwall;
	private static Cezerye cezerye;
	public static Firildak firildak;
	private boolean runPressed;
	private boolean stopPressed;
	int gizmoSize;
	private static boolean isLevel2;
	private static String addString;
	private BouncingBall ball2;
	public boolean isStopPressed() {
		return stopPressed;
	}

	public void setStopPressed(boolean stopPressed) {
		this.stopPressed = stopPressed;
	}

	public static ArrayList<String> getKeyList() {
		return keyList;
	}

	public static void setKeyList(ArrayList<String> keyList) {
		DesignAnimationWindow.keyList = keyList;
	}

	public boolean isRunPressed() {
		return runPressed;
	}

	public void setRunPressed(boolean runPressed) {
		this.runPressed = runPressed;
	}

	public String getAddString() {
		return addString;
	}

	public static void setAddString(String x) {
		addString = x;
	}

	public static ArrayList<BouncingBall> getBallList() {
		return ballList;
	}

	public static void setBallList(ArrayList<BouncingBall> ballList) {
		DesignAnimationWindow.ballList = ballList;
	}

	public static ArrayList<Tokat> getTokatList() {
		return tokatList;
	}

	public static void setTokatList(ArrayList<Tokat> tokatList) {
		DesignAnimationWindow.tokatList = tokatList;
	}

	public static ArrayList<Takoz> getTakozList() {
		return takozList;
	}

	public static void setTakozList(ArrayList<Takoz> takozList) {
		DesignAnimationWindow.takozList = takozList;
	}

	public static ArrayList<Firildak> getFirildakList() {
		return firildakList;
	}

	public static void setFirildakList(ArrayList<Firildak> firildakList) {
		DesignAnimationWindow.firildakList = firildakList;
	}

	public static BouncingBall getBall() {
		return ball;
	}

	public void setBall(BouncingBall ball) {
		this.ball = ball;
	}

	public Cezmi getCezmi() {
		return cezmi;
	}

	public void setCezmi(Cezmi cezmi) {
		this.cezmi = cezmi;
	}

	public boolean isEditmode() {
		return editmode;
	}

	public void setEditmode(boolean editmode) {
		this.editmode = editmode;
	}

	public boolean isMode() {
		return mode;
	}

	public static void setCezerye(Cezerye cezerye) {
		DesignAnimationWindow.cezerye = cezerye;
	}

	public DesignAnimationWindow(boolean level) {
		// effects: initializes this to be in the off mode.

		super();   
		engel = new Walls(240,440,(int)(0.25 * Test.L),3*Test.L);
		leftwall = new Walls(0,0,1,(int)25 * Test.L);
		rightwall = new Walls(25*Test.L,0,1,(int)25 * Test.L);
		topwall = new Walls(0,0,(int)25 * Test.L,1);
		bottomwall = new Walls(0,25*Test.L,(int)25 * Test.L,1);
		Grid.setGrid();
		takozList = new ArrayList<Takoz>();
		tokatList = new ArrayList<Tokat>();
		firildakList = new ArrayList<Firildak>();
		ballList = new ArrayList<BouncingBall>();
		cezmiList = new ArrayList<Cezmi>();
		keyList = new ArrayList<String>();
		keyList.add("A");
		keyList.add("D");
		keyList.add("Left");
		keyList.add("Right");
		keyList.add("E");
		keyList.add("R");
		keyList.add("O");
		keyList.add("P");
		cezmi = new Cezmi(5*Test.L);
		cezmi2 = new Cezmi(20*Test.L);
		cezmiList.add(cezmi);
		cezmiList.add(cezmi2);
		cezerye = new Cezerye(40,40);
		ball = new BouncingBall(240,240,10,10);
		ballList.add(ball);
		isLevel2 = level;
		if(isLevel2){
			System.err.println("DENEME BALL2");
			ball.setMu(ball.getMu()*2);
			ball.setMu2(ball.getMu2()*2);
			System.err.println(isLevel2 + " sadasdsadaýsjdýwejfoprejgeýjrepgýjreýohfre");
			ball2 = new BouncingBall(260, 260, 10, 10);
			ballList.add(ball2);
		}

		// this only initializes the timer, we actually start and stop the
		// timer in the setMode() method
		eventListener = new AnimationEventListener();
		// The first parameter is how often (in milliseconds) the timer
		// should call us back.  50 milliseconds = 20 frames/second
		timer = new Timer(50, eventListener);
		this.setBackground(Color.BLACK);
		mode = false;
	}

	public static  boolean isLevel2() {
		return isLevel2;
	}

	public void setLevel2(boolean isLevel2) {
		this.isLevel2 = isLevel2;
	}

	// This is just here so that we can accept the keyboard focus
	public boolean isFocusTraversable() { return true; }

	public void paint(Graphics g) {
		// modifies: <g>
		// effects: Repaints the Graphics area <g>.  Swing will then send the
		//          newly painted g to the screen.

		// first repaint the proper background color (controlled by
		// the windowing system)

		super.paintComponent(g);
		WallPainter.paint(g, engel);
		WallPainter.paint(g, leftwall);
		WallPainter.paint(g, rightwall);
		WallPainter.paint(g, topwall);
		WallPainter.paint(g, bottomwall);
		CezmiPainter.paint(g, cezmi);
		CezmiPainter.paint(g, cezmi2);

			for(int i = 0; i<ballList.size();i++){
		BallPainter.paint(g, ballList.get(i));
			}
			
			
			
			
			
		CezeryePainter.paint(g, cezerye);

		gizmoSize = tokatList.size() + takozList.size() + firildakList.size();

		for(int i = 0; i<Grid.getLines().size();i++){
			GridPainter.paint(g, Grid.getLines().get(i));
		}

		for(int i = 0; i<takozList.size();i++){
			TakozPainter.paint(g, takozList.get(i));
		}

		for(int i = 0; i<tokatList.size();i++){
			Tokat t = tokatList.get(i);
			TokatPainter.paint(g, t);
			tokatList.set(i, t);

		}
		for(int i = 0; i<firildakList.size();i++){
			FirildakPainter.paint(g, firildakList.get(i));
		}

	}

	public void activateEditMode(){
		setMode(true);
		timer.stop();
	}

	public void setMode(boolean m) {
		// modifies: this
		// effects: changes the mode to <m>.

		if (mode == true) {
			// we're about to change mode: turn off all the old listeners
			removeMouseListener(eventListener);
			removeMouseMotionListener(eventListener);
			removeKeyListener(eventListener);
		}

		mode = m;

		if (mode == true) {
			// the mode is true: turn on the listeners
			addMouseListener(eventListener);
			addMouseMotionListener(eventListener);
			addKeyListener(eventListener);
			requestFocus();           // make sure keyboard is directed to us
			timer.start();
		}
		else {
			timer.stop();
		}

	}

	public void XmlWriter(){
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("board");
			doc.appendChild(rootElement);

			if(isLevel2){
				rootElement.setAttribute("level", "2");
			}else{
				rootElement.setAttribute("level","1");
			}

			for(int i = 0; i<ballList.size();i++){
			Element balls = doc.createElement("ball");
			rootElement.appendChild(balls);

		

				Attr attr = doc.createAttribute("x");
				attr.setValue(Integer.toString(ballList.get(i).getX()/Test.L));
				balls.setAttributeNode(attr);

				Attr attr2 = doc.createAttribute("y");
				attr2.setValue(Integer.toString(ballList.get(i).getY()/Test.L));
				balls.setAttributeNode(attr2);

				Attr attr3 = doc.createAttribute("xVelocity");
				attr3.setValue(Integer.toString((int)ballList.get(i).getVx()));
				balls.setAttributeNode(attr3);

				Attr attr4 = doc.createAttribute("yVelocity");
				attr4.setValue(Integer.toString((int)ballList.get(i).getVy()));
				balls.setAttributeNode(attr4);

			}
			

			Element cezmi1 = doc.createElement("cezmi1");
			rootElement.appendChild(cezmi1);

			for(int i = 0; i< cezmiList.size()-1 ; i++){

				Attr attr = doc.createAttribute("x");
				attr.setValue(Integer.toString(cezmiList.get(0).getPosX()/Test.L));
				cezmi1.setAttributeNode(attr);

				Attr attr2 = doc.createAttribute("y");
				attr2.setValue(Integer.toString(cezmiList.get(0).getPosY()/Test.L));
				cezmi1.setAttributeNode(attr2);

				Attr attr3 = doc.createAttribute("score");
				attr3.setValue(Integer.toString(cezmiList.get(0).getScore()));
				cezmi1.setAttributeNode(attr3);
			}

			Element cezmi2 = doc.createElement("cezmi2");
			rootElement.appendChild(cezmi2);

			for(int i = 0; i< cezmiList.size()-1 ; i++){

				Attr attr = doc.createAttribute("x");
				attr.setValue(Integer.toString(cezmiList.get(1).getPosX()/Test.L));
				cezmi2.setAttributeNode(attr);

				Attr attr2 = doc.createAttribute("y");
				attr2.setValue(Integer.toString(cezmiList.get(1).getPosY()/Test.L));
				cezmi2.setAttributeNode(attr2);

				Attr attr3 = doc.createAttribute("score");
				attr3.setValue(Integer.toString(cezmiList.get(1).getScore()));
				cezmi2.setAttributeNode(attr3);
			}

			Element cezeryes = doc.createElement("cezeryes");
			rootElement.appendChild(cezeryes);

			for(int i = 0 ; i<1;i++){

				Element ecezerye = doc.createElement("cezerye");

				Attr attr = doc.createAttribute("x");
				attr.setValue(Integer.toString(cezerye.getX()/Test.L));
				ecezerye.setAttributeNode(attr);

				Attr attr2 = doc.createAttribute("y");
				attr2.setValue(Integer.toString(cezerye.getY()/Test.L));
				ecezerye.setAttributeNode(attr2);

				Attr attr3 = doc.createAttribute("time");
				attr3.setValue(Integer.toString(cezerye.getCreationTime()));
				ecezerye.setAttributeNode(attr3);

				cezeryes.appendChild(ecezerye);
			}

			Element gizmos = doc.createElement("gizmos");
			rootElement.appendChild(gizmos);

			//http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/

			//For gizmos
			for(int x = 0; x<takozList.size();x++){

				//For squareTakoz
				if(takozList.get(x).getTakozType() == "Square"){

					Element squareTakoz = doc.createElement("squareTakoz");

					Attr attr = doc.createAttribute("x");
					attr.setValue(Integer.toString(takozList.get(x).getX()/Test.L));
					squareTakoz.setAttributeNode(attr);

					Attr attr2 = doc.createAttribute("y");
					attr2.setValue(Integer.toString(takozList.get(x).getY()/Test.L));
					squareTakoz.setAttributeNode(attr2);

					gizmos.appendChild(squareTakoz);

				}else if(takozList.get(x).getTakozType() == "Triangular"){

					Element triangleTakoz = doc.createElement("triangleTakoz");

					Attr attr = doc.createAttribute("x");
					attr.setValue(Integer.toString(takozList.get(x).getX()/Test.L));
					triangleTakoz.setAttributeNode(attr);

					Attr attr2 = doc.createAttribute("y");
					attr2.setValue(Integer.toString(takozList.get(x).getY()/Test.L));
					triangleTakoz.setAttributeNode(attr2);

					Attr attr3 = doc.createAttribute("orientation");
					attr3.setValue(Integer.toString(takozList.get(x).getOrientation()));
					triangleTakoz.setAttributeNode(attr3);

					gizmos.appendChild(triangleTakoz);
				}
			}
			//For firildak

			for(int y = 0; y<firildakList.size();y++){

				Element firildak = doc.createElement("firildak");

				Attr attr = doc.createAttribute("x");
				attr.setValue(Integer.toString(firildakList.get(y).getX()/Test.L));
				firildak.setAttributeNode(attr);

				Attr attr2 = doc.createAttribute("y");
				attr2.setValue(Integer.toString(firildakList.get(y).getY()/Test.L));
				firildak.setAttributeNode(attr2);

				Attr attr3 = doc.createAttribute("angle");
				attr3.setValue(Double.toString(firildakList.get(y).getAngle()));
				firildak.setAttributeNode(attr3);

				gizmos.appendChild(firildak);
			}
			//For tokat
			for(int j = 0; j<tokatList.size() ; j++){

				if(tokatList.get(j).getType() == "LeftTokat"){

					Element leftTokat = doc.createElement("leftTokat");

					Attr attr = doc.createAttribute("x");
					attr.setValue(Integer.toString(tokatList.get(j).getX()/Test.L));
					leftTokat.setAttributeNode(attr);

					Attr attr2 = doc.createAttribute("y");
					attr2.setValue(Integer.toString(tokatList.get(j).getY()/Test.L));
					leftTokat.setAttributeNode(attr2);

					Attr attr3 = doc.createAttribute("orientation");
					attr3.setValue(Integer.toString(tokatList.get(j).getOrientation()));
					leftTokat.setAttributeNode(attr3);

					gizmos.appendChild(leftTokat);

				}else if(tokatList.get(j).getType() == "RightTokat"){

					Element rightTokat = doc.createElement("rightTokat");

					Attr attr = doc.createAttribute("x");
					attr.setValue(Integer.toString(tokatList.get(j).getX()/Test.L));
					rightTokat.setAttributeNode(attr);

					Attr attr2 = doc.createAttribute("y");
					attr2.setValue(Integer.toString(tokatList.get(j).getY()/Test.L));
					rightTokat.setAttributeNode(attr2);

					Attr attr3 = doc.createAttribute("orientation");
					attr3.setValue(Integer.toString(tokatList.get(j).getOrientation()));
					rightTokat.setAttributeNode(attr3);

					gizmos.appendChild(rightTokat);
				}
			}

			Element keys = doc.createElement("keys");
			rootElement.appendChild(keys);

			for(int i =0;i<keyList.size();i++){

				if(keyList.get(i) == "A"){
					Element akey = doc.createElement("key1left");

					Attr attr = doc.createAttribute("key");
					attr.setValue(keyList.get(i));
					akey.setAttributeNode(attr);

					keys.appendChild(akey);
				}else if(keyList.get(i) == "D"){

					Element dkey = doc.createElement("key1right");

					Attr attr = doc.createAttribute("key");
					attr.setValue(keyList.get(i));
					dkey.setAttributeNode(attr);

					keys.appendChild(dkey);
				}else if(keyList.get(i) == "E"){

					Element ekey = doc.createElement("key1lefttokat");

					Attr attr = doc.createAttribute("key");
					attr.setValue(keyList.get(i));
					ekey.setAttributeNode(attr);

					keys.appendChild(ekey);
				}else if(keyList.get(i) == "R"){

					Element rkey = doc.createElement("key1righttokat");

					Attr attr = doc.createAttribute("key");
					attr.setValue(keyList.get(i));
					rkey.setAttributeNode(attr);

					keys.appendChild(rkey);
				}else if(keyList.get(i) == "O"){

					Element okey = doc.createElement("key2lefttokat");

					Attr attr = doc.createAttribute("key");
					attr.setValue(keyList.get(i));
					okey.setAttributeNode(attr);

					keys.appendChild(okey);
				}else if(keyList.get(i) == "P"){

					Element pkey = doc.createElement("key2righttokat");

					Attr attr = doc.createAttribute("key");
					attr.setValue(keyList.get(i));
					pkey.setAttributeNode(attr);

					keys.appendChild(pkey);
				}else if(keyList.get(i) == "Left"){

					Element leftkey = doc.createElement("key2left");

					Attr attr = doc.createAttribute("key");
					attr.setValue(keyList.get(i));
					leftkey.setAttributeNode(attr);

					keys.appendChild(leftkey);
				}else if(keyList.get(i) == "Right"){

					Element rightkey = doc.createElement("key2right");

					Attr attr = doc.createAttribute("key");
					attr.setValue(keyList.get(i));
					rightkey.setAttributeNode(attr);

					keys.appendChild(rightkey);
				}
			}


			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("deneme1.xml"));

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public static ArrayList<Cezmi> getCezmiList() {
		return cezmiList;
	}

	public static void setCezmiList(ArrayList<Cezmi> cezmiList) {
		DesignAnimationWindow.cezmiList = cezmiList;
	}

	public Cezmi getCezmi2() {
		return cezmi2;
	}

	public void setCezmi2(Cezmi cezmi2) {
		this.cezmi2 = cezmi2;
	}

	public static Cezerye getCezerye(){
		return cezerye;
	}


	class AnimationEventListener extends MouseAdapter
	implements MouseMotionListener, KeyListener, ActionListener
	{

		private boolean leftPressed;
		private boolean rightPressed;
		private boolean aPressed;
		private boolean dPressed;

		private boolean leftPlayerLeftTokatPressedE;
		private boolean leftPlayerRightTokatPressedR;
		private boolean rightPlayerLeftTokatPressedO;
		private boolean rightPlayerRightTokatPressedP;
		private int leftPlayerLeftTokatCounter;
		private int leftPlayerRightTokatCounter;
		private int rightPlayerLeftTokatCounter;
		private int rightPlayerRightTokatCounter;

		int dragx;
		int dragy;
		Takoz takoz;
		Tokat tokat;
		Firildak firildak;
		boolean takozmove = false;
		boolean tokatmove = false;
		boolean firildakmove = false;
		boolean validPosition = false;

		// overview: AnimationEventListener is an inner class that
		// responds to all sorts of external events, and provides the
		// required semantic operations for our particular program.  It
		// owns, and sends semantic actions to the ball and window of the
		// outer class

		// MouseAdapter gives us empty methods for the MouseListener
		// interface: mouseClicked, mouseEntered, mouseExited, mousePressed,
		// and mouseReleased.

		// for this example we only need to override mouseClicked
		public void mouseClicked(MouseEvent e) {
			// modifes: the ball that this listener owns
			// effects: causes the ball to be bumped in a random direction

			if(isStopPressed() == false && isMode() == true && isEditmode() == true && isRunPressed() == false && SwingUtilities.isLeftMouseButton(e)){

				if(addString.equals("triangleTakoz")){
					if(e.getY() < 500-6*Test.L){

						Takoz triangle = new Takoz(e.getX(), e.getY(), "Triangular");

						if(!takozList.isEmpty()){

							int size = takozList.size();

							for(int i =0;i<takozList.size();i++){
								if(triangle.getX()>=takozList.get(i).getX() && triangle.getX()<=takozList.get(i).getX()+takozList.get(i).getWidth() &&triangle.getY()>=takozList.get(i).getY() && triangle.getY()<=takozList.get(i).getY()+takozList.get(i).getHeight() )
								{
									takozList.remove(takozList.get(i));
									repaint();
								}
							}
							if(size == takozList.size()){
								validPosition= true;
								if(!firildakList.isEmpty()){
									for(int i=0;i<firildakList.size();i++){
										if(triangle.getX()>=firildakList.get(i).getX() && triangle.getX()<=firildakList.get(i).getX()+firildakList.get(i).getWidth() &&triangle.getY()>=firildakList.get(i).getY() && triangle.getY()<=firildakList.get(i).getY()+firildakList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(!tokatList.isEmpty()){
									for(int i=0;i<tokatList.size();i++){
										if(triangle.getX()>=tokatList.get(i).getX() && triangle.getX()<=tokatList.get(i).getX()+tokatList.get(i).getWidth() &&triangle.getY()>=tokatList.get(i).getY() && triangle.getY()<=tokatList.get(i).getY()+tokatList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(validPosition){
									takozList.add(triangle);
									TakozPainter.paint(getGraphics(), triangle);
								}
							}
						}else{
							validPosition= true;
							if(!firildakList.isEmpty()){
								for(int i=0;i<firildakList.size();i++){
									if(triangle.getX()>=firildakList.get(i).getX() && triangle.getX()<=firildakList.get(i).getX()+firildakList.get(i).getWidth() &&triangle.getY()>=firildakList.get(i).getY() && triangle.getY()<=firildakList.get(i).getY()+firildakList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(!tokatList.isEmpty()){
								for(int i=0;i<tokatList.size();i++){
									if(triangle.getX()>=tokatList.get(i).getX() && triangle.getX()<=tokatList.get(i).getX()+tokatList.get(i).getWidth() &&triangle.getY()>=tokatList.get(i).getY() && triangle.getY()<=tokatList.get(i).getY()+tokatList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(validPosition){
								takozList.add(triangle);
								TakozPainter.paint(getGraphics(), triangle);
							}
						}
					}
					else{
						System.out.println("Invalid coordinate");
					}
				}else if(addString.equals("squareTakoz")){
					if(e.getY() < 500-6*Test.L){
						Takoz square = new Takoz(e.getX(), e.getY(), "Square");
						if(!takozList.isEmpty())
						{
							int size = takozList.size();

							for(int i =0;i<takozList.size();i++){

								if(square.getX()>=takozList.get(i).getX() && square.getX()<=takozList.get(i).getX()+takozList.get(i).getWidth() && square.getY()>=takozList.get(i).getY() && square.getY()<=takozList.get(i).getY()+takozList.get(i).getHeight() )
								{
									takozList.remove(takozList.get(i));
									repaint();
								}
							}
							if(size == takozList.size()){
								validPosition= true;
								if(!firildakList.isEmpty()){
									for(int i=0;i<firildakList.size();i++){
										if(square.getX()>=firildakList.get(i).getX() && square.getX()<=firildakList.get(i).getX()+firildakList.get(i).getWidth() &&square.getY()>=firildakList.get(i).getY() && square.getY()<=firildakList.get(i).getY()+firildakList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(!tokatList.isEmpty()){
									for(int i=0;i<tokatList.size();i++){
										if(square.getX()>=tokatList.get(i).getX() && square.getX()<=tokatList.get(i).getX()+tokatList.get(i).getWidth() &&square.getY()>=tokatList.get(i).getY() && square.getY()<=tokatList.get(i).getY()+tokatList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(validPosition){
									takozList.add(square);
									TakozPainter.paint(getGraphics(), square);
								}
							}
						}else{
							validPosition= true;
							if(!firildakList.isEmpty()){
								for(int i=0;i<firildakList.size();i++){
									if(square.getX()>=firildakList.get(i).getX() && square.getX()<=firildakList.get(i).getX()+firildakList.get(i).getWidth() &&square.getY()>=firildakList.get(i).getY() && square.getY()<=firildakList.get(i).getY()+firildakList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(!tokatList.isEmpty()){
								for(int i=0;i<tokatList.size();i++){
									if(square.getX()>=tokatList.get(i).getX() && square.getX()<=tokatList.get(i).getX()+tokatList.get(i).getWidth() &&square.getY()>=tokatList.get(i).getY() && square.getY()<=tokatList.get(i).getY()+tokatList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(validPosition){
								takozList.add(square);
								TakozPainter.paint(getGraphics(), square);
							}
						}
					}else{
						System.out.println("Invalid coordinate");
					}
				}else if(addString.equals("firildak")){
					if(e.getY() < 500-6*Test.L){
						Firildak firildak = new Firildak(e.getX(), e.getY());
						if(!firildakList.isEmpty()){
							int size = firildakList.size();
							for(int i =0;i<firildakList.size();i++){
								if(firildak.getX()>=firildakList.get(i).getX() && firildak.getX()<=firildakList.get(i).getX()+firildakList.get(i).getWidth() && firildak.getY()>=firildakList.get(i).getY() && firildak.getY()<=firildakList.get(i).getY()+firildakList.get(i).getHeight() )
								{
									firildakList.remove(firildakList.get(i));
									repaint();
								}
							}
							if(size == firildakList.size()){
								validPosition=true;
								if(!tokatList.isEmpty()){
									for(int i=0;i<tokatList.size();i++){
										if(firildak.getX()>=tokatList.get(i).getX() && firildak.getX()<=tokatList.get(i).getX()+tokatList.get(i).getWidth() &&firildak.getY()>=tokatList.get(i).getY() && firildak.getY()<=tokatList.get(i).getY()+tokatList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(!takozList.isEmpty()){
									for(int i=0;i<takozList.size();i++){
										if(firildak.getX()>=takozList.get(i).getX() && firildak.getX()<=takozList.get(i).getX()+takozList.get(i).getWidth() &&firildak.getY()>=takozList.get(i).getY() && firildak.getY()<=takozList.get(i).getY()+takozList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(validPosition){
									firildakList.add(firildak);
									FirildakPainter.paint(getGraphics(), firildak);		
								}
							}
						}
						else
						{
							validPosition=true;
							if(!tokatList.isEmpty()){
								for(int i=0;i<tokatList.size();i++){
									if(firildak.getX()>=tokatList.get(i).getX() && firildak.getX()<=tokatList.get(i).getX()+tokatList.get(i).getWidth() &&firildak.getY()>=tokatList.get(i).getY() && firildak.getY()<=tokatList.get(i).getY()+tokatList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(!takozList.isEmpty()){
								for(int i=0;i<takozList.size();i++){
									if(firildak.getX()>=takozList.get(i).getX() && firildak.getX()<=takozList.get(i).getX()+takozList.get(i).getWidth() &&firildak.getY()>=takozList.get(i).getY() && firildak.getY()<=takozList.get(i).getY()+takozList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(validPosition){
								firildakList.add(firildak);
								FirildakPainter.paint(getGraphics(), firildak);		
							}						
						}
					}else{
						System.out.println("Invalid coordinate");
					}
				}else if(addString.equals("leftTokat")){
					if(e.getY() < 500-6*Test.L){
						Tokat left = new Tokat(e.getX(), e.getY(), "LeftTokat");
						if(!tokatList.isEmpty()){
							int size = tokatList.size();
							for(int i =0;i<tokatList.size();i++){
								if(left.getX()>=tokatList.get(i).getX() && left.getX()<=tokatList.get(i).getX()+tokatList.get(i).getWidth() && left.getY()>=tokatList.get(i).getY() && left.getY()<=tokatList.get(i).getY()+tokatList.get(i).getHeight() )
								{
									tokatList.remove(tokatList.get(i));
									repaint();
								}
							}
							if(size == tokatList.size()){
								validPosition=true;

								if(!firildakList.isEmpty()){
									for(int i=0;i<firildakList.size();i++){
										if(left.getX()>=firildakList.get(i).getX() && left.getX()<=firildakList.get(i).getX()+firildakList.get(i).getWidth() &&left.getY()>=firildakList.get(i).getY() && left.getY()<=firildakList.get(i).getY()+firildakList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(!takozList.isEmpty()){
									for(int i=0;i<takozList.size();i++){
										if(left.getX()>=takozList.get(i).getX() && left.getX()<=takozList.get(i).getX()+takozList.get(i).getWidth() &&left.getY()>=takozList.get(i).getY() && left.getY()<=takozList.get(i).getY()+takozList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(validPosition){
									tokatList.add(left);
									TokatPainter.paint(getGraphics(), left);
								}
							}
						}else
						{
							validPosition=true;

							if(!firildakList.isEmpty()){
								for(int i=0;i<firildakList.size();i++){
									if(left.getX()>=firildakList.get(i).getX() && left.getX()<=firildakList.get(i).getX()+firildakList.get(i).getWidth() &&left.getY()>=firildakList.get(i).getY() && left.getY()<=firildakList.get(i).getY()+firildakList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(!takozList.isEmpty()){
								for(int i=0;i<takozList.size();i++){
									if(left.getX()>=takozList.get(i).getX() && left.getX()<=takozList.get(i).getX()+takozList.get(i).getWidth() &&left.getY()>=takozList.get(i).getY() && left.getY()<=takozList.get(i).getY()+takozList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(validPosition){
								tokatList.add(left);
								TokatPainter.paint(getGraphics(), left);
							}
						}
					}else{
						System.out.println("Invalid coordinate");
					}
				}else if(addString.equals("rightTokat")){
					if(e.getY() < 500-6*Test.L){
						Tokat right = new Tokat(e.getX(), e.getY(), "RightTokat");
						if(!tokatList.isEmpty()){
							int size = tokatList.size();
							for(int i =0;i<tokatList.size();i++){
								if(right.getX()>=tokatList.get(i).getX() && right.getX()<=tokatList.get(i).getX()+tokatList.get(i).getWidth() && right.getY()>=tokatList.get(i).getY() && right.getY()<=tokatList.get(i).getY()+tokatList.get(i).getHeight() )
								{	
									tokatList.remove(tokatList.get(i));
									repaint();
								}
							}
							if(size==tokatList.size()){
								validPosition=true;

								if(!firildakList.isEmpty()){
									for(int i=0;i<firildakList.size();i++){
										if(right.getX()>=firildakList.get(i).getX() && right.getX()<=firildakList.get(i).getX()+firildakList.get(i).getWidth() &&right.getY()>=firildakList.get(i).getY() && right.getY()<=firildakList.get(i).getY()+firildakList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(!takozList.isEmpty()){
									for(int i=0;i<takozList.size();i++){
										if(right.getX()>=takozList.get(i).getX() && right.getX()<=takozList.get(i).getX()+takozList.get(i).getWidth() &&right.getY()>=takozList.get(i).getY() && right.getY()<=takozList.get(i).getY()+takozList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(validPosition){
									tokatList.add(right);
									TokatPainter.paint(getGraphics(), right);
								}
							}
						}else
						{									
							validPosition=true;

							if(!firildakList.isEmpty()){
								for(int i=0;i<firildakList.size();i++){
									if(right.getX()>=firildakList.get(i).getX() && right.getX()<=firildakList.get(i).getX()+firildakList.get(i).getWidth() &&right.getY()>=firildakList.get(i).getY() && right.getY()<=firildakList.get(i).getY()+firildakList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(!takozList.isEmpty()){
								for(int i=0;i<takozList.size();i++){
									if(right.getX()>=takozList.get(i).getX() && right.getX()<=takozList.get(i).getX()+takozList.get(i).getWidth() &&right.getY()>=takozList.get(i).getY() && right.getY()<=takozList.get(i).getY()+takozList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}

							if(validPosition){
								tokatList.add(right);
								TokatPainter.paint(getGraphics(), right);
							}
						}
					}else{
						System.out.println("Invalid coordinate");
					}
				}	
			}

			dragx = e.getX();
			dragy = e.getY();

			for(int i = 0; i < takozList.size();i++){
				if(dragx >= takozList.get(i).getX() && dragx <= takozList.get(i).getX() + takozList.get(i).getWidth() && dragy >= takozList.get(i).getY() && dragy <= takozList.get(i).getY() + takozList.get(i).getHeight()){

					takozmove = true;
					firildakmove = false;
					tokatmove = false;

					takoz = takozList.get(i);
				}
			}

			for(int i = 0; i < firildakList.size();i++){
				if(dragx >= firildakList.get(i).getX() && dragx <= firildakList.get(i).getX() + firildakList.get(i).getWidth() && dragy >= firildakList.get(i).getY() && dragy <= firildakList.get(i).getY() + firildakList.get(i).getHeight()){

					firildakmove = true;
					takozmove = false;
					tokatmove = false;

					firildak = firildakList.get(i);
				}
			}

			for(int i = 0; i < tokatList.size();i++){
				if(dragx >= tokatList.get(i).getX() && dragx <= tokatList.get(i).getX() + tokatList.get(i).getWidth() && dragy >= tokatList.get(i).getY() && dragy <= tokatList.get(i).getY() + tokatList.get(i).getHeight()){

					tokatmove = true;
					takozmove = false;
					firildakmove = false;
					tokat = tokatList.get(i);
				}
			}

			if(isStopPressed() == false && isMode() == true && isEditmode()== true && isRunPressed() == false && SwingUtilities.isRightMouseButton(e)){
				if(e.getY() < 500-6*Test.L){
					if(takozmove){

						takozList.remove(takoz);
						takoz.setX(e.getX());
						takoz.setY(e.getY());

						takozList.add(takoz);
						repaint();
					}
					else if(tokatmove){

						tokatList.remove(tokat);
						tokat.setX(e.getX());
						tokat.setY(e.getY());

						tokatList.add(tokat);
						repaint();	
					}
					else if(firildakmove){

						firildakList.remove(firildak);
						firildak.setX(e.getX());
						firildak.setY(e.getY());

						firildakList.add(firildak);						
						repaint();
					}
				}
				else{
					System.err.println("Invalid drag operation because of: "+ e.getX() +  "and "+ e.getY() + " is invalid");
				}
			}

			if(isStopPressed() == false && isMode() == true && isEditmode()== true && isRunPressed() == false && SwingUtilities.isMiddleMouseButton(e)){

				if(takozmove){
					System.out.println("takozmove: " + takozmove + " tokatmove: "+ tokatmove);
					takozList.remove(takoz);
					takoz.rotateClockwise();
					takoz.setOrientation(takoz.getOrientation()+90);
					takozList.add(takoz);
					repaint();

				}else if(tokatmove){
					System.out.println("takozmove: " + takozmove + " tokatmove: "+ tokatmove);
					tokatList.remove(tokat);
					tokat.rotateClockwise();
					tokat.setOrientation(tokat.getOrientation()+90);
					tokatList.add(tokat);
					repaint();
				}
			}
		}

		// Here's the MouseMotionListener interface
		public void mouseDragged(MouseEvent e) { 


		}
		public void mouseMoved(MouseEvent e) { 

		}

		// Here's the KeyListener interface
		public void keyPressed(KeyEvent e) {
			// modifes: the ball that this listener owns
			// effects: causes the ball to be bumped in a random direction but
			//          only if one of the keys A-J is pressed.
			int keynum = e.getKeyCode();

			if(e.getKeyCode() == KeyEvent.VK_LEFT) 
			{
				leftPressed = true;
			}

			if(e.getKeyCode() == KeyEvent.VK_RIGHT){

				rightPressed = true;
			}

			if(e.getKeyCode() == KeyEvent.VK_A) 
			{
				aPressed = true;
			}

			if(e.getKeyCode() == KeyEvent.VK_D) 
			{
				dPressed = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_E) 
			{
				leftPlayerLeftTokatPressedE = true;
				leftPlayerLeftTokatCounter++;
			}

			if(e.getKeyCode() == KeyEvent.VK_R) 
			{
				leftPlayerRightTokatPressedR = true;
				leftPlayerRightTokatCounter++;
			}

			if(e.getKeyCode() == KeyEvent.VK_O) 
			{
				rightPlayerLeftTokatPressedO = true;
				rightPlayerLeftTokatCounter++;
			}

			if(e.getKeyCode() == KeyEvent.VK_P) 
			{
				rightPlayerRightTokatPressedP = true;
				rightPlayerRightTokatCounter++;
			}
		}

		public void keyReleased(KeyEvent e) { 


			if(e.getKeyCode() == KeyEvent.VK_D) 
			{

				dPressed = false;

			}

			if(e.getKeyCode() == KeyEvent.VK_R) 
			{

				leftPlayerRightTokatPressedR = false;

			}
			if(e.getKeyCode() == KeyEvent.VK_E) 
			{

				leftPlayerLeftTokatPressedE = false;

			}

			if(e.getKeyCode() == KeyEvent.VK_O) 
			{

				rightPlayerLeftTokatPressedO = false;

			}
			if(e.getKeyCode() == KeyEvent.VK_P) 
			{

				rightPlayerRightTokatPressedP = false;

			}
			if(e.getKeyCode() == KeyEvent.VK_A) 
			{

				aPressed = false;

			}

			if(e.getKeyCode() == KeyEvent.VK_LEFT) 
			{

				leftPressed = false;

			}

			if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
			{

				rightPressed = false;

			}

		}

		public void tick(){

			if(leftPressed) 
			{

				cezmi2.moveLeftCezmi2();
				Rectangle oldPosCezmi = cezmi2.boundingBox();
				Rectangle repaintArea3 = oldPosCezmi.union(cezmi2.boundingBox());

				repaint();
				repaint(repaintArea3.x,
						repaintArea3.y,
						repaintArea3.width,
						repaintArea3.height);
			}

			if(leftPlayerLeftTokatPressedE) 
			{
				for(int i = 0;i<tokatList.size();i++){

					if(tokatList.get(i).getType().equals("LeftTokat") && tokatList.get(i).getWhichCezmi()=="LeftCezmi"){
						if(leftPlayerLeftTokatCounter%2==1){

							final int a = i;

							SwingWorker<Boolean,Void> worker = new SwingWorker<Boolean, Void>() {

								@Override
								protected Boolean doInBackground() throws Exception {
									Tokat t =tokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=-90){
										t.rotateL90();	
										TokatPainter.paint(getGraphics(), t);
										tokatList.set(a, t);	
									}
									return null;
								}

							};
							worker.execute();

						}
						if(leftPlayerLeftTokatCounter%2==0){
							final int a = i;

							SwingWorker<Boolean,Void> worker = new SwingWorker<Boolean, Void>() {

								@Override
								protected Boolean doInBackground() throws Exception {
									Tokat t =tokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=0){
										t.rotateML90();	
										TokatPainter.paint(getGraphics(), t);
										tokatList.set(a, t);	
									}
									return null;
								}

							};
							worker.execute();


						}
					}


					Rectangle oldPosTokat = tokatList.get(i).boundingBox();
					Rectangle repaintArea3 = oldPosTokat.union(tokatList.get(i).boundingBox());

					repaint();
					repaint(repaintArea3.x,
							repaintArea3.y,
							repaintArea3.width,
							repaintArea3.height);
				}

			}
			if(leftPlayerRightTokatPressedR) 
			{
				for(int i =0;i<tokatList.size();i++){
					if(tokatList.get(i).getType().equals("RightTokat") && tokatList.get(i).getWhichCezmi() == "LeftCezmi"){
						if(leftPlayerRightTokatCounter%2==1){
							final int a = i;

							SwingWorker<Boolean,Void> worker = new SwingWorker<Boolean, Void>() {

								@Override
								protected Boolean doInBackground() throws Exception {
									Tokat t =tokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=90){
										t.rotate90();	
										TokatPainter.paint(getGraphics(), t);
										tokatList.set(a, t);	
									}
									return null;
								}

							};
							worker.execute();

						}
						if(leftPlayerRightTokatCounter%2==0){

							final int a = i;

							SwingWorker<Boolean,Void> worker = new SwingWorker<Boolean, Void>() {

								@Override
								protected Boolean doInBackground() throws Exception {
									Tokat t =tokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=0){
										t.rotateM90();	
										TokatPainter.paint(getGraphics(), t);
										tokatList.set(a, t);	
									}
									return null;
								}

							};
							worker.execute();

						}
					}


					Rectangle oldPosTokat2 = tokatList.get(i).boundingBox();
					Rectangle repaintArea3 = oldPosTokat2.union(tokatList.get(i).boundingBox());

					repaint();
					repaint(repaintArea3.x,
							repaintArea3.y,
							repaintArea3.width,
							repaintArea3.height);
				}
			}
			if(rightPlayerLeftTokatPressedO) 
			{
				for(int i = 0;i<tokatList.size();i++){

					if(tokatList.get(i).getType().equals("LeftTokat") && tokatList.get(i).getWhichCezmi()=="RightCezmi"){
						if(rightPlayerLeftTokatCounter%2==1){

							final int a = i;

							SwingWorker<Boolean,Void> worker = new SwingWorker<Boolean, Void>() {

								@Override
								protected Boolean doInBackground() throws Exception {
									Tokat t =tokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=-90){
										t.rotateL90();	
										TokatPainter.paint(getGraphics(), t);
										tokatList.set(a, t);	
									}
									return null;
								}

							};
							worker.execute();


						}
						if(rightPlayerLeftTokatCounter%2==0){
							final int a = i;

							SwingWorker<Boolean,Void> worker = new SwingWorker<Boolean, Void>() {

								@Override
								protected Boolean doInBackground() throws Exception {
									Tokat t =tokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=0){
										t.rotateML90();	
										TokatPainter.paint(getGraphics(), t);
										tokatList.set(a, t);	
									}
									return null;
								}

							};
							worker.execute();

						}
					}


					Rectangle oldPosTokat = tokatList.get(i).boundingBox();
					Rectangle repaintArea3 = oldPosTokat.union(tokatList.get(i).boundingBox());

					repaint();
					repaint(repaintArea3.x,
							repaintArea3.y,
							repaintArea3.width,
							repaintArea3.height);
				}

			}
			if(rightPlayerRightTokatPressedP) 
			{
				for(int i =0;i<tokatList.size();i++){
					if(tokatList.get(i).getType().equals("RightTokat") && tokatList.get(i).getWhichCezmi() == "RightCezmi"){
						if(rightPlayerRightTokatCounter%2==1){
							final int a = i;

							SwingWorker<Boolean,Void> worker = new SwingWorker<Boolean, Void>() {

								@Override
								protected Boolean doInBackground() throws Exception {
									Tokat t =tokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=90){
										t.rotate90();	
										TokatPainter.paint(getGraphics(), t);
										tokatList.set(a, t);	
									}
									return null;
								}

							};
							worker.execute();

						}
						if(rightPlayerRightTokatCounter%2==0){
							final int a = i;

							SwingWorker<Boolean,Void> worker = new SwingWorker<Boolean, Void>() {

								@Override
								protected Boolean doInBackground() throws Exception {
									Tokat t =tokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=0){
										t.rotateM90();	
										TokatPainter.paint(getGraphics(), t);
										tokatList.set(a, t);	
									}
									return null;
								}

							};
							worker.execute();

						}
					}


					Rectangle oldPosTokat2 = tokatList.get(i).boundingBox();
					Rectangle repaintArea3 = oldPosTokat2.union(tokatList.get(i).boundingBox());

					repaint();
					repaint(repaintArea3.x,
							repaintArea3.y,
							repaintArea3.width,
							repaintArea3.height);
				}
			}

			if(aPressed) 
			{
				cezmi.moveLeftCezmi();
				Rectangle oldPosCezmi = cezmi.boundingBox();
				Rectangle repaintArea3 = oldPosCezmi.union(cezmi.boundingBox());

				repaint();
				repaint(repaintArea3.x,
						repaintArea3.y,
						repaintArea3.width,
						repaintArea3.height);
			}
			if(dPressed) 
			{
				cezmi.moveRightCezmi();
				Rectangle oldPosCezmi = cezmi.boundingBox();
				Rectangle repaintArea3 = oldPosCezmi.union(cezmi.boundingBox());

				repaint();
				repaint(repaintArea3.x,
						repaintArea3.y,
						repaintArea3.width,
						repaintArea3.height);
			}

			if(rightPressed){

				cezmi2.moveRightCezmi2();
				Rectangle oldPosCezmi = cezmi2.boundingBox();
				Rectangle repaintArea3 = oldPosCezmi.union(cezmi2.boundingBox());

				repaint();
				repaint(repaintArea3.x,
						repaintArea3.y,
						repaintArea3.width,
						repaintArea3.height);
			}
		}

		public void keyTyped(KeyEvent e) { }

		// this is the callback for the timer
		public void actionPerformed(ActionEvent e) {
			// modifes: both the ball and the window that this listener owns
			// effects: causes the ball to move and the window to be updated
			//          to show the new position of the ball.
			tick();
			SwingWorker<Boolean,Void> ballWorker = new SwingWorker<Boolean, Void>() {

				@Override
				protected Boolean doInBackground() throws Exception {
					for(int i = 0; i<ballList.size();i++){
						ballList.get(i).move();
						ApplicationWindowController.getScorePlayer1();
						ApplicationWindowController.getScorePlayer2();
							}
					
					return null;
				}

			};
			ballWorker.execute();


			SwingWorker<Boolean,Void> cezeryeWorker = new SwingWorker<Boolean, Void>() {

				@Override
				protected Boolean doInBackground() throws Exception {
					cezerye.cezeryeMotion();
					return null;
				}

			};
			cezeryeWorker.execute();

			// Have Swing tell the AnimationWindow to run its paint()
			// method.  One could also call repaint(), but this would
			// repaint the entire window as opposed to only the portion that
			// has changed.
			repaint();
		}
	}
}
