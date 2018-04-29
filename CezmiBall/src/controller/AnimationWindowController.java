package controller;

import model.BouncingBall;
import model.Cezmi;
import view.ApplicationWindow;
import view.DesignAnimationWindow;
import view.XmlAnimationWindow;


public class AnimationWindowController {

	private static boolean xml = ApplicationWindow.xml;
	
	public static void setLists(){
		
		if(xml){
		System.err.println("girdi");
			BouncingBall.setTakozList(XmlAnimationWindow.getXtakozList());
			BouncingBall.setTokatList(XmlAnimationWindow.getXtokatList());
			BouncingBall.setCezerye(XmlAnimationWindow.getXcezerye());
			BouncingBall.setFirildakList(XmlAnimationWindow.getXfirildakList());
			BouncingBall.setCezmiList(XmlAnimationWindow.getXcezmiList());
			BouncingBall.setBallList(XmlAnimationWindow.getXballList());
			BouncingBall.setLevel2(XmlAnimationWindow.isLevel2());
			Cezmi.setLevel2(XmlAnimationWindow.isLevel2());
			
			
		}else{
			BouncingBall.setTakozList(DesignAnimationWindow.getTakozList());
			BouncingBall.setTokatList(DesignAnimationWindow.getTokatList());
			BouncingBall.setCezerye(DesignAnimationWindow.getCezerye());
			BouncingBall.setFirildakList(DesignAnimationWindow.getFirildakList());
			BouncingBall.setCezmiList(DesignAnimationWindow.getCezmiList());
			BouncingBall.setBallList(DesignAnimationWindow.getBallList());
			BouncingBall.setLevel2(DesignAnimationWindow.isLevel2());
			Cezmi.setLevel2(DesignAnimationWindow.isLevel2());
			
		}
	}
	public static void setXml(boolean a){
		xml = a;
	}
}
