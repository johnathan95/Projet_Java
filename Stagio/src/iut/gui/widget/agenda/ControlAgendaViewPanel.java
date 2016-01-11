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
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerModel;

import iut.app.ApplicationSession;

public class ControlAgendaViewPanel extends JPanel {

	CardLayout agendaViewLayout;
	JPanel contentPane;
	
	int selectedYear;
	int selectedMonth;
	int selectedDay;
	/**
	 * Constructeur de l'objet ControlAgendaViewPanel.
	 * Ce constructeur créer deux ComboBox et un Spinner
	 * @param layerLayout
	 * @param contentPane
	 */
	public ControlAgendaViewPanel(CardLayout layerLayout, final JPanel contentPane) {

		this.agendaViewLayout = layerLayout;
		this.contentPane = contentPane;
		JPanel commandPanel = new JPanel();
		JPanel bottom = new JPanel();
		JButton btnWebSite = new JButton("publish in the website");
		commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.PAGE_AXIS));
		Calendar calendar = Calendar.getInstance();
        SpinnerNumberModel dateModel = new SpinnerNumberModel(calendar.get(Calendar.YEAR),
        												calendar.get(Calendar.YEAR)-5,
        												calendar.get(Calendar.YEAR)+5,
        												1);
        JSpinner  yearsSpinner       = new JSpinner(dateModel);
        yearsSpinner.setEditor(new JSpinner.NumberEditor(yearsSpinner, "#"));
		JComboBox monthComboBox      = new JComboBox(ApplicationSession.instance().getMonths());
		JComboBox daysOfWeekComboBox = new JComboBox(ApplicationSession.instance().getDays());

		commandPanel.add(yearsSpinner);
		commandPanel.add(monthComboBox);
		commandPanel.add(daysOfWeekComboBox);
		commandPanel.add(btnWebSite);
		
		this.add(commandPanel, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.PAGE_END);
	}
	
	public int getYear() {
		return selectedYear;
	}
	public int getMonth() {
		return selectedMonth;
	}
	public int getDay() {
		return selectedDay;
	}
	
}
