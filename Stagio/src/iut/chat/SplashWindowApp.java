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

public class SplashWindowApp {
    public SplashWindowApp(final Frame f, int waitTime,final Test F) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = F.getSize();
        if (frameSize.height > screenSize.height) {
          frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
          frameSize.width = screenSize.width;
        }
        F.setLocation( (screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);
        F.setVisible(true);
        final int pause = waitTime;
        
        //thread pour fermer le splash screen
        final Runnable closeRunner = new Runnable() {
            public void run() {
                F.setVisible(false);
                F.dispose();
                f.setVisible(true);
            }
        };
        Runnable waitRunner = new Runnable() {
            public void run() {
                try {
                  int x=0,y=pause/100;
                  while(x<pause){
                    F.jProgressBar1.setValue(F.jProgressBar1.getValue()+1);
                    Thread.sleep(y);
                    x+=y;}
                    SwingUtilities.invokeAndWait(closeRunner);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        //affiche le splash screen
        //lance le thread qui ferme le splash screen apres un certain temps
        Thread splashThread = new Thread(waitRunner, "SplashThread");
        splashThread.start();
    }
    public SplashWindowApp(final Frame f, int waitTime,final Essai F) {
       //super(f);
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       Dimension frameSize = F.getSize();
       if (frameSize.height > screenSize.height) {
         frameSize.height = screenSize.height;
       }
       if (frameSize.width > screenSize.width) {
         frameSize.width = screenSize.width;
       }
       F.setLocation( (screenSize.width - frameSize.width) / 2,
                         (screenSize.height - frameSize.height) / 2);
       F.setVisible(true);
       //afin d'acceder à la valeur WaitTime
       final int pause = waitTime;

       final Runnable closeRunner = new Runnable() {
           public void run() {
               F.setVisible(false);
               F.dispose();
               f.setVisible(true);
           }
       };
       Runnable waitRunner = new Runnable() {
           public void run() {
               try {
                 int x=0,y=pause/100;
                 while(x<pause){
                   F.jProgressBar1.setValue(F.jProgressBar1.getValue()+1);
                   Thread.sleep(y);
                   x+=y;}

                   SwingUtilities.invokeAndWait(closeRunner);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       };

       //affiche le splash screen
       //lance le thread qui ferme le splash screen apres un certain temps
       Thread splashThread = new Thread(waitRunner, "SplashThread");
       splashThread.start();
   }

}
