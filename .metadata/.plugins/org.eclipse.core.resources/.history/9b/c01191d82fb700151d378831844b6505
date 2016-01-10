package iut.gui.frames;

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
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import iut.app.ApplicationSession;
import iut.gui.listeners.*;
import iut.gui.widget.agenda.AgendaPanelFactory;
import iut.gui.widget.agenda.ControlAgendaViewPanel;
import iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;
import iut.io.*;

public class SchedulerFrame extends JFrame {

	CardLayout layerLayout;
	AgendaPanelFactory agendaPanelFactory;

	JPanel contentPane;
	JPanel dayView;
	JPanel weekView;
	JPanel monthView;

	AppStateWriter xmlSaver;
	AppStateReader xmlLoader;

	protected void setupUI() {

		contentPane = new JPanel();
		layerLayout = new CardLayout();
		contentPane.setLayout(layerLayout);
		ControlAgendaViewPanel agendaViewPanel = new ControlAgendaViewPanel(
				layerLayout, contentPane);
		agendaPanelFactory = new AgendaPanelFactory();
		dayView = agendaPanelFactory.getAgendaView(ActiveView.DAY_VIEW);
		weekView = agendaPanelFactory.getAgendaView(ActiveView.WEEK_VIEW);
		monthView = agendaPanelFactory.getAgendaView(ActiveView.MONTH_VIEW);
		xmlLoader = new AppStateReader();
		xmlSaver = new AppStateWriter();
		contentPane.add(dayView, ActiveView.DAY_VIEW.name());
		contentPane.add(weekView, ActiveView.WEEK_VIEW.name());
		contentPane.add(monthView, ActiveView.MONTH_VIEW.name());

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				agendaViewPanel, contentPane);
		this.setContentPane(splitPane);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		/* File Menu */
		menu = new JMenu("File");

		menuItem = new JMenuItem("Load");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				// JFileChooser fileChooser = new
				// JFileChooser(ApplicationSession.instance().getString("save"));
				JFileChooser fileChooser = new JFileChooser();
				
				if (fileChooser.showSaveDialog(SchedulerFrame.this) == 0) {
					File fileToLoad = fileChooser.getSelectedFile();
					XMLProjectReader xmltools = new XMLProjectReader();
					try {
						xmltools.load(fileToLoad);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Loaded");
				}
			}
		});

		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fileChooser = new JFileChooser();

				if (fileChooser.showSaveDialog(SchedulerFrame.this) == 0) {
					File fileToSave = fileChooser.getSelectedFile();
					if (!fileToSave.toString().toLowerCase().endsWith("xml")) {
						fileToSave = new File(String.valueOf(fileToSave
								.toString()) + ".xml");
					}

					XMLProjectWriter xmltools = new XMLProjectWriter();
					xmltools.save(ApplicationSession.instance().getAgenda1(),
							fileToSave);
					JOptionPane.showMessageDialog(null, "Saved");

				}
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Quit");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, "Not yet implemented","info", JOptionPane.INFORMATION_MESSAGE, null);
				System.exit(0);
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);

		/* Edit Menu */
		menu = new JMenu("Edit");
		JMenu submenu = new JMenu("View");
		menuItem = new JMenuItem("Day");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				layerLayout.show(contentPane, ActiveView.DAY_VIEW.name());
			}
		});
		submenu.add(menuItem);
		menuItem = new JMenuItem("Week");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				layerLayout.show(contentPane, ActiveView.WEEK_VIEW.name());
			}
		});
		submenu.add(menuItem);
		menuItem = new JMenuItem("Month");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				layerLayout.show(contentPane, ActiveView.MONTH_VIEW.name());
			}
		});
		submenu.add(menuItem);
		menu.add(submenu);
		menuBar.add(menu);

		/* Chat Menu */
		menu = new JMenu("Chat");

		menuItem = new JMenuItem("Serveur");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				iut.chat.Srv_Chat.main(null);
				System.out.println("ServeurOK");

			}
		});

		menu.add(menuItem);
		menuBar.add(menu);
		/* Help Menu */
		menu = new JMenu("Help");
		menuItem = new JMenuItem("Display");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// EX 4 : ajouter l'aide
				JOptionPane.showMessageDialog(null, "Not yet implemented",
						"info", JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		menu.add(menuItem);
		menuItem = new JMenuItem("About");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Not yet implemented",
						"info", JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);

		this.setJMenuBar(menuBar);
		this.pack();
		layerLayout.next(contentPane);
	}

	public SchedulerFrame() {
		super();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		contentPane = null;
		dayView = null;
		weekView = null;
		monthView = null;
		agendaPanelFactory = null;
		setupUI();

	}

	public SchedulerFrame(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setupUI();
	}

}
