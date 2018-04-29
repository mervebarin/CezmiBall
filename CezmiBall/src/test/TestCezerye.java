package test;
import static org.junit.Assert.*;

import org.junit.Test;

import model.Cezerye;

public class TestCezerye {

	Cezerye cezerye1;
	Cezerye cezerye2;
	/**
	 * adds a cezerye with valid positions on the board 
	 * @result Cezerye will be created within the boundaries of the board 
	 * wih test runner class, 
	 */
	@Test
	public void testsummonCezerye() {
		
		cezerye1 = new Cezerye(200,200);
		
		cezerye1.summonCezerye(); // puts cezerye at random pos on the board
		
		//gets the initial pos of cezerye after summonCezerye()
		int xpos = cezerye1.getX();
		int ypos = cezerye1.getY();
		
		assertTrue(xpos>0);
		assertTrue(xpos<=480);
		assertTrue(ypos>0);
		assertTrue(ypos<=460);
		
		System.out.println("Cezerye xpos: " + xpos);
		System.out.println("Cezerye ypos: " + ypos);
	
	}
	
//	@Test
//	/**
//	 * @requires cezmi1,cezmi2 indicate the left and right cezmi respectively.
//	 */
//	public void testSpecialEffect(){
//		Cezmi cezmi1 = new Cezmi(100);
//		Cezmi cezmi2= new Cezmi(300);
//		
//		specialEffect(cezmi1,cezmi2);
//			
//	}
    /**
     * deletes a cezerye from the board
     * @result deletes a cezerye from the board configuration
     */
	@Test
	public void testclearCezerye(){
		
		cezerye1= new Cezerye(200,200);
		cezerye1.clearCezerye();
		int width = cezerye1.getWidth();
		int height = cezerye1.getHeight();
		boolean alive = cezerye1.alive;
		
		assertEquals(width,0);
		assertEquals(height,0);
		assertEquals(alive,false);
		
		System.out.println("Test for the cezerye:" + " " + "width:" + width + ", " + "height: " + height + ", " + "alive: " + alive );
		
			
	}
}
