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
import org.xml.sax.XMLReader;

import main.Test;
import model.BouncingBall;
import model.Cezerye;
import model.Cezmi;
import model.Firildak;
import model.Gizmo;
import model.Takoz;
import model.Tokat;
import model.Walls;
import model.XmlReader;
import view.BallPainter;
import view.CezeryePainter;
import view.FirildakPainter;
import view.TakozPainter;
import view.TokatPainter;
import view.CezmiPainter;
import view.WallPainter;


public class XmlAnimationWindow extends JPanel{
	// overview: an AnimationWindow is an area on the screen in which a
	// bouncing ball animation occurs.  AnimationWindows have two modes:
	// on and off.  During the on mode the ball moves, during the off
	// mode the ball doesn't move.

	private AnimationEventListener eventListener;
	private static ArrayList<BouncingBall> xballList;
	private static ArrayList<Cezmi> xcezmiList;
	private static ArrayList<Tokat> xtokatList;
	private static ArrayList<Takoz> xtakozList;
	private static ArrayList<Firildak> xfirildakList;
	private static ArrayList<String> keyList;
	private static Cezerye xcezerye;
	private Timer timer;
	private boolean mode;
	private boolean editmode;
	private Walls engel;
	private Walls leftwall;
	private Walls rightwall;
	private Walls topwall;
	private Walls bottomwall;
	private int cezeryeCreationTime;
	private long sysCurTime;
	private static boolean isLevel2;

	private boolean runPressed;
	private boolean stopPressed;
	private static String addString;
	BouncingBall a;
	BouncingBall b;

	public boolean isRunPressed() {
		return runPressed;
	}

	public void setRunPressed(boolean runPressed) {
		this.runPressed = runPressed;
	}

	public boolean isStopPressed() {
		return stopPressed;
	}

	public void setStopPressed(boolean stopPressed) {
		this.stopPressed = stopPressed;
	}

	public static String getAddString() {
		return addString;
	}

	public static void setAddString(String addString) {
		XmlAnimationWindow.addString = addString;
	}

	public static boolean isLevel2() {
		return isLevel2;
	}

	public static void setLevel2(boolean isLevel2) {
		XmlAnimationWindow.isLevel2 = isLevel2;
	}

	public static ArrayList<String> getKeyList() {
		return keyList;
	}

	public static void setKeyList(ArrayList<String> keyList) {
		XmlAnimationWindow.keyList = keyList;
	}

	public static ArrayList<BouncingBall> getXballList() {
		return xballList;
	}

	public static void setXballList(ArrayList<BouncingBall> ballList) {
		xballList = ballList;
	}

	public static ArrayList<Cezmi> getXcezmiList() {
		return xcezmiList;
	}

	public static void setXcezmiList(ArrayList<Cezmi> xcezmiList) {
		XmlAnimationWindow.xcezmiList = xcezmiList;
	}

	public static ArrayList<Tokat> getXtokatList() {
		return xtokatList;
	}

	public static void setXtokatList(ArrayList<Tokat> xtokatList) {
		XmlAnimationWindow.xtokatList = xtokatList;
	}

	public static ArrayList<Takoz> getXtakozList() {
		return xtakozList;
	}

	public static void setXtakozList(ArrayList<Takoz> xtakozList) {
		XmlAnimationWindow.xtakozList = xtakozList;
	}

	public static ArrayList<Firildak> getXfirildakList() {
		return xfirildakList;
	}

	public static void setXfirildakList(ArrayList<Firildak> xfirildakList) {
		XmlAnimationWindow.xfirildakList = xfirildakList;
	}

	public static  Cezerye getXcezerye() {
		return xcezerye;
	}

	public  void setXcezerye(Cezerye xcezerye) {
		this.xcezerye = xcezerye;
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

	public XmlAnimationWindow() {
		// effects: initializes this to be in the off mode.


		super();                    // do the standard JPanel setup stuff

		setXtokatList(XmlReader.tokatList);
		setXtakozList(XmlReader.takozList);
		setXballList(XmlReader.ballList);
		setXfirildakList(XmlReader.firildakList);
		setXcezmiList(XmlReader.cezmiList);
		setXcezerye(XmlReader.cezerye);
		setKeyList(XmlReader.keyList);
		setLevel2(XmlReader.isLevel2);

		engel = new Walls(240,440,(int)(0.25 * Test.L),3*Test.L);
		leftwall = new Walls(0,0,1,(int)25 * Test.L);
		rightwall = new Walls(25*Test.L,0,1,(int)25 * Test.L);
		topwall = new Walls(0,0,(int)25 * Test.L,1);
		bottomwall = new Walls(0,25*Test.L,(int)25 * Test.L,1);




		// this only initializes the timer, we actually start and stop the
		// timer in the setMode() method
		eventListener = new AnimationEventListener();
		// The first parameter is how often (in milliseconds) the timer
		// should call us back.  50 milliseconds = 20 frames/second
		timer = new Timer(50, eventListener);
		this.setBackground(Color.BLACK);
		mode = false;
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

		for(int i = 0; i<xtokatList.size();i++){
			TokatPainter.paint(g, xtokatList.get(i));
			//System.out.println(tokatList.get(i).getType());
		}

		for(int i = 0; i<xtakozList.size();i++){
			TakozPainter.paint(g, xtakozList.get(i));
		}

		for(int i = 0; i<xfirildakList.size();i++){
			FirildakPainter.paint(g, xfirildakList.get(i));
		}


		for(int i = 0; i<xballList.size();i++){
			BallPainter.paint(g, xballList.get(i));
		}





		for(int i = 0; i<xcezmiList.size();i++){
			CezmiPainter.paint(g, xcezmiList.get(i));
		}

	//	xcezerye = XmlReader.cezerye;
	//	BouncingBall.setCezerye(xcezerye);
		CezeryePainter.paint(g, xcezerye);
		
		WallPainter.paint(g, engel);
		WallPainter.paint(g, leftwall);
		WallPainter.paint(g, rightwall);
		WallPainter.paint(g, topwall);
		WallPainter.paint(g, bottomwall);

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
			}else {
				rootElement.setAttribute("level", "1");
			}
			Element balls = doc.createElement("ball");
			rootElement.appendChild(balls);

			for(int i = 0; i<xballList.size();i++){

				Attr attr = doc.createAttribute("x");
				attr.setValue(Integer.toString(xballList.get(i).getX()/Test.L));
				balls.setAttributeNode(attr);

				Attr attr2 = doc.createAttribute("y");
				attr2.setValue(Integer.toString(xballList.get(i).getY()/Test.L));
				balls.setAttributeNode(attr2);

				Attr attr3 = doc.createAttribute("xVelocity");
				attr3.setValue(Integer.toString((int)xballList.get(i).getVx()));
				balls.setAttributeNode(attr3);

				Attr attr4 = doc.createAttribute("yVelocity");
				attr4.setValue(Integer.toString((int)xballList.get(i).getVy()));
				balls.setAttributeNode(attr4);

			}

			Element cezmi1 = doc.createElement("cezmi1");
			rootElement.appendChild(cezmi1);

			for(int i = 0; i< xcezmiList.size()-1 ; i++){

				Attr attr = doc.createAttribute("x");
				attr.setValue(Integer.toString(xcezmiList.get(0).getPosX()/Test.L));
				cezmi1.setAttributeNode(attr);

				Attr attr2 = doc.createAttribute("y");
				attr2.setValue(Integer.toString(xcezmiList.get(0).getPosY()/Test.L));
				cezmi1.setAttributeNode(attr2);

				Attr attr3 = doc.createAttribute("score");
				attr3.setValue(Integer.toString(xcezmiList.get(0).getScore()));
				cezmi1.setAttributeNode(attr3);
			}

			Element cezmi2 = doc.createElement("cezmi2");
			rootElement.appendChild(cezmi2);

			for(int i = 0; i< xcezmiList.size()-1 ; i++){

				Attr attr = doc.createAttribute("x");
				attr.setValue(Integer.toString(xcezmiList.get(1).getPosX()/Test.L));
				cezmi2.setAttributeNode(attr);

				Attr attr2 = doc.createAttribute("y");
				attr2.setValue(Integer.toString(xcezmiList.get(1).getPosY()/Test.L));
				cezmi2.setAttributeNode(attr2);

				Attr attr3 = doc.createAttribute("score");
				attr3.setValue(Integer.toString(xcezmiList.get(1).getScore()));
				cezmi2.setAttributeNode(attr3);
			}

			Element cezeryes = doc.createElement("cezeryes");
			rootElement.appendChild(cezeryes);

			for(int i = 0 ; i<1.;i++){

				Element cezerye = doc.createElement("cezerye");

				Attr attr = doc.createAttribute("x");
				attr.setValue(Integer.toString(xcezerye.getX()/Test.L));
				cezerye.setAttributeNode(attr);

				Attr attr2 = doc.createAttribute("y");
				attr2.setValue(Integer.toString(xcezerye.getY()/Test.L));
				cezerye.setAttributeNode(attr2);

				Attr attr3 = doc.createAttribute("time");
				attr3.setValue(Integer.toString(xcezerye.getCreationTime()));
				cezerye.setAttributeNode(attr3);

				cezeryes.appendChild(cezerye);
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

			Element gizmos = doc.createElement("gizmos");
			rootElement.appendChild(gizmos);

			//http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/

			//For gizmos
			//For takoz
			for(int x = 0; x<xtakozList.size();x++){

				//For squareTakoz
				if(xtakozList.get(x).getTakozType() == "Square"){

					Element squareTakoz = doc.createElement("squareTakoz");

					Attr attr = doc.createAttribute("x");
					attr.setValue(Integer.toString(xtakozList.get(x).getX()/Test.L));
					squareTakoz.setAttributeNode(attr);

					Attr attr2 = doc.createAttribute("y");
					attr2.setValue(Integer.toString(xtakozList.get(x).getY()/Test.L));
					squareTakoz.setAttributeNode(attr2);

					gizmos.appendChild(squareTakoz);

				}else if(xtakozList.get(x).getTakozType() == "Triangular"){

					Element triangleTakoz = doc.createElement("triangleTakoz");

					Attr attr = doc.createAttribute("x");
					attr.setValue(Integer.toString(xtakozList.get(x).getX()/Test.L));
					triangleTakoz.setAttributeNode(attr);

					Attr attr2 = doc.createAttribute("y");
					attr2.setValue(Integer.toString(xtakozList.get(x).getY()/Test.L));
					triangleTakoz.setAttributeNode(attr2);

					Attr attr3 = doc.createAttribute("orientation");
					attr3.setValue(Integer.toString(xtakozList.get(x).getOrientation()));
					triangleTakoz.setAttributeNode(attr3);

					gizmos.appendChild(triangleTakoz);
				}
			}
			//For firildak

			for(int y = 0; y<xfirildakList.size();y++){

				Element firildak = doc.createElement("firildak");

				Attr attr = doc.createAttribute("x");
				attr.setValue(Integer.toString(xfirildakList.get(y).getX()/Test.L));
				firildak.setAttributeNode(attr);

				Attr attr2 = doc.createAttribute("y");
				attr2.setValue(Integer.toString(xfirildakList.get(y).getY()/Test.L));
				firildak.setAttributeNode(attr2);

				Attr attr3 = doc.createAttribute("angle");
				attr3.setValue(Double.toString(xfirildakList.get(y).getAngle()));
				firildak.setAttributeNode(attr3);

				gizmos.appendChild(firildak);
			}
			//For tokat
			for(int j = 0; j<xtokatList.size() ; j++){

				if(xtokatList.get(j).getType() == "LeftTokat"){

					Element leftTokat = doc.createElement("leftTokat");

					Attr attr = doc.createAttribute("x");
					attr.setValue(Integer.toString(xtokatList.get(j).getX()/Test.L));
					leftTokat.setAttributeNode(attr);

					Attr attr2 = doc.createAttribute("y");
					attr2.setValue(Integer.toString(xtokatList.get(j).getY()/Test.L));
					leftTokat.setAttributeNode(attr2);

					Attr attr3 = doc.createAttribute("orientation");
					attr3.setValue(Integer.toString(xtokatList.get(j).getOrientation()));
					leftTokat.setAttributeNode(attr3);

					gizmos.appendChild(leftTokat);

				}else if(xtokatList.get(j).getType() == "RightTokat"){

					Element rightTokat = doc.createElement("rightTokat");

					Attr attr = doc.createAttribute("x");
					attr.setValue(Integer.toString(xtokatList.get(j).getX()/Test.L));
					rightTokat.setAttributeNode(attr);

					Attr attr2 = doc.createAttribute("y");
					attr2.setValue(Integer.toString(xtokatList.get(j).getY()/Test.L));
					rightTokat.setAttributeNode(attr2);

					Attr attr3 = doc.createAttribute("orientation");
					attr3.setValue(Integer.toString(xtokatList.get(j).getOrientation()));
					rightTokat.setAttributeNode(attr3);

					gizmos.appendChild(rightTokat);
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


	class AnimationEventListener extends MouseAdapter
	implements MouseMotionListener, KeyListener, ActionListener
	{

		// overview: AnimationEventListener is an inner class that
		// responds to all sorts of external events, and provides the
		// required semantic operations for our particular program.  It
		// owns, and sends semantic actions to the ball and window of the
		// outer class

		// MouseAdapter gives us empty methods for the MouseListener
		// interface: mouseClicked, mouseEntered, mouseExited, mousePressed,
		// and mouseReleased.

		// for this example we only need to override mouseClicked

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

		public void mouseClicked(MouseEvent e) {
			// modifes: the ball that this listener owns
			// effects: causes the ball to be bumped in a random direction

			if(isStopPressed() == false && isMode() == true && isEditmode() == true && isRunPressed() == false && SwingUtilities.isLeftMouseButton(e)){

				if(addString.equals("triangleTakoz")){
					if(e.getY() < 500-6*Test.L){

						Takoz triangle = new Takoz(e.getX(), e.getY(), "Triangular");

						if(!xtakozList.isEmpty()){

							int size = xtakozList.size();

							for(int i =0;i<xtakozList.size();i++){
								if(triangle.getX()>=xtakozList.get(i).getX() && triangle.getX()<=xtakozList.get(i).getX()+xtakozList.get(i).getWidth() &&triangle.getY()>=xtakozList.get(i).getY() && triangle.getY()<=xtakozList.get(i).getY()+xtakozList.get(i).getHeight() )
								{
									xtakozList.remove(xtakozList.get(i));
									repaint();
								}
							}
							if(size == xtakozList.size()){
								validPosition= true;
								if(!xfirildakList.isEmpty()){
									for(int i=0;i<xfirildakList.size();i++){
										if(triangle.getX()>=xfirildakList.get(i).getX() && triangle.getX()<=xfirildakList.get(i).getX()+xfirildakList.get(i).getWidth() &&triangle.getY()>=xfirildakList.get(i).getY() && triangle.getY()<=xfirildakList.get(i).getY()+xfirildakList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(!xtokatList.isEmpty()){
									for(int i=0;i<xtokatList.size();i++){
										if(triangle.getX()>=xtokatList.get(i).getX() && triangle.getX()<=xtokatList.get(i).getX()+xtokatList.get(i).getWidth() &&triangle.getY()>=xtokatList.get(i).getY() && triangle.getY()<=xtokatList.get(i).getY()+xtokatList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(validPosition){
									xtakozList.add(triangle);
									TakozPainter.paint(getGraphics(), triangle);
								}
							}
						}else{
							validPosition= true;
							if(!xfirildakList.isEmpty()){
								for(int i=0;i<xfirildakList.size();i++){
									if(triangle.getX()>=xfirildakList.get(i).getX() && triangle.getX()<=xfirildakList.get(i).getX()+xfirildakList.get(i).getWidth() &&triangle.getY()>=xfirildakList.get(i).getY() && triangle.getY()<=xfirildakList.get(i).getY()+xfirildakList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(!xtokatList.isEmpty()){
								for(int i=0;i<xtokatList.size();i++){
									if(triangle.getX()>=xtokatList.get(i).getX() && triangle.getX()<=xtokatList.get(i).getX()+xtokatList.get(i).getWidth() &&triangle.getY()>=xtokatList.get(i).getY() && triangle.getY()<=xtokatList.get(i).getY()+xtokatList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(validPosition){
								xtakozList.add(triangle);
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
						if(!xtakozList.isEmpty())
						{
							int size = xtakozList.size();

							for(int i =0;i<xtakozList.size();i++){

								if(square.getX()>=xtakozList.get(i).getX() && square.getX()<=xtakozList.get(i).getX()+xtakozList.get(i).getWidth() && square.getY()>=xtakozList.get(i).getY() && square.getY()<=xtakozList.get(i).getY()+xtakozList.get(i).getHeight() )
								{
									xtakozList.remove(xtakozList.get(i));
									repaint();
								}
							}
							if(size == xtakozList.size()){
								validPosition= true;
								if(!xfirildakList.isEmpty()){
									for(int i=0;i<xfirildakList.size();i++){
										if(square.getX()>=xfirildakList.get(i).getX() && square.getX()<=xfirildakList.get(i).getX()+xfirildakList.get(i).getWidth() &&square.getY()>=xfirildakList.get(i).getY() && square.getY()<=xfirildakList.get(i).getY()+xfirildakList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(!xtokatList.isEmpty()){
									for(int i=0;i<xtokatList.size();i++){
										if(square.getX()>=xtokatList.get(i).getX() && square.getX()<=xtokatList.get(i).getX()+xtokatList.get(i).getWidth() &&square.getY()>=xtokatList.get(i).getY() && square.getY()<=xtokatList.get(i).getY()+xtokatList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(validPosition){
									xtakozList.add(square);
									TakozPainter.paint(getGraphics(), square);
								}
							}
						}else{
							validPosition= true;
							if(!xfirildakList.isEmpty()){
								for(int i=0;i<xfirildakList.size();i++){
									if(square.getX()>=xfirildakList.get(i).getX() && square.getX()<=xfirildakList.get(i).getX()+xfirildakList.get(i).getWidth() &&square.getY()>=xfirildakList.get(i).getY() && square.getY()<=xfirildakList.get(i).getY()+xfirildakList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(!xtokatList.isEmpty()){
								for(int i=0;i<xtokatList.size();i++){
									if(square.getX()>=xtokatList.get(i).getX() && square.getX()<=xtokatList.get(i).getX()+xtokatList.get(i).getWidth() &&square.getY()>=xtokatList.get(i).getY() && square.getY()<=xtokatList.get(i).getY()+xtokatList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(validPosition){
								xtakozList.add(square);
								TakozPainter.paint(getGraphics(), square);
							}
						}
					}else{
						System.out.println("Invalid coordinate");
					}
				}else if(addString.equals("firildak")){
					if(e.getY() < 500-6*Test.L){
						Firildak firildak = new Firildak(e.getX(), e.getY());
						if(!xfirildakList.isEmpty()){
							int size = xfirildakList.size();
							for(int i =0;i<xfirildakList.size();i++){
								if(firildak.getX()>=xfirildakList.get(i).getX() && firildak.getX()<=xfirildakList.get(i).getX()+xfirildakList.get(i).getWidth() && firildak.getY()>=xfirildakList.get(i).getY() && firildak.getY()<=xfirildakList.get(i).getY()+xfirildakList.get(i).getHeight() )
								{
									xfirildakList.remove(xfirildakList.get(i));
									repaint();
								}
							}
							if(size == xfirildakList.size()){
								validPosition=true;
								if(!xtokatList.isEmpty()){
									for(int i=0;i<xtokatList.size();i++){
										if(firildak.getX()>=xtokatList.get(i).getX() && firildak.getX()<=xtokatList.get(i).getX()+xtokatList.get(i).getWidth() &&firildak.getY()>=xtokatList.get(i).getY() && firildak.getY()<=xtokatList.get(i).getY()+xtokatList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(!xtakozList.isEmpty()){
									for(int i=0;i<xtakozList.size();i++){
										if(firildak.getX()>=xtakozList.get(i).getX() && firildak.getX()<=xtakozList.get(i).getX()+xtakozList.get(i).getWidth() &&firildak.getY()>=xtakozList.get(i).getY() && firildak.getY()<=xtakozList.get(i).getY()+xtakozList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(validPosition){
									xfirildakList.add(firildak);
									FirildakPainter.paint(getGraphics(), firildak);		
								}
							}
						}
						else
						{
							validPosition=true;
							if(!xtokatList.isEmpty()){
								for(int i=0;i<xtokatList.size();i++){
									if(firildak.getX()>=xtokatList.get(i).getX() && firildak.getX()<=xtokatList.get(i).getX()+xtokatList.get(i).getWidth() &&firildak.getY()>=xtokatList.get(i).getY() && firildak.getY()<=xtokatList.get(i).getY()+xtokatList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(!xtakozList.isEmpty()){
								for(int i=0;i<xtakozList.size();i++){
									if(firildak.getX()>=xtakozList.get(i).getX() && firildak.getX()<=xtakozList.get(i).getX()+xtakozList.get(i).getWidth() &&firildak.getY()>=xtakozList.get(i).getY() && firildak.getY()<=xtakozList.get(i).getY()+xtakozList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(validPosition){
								xfirildakList.add(firildak);
								FirildakPainter.paint(getGraphics(), firildak);		
							}						
						}
					}else{
						System.out.println("Invalid coordinate");
					}
				}else if(addString.equals("leftTokat")){
					if(e.getY() < 500-6*Test.L){
						Tokat left = new Tokat(e.getX(), e.getY(), "LeftTokat");
						if(!xtokatList.isEmpty()){
							int size = xtokatList.size();
							for(int i =0;i<xtokatList.size();i++){
								if(left.getX()>=xtokatList.get(i).getX() && left.getX()<=xtokatList.get(i).getX()+xtokatList.get(i).getWidth() && left.getY()>=xtokatList.get(i).getY() && left.getY()<=xtokatList.get(i).getY()+xtokatList.get(i).getHeight() )
								{
									xtokatList.remove(xtokatList.get(i));
									repaint();
								}
							}
							if(size == xtokatList.size()){
								validPosition=true;

								if(!xfirildakList.isEmpty()){
									for(int i=0;i<xfirildakList.size();i++){
										if(left.getX()>=xfirildakList.get(i).getX() && left.getX()<=xfirildakList.get(i).getX()+xfirildakList.get(i).getWidth() &&left.getY()>=xfirildakList.get(i).getY() && left.getY()<=xfirildakList.get(i).getY()+xfirildakList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(!xtakozList.isEmpty()){
									for(int i=0;i<xtakozList.size();i++){
										if(left.getX()>=xtakozList.get(i).getX() && left.getX()<=xtakozList.get(i).getX()+xtakozList.get(i).getWidth() &&left.getY()>=xtakozList.get(i).getY() && left.getY()<=xtakozList.get(i).getY()+xtakozList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(validPosition){
									xtokatList.add(left);
									TokatPainter.paint(getGraphics(), left);
								}
							}
						}else
						{
							validPosition=true;

							if(!xfirildakList.isEmpty()){
								for(int i=0;i<xfirildakList.size();i++){
									if(left.getX()>=xfirildakList.get(i).getX() && left.getX()<=xfirildakList.get(i).getX()+xfirildakList.get(i).getWidth() &&left.getY()>=xfirildakList.get(i).getY() && left.getY()<=xfirildakList.get(i).getY()+xfirildakList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(!xtakozList.isEmpty()){
								for(int i=0;i<xtakozList.size();i++){
									if(left.getX()>=xtakozList.get(i).getX() && left.getX()<=xtakozList.get(i).getX()+xtakozList.get(i).getWidth() &&left.getY()>=xtakozList.get(i).getY() && left.getY()<=xtakozList.get(i).getY()+xtakozList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(validPosition){
								xtokatList.add(left);
								TokatPainter.paint(getGraphics(), left);
							}
						}
					}else{
						System.out.println("Invalid coordinate");
					}
				}else if(addString.equals("rightTokat")){
					if(e.getY() < 500-6*Test.L){
						Tokat right = new Tokat(e.getX(), e.getY(), "RightTokat");
						if(!xtokatList.isEmpty()){
							int size = xtokatList.size();
							for(int i =0;i<xtokatList.size();i++){
								if(right.getX()>=xtokatList.get(i).getX() && right.getX()<=xtokatList.get(i).getX()+xtokatList.get(i).getWidth() && right.getY()>=xtokatList.get(i).getY() && right.getY()<=xtokatList.get(i).getY()+xtokatList.get(i).getHeight() )
								{	
									xtokatList.remove(xtokatList.get(i));
									repaint();
								}
							}
							if(size==xtokatList.size()){
								validPosition=true;

								if(!xfirildakList.isEmpty()){
									for(int i=0;i<xfirildakList.size();i++){
										if(right.getX()>=xfirildakList.get(i).getX() && right.getX()<=xfirildakList.get(i).getX()+xfirildakList.get(i).getWidth() &&right.getY()>=xfirildakList.get(i).getY() && right.getY()<=xfirildakList.get(i).getY()+xfirildakList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(!xtakozList.isEmpty()){
									for(int i=0;i<xtakozList.size();i++){
										if(right.getX()>=xtakozList.get(i).getX() && right.getX()<=xtakozList.get(i).getX()+xtakozList.get(i).getWidth() &&right.getY()>=xtakozList.get(i).getY() && right.getY()<=xtakozList.get(i).getY()+xtakozList.get(i).getHeight() )
										{
											System.out.println("Invalid position");
											validPosition= false;
										}
									}
								}
								if(validPosition){
									xtokatList.add(right);
									TokatPainter.paint(getGraphics(), right);
								}
							}
						}else
						{									
							validPosition=true;

							if(!xfirildakList.isEmpty()){
								for(int i=0;i<xfirildakList.size();i++){
									if(right.getX()>=xfirildakList.get(i).getX() && right.getX()<=xfirildakList.get(i).getX()+xfirildakList.get(i).getWidth() &&right.getY()>=xfirildakList.get(i).getY() && right.getY()<=xfirildakList.get(i).getY()+xfirildakList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}
							if(!xtakozList.isEmpty()){
								for(int i=0;i<xtakozList.size();i++){
									if(right.getX()>=xtakozList.get(i).getX() && right.getX()<=xtakozList.get(i).getX()+xtakozList.get(i).getWidth() &&right.getY()>=xtakozList.get(i).getY() && right.getY()<=xtakozList.get(i).getY()+xtakozList.get(i).getHeight() )
									{
										System.out.println("Invalid position");
										validPosition= false;
									}
								}
							}

							if(validPosition){
								xtokatList.add(right);
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

			for(int i = 0; i < xtakozList.size();i++){
				if(dragx >= xtakozList.get(i).getX() && dragx <= xtakozList.get(i).getX() + xtakozList.get(i).getWidth() && dragy >= xtakozList.get(i).getY() && dragy <= xtakozList.get(i).getY() + xtakozList.get(i).getHeight()){

					takozmove = true;
					firildakmove = false;
					tokatmove = false;

					takoz = xtakozList.get(i);
				}
			}

			for(int i = 0; i < xfirildakList.size();i++){
				if(dragx >= xfirildakList.get(i).getX() && dragx <= xfirildakList.get(i).getX() + xfirildakList.get(i).getWidth() && dragy >= xfirildakList.get(i).getY() && dragy <= xfirildakList.get(i).getY() + xfirildakList.get(i).getHeight()){

					firildakmove = true;
					takozmove = false;
					tokatmove = false;

					firildak = xfirildakList.get(i);
				}
			}

			for(int i = 0; i < xtokatList.size();i++){
				if(dragx >= xtokatList.get(i).getX() && dragx <= xtokatList.get(i).getX() + xtokatList.get(i).getWidth() && dragy >= xtokatList.get(i).getY() && dragy <= xtokatList.get(i).getY() + xtokatList.get(i).getHeight()){

					tokatmove = true;
					takozmove = false;
					firildakmove = false;
					tokat = xtokatList.get(i);
				}
			}

			if(isStopPressed() == false && isMode() == true && isEditmode()== true && isRunPressed() == false && SwingUtilities.isRightMouseButton(e)){
				if(e.getY() < 500-6*Test.L){
					if(takozmove){

						xtakozList.remove(takoz);
						takoz.setX(e.getX());
						takoz.setY(e.getY());

						xtakozList.add(takoz);
						repaint();
					}
					else if(tokatmove){

						xtokatList.remove(tokat);
						tokat.setX(e.getX());
						tokat.setY(e.getY());

						xtokatList.add(tokat);
						repaint();	
					}
					else if(firildakmove){

						xfirildakList.remove(firildak);
						firildak.setX(e.getX());
						firildak.setY(e.getY());

						xfirildakList.add(firildak);						
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
					xtakozList.remove(takoz);
					takoz.rotateClockwise();
					takoz.setOrientation(takoz.getOrientation()+90);
					xtakozList.add(takoz);
					repaint();

				}else if(tokatmove){
					System.out.println("takozmove: " + takozmove + " tokatmove: "+ tokatmove);
					xtokatList.remove(tokat);
					tokat.rotateClockwise();
					tokat.setOrientation(tokat.getOrientation()+90);
					xtokatList.add(tokat);
					repaint();
				}
			}
		}

		// Here's the MouseMotionListener interface
		public void mouseDragged(MouseEvent e) { }
		public void mouseMoved(MouseEvent e) { }

		// Here's the KeyListener interface
		public void keyPressed(KeyEvent e) {
			// modifes: the ball that this listener owns
			// effects: causes the ball to be bumped in a random direction but
			//          only if one of the keys A-J is pressed.
			int keynum = e.getKeyCode();

			if(e.getKeyCode() == KeyEvent.VK_LEFT) //	Move right 39.
			{
				leftPressed = true;
			}

			if(e.getKeyCode() == KeyEvent.VK_A) //	Move right 39.
			{
				aPressed = true;
			}

			if(e.getKeyCode() == KeyEvent.VK_D) //	Move right 39.
			{
				dPressed = true;
			}

			if(e.getKeyCode() == KeyEvent.VK_RIGHT){

				rightPressed = true;
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

			if(e.getKeyCode() == KeyEvent.VK_O) //	Move right 39.
			{
				rightPlayerLeftTokatPressedO = true;
				rightPlayerLeftTokatCounter++;
			}

			if(e.getKeyCode() == KeyEvent.VK_P) //	Move right 39.
			{
				rightPlayerRightTokatPressedP = true;
				rightPlayerRightTokatCounter++;
			}


		}
		public void keyReleased(KeyEvent e) { 
			if(e.getKeyCode() == KeyEvent.VK_D) //	Move right 39.
			{

				dPressed = false;

			}


			if(e.getKeyCode() == KeyEvent.VK_A) //	Move right 39.
			{

				aPressed = false;

			}

			if(e.getKeyCode() == KeyEvent.VK_LEFT) //	Move right 39.
			{

				leftPressed = false;

			}


			if(e.getKeyCode() == KeyEvent.VK_RIGHT) //	Move right 39.
			{

				rightPressed = false;

			}

			if(e.getKeyCode() == KeyEvent.VK_R) //	Move right 39.
			{

				leftPlayerRightTokatPressedR = false;

			}
			if(e.getKeyCode() == KeyEvent.VK_E) //	Move right 39.
			{

				leftPlayerLeftTokatPressedE = false;

			}

			if(e.getKeyCode() == KeyEvent.VK_O) //	Move right 39.
			{

				rightPlayerLeftTokatPressedO = false;

			}
			if(e.getKeyCode() == KeyEvent.VK_P) //	Move right 39.
			{

				rightPlayerRightTokatPressedP = false;

			}
		}



		public void tick(){


			if(leftPressed) 
			{

				xcezmiList.get(1).moveLeftCezmi2();
				Rectangle oldPosCezmi = xcezmiList.get(1).boundingBox();
				Rectangle repaintArea3 = oldPosCezmi.union(xcezmiList.get(1).boundingBox());

				repaint();
				repaint(repaintArea3.x,
						repaintArea3.y,
						repaintArea3.width,
						repaintArea3.height);
			}

			if(leftPlayerLeftTokatPressedE) 
			{
				for(int i = 0;i<xtokatList.size();i++){

					if(xtokatList.get(i).getType().equals("LeftTokat") && xtokatList.get(i).getWhichCezmi()=="LeftCezmi"){
						if(leftPlayerLeftTokatCounter%2==1){

							final int a = i;

							SwingWorker<Boolean,Void> worker = new SwingWorker<Boolean, Void>() {

								@Override
								protected Boolean doInBackground() throws Exception {
									Tokat t =xtokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=-90){
										t.rotateL90();	
										TokatPainter.paint(getGraphics(), t);
										xtokatList.set(a, t);	
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
									Tokat t =xtokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=0){
										t.rotateML90();	
										TokatPainter.paint(getGraphics(), t);
										xtokatList.set(a, t);	
									}
									return null;
								}

							};
							worker.execute();


						}
					}


					Rectangle oldPosTokat = xtokatList.get(i).boundingBox();
					Rectangle repaintArea3 = oldPosTokat.union(xtokatList.get(i).boundingBox());

					repaint();
					repaint(repaintArea3.x,
							repaintArea3.y,
							repaintArea3.width,
							repaintArea3.height);
				}

			}
			if(leftPlayerRightTokatPressedR) 
			{
				for(int i =0;i<xtokatList.size();i++){
					if(xtokatList.get(i).getType().equals("RightTokat") && xtokatList.get(i).getWhichCezmi() == "LeftCezmi"){
						if(leftPlayerRightTokatCounter%2==1){
							final int a = i;

							SwingWorker<Boolean,Void> worker = new SwingWorker<Boolean, Void>() {

								@Override
								protected Boolean doInBackground() throws Exception {
									Tokat t =xtokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=90){
										t.rotate90();	
										TokatPainter.paint(getGraphics(), t);
										xtokatList.set(a, t);	
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
									Tokat t =xtokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=0){
										t.rotateM90();	
										TokatPainter.paint(getGraphics(), t);
										xtokatList.set(a, t);	
									}
									return null;
								}

							};
							worker.execute();

						}
					}


					Rectangle oldPosTokat2 = xtokatList.get(i).boundingBox();
					Rectangle repaintArea3 = oldPosTokat2.union(xtokatList.get(i).boundingBox());

					repaint();
					repaint(repaintArea3.x,
							repaintArea3.y,
							repaintArea3.width,
							repaintArea3.height);
				}
			}
			if(rightPlayerLeftTokatPressedO) 
			{
				for(int i = 0;i<xtokatList.size();i++){

					if(xtokatList.get(i).getType().equals("LeftTokat") && xtokatList.get(i).getWhichCezmi()=="RightCezmi"){
						if(rightPlayerLeftTokatCounter%2==1){

							final int a = i;

							SwingWorker<Boolean,Void> worker = new SwingWorker<Boolean, Void>() {

								@Override
								protected Boolean doInBackground() throws Exception {
									Tokat t =xtokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=-90){
										t.rotateL90();	
										TokatPainter.paint(getGraphics(), t);
										xtokatList.set(a, t);	
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
									Tokat t =xtokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=0){
										t.rotateML90();	
										TokatPainter.paint(getGraphics(), t);
										xtokatList.set(a, t);	
									}
									return null;
								}

							};
							worker.execute();

						}
					}


					Rectangle oldPosTokat = xtokatList.get(i).boundingBox();
					Rectangle repaintArea3 = oldPosTokat.union(xtokatList.get(i).boundingBox());

					repaint();
					repaint(repaintArea3.x,
							repaintArea3.y,
							repaintArea3.width,
							repaintArea3.height);
				}

			}
			if(rightPlayerRightTokatPressedP) 
			{
				for(int i =0;i<xtokatList.size();i++){
					if(xtokatList.get(i).getType().equals("RightTokat") && xtokatList.get(i).getWhichCezmi() == "RightCezmi"){
						if(rightPlayerRightTokatCounter%2==1){
							final int a = i;

							SwingWorker<Boolean,Void> worker = new SwingWorker<Boolean, Void>() {

								@Override
								protected Boolean doInBackground() throws Exception {
									Tokat t =xtokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=90){
										t.rotate90();	
										TokatPainter.paint(getGraphics(), t);
										xtokatList.set(a, t);	
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
									Tokat t =xtokatList.get(a);
									System.out.println("burdayým");

									while(t.angle!=0){
										t.rotateM90();	
										TokatPainter.paint(getGraphics(), t);
										xtokatList.set(a, t);	
									}
									return null;
								}

							};
							worker.execute();

						}
					}


					Rectangle oldPosTokat2 = xtokatList.get(i).boundingBox();
					Rectangle repaintArea3 = oldPosTokat2.union(xtokatList.get(i).boundingBox());

					repaint();
					repaint(repaintArea3.x,
							repaintArea3.y,
							repaintArea3.width,
							repaintArea3.height);
				}
			}

			if(aPressed) 
			{
				xcezmiList.get(0).moveLeftCezmi();
				Rectangle oldPosCezmi = xcezmiList.get(0).boundingBox();
				Rectangle repaintArea3 = oldPosCezmi.union(xcezmiList.get(0).boundingBox());

				repaint();
				repaint(repaintArea3.x,
						repaintArea3.y,
						repaintArea3.width,
						repaintArea3.height);
			}
			if(dPressed) 
			{
				xcezmiList.get(0).moveRightCezmi();
				Rectangle oldPosCezmi = xcezmiList.get(0).boundingBox();
				Rectangle repaintArea3 = oldPosCezmi.union(xcezmiList.get(0).boundingBox());

				repaint();
				repaint(repaintArea3.x,
						repaintArea3.y,
						repaintArea3.width,
						repaintArea3.height);
			}

			if(rightPressed){

				xcezmiList.get(1).moveRightCezmi2();
				Rectangle oldPosCezmi = xcezmiList.get(1).boundingBox();
				Rectangle repaintArea3 = oldPosCezmi.union(xcezmiList.get(1).boundingBox());

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

					for(int i = 0;i<xballList.size();i++){
						xballList.get(i).move();
						
					}
					return null;
				}

			};
			ballWorker.execute();


			SwingWorker<Boolean,Void> cezeryeWorker = new SwingWorker<Boolean, Void>() {

				@Override
				protected Boolean doInBackground() throws Exception {

					xcezerye.cezeryeMotion();		

					return null;
				}

			};
			cezeryeWorker.execute();

			repaint();
		}
	}



	
}























