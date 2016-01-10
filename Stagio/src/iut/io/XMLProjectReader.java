package iut.io;
/**
 * <p>
 * Nom de l'application : STAGIO gestionnaire de stage
 * </p>
 * <p>
 * Description : gestionnaire de stage
 * </p>
 * 
 * @author Johnathan, Joe, Pierre et Thibault
 * @version 1.0
 */
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import iut.app.ExamEvent;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * 
 *Classe permettant l'accès à la bdd
 */
public class XMLProjectReader {
	public XMLProjectReader() {

	}

	public  LinkedList<ExamEvent> load(java.io.File xmlfile) throws IOException {
		LinkedList<ExamEvent> data = new LinkedList<ExamEvent>();
		// on ouvre le decodeur
	    XMLDecoder decoder = new XMLDecoder(new FileInputStream(xmlfile));
	    try {
	        // on deserialise de l'objet
	    	 data = (LinkedList<ExamEvent>)decoder.readObject();
	    } finally {
	        // on ferme le decodeur
	        decoder.close();
	    }
		return data;	
	}
}

