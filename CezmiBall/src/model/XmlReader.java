package model;


import java.io.File; 
import java.util.ArrayList; 
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import org.w3c.dom.Document; 
import org.w3c.dom.Element; 
import org.w3c.dom.Node; 
import org.w3c.dom.NodeList;

import main.Test; 

public class XmlReader { 	
	public static Integer[] coordinates; 	
	public static double xVelocity; 	
	public static double yVelocity; 	
	public static int xCoor; 	
	public static int yCoor; 	
	public static int score; 	
	public static String type ; 	
	public static String specificType; 	
	public static double angle; 	
	public static ArrayList<Tokat> tokatList; 
	public static ArrayList<Takoz> takozList; 
	public static ArrayList<Firildak> firildakList;  	
	public static ArrayList<BouncingBall> ballList;
	public static ArrayList<Integer> ballcordList;
	public static ArrayList<Double> ballvelList;
	public static ArrayList<String> keyList;
	public static Cezerye cezerye; 	
	public static ArrayList<Cezmi> cezmiList; 	
	public static int creationTime;
	public static int orientation;
	public static String key;
	public static boolean isLevel2;
	public XmlReader(){ 	} 	

	public void openXml(String input) { 		

		tokatList =  new ArrayList<Tokat>(); 
		takozList = new ArrayList<Takoz>(); 	
		firildakList = new ArrayList<Firildak>();
		ballcordList = new ArrayList<Integer>();
		ballvelList = new ArrayList<Double>();
		ballList = new ArrayList<BouncingBall>();
		cezmiList = new ArrayList<Cezmi>(); 
		keyList = new ArrayList<String>();

		try { 	

			File inputFile = new File(input); 			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 			
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();                         //https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm 			
			Document doc = dbBuilder.parse(inputFile);											//I have used this site to open the xml file. I haven't modifyed it. 			
			doc.getDocumentElement().normalize(); 		
			NodeList board = doc.getElementsByTagName("board");
			Element level = (Element) board.item(0);

			if(level.getAttribute("level")=="1")
			{
				isLevel2 = false;
			}else {
				isLevel2 = true;
			}

			NodeList xmlballs = doc.getElementsByTagName("ball"); 	
			NodeList cezmi1 = doc.getElementsByTagName("cezmi1"); 			
			NodeList cezmi2 = doc.getElementsByTagName("cezmi2"); 			
			NodeList cezeryes = doc.getElementsByTagName("cezerye"); 	
			NodeList xmlsquareTakozList = doc.getElementsByTagName("squareTakoz");
			NodeList xmltriangularTakozList = doc.getElementsByTagName("triangleTakoz");
			NodeList xmlfirildakList = doc.getElementsByTagName("firildak");
			NodeList xmlleftTokatList = doc.getElementsByTagName("leftTokat");
			NodeList xmlrightTokatList = doc.getElementsByTagName("rightTokat");
			NodeList xmlkeyList = doc.getElementsByTagName("keys");

			//BALL RADIUS EKLENMELI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1 	

			if(xmlballs.getLength() != 0){

				for(int i = 0; i <xmlballs.getLength();i++){ 
					Node ball = xmlballs.item(i); 	
					if(ball.getNodeType() == Node.ELEMENT_NODE){ 
						Element e = (Element) ball;
						xCoor = (int)Double.parseDouble(e.getAttribute("x"))*Test.L;
						yCoor = (int)Double.parseDouble(e.getAttribute("y"))*Test.L; 						
						xVelocity = Double.parseDouble(e.getAttribute("xVelocity")); 
						yVelocity = Double.parseDouble(e.getAttribute("yVelocity")); 
						
				 
						
					
						ballList.add(new BouncingBall(xCoor,yCoor,xVelocity,yVelocity)); 
						
			
					
					} 	
				}	
			}
			
		
			
			if(cezmi1.getLength() != 0){ 	

				Node leftCezmi = cezmi1.item(0); 

				if(leftCezmi.getNodeType() == Node.ELEMENT_NODE){ 

					Element e = (Element) leftCezmi; 	
					xCoor = (int)Double.parseDouble(e.getAttribute("x"))*Test.L; 
					yCoor = (int)Double.parseDouble(e.getAttribute("y")); 
					score= Integer.parseInt(e.getAttribute("score")); 	
					Cezmi gameLeftCezmi = new Cezmi(xCoor); 	

					gameLeftCezmi.setScore(score); 	
					cezmiList.add(gameLeftCezmi); 
				} 			
			} 	
			if(cezmi2.getLength() != 0) 	
			{ 	

				Node rightCezmi = cezmi2.item(0); 

				if(rightCezmi.getNodeType() == Node.ELEMENT_NODE){ 

					Element e = (Element) rightCezmi; 					
					xCoor = (int)Double.parseDouble(e.getAttribute("x"))*Test.L; 					
					yCoor = (int)Double.parseDouble(e.getAttribute("y")); 					
					score= Integer.parseInt(e.getAttribute("score")); 	
					Cezmi gameRightCezmi = new Cezmi(xCoor);

					gameRightCezmi.setScore(score); 	
					cezmiList.add(gameRightCezmi); 	
				} 	
			} 	
			if(cezeryes.getLength() != 0) 	
			{ 	
				for(int i = 0; i < cezeryes.getLength();i++){ 	

					Node xmlCezerye = cezeryes.item(i); 

					if(xmlCezerye.getNodeType() == Node.ELEMENT_NODE){ 	

						Element e = (Element) xmlCezerye; 	
						xCoor = (int)Double.parseDouble(e.getAttribute("x"))*Test.L; 					
						yCoor = (int)Double.parseDouble(e.getAttribute("y"))*Test.L; 					
						creationTime = (int)Double.parseDouble(e.getAttribute("time"));
						Cezerye gameCezerye = new Cezerye(xCoor, yCoor);
						gameCezerye.setCreationTime(creationTime);
						cezerye = gameCezerye;
					} 	
				} 	
			}	
			if(xmlsquareTakozList.getLength() != 0) 	
			{ 	
				for(int i = 0; i < xmlsquareTakozList.getLength();i++){ 

					Node gizmoNode = xmlsquareTakozList.item(i); 	

					if(gizmoNode.getNodeType() == Node.ELEMENT_NODE) 
					{ 	
						Element e = (Element)gizmoNode; 
						xCoor = (int)Double.parseDouble(e.getAttribute("x"))*Test.L; 					
						yCoor = (int)Double.parseDouble(e.getAttribute("y"))*Test.L; 					
						Takoz takoz = new Takoz(xCoor,yCoor,"Square"); 
						takozList.add(takoz); 
					} 		
				}
			}
			if(xmltriangularTakozList.getLength() != 0) 	
			{ 
				for(int i = 0; i < xmltriangularTakozList.getLength();i++){ 

					Node gizmoNode = xmltriangularTakozList.item(i); 	

					if(gizmoNode.getNodeType() == Node.ELEMENT_NODE) 
					{ 	
						Element e = (Element)gizmoNode; 
						xCoor = (int)Double.parseDouble(e.getAttribute("x"))*Test.L; 					
						yCoor = (int)Double.parseDouble(e.getAttribute("y"))*Test.L; 					
						orientation = Integer.parseInt(e.getAttribute("orientation"));

						Takoz takoz = new Takoz(xCoor, yCoor, "Triangular");
						takoz.setOrientation(orientation);
						takozList.add(takoz); 			
					} 				
				}
			}
			if(xmlfirildakList.getLength() != 0) 	
			{ 	
				for(int i = 0; i < xmlfirildakList.getLength();i++){ 

					Node gizmoNode = xmlfirildakList.item(i); 	

					if(gizmoNode.getNodeType() == Node.ELEMENT_NODE) 
					{ 	
						Element e = (Element)gizmoNode; 
						xCoor = (int)Double.parseDouble(e.getAttribute("x"))*Test.L; 					
						yCoor = (int)Double.parseDouble(e.getAttribute("y"))*Test.L; 					
						angle = Double.parseDouble(e.getAttribute("angle"));
						Firildak firildak = new Firildak(xCoor,yCoor);
						firildak.setAngle(angle); 							
						firildakList.add(firildak);
					} 
				}
			}
			if(xmlleftTokatList.getLength() != 0) 	
			{ 	
				for(int i = 0; i < xmlleftTokatList.getLength();i++){ 

					Node gizmoNode = xmlleftTokatList.item(i); 	

					if(gizmoNode.getNodeType() == Node.ELEMENT_NODE) 
					{ 	
						Element e = (Element)gizmoNode; 
						xCoor = (int)Double.parseDouble(e.getAttribute("x"))*Test.L; 					
						yCoor = (int)Double.parseDouble(e.getAttribute("y"))*Test.L; 					
						orientation = Integer.parseInt(e.getAttribute("orientation"));

						Tokat leftTokat = new Tokat(xCoor,yCoor,"LeftTokat"); 							

						leftTokat.setOrientation(orientation);
						tokatList.add(leftTokat); 		
					} 					
				}
			}
			if(xmlrightTokatList.getLength() != 0) 	
			{ 	
				for(int i = 0; i < xmlrightTokatList.getLength();i++){ 

					Node gizmoNode = xmlrightTokatList.item(i); 	

					if(gizmoNode.getNodeType() == Node.ELEMENT_NODE) 
					{ 	
						Element e = (Element)gizmoNode; 

						xCoor = (int)Double.parseDouble(e.getAttribute("x"))*Test.L; 					
						yCoor = (int)Double.parseDouble(e.getAttribute("y"))*Test.L; 					
						orientation = Integer.parseInt(e.getAttribute("orientation"));

						Tokat rightTokat = new Tokat(xCoor,yCoor,"RightTokat"); 	

						rightTokat.setOrientation(orientation);
						tokatList.add(rightTokat); 	
					} 	
				}
			}
			if(xmlkeyList.getLength() !=0)
			{
				for(int i =0;i<xmlkeyList.getLength();i++){

					Node keyitem = xmlkeyList.item(i);

					if(keyitem.getNodeType() == Node.ELEMENT_NODE )
					{
						Element e = (Element) keyitem;
						key = e.getAttribute("key");
						keyList.add(key);
					}
				}
			}
		}catch(Exception e){ 			
			System.out.println(e.toString()); 		
		} 	
	}
}
