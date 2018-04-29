package test;
import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import model.BouncingBall;
import physics.Vect;

/**
 * check whether the ball correctly random bumps or not.
 * @requires vx and vy randomly choosen
 * @modifies vx and vy
 * @effects -vx and -vy
 */
public class TestBouncingBall {

	BouncingBall ball1;
	
	@Test
	public void test() {
	
	ball1 = new BouncingBall(5,4,3,4);
	
	Vect v = ball1.getBallVelocityVector();
	int i =(int) v.x();
	int j=(int) v.y();
	
	ball1.randomBump();
	
	int a = -i;
	int b= -j;
	
	assertEquals(-i,a);
	assertEquals(-j,b);
	
	System.out.println("Test for the bouncing ball: " + "initial vx and vy :" + " "+ i + ", " + j + "final vx and vy:" +  " " + a + b  );
	
		
	}
	
}
