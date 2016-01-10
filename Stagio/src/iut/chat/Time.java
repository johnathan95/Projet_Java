package iut.chat;
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
import java.awt.*;
import javax.swing.*;

public class Time extends JWindow {

  public Time(final Frame f, int waitTime) {
      super(f);
      final JLabel jlabel = new JLabel("Connexion au serveur de chat en cours...", SwingConstants.CENTER);
      jlabel.setFont(new Font("serif",Font.BOLD,14));
      jlabel.setForeground(Color.BLUE);
      jlabel.setBackground(Color.white);
      //ajoute le label au panel
      getContentPane().add(jlabel, BorderLayout.CENTER);
      pack();
      //centre le splash screen
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension labelSize = jlabel.getPreferredSize();
      setLocation(screenSize.width / 2 - (labelSize.width / 2),
              screenSize.height / 2 - (labelSize.height / 2));
 
  }

}
