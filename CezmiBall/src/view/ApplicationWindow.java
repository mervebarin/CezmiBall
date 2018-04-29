package view;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import controller.AnimationWindowController;
import controller.ApplicationWindowController;
import controller.DesignAnimationWindowController;
import controller.Menu;
import controller.XmlAnimationWindowController;
import main.Test;
import model.BouncingBall;
import model.Gizmo;
import model.Grid;
import model.XmlReader;


public class ApplicationWindow extends JFrame{

	protected DesignAnimationWindow designAnimationWindow;
	protected XmlAnimationWindow xmlAnimationWindow;

	public DesignAnimationWindow getDesignAnimationWindow() {
		return designAnimationWindow;
	}
	public XmlAnimationWindow getXmlAnimationWindow(){
		return xmlAnimationWindow;
	}

	public void setDesignAnimationWindow(DesignAnimationWindow designAnimationWindow) {
		this.designAnimationWindow = designAnimationWindow;
	}
	private static JTextField score1;
	private static JTextField score2;
	public static JTextField winner;
	private int totalscore;
	private static ApplicationWindow instance;
	public static boolean xml;
	private static boolean isLevel2;
	private boolean again = false;
	JScrollPane scrollPane;
	JToolBar toolBar;
	public static boolean isLevel2() {
		return isLevel2;
	}

	public static void setLevel2(boolean isLevel2) {
		ApplicationWindow.isLevel2 = isLevel2;
	}

	private ApplicationWindow() {
		// effects: Initializes the application window so that it contains
		//          a toolbar and an animation window.

		// Title bar
		super("Swing Demonstration Program");

		// respond to the window system asking us to quit
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});


		//Create the toolbar.
		toolBar = new JToolBar();
		addButtons(toolBar);

		JPanel scorpanel = new JPanel();
		score1 = new JTextField("0");
		score1.setFont(score1.getFont().deriveFont(15f));
		score1.setSize(50,20);
		score1.setPreferredSize(new Dimension(50,20));
		score1.setEnabled(false);
		score2 = new JTextField("0");
		score2.setFont(score2.getFont().deriveFont(15f));
		score2.setEnabled(false);
		score2.setSize(50, 20);
		score2.setPreferredSize(new Dimension(50,20));
		JLabel player1 = new JLabel("Player 1:");
		JLabel player2 = new JLabel("Player 2:");
		winner = new JTextField("WINNER");
		winner.setEnabled(false);

		scorpanel.add(player1);
		scorpanel.add(getScore1());
		scorpanel.add(player2);
		scorpanel.add(getScore2());
		scorpanel.add(winner);
		//Create the animation area used for output.
		if(xml){
			String inputFile = null;
			try{
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("C:\\Users"));
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);	
				int returnVal = fc.showOpenDialog(null);

				if(returnVal == JFileChooser.APPROVE_OPTION){
					File selectedFile = fc.getSelectedFile();
					inputFile = selectedFile.getName();
				}
				//System.out.println("MERT");
				String schemaFile = "C:\\Users\\Mert\\Desktop\\cezmiFinal4\\cezmi.xsd";
				Source xmlFile = new StreamSource(new File(inputFile));
				SchemaFactory schemaFactory = SchemaFactory					//http://stackoverflow.com/questions/15732/whats-the-best-way-to-validate-an-xml-file-against-an-xsd-file
						.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				Schema schema = schemaFactory.newSchema(new File(schemaFile));
				Validator validator = schema.newValidator();
				try {
					validator.validate(xmlFile);
					System.out.println(xmlFile.getSystemId() + " is valid");
				} catch (SAXException e1) {
					System.out.println(xmlFile.getSystemId() + " is NOT valid");
					System.out.println("Reason: " + e1.getLocalizedMessage());
				}

			}catch(Exception e1){
				System.out.println("eror" + e1.getMessage() + " " + e1.getCause());
			}

			XmlReader reader = new XmlReader();
			reader.openXml(inputFile);
			xmlAnimationWindow = new XmlAnimationWindow();
			scrollPane = new JScrollPane(xmlAnimationWindow);
			final XmlAnimationWindowController animationWindowController = new XmlAnimationWindowController();
			scrollPane.setSize(25*Test.L , 25 * Test.L);
			xmlAnimationWindow.setLevel2(isLevel2);
			AnimationWindowController.setXml(true);
			AnimationWindowController.setLists();

			JPanel addMenu = new JPanel();
			Button leftTokatButton = new Button("Left Tokat");
			Button rightTokatButton = new Button("Right Tokat");
			Button firildakButton = new Button("Firildak");
			Button triangleTakozButton = new Button("Triangle Takoz");
			Button squareTakozButton = new Button("Square Takoz");

			leftTokatButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					animationWindowController.lefttokatHandle();
				}
			});


			rightTokatButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					animationWindowController.righttokatHandle();

				}
			});


			firildakButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					animationWindowController.firildakHandle();
				}
			});
			triangleTakozButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					animationWindowController.triangleTakozHandle();

				}
			});
			squareTakozButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					animationWindowController.squareTakozHandle();

				}
			});

			addMenu.add(squareTakozButton);
			addMenu.add(triangleTakozButton);
			addMenu.add(firildakButton);
			addMenu.add(leftTokatButton);
			addMenu.add(rightTokatButton);
			addMenu.setLayout(new GridLayout(5, 1));


			//Lay out the content pane.
			JPanel contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout());
			contentPane.setPreferredSize(new Dimension(25*Test.L + 100, 25*Test.L +67));
			contentPane.add(toolBar, BorderLayout.NORTH);
			contentPane.add(scrollPane, BorderLayout.CENTER);
			contentPane.add(scorpanel, BorderLayout.SOUTH);
			contentPane.add(addMenu, BorderLayout.WEST);
			setContentPane(contentPane);
			scrollPane.setEnabled(true);
			AnimationWindowController.setLists();
		}
		else{


			designAnimationWindow = new DesignAnimationWindow(isLevel2);
			final DesignAnimationWindowController animationWindowController = new DesignAnimationWindowController();
			scrollPane = new JScrollPane(designAnimationWindow);

			scrollPane.setSize(25*Test.L , 25 * Test.L);

			JPanel addMenu = new JPanel();
			Button leftTokatButton = new Button("Left Tokat");
			Button rightTokatButton = new Button("Right Tokat");
			Button firildakButton = new Button("Firildak");
			Button triangleTakozButton = new Button("Triangle Takoz");
			Button squareTakozButton = new Button("Square Takoz");
			System.out.println(isLevel2);
			designAnimationWindow.setLevel2(isLevel2);
			leftTokatButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					animationWindowController.lefttokatHandle();
				}
			});


			rightTokatButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					animationWindowController.righttokatHandle();

				}
			});


			firildakButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					animationWindowController.firildakHandle();
				}
			});
			triangleTakozButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					animationWindowController.triangleTakozHandle();

				}
			});
			squareTakozButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					animationWindowController.squareTakozHandle();

				}
			});

			addMenu.add(squareTakozButton);
			addMenu.add(triangleTakozButton);
			addMenu.add(firildakButton);
			addMenu.add(leftTokatButton);
			addMenu.add(rightTokatButton);
			addMenu.setLayout(new GridLayout(5, 1));
			//Lay out the content pane.
			JPanel contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout());
			contentPane.setPreferredSize(new Dimension(25*Test.L + 100, 25*Test.L + 67));
			contentPane.add(toolBar, BorderLayout.NORTH);
			contentPane.add(scrollPane, BorderLayout.CENTER);
			contentPane.add(scorpanel, BorderLayout.SOUTH);
			contentPane.add(addMenu, BorderLayout.WEST);
			setContentPane(contentPane);
			scrollPane.setEnabled(true);
			AnimationWindowController.setLists();
		}

		// Put it in a scrollPane, (this makes a border)


	}

	protected void addButtons(final JToolBar toolBar) {
		// modifies: toolBar
		// effects: adds Run, Stop and Quit buttons to toolBar

		JButton button = null;

		button = new JButton("Run");
		button.setToolTipText("Start the animation");
		// when this button is pushed it calls animationWindow.setMode(true)
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(xml){
					xmlAnimationWindow.setMode(true);
					xmlAnimationWindow.setEditmode(false);
					xmlAnimationWindow.setRunPressed(true);
				}
				else{
					designAnimationWindow.setMode(true);
					designAnimationWindow.setEditmode(false);
					designAnimationWindow.setRunPressed(true);
					Grid.clearLines();
					repaint();
				}
			}
		});

		toolBar.add(button);

		button = new JButton("Stop");
		button.setToolTipText("Stop the animation");
		// when this button is pushed it calls animationWindow.setMode(false)
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(xml){
					xmlAnimationWindow.setMode(false);
					xmlAnimationWindow.setStopPressed(true);
				}
				else{
					designAnimationWindow.setMode(false);
					designAnimationWindow.setStopPressed(true);

				}
			}
		});
		toolBar.add(button);

		button = new JButton("Edit");
		button.setToolTipText("Edit the board");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(xml){
					xmlAnimationWindow.activateEditMode(); 
					xmlAnimationWindow.setEditmode(true);
					xmlAnimationWindow.setRunPressed(false);
					xmlAnimationWindow.setStopPressed(false);
				}
				else{
					designAnimationWindow.activateEditMode(); 
					designAnimationWindow.setEditmode(true);
					designAnimationWindow.setRunPressed(false);
					designAnimationWindow.setStopPressed(false);

				}
			}
		});
		toolBar.add(button);

		button = new JButton("Quit");
		button.setToolTipText("Quit the program");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		toolBar.add(button); 

		button = new JButton("Save");
		button.setToolTipText("Save the program");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(xml){
					xmlAnimationWindow.XmlWriter();
				}
				else{
					designAnimationWindow.XmlWriter();
				}
			}
		});  
		toolBar.add(button);

		button = new JButton("Load");
		button.setToolTipText("Load game");
		button.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e){




				if(xmlAnimationWindow!=null){
					xmlAnimationWindow.setXcezerye(null);
					xmlAnimationWindow.setXballList(null);
					xmlAnimationWindow.setXcezmiList(null);
					xmlAnimationWindow.setXfirildakList(null);
					xmlAnimationWindow.setXtakozList(null);
					xmlAnimationWindow.setXtokatList(null);

				}
				if(designAnimationWindow!=null){
					designAnimationWindow.setBallList(null);
					designAnimationWindow.setCezerye(null);
					designAnimationWindow.setCezmiList(null);
					designAnimationWindow.setFirildakList(null);
					designAnimationWindow.setTakozList(null);
					designAnimationWindow.setTokatList(null);
				}

				AnimationWindowController.setLists();
				//		xmlAnimationWindow = null;
				//		designAnimationWindow = null;
				instance.dispose();
				instance.setEnabled(false);
				instance.setVisible(false);
				instance = null;

				getContentPane().remove(scrollPane);
				scrollPane.setEnabled(false);
				scrollPane = null;
				repaint();
				revalidate();
				dispose();

				Menu m = new Menu();
				m.getDesignButton().setEnabled(false);
				m.getDesignButton().setVisible(false);
				m.getLevelbox().setVisible(false);
				m.getLevelbox().setEnabled(false);
				m.pack();
				m.setVisible(true);

				xml = true;



			}

		});  
		toolBar.add(button);
	}



	public static ApplicationWindow getInstance(){
		if(instance == null){

			instance = new ApplicationWindow();
			System.out.println("First Time Application Window created");
		}

		return instance;
	}




	public static JTextField getScore1() {
		return score1;
	}
	public static   JTextField getScore2() {
		return score2;
	}





}
