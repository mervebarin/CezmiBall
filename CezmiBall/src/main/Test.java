package main;
import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

import controller.Menu;
import model.XmlReader;
import view.ApplicationWindow;

import javax.xml.XMLConstants;
import java.net.URL;

import javax.swing.JFileChooser;

public class Test {

	public static final int L = 20;
	public static void main(String[] args) {
		Menu m = new Menu();
		m.setSize(25*L+100, 25*L+100);
		m.setVisible(true);
	}
}
