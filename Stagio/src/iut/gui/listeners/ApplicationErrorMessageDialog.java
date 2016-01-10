package iut.gui.listeners;
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
import javax.swing.JOptionPane;

public class ApplicationErrorMessageDialog extends
		AbstractApplicationMessageDialog {

	@Override
	protected void showMessage(String level, String message) {
		JOptionPane.showMessageDialog(null, message, level, JOptionPane.ERROR_MESSAGE, null);
	}

}
