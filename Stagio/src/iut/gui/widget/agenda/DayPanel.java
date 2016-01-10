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
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;
import iut.gui.widget.agenda.WeekPanel.WeekDayNames;

public class DayPanel extends EventPanel {

	public DayPanel(ActiveView activeView,WeekDayNames weekDayNames) {
		super(activeView);
		switch (activeView) {
		case DAY_VIEW:
		case WEEK_VIEW:
			GridLayout daysLayout;
			switch(weekDayNames) {
			case EMPTYDAY:
				daysLayout = new GridLayout(24,1);
				this.setLayout(daysLayout);
				break;
			default:
				daysLayout = new GridLayout(25,1);
				this.setLayout(daysLayout);
				this.add(new JLabel(weekDayNames.toString()));
			}
						
			for (int hi = 0;hi<24;hi++) {
				JPanel hour = new JPanel();
				// EX4 : Creer votre propre widget permettant, l'ajout d'un évènement et d'une personne
				hour.add(new JLabel(new Integer(hi).toString()));
				this.add(hour);
			}
			break;
		case MONTH_VIEW:
			JPanel hour = new JPanel();
			hour.add(new JLabel("H"));
			this.add(hour);
		
		}
	}
	
	protected void setupUIDayView() {
		
	}
	protected void setupUIWeekView() {
		
	}
	protected void setupUIMonthView() {
		
	}


}
