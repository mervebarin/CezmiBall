package model;
import java.awt.Rectangle;

public interface Gizmo {

	
	String getClassType();	
	
	Rectangle boundingBox();
	
	int getX();
	
	int getY();
	
	void setX(int x);
	
	void setY(int y);
	
	int getHeight();
	
	int getWidth();
	
	void setHeight(int height);
	
	void setWidth(int width);
	
	
}
