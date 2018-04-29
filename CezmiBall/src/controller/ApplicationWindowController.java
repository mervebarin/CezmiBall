package controller;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;

import model.BouncingBall;
import view.ApplicationWindow;

public class ApplicationWindowController {



	private	static ApplicationWindowController instance;
	
	public static ApplicationWindowController getInstance(){
		if(instance == null){
			instance = new ApplicationWindowController();
		}
		
		return instance;
	}

	
	public static double getScorePlayer1(){
	
		if(Double.valueOf(ApplicationWindow.getScore1().getText()) >= 10){
			ApplicationWindow.getInstance();
			ApplicationWindow.winner.setBackground(Color.GREEN);
			ApplicationWindow.getInstance();
			ApplicationWindow.winner.setFont(ApplicationWindow.getInstance().winner.getFont().deriveFont(Font.PLAIN, 50f));
			ApplicationWindow.getInstance();
			ApplicationWindow.winner.setSize(400, 400);
			ApplicationWindow.getInstance();
			ApplicationWindow.winner.setText("PLAYER 1 WIN");
			ApplicationWindow.getInstance().repaint();
			ApplicationWindow.getInstance().pack();
		}
		return Double.valueOf(ApplicationWindow.getScore1().getText());
	}


	public  static double getScorePlayer2(){
		if(Double.valueOf(ApplicationWindow.getScore2().getText()) >= 10){
			ApplicationWindow.getInstance();
			ApplicationWindow.winner.setBackground(Color.GREEN);
			ApplicationWindow.getInstance();
			ApplicationWindow.winner.setFont(ApplicationWindow.getInstance().winner.getFont().deriveFont(Font.PLAIN, 50f));
			ApplicationWindow.getInstance();
			ApplicationWindow.winner.setSize(400, 400);
			ApplicationWindow.getInstance();
			ApplicationWindow.winner.setText("PLAYER 2 WIN");
			ApplicationWindow.getInstance().repaint();
			ApplicationWindow.getInstance().pack();
		}
		return Double.valueOf(ApplicationWindow.getScore2().getText());
	}

	public static  void setScore1(int score) {
		
		BouncingBall.shrinked = false;

		ApplicationWindow.getInstance().getScore1().setText(String.valueOf(score));
		
		
	}


	public static  void setScore2(int score) {
		BouncingBall.shrinked = false;
		ApplicationWindow.getInstance().getScore2().setText(String.valueOf(score));
		
	}


	public static double getTotalScore(){

		return getScorePlayer1()+getScorePlayer2();
	}





}
