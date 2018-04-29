package test;
import static org.junit.Assert.*;

import org.junit.Test;

import model.Tokat;
/**
 * tests the rotation functions of two types of tokat
 */
public class TokatTest {

	Tokat tokat1;
	
	@Test
	/**
	 * the right tokat is rotated by 90 by rotate90, 
	 * the test checks whether the rotation is correct
	 */
	public void testrotate90() {
	
		tokat1 = new Tokat(200,200,"Tokat");
	    tokat1.rotate90();
	    int a = tokat1.angle;
	    
	    assertEquals(90,a);
	    
	    System.out.println("Test for the rotation angle of right tokat:" + " " + "angle is: " + a);
	
	}
	@Test
	/**
	 * the right tokat is rotated back to its original position by rotateM90 method, 
	 * the test checks whether the rerotation is correct
	 */
	public void testrotateM90() {
		
		tokat1 = new Tokat(200,200,"Tokat");
	    tokat1.rotateM90();
	    int a = tokat1.angle;
	    
	    assertEquals(0,a);
	    System.out.println("Test for the rotation angle of right tokat:" + " " + "angle is: " + a);
	}
	@Test
	/**
	 * the left tokat is rotated by 270 or anticlockwise 90 by rotateL90, 
	 * the test checks whether the rotation is correct
	 */
	public void testrotateL90(){
		tokat1 = new Tokat(200,200,"Tokat");
	    tokat1.rotateL90();
	    int a = tokat1.angle;
	    
	    assertEquals(270,a);
		
	    System.out.println("Test for the rotation angle of left tokat:" + " " + "angle is: " + a);
	}
    @Test
    /**
	 * the left tokat is rotated back by rotateML90, 
	 * the test checks whether the rotation is correct
	 */
	public void testrotateML90(){
		tokat1 = new Tokat(200,200,"Tokat");
	    tokat1.rotateML90();
	    int a = tokat1.angle;
	    
	    assertEquals(90,a);
	    
	    System.out.println("Test for the rotation angle of left tokat:" + " " + "angle is: " + a);
	}
}
