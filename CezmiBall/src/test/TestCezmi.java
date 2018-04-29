package test;
import static org.junit.Assert.*;

import org.junit.Test;

import model.Cezmi;


public class TestCezmi {

	//creation of two instances of Cezmi class to test 4 methods
	Cezmi cezmi1 = new Cezmi(0);
	Cezmi cezmi2 = new Cezmi(250);

	@Test
	/**
	 * @requires the range for x position of left cezmi is 0 <= x < 220
	 * tests whether a left cezmi moves correctly toward left or not
	 * we tested for both boundary value(0) and normal expected value(200)  
	 */
	public void testMoveLeftCezmi1(){
		
		cezmi1.setPosX(0);
		cezmi1.setPosY(300);
		cezmi1.setRadius(10);
		cezmi1.moveLeftCezmi();
		int f = (int)cezmi1.getPosX() ;

		assertEquals(f, 0);
			
		
		cezmi1.setPosX(200);
		cezmi1.moveLeftCezmi();
		f = (int) cezmi1.getPosX();

		assertEquals(f, 190);
		
	}
	
	@Test
	/**
	 * @requires the range for x position of left cezmi is 0 <= x < 220
	 * tests whether a left cezmi moves correctly toward right or not
	 * we tested for both boundary value(220) and normal expected value(100)  
	 */
	public void testMoveRightCezmi1(){
		
		cezmi1.setPosX(100);
		cezmi1.setPosY(300);
		cezmi1.setRadius(10);
		cezmi1.moveRightCezmi();
		int f = (int) cezmi1.getPosX();
		
		assertEquals(f, 110);
		
		cezmi1.setPosX(220);
		cezmi1.moveRightCezmi();
		f = (int) cezmi1.getPosX();
		
		assertEquals(f, 220);
	}
	
	@Test
	/**
	 * @requires the range for x position of right cezmi is 250<= x < 480
	 * tests whether a right cezmi moves correctly toward left correctly or not
	 * we tested for both boundary value(250) and normal expected value(300)  
	 */
	public void testMoveLeftCezmi2(){
		
		cezmi2.setPosX(250);
		cezmi2.setPosY(300);
		cezmi2.setRadius(10);
		cezmi2.moveLeftCezmi2();
		int f = (int) cezmi2.getPosX() ;

		assertEquals(f, 250);
			
		
		cezmi2.setPosX(300);
		cezmi2.moveLeftCezmi2();
		f = (int) cezmi2.getPosX() ;

		assertEquals(f, 290);
		
	}
	
	@Test
	/**
	 * @requires the range for x position of right cezmi is 250<= x < 480
	 * tests whether a right cezmi moves correctly toward right correctly or not
	 * we tested for both boundary value(480) and normal expected value(300)  
	 */
	public void testMoveRightCezmi2(){
		
		cezmi2.setPosX(480);
		cezmi2.setPosY(300);
		cezmi2.setRadius(10);
		cezmi2.moveRightCezmi2();
		int f = (int) cezmi2.getPosX();
		
		assertEquals(f, 480);
		
		cezmi2.setPosX(300);
		cezmi2.moveRightCezmi2();
		f = (int) cezmi2.getPosX();
		
		assertEquals(f, 310);
	}
	
}
