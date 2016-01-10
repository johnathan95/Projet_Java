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
import javax.swing.JPanel;


import iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;
import iut.app.ApplicationSession;

public class WeekPanel extends EventPanel {

	public enum WeekDayNames {
		EMPTYDAY("",""),
		MONDAY(ApplicationSession.instance().getString("monday"),ApplicationSession.instance().getString("mon")),
		TUESDAY(ApplicationSession.instance().getString("tuesday"),ApplicationSession.instance().getString("tue")),
		WEDNESDAY(ApplicationSession.instance().getString("wednesday"),ApplicationSession.instance().getString("wed")),
		THURSDAY(ApplicationSession.instance().getString("thursday"),ApplicationSession.instance().getString("thu")),
		FRIDAY(ApplicationSession.instance().getString("friday"),ApplicationSession.instance().getString("fri")),
		SATURDAY(ApplicationSession.instance().getString("saturday"),ApplicationSession.instance().getString("sat")),
		SUNDAY(ApplicationSession.instance().getString("sunday"),ApplicationSession.instance().getString("sun"));
		
		private String name;
		private String shortName;
		
		WeekDayNames(String name,String shortName) {
			this.name = name;
			this.shortName = shortName;
		}
		
		public String getShortName() {
			return shortName;
		}
		
		public String toString() {
			return name;
		}
	}
	
	public WeekPanel() {
		super(ActiveView.WEEK_VIEW);
		GridLayout daysOfWeekLayout = new GridLayout(1,7);		
		this.setLayout(daysOfWeekLayout);
		for (int di = 0;di<7;di++)	{
			this.add(new DayPanel(ActiveView.WEEK_VIEW,WeekDayNames.values()[di+1]));
		}
	}
}
