package controller;

import view.ApplicationWindow;

public class XmlAnimationWindowController {
	public  XmlAnimationWindowController(){
		
	}
	
	public void lefttokatHandle(){
		ApplicationWindow.getInstance().getXmlAnimationWindow().activateEditMode();
		ApplicationWindow.getInstance().getXmlAnimationWindow().setEditmode(true);
		ApplicationWindow.getInstance().getXmlAnimationWindow().setAddString("leftTokat");

	}
	
	public void righttokatHandle(){
		ApplicationWindow.getInstance().getXmlAnimationWindow().activateEditMode();
		ApplicationWindow.getInstance().getXmlAnimationWindow().setEditmode(true);
		ApplicationWindow.getInstance().getXmlAnimationWindow().setAddString("rightTokat");
	}
	public void firildakHandle(){
		ApplicationWindow.getInstance().getXmlAnimationWindow().activateEditMode();
		ApplicationWindow.getInstance().getXmlAnimationWindow().setEditmode(true);
		ApplicationWindow.getInstance().getXmlAnimationWindow().setAddString("firildak");
	}
	
	public void squareTakozHandle(){
		ApplicationWindow.getInstance().getXmlAnimationWindow().activateEditMode();
		ApplicationWindow.getInstance().getXmlAnimationWindow().setEditmode(true);
		ApplicationWindow.getInstance().getXmlAnimationWindow().setAddString("squareTakoz");
	}
	
	public void triangleTakozHandle(){
		ApplicationWindow.getInstance().getXmlAnimationWindow().activateEditMode();
		ApplicationWindow.getInstance().getXmlAnimationWindow().setEditmode(true);
		ApplicationWindow.getInstance().getXmlAnimationWindow().setAddString("triangleTakoz");
	}
}
