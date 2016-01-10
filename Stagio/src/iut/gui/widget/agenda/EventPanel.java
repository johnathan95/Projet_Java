package iut.gui.widget.agenda;
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
import javax.swing.JPanel;

public class EventPanel extends JPanel {
	
	protected AgendaPanelFactory.ActiveView activeView;
	public EventPanel(AgendaPanelFactory.ActiveView activeView) {
		this.activeView = activeView;
	}

}
