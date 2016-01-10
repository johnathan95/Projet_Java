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
import java.io.File;
import java.util.Date;

import iut.app.ApplicationSession;


/**
 *  sauvegarde l'état et de l'application
 *
 */


public class AppStateWriter {
	private XMLProjectWriter xmlWriter;
	private String output;
	public AppStateWriter() {
		xmlWriter = new XMLProjectWriter() ;
	}

	public void save() throws Exception {
		// TODO Auto-generated method stub
		File dir = new File("backup");

        if (!dir.isDirectory()) {
            if (!dir.mkdir()) throw new Exception("backup mkdir abort");
        }
         output = "backup/save-" + new Date().getTime() + ".xml";
        //xmlWriter.save(ApplicationSession.instance().getAgenda1(), output);
	}
}
