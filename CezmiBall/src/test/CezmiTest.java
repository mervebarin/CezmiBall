package test;
import static org.junit.Assert.*;

import org.junit.Test;

import model.Cezmi;


public class CezmiTest {

	Cezmi cezmi1 = new Cezmi(0);
	Cezmi cezmi2 = new Cezmi(250);

	@Test
	public void testMoveLeftCezmi1(){
		
		cezmi1.setPosX(0);
		cezmi1.setPosY(300);
		cezmi1.setRadius(10);
		cezmi1.moveLeftCezmi();
		int f = (int) cezmi1.getPosX() ;

		assertEquals(f, 0);
			
		
		cezmi1.setPosX(200);
		cezmi1.moveLeftCezmi();
		f = (int) cezmi1.getPosX();

		assertEquals(f, 190);
		
	}
	
	@Test
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
