package test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.BouncingBall;
import model.Firildak;
import model.XmlReader;

public class TestXmlReader {
	
	//Checks if xml document opens correctly and gets the gizmo number correctly. 
	@Test
	public void testNumberOfGizmos(){
		
		XmlReader reader = new XmlReader();
		reader.openXml("HadiCezmi1.xml");
		
		int firildakListSize = XmlReader.firildakList.size();
		int takozListSize = XmlReader.takozList.size();
		int tokatListSize = XmlReader.tokatList.size();
		
		int numberOfGizmos = firildakListSize + takozListSize + tokatListSize; 
		int count = 5;
		
		System.out.println(firildakListSize + " "+ takozListSize +" " + tokatListSize);
		assertEquals(numberOfGizmos, count);
		
		System.out.println("The number of gizmos is: " + count);
	}
	
	//Checks if the position of the ball in xml document is true.
	@Test
	public void testopenXml() {
		
		
		XmlReader reader = new XmlReader();
		reader.openXml("HadiCezmi1.xml");
		
		BouncingBall ball = XmlReader.ballList.get(0);
		
		int xPos = ball.getX();
		int yPos = ball.getY();

		int x = (int) 4.3;
		int y = (int) 4.5;
		
		assertEquals(xPos, x);
		assertEquals(yPos, y);
		
		System.out.println("The expected poisition of the ball is: " + "x: " + x + ", " + "y: " + y);
	}
	 
	//Checks if the firildak position in the xml document is true.
	@Test
	public void testFirildakPosition(){
		
		XmlReader reader = new XmlReader();
		reader.openXml("HadiCezmi1.xml");
		
		Firildak firildak = XmlReader.firildakList.get(0);
		
		int firildakX = firildak.x;
		int firildakY = firildak.y;
		int a = 10;
		int b = 12;
		
		assertEquals(firildakX, a);
		assertEquals(firildakY, b);
		
		System.out.println("Firildak position: x: " + firildakX + " y: " + firildakY);
	}


}
