package iut.tools;

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
import java.util.Locale;

import iut.app.CommandLineOption;
import iut.app.CommandLineParser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class IUTScheduler {
	public static void main(String[] args) {
		Locale.setDefault(Locale.FRANCE);

		CommandLineParser commandLineParser = new CommandLineParser();
		CommandLineOption<java.io.File> configOption = new CommandLineOption<java.io.File>(
				CommandLineOption.OptionType.FILE, "config",
				"configuration file", new java.io.File("/tmp"));
		CommandLineOption<String> localeOption = new CommandLineOption<String>(
				CommandLineOption.OptionType.STRING, "locale", "en|fr",
				new String("fr"));

		CommandLineOption<java.io.File> projectOption = new CommandLineOption<java.io.File>(
				CommandLineOption.OptionType.FILE, "project file",
				"xml project file", new java.io.File("/tmp"));

		commandLineParser.addOption(configOption);
		commandLineParser.addOption(localeOption);
		commandLineParser.addOption(projectOption);

		commandLineParser.parse(args);

		System.err.println("Option:"
				+ commandLineParser.getOption("config").getValue());
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame mainFrame = new iut.gui.frames.SchedulerFrame(
						"STAGIO - gestionnaire de stages  | IUT d'Orsay");
				mainFrame.setVisible(true);
			}
		});
	}

}
