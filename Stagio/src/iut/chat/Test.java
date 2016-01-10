package iut.chat;

import java.awt.*;
import javax.swing.*;
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
public class Test extends JWindow {
  static JProgressBar jProgressBar1 = new JProgressBar();
  JPanel Pan=new Fenetre();
  public Test() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.getContentPane().setLayout(null);
    jProgressBar1.setForeground(new Color(55, 255, 62));
    jProgressBar1.setBounds(new Rectangle(-1, 301, 399, 21));
    Pan.setBounds(new Rectangle(0, 1, 398, 300));
    this.getContentPane().add(Pan);
    this.getContentPane().add(jProgressBar1);
    this.setSize(new Dimension(399, 323));
  }
}
