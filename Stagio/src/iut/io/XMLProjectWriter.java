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
import java.util.ArrayList;
import java.util.LinkedList;

import iut.app.CommandLineParser;
import iut.app.ExamEvent;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

//EX 1 Completer la classe 

public class XMLProjectWriter {

	public XMLProjectWriter() {

	}

	public void save(LinkedList<ExamEvent> data, java.io.File xmlfile) {
		/*
		 * CommandLineParser clp = new CommandLineParser(); String[] FILE =
		 * null; clp.parse(FILE); StreamResult result = new StreamResult(new
		 * File("C:/Users/johnathan/Documents/GitHub/iutjava/train/file.xml"));
		 * StreamResult result1 = new StreamResult(System.out);
		 */
		/**
		 * 
		 */
		XMLEncoder encoder;
		try {
			encoder = new XMLEncoder(new FileOutputStream(xmlfile));
			encoder.writeObject(data);
			encoder.flush();
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
/*
		// récupération d'une instance de la classe "DocumentBuilderFactory"
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			// création d'un parseur

			final DocumentBuilder builder = factory.newDocumentBuilder();

			// création d'un Document

			final Document document = builder.newDocument();

			// création de l'Element racine

			final Element racine = document.createElement("agenda");
			document.appendChild(racine);
			for (int evt = 0; evt < 5; evt++) {

				// création d'une personne

				final Comment comment = document.createComment("Bruce WAYNE");
				racine.appendChild(comment);

				final Element event = document.createElement("date");
				event.setAttribute("public", "yes");
				event.setAttribute("id", "" + evt);
				event.setAttribute("date", "2016-06-23 " + (14 + evt) + ":00");
				event.setAttribute("duration", "2:00:00");
				racine.appendChild(event);

				// création du nom et du prénom

				final Element nom = document.createElement("nom");
				nom.appendChild(document.createTextNode("WAYNE"));

				final Element prenom = document.createElement("prenom");
				prenom.appendChild(document.createTextNode("Bruce"));

				prenom.appendChild(nom);
				prenom.appendChild(prenom);

				for (ExamEvent d : data) {
					Element eventTitle = document.createElement("jury");
					eventTitle.appendChild(document.createTextNode("JURY "
							+ d.getJury()));

				}
			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}

		
		  TransformerFactory transformerFactory =
		 TransformerFactory.newInstance(); Transformer transformer =
		 transformerFactory.newTransformer(); DOMSource source = new
		 DOMSource(document); StreamResult output = new StreamResult(new
		 File(System.getProperty("user.home")+"/save.xml"));
		 transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		 transformer.setOutputProperty
		 ("{http://xml.apache.org/xslt}indent-amount", "2");
		 transformer.transform(source, output);
		 */
	}
}
