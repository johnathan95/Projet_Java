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
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Fenetre
    extends JPanel{
    private String image = "C:/Users/johnathan/Desktop/DUT/PROG WEB/Site de Gestion de Stage sans CMS/images/stagiob.png";
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(Toolkit.getDefaultToolkit().getImage(image),0,0,this);
    }
    public Fenetre(){
      super();
    }
}
