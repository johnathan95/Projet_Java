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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

import iut.gui.widget.agenda.WeekPanel.WeekDayNames;

public class AgendaPanelFactory {

	public enum ActiveView{
		MONTH_VIEW("Month View"),
		WEEK_VIEW("Week View"),
		DAY_VIEW("Day View");
		
		private String activeView;
		
		ActiveView(String activeView) {
			this.activeView = activeView;
		}
		
		public String toString() {
			return activeView;
		}		
	}
	
	public AgendaPanelFactory() {
	}
	/**
	 * Retourne une initialisation de la vue, en renseignant le parametre de type ActiveView
	 * @param activeView
	 * @return JPanel
	 */
	public JPanel getAgendaView(ActiveView activeView) {
		JPanel agendaView = null;
		switch (activeView) {
			case MONTH_VIEW:
				MonthPanel monthPanel = new MonthPanel();
				agendaView = monthPanel;
				break;
			case WEEK_VIEW:
				WeekPanel weekPanel = new WeekPanel();
				agendaView = weekPanel;
				break;
			case DAY_VIEW:
				int monthDay = new GregorianCalendar().get(Calendar.DAY_OF_MONTH) % 7;
				DayPanel dayPanel = new DayPanel(activeView, WeekDayNames.values()[monthDay+1]);
				agendaView = dayPanel;
				break;
			default:
				break;				
		}
		return agendaView;
	}

}
