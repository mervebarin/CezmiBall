package controller;

import view.ApplicationWindow;

public class DesignAnimationWindowController {

	

	public  DesignAnimationWindowController(){
		
	}
	
	public void lefttokatHandle(){
		ApplicationWindow.getInstance().getDesignAnimationWindow().activateEditMode();
		ApplicationWindow.getInstance().getDesignAnimationWindow().setEditmode(true);
		ApplicationWindow.getInstance().getDesignAnimationWindow().setAddString("leftTokat");

	}
	
	public void righttokatHandle(){
		ApplicationWindow.getInstance().getDesignAnimationWindow().activateEditMode();
		ApplicationWindow.getInstance().getDesignAnimationWindow().setEditmode(true);
		ApplicationWindow.getInstance().getDesignAnimationWindow().setAddString("rightTokat");
	}
	public void firildakHandle(){
		ApplicationWindow.getInstance().getDesignAnimationWindow().activateEditMode();
		ApplicationWindow.getInstance().getDesignAnimationWindow().setEditmode(true);
		ApplicationWindow.getInstance().getDesignAnimationWindow().setAddString("firildak");
	}
	
	public void squareTakozHandle(){
		ApplicationWindow.getInstance().getDesignAnimationWindow().activateEditMode();
		ApplicationWindow.getInstance().getDesignAnimationWindow().setEditmode(true);
		ApplicationWindow.getInstance().getDesignAnimationWindow().setAddString("squareTakoz");
	}
	
	public void triangleTakozHandle(){
		ApplicationWindow.getInstance().getDesignAnimationWindow().activateEditMode();
		ApplicationWindow.getInstance().getDesignAnimationWindow().setEditmode(true);
		ApplicationWindow.getInstance().getDesignAnimationWindow().setAddString("triangleTakoz");
	}
}
