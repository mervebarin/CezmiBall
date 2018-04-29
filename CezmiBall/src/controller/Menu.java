package controller;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import main.Test;
import model.XmlReader;
import view.ApplicationWindow;
import view.XmlAnimationWindow;

public class Menu extends JFrame{

	public static String inputFile;

	private Button designButton;
	public static Button xmlButton;
	private JCheckBox levelbox;
	public static boolean isLevel2 = false;

	public Menu(){
		super();


		JPanel buttonPane = new JPanel();
		designButton = new Button("Design");
		xmlButton = new Button("XML");

		levelbox = new JCheckBox("LEVEL 2");

		
	

		levelbox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				isLevel2 = !isLevel2;
				ApplicationWindow.setLevel2(isLevel2);
				System.out.println("Current is Level 2" + " " + isLevel2);
			}
		});



		designButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				ApplicationWindow.xml = false;
				ApplicationWindow frame = ApplicationWindow.getInstance();
				// the following code realizes the top level application window
				frame.pack();
				frame.setVisible(true);
				setVisible(false);

			}
		});  

		xmlButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				
				ApplicationWindow.xml = true;

			


				ApplicationWindow frame = ApplicationWindow.getInstance();
				// the following code realizes the top level application window
				frame.pack();
				frame.setVisible(true);
				setVisible(false);

			}
		});  

		buttonPane.add(designButton);
		buttonPane.add(xmlButton);
		buttonPane.add(levelbox);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(25*Test.L + 10, 25*Test.L +60));
		contentPane.add(buttonPane, BorderLayout.CENTER);
		setContentPane(contentPane);
	}

	public Button getDesignButton() {
		return designButton;
	}

	public void setDesignButton(Button designButton) {
		this.designButton = designButton;
	}

	public JCheckBox getLevelbox() {
		return levelbox;
	}

	public void setLevelbox(JCheckBox levelbox) {
		this.levelbox = levelbox;
	}

	public static Button getXmlButton() {
		return xmlButton;
	}

	public void setXmlButton(Button xmlButton) {
		this.xmlButton = xmlButton;
	}
	
	

}
