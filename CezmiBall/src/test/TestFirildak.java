package test;
import static org.junit.Assert.*;

import org.junit.Test;

import model.Firildak;


public class TestFirildak {

	@Test
	/**
	 * checks the initial and final angle before and after rotation of firildak
	 */
	public void testangularRotate() {
		
		Firildak firildak = new Firildak(200,200);
		
		int i = (int)firildak.getAngle();
		firildak.angularRotate();
		int f = (int)firildak.getAngle();
		
		assertEquals(i+1,f);
		System.out.println("initial xpos for Firildak" + i);
		System.out.println("final xpos for Firildak" + f);
	}
	
	 

}
