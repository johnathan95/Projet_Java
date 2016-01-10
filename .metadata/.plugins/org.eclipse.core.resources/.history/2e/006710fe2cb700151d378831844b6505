package iut.chat;

import java.io.*;
import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
public class Srv_Chat extends UnicastRemoteObject implements Contrat {

  //Nom du fichier utilisé pour sauvegarder les clients inscrits dans le service de chat
  private final String NOM_FICHIER="Liste des clients.dat";
  public static int num_port=1050;
  //Liste utilisé pour vérifier l'unicité des clients inscrits dans le service
  private ArrayList Clients=new ArrayList();
  //La liste des clients connectés
  ArrayList Clients_conx=new ArrayList();
  boolean packFrame = false;
  String[] columnNames = {"Nom du client", "Mot de passe","Etat"};
  String[] [] dataTable=new String[20][3];
  JTable jTable1=new JTable(dataTable,columnNames);
  private int n=0;
  ObjectInputStream fe;
  ObjectOutputStream fs;
/**
 * Constructeur de la classe Srv_Chat
 * @throws RemoteException
 */
  public Srv_Chat() throws RemoteException {
      super();
      Server_GUI frame = new Server_GUI();
      litData();
      Test t=new Test();
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    //centrer la fenetre
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    SplashWindowApp s=new SplashWindowApp(frame,3000,t);
  }

  /**
   * Fonction permettant d'attribuer un mot de passe à l'utilisateur
   * @return String
   */
  public static String attribuer_pwd() {
      String res = "";
      String[] t = {
          "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
          "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
          "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
          "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
          , "1", "2", "3", "4", "5", "6",
          "7", "8", "9", "0", "=", "$", "!", "%", "(", ")", "+", "-", "*", "&",
          "."};
      //La taille du mot de passe est un nombre compris entre 6 et 10 générée aléatoirement
      int n = (int) (Math.random() * 5 + 6);
      //Génération du mot de passe
      for (int i = 0; i < n; i++)
        res += t[ (int) (Math.random() * 73)];
      return(res);
    }
public void ecritData(){
    File f=new File(NOM_FICHIER);
    try{
    fs=new ObjectOutputStream(new FileOutputStream(f));
    }
    catch(IOException e){
    System.out.println(e);
    }
      try {
        for (int i = 0; i < Clients.size(); i++) {
          fs.writeObject( (Client) Clients.get(i));
        }
      }
      catch (IOException ex1) {
      }
      finally{
        try {
          fs.close();
        }
        catch (IOException ex) {
        }
      }
    }

	/**
	 * Procedure qui permet de lire les clients à partir du fichier NOM_FICHIER
	 */
    public void litData(){
     boolean trouve=true,pasEncore=true;
    try {
      fe = new ObjectInputStream(new FileInputStream(NOM_FICHIER));
    }
    catch(FileNotFoundException e){
      System.out.println(e);
      trouve = false;
  }
    catch (IOException ex1) {
      System.out.println(ex1);
    }
   if(trouve){
       while (pasEncore) {
        try {
          Client c = (Client) fe.readObject();
          Clients.add(c);
          System.out.println("ok");
          dataTable[Clients.size()][0]= c.get_nom();
          dataTable[Clients.size()][1]= c.get_pwd();
          dataTable[Clients.size()][2]= "Non connecté";
        }
        catch (EOFException e) {
          pasEncore = false;
          System.out.println("fin de fichier");
          jTable1.repaint();
    }
        catch (ClassNotFoundException ex2) {
        }
        catch (IOException ex2) {
        }
       }
       try {
        fe.close();
      }
      catch (IOException ex) {
      }
     }
   }

    /**
     * Permet l'arrêt du serveur
     */
    public synchronized void terminate_Srv() {
      try {
       Naming.unbind("JChat");
     }
     catch (MalformedURLException ex1) {
     }
     catch (NotBoundException ex1) {
     }
     catch (RemoteException ex1) {
     }
      for(int i=0;i<Clients.size();i++)
        ((Client)Clients.get(i)).set_state(false);
       for(int i=0;i<Clients_conx.size();i++)
         {
           Interface1 I = null;
       try {
         I = (Interface1) Naming.lookup("rmi://"+((Adr_Cli)Clients_conx.get(i)).get_adr()+
                                       ":"+((Adr_Cli)Clients_conx.get(i)).get_port()+
                                       "/"+((Adr_Cli)Clients_conx.get(i)).get_n());
         I.close_notification();
       }
       catch (MalformedURLException ex2) {
         System.out.println(ex2);
       }
       catch (NotBoundException ex2) {
        System.out.println(ex2);
       }
       catch (RemoteException ex) {
         System.out.println(ex);
       }
         }
      //Changement de l'état des clients dans le serveur
      for(int i=0;i<Clients.size();i++)
        if(dataTable[i][2]=="Connecté"){
        dataTable[i][2]="Non connecté";
        jTable1.repaint();
      }
     }
    /**
     * Permet le démarrage du serveur
     */
     public synchronized void demarrer() {
       String T[] = new String [20];
       int j = 0;
       // Lancement du registre RMI des noms  des objets distants (annuaire)
       try {
         java.rmi.registry.LocateRegistry.createRegistry(1099);
       }
       catch (RemoteException ex1) {
       }
       if (n == 0) {
         //la création d'un objet de la classe de l'objet distant et enregistrement
         //de l'objet créé dans le registre de nom en lui affectant un nom
         try {
           Naming.rebind("JChat", this); // enregistrement
         }
         catch (MalformedURLException ex2) {
         }
         catch (RemoteException ex2) {
         }
         JOptionPane.showMessageDialog(null, "Le service de chat est démarré",
                                       "Message d’avertissement",
                                       JOptionPane.INFORMATION_MESSAGE);
         n++;
       }
       else {
         try {
           Naming.rebind("JChat", this);
         }
         catch (MalformedURLException ex) {
         }
         catch (RemoteException ex) {
         }
         JOptionPane.showMessageDialog(null,
                                       "Le service de chat est démarré",
                                       "Message d’avertissement",
                                       JOptionPane.INFORMATION_MESSAGE);
         for (int  i = 0; i < Clients_conx.size(); i++) {
           Interface1 I = null;
           try {
             I = (Interface1) Naming.lookup("rmi://" +
                                            ( (Adr_Cli) Clients_conx.get(i)).
                                            get_adr() +
                                            ":" +
                                            ( (Adr_Cli) Clients_conx.get(i)).
                                            get_port() +
                                            "/" +
                                            ( (Adr_Cli) Clients_conx.get(i)).get_n());
           }
           catch (java.rmi.ConnectException ex) {
             JOptionPane.showMessageDialog(null,
                                          "Le client " +
                                          ( (Adr_Cli) Clients_conx.get(i)).get_n() +
                                          " a quitté l'application");
            T[j] = ((Adr_Cli) Clients_conx.get(i)).get_n();
            j++;
           }
           catch (MalformedURLException ex2) {
           }
           catch (NotBoundException ex2) {

           }
           catch (RemoteException ex) {
           }
         }
         //Suppression des clients qui ont fermés l'application avec ctr+c
         if(j>0){
         for (int i = 0; i < j; i++) {
           int k=0;
            while(k<Clients_conx.size()&&( (Adr_Cli) Clients_conx.get(k)).get_n().compareTo(T[i])!=0)
              k++;
              if(k<Clients_conx.size())
                Clients_conx.remove(k);
         }
         }
         //Restauration de l'état des clients connectés dans le serveur
         int k=0;
         for(int i=0;i<Clients.size();i++){
          while(k<j&&((Client)Clients.get(i)).get_nom().compareTo(T[k])!=0)
              k++;
              if(k==j){
                ( (Client) Clients.get(i)).set_state(true);
                  dataTable[i][2] = "Connecté";
              }
              k=0;
           }
         for (int i = 0; i < Clients_conx.size(); i++) {
           Interface1 I = null;
           try {
             I = (Interface1) Naming.lookup("rmi://" +
                                            ( (Adr_Cli) Clients_conx.get(i)).
                                            get_adr() +
                                            ":" +
                                            ( (Adr_Cli) Clients_conx.get(i)).
                                            get_port() +
                                            "/" +
                                            ( (Adr_Cli) Clients_conx.get(i)).get_n());
             I.start_notification();
           }
           catch (MalformedURLException ex2) {
             System.out.println(ex2);
           }
           catch (NotBoundException ex2) {
           System.out.println(ex2);
           }
           catch (RemoteException ex) {
             System.out.println(ex);
           }
         }
       }
     }

  /**
   * Procedure qui permer d'ajouter un client à la liste des clients
   * @param nom
   * @param mdp
   */
  public synchronized void Ajouter_Cli(String nom,String mdp)
  {
   int i=0;
   Client C = null;
   C = new Client(nom, mdp,1);
   if(Clients.size()==0)
      {
        Clients.add(C);
        JOptionPane.showMessageDialog(null,"Client ajouté avec succés","Confirmation de l'ajout",JOptionPane.INFORMATION_MESSAGE );
        dataTable[Clients.size()-1][0]=" "+nom;
        dataTable[Clients.size()-1][1]=mdp;
        dataTable[Clients.size()-1][2]="Non connecté";
        jTable1.repaint();
               }
   else{
     if(Clients.size()<20){
       //Recherche du client
       while (i < Clients.size() &&
              C.get_nom().compareTo( ( (Client) Clients.get(i)).get_nom()) !=
              0)
         i++;
       if (i >= Clients.size()) {
         Clients.add(C);
         JOptionPane.showMessageDialog(null, "Client ajouté avec succés",
                                       "Confirmation de l'ajout",
                                       JOptionPane.INFORMATION_MESSAGE);
         dataTable[Clients.size() - 1][0] = " "+nom;
         dataTable[Clients.size() - 1][1] = mdp;
         dataTable[Clients.size() - 1][2] = "Non connecté";
         jTable1.repaint();
       }
       else
       JOptionPane.showMessageDialog(null,"Le pseudonyme choisie est déja utilisé par un autre client" );
     }
     else
            JOptionPane.showMessageDialog(null,"Le nombre maximum des client est atteint" );
   }

  }
  /**
   * Permet de supprimer un client
   * @param nom
   */
   public synchronized void Supprimer_Cli(String nom) {
     int i=0,j;
     while(i<Clients.size()&&!(((Client)Clients.get(i)).get_nom()).equals(nom))
       i++;
       if(i>=Clients.size())
         JOptionPane.showMessageDialog(null,"Le client avec le nom spéifié est inexistant");
    //Le client existe : il y a 2 cas soit il est connecté ou pas
       else
       {
           j=i;
       if(((Client)Clients.get(i)).get_state()){
         i=0;
         while(i<Clients_conx.size()&&!((Adr_Cli)Clients_conx.get(i)).get_n().equals(nom))
           i++;
         Clients_conx.remove(i);
          for(i=0;i<Clients_conx.size();i++){
              Interface1 I = null;
         try {
           I = (Interface1) Naming.lookup("rmi://"+((Adr_Cli)Clients_conx.get(i)).get_adr()+
                                         ":"+((Adr_Cli)Clients_conx.get(i)).get_port()+
                                         "/"+((Adr_Cli)Clients_conx.get(i)).get_n());
            I.clidisconnect(nom);
         }
         catch (MalformedURLException ex2) {
         }
         catch (NotBoundException ex2) {
         }
         catch (RemoteException ex) {
         }
            }

         }
         if(j==Clients.size()-1){
           dataTable[j][0]="";
           dataTable[j][1]="";
           dataTable[j][2]="";
         }
         else
         {
             //Décalage des élements du tableau vers le haut
             for(i=j;i<Clients.size()-1;i++)
             {
               dataTable[i][0]=dataTable[i+1][0];
               dataTable[i][1]=dataTable[i+1][1];
               dataTable[i][2]=dataTable[i+1][2];
             }
             //Suppression du dernier élement du tableau
             dataTable[Clients.size()-1][0]="";
             dataTable[Clients.size()-1][1]="";
             dataTable[Clients.size()-1][2]="";
         }
          Clients.remove(j);

          JOptionPane.showMessageDialog(null,"Le client de pseudonyme "+nom+" est supprimé avec succés","Confirmation de suppression",JOptionPane.INFORMATION_MESSAGE);
          jTable1.repaint();
      }
   }

public String[] list_con()throws RemoteException{
  String [] T=new String[49];
  for(int i=0;i<Clients_conx.size();i++)
    T[i]=((Adr_Cli)Clients_conx.get(i)).get_n();
  return T;
}
  public int getnbrcon()throws RemoteException{
    return Clients_conx.size();
  }

  /**
   * Permet de connecter un client au service
   */
   public synchronized boolean connect(String nom,String mdp,String adr,int num_port)throws RemoteException {
     boolean res = false;
     int i = 0;
     //Recherche du client dans la liste des clients
     while (i < Clients.size() &&
            (nom.compareTo( ( (Client) Clients.get(i)).get_nom()) != 0 ||
             mdp.compareTo( ( (Client) Clients.get(i)).get_pwd()) != 0))
       i++;
     if (i < Clients.size()) {
       res = true;
       //changement de l'état du client dans le serveur
       ( (Client) Clients.get(i)).set_state(true);
       dataTable[i][2]="Connecté";
       jTable1.repaint();
       Adr_Cli A=new Adr_Cli(nom,adr,num_port);
       Clients_conx.add(A);
       if (Clients_conx.size() > 1) {
         for(i=0;i<=Clients_conx.size()-1;i++){
         Interface1 I = null;
         if(((Adr_Cli)Clients_conx.get(i)).get_n().compareTo(nom)!=0)
         {
         try {
           I = (Interface1) Naming.lookup("rmi://"+((Adr_Cli)Clients_conx.get(i)).get_adr()+
                                          ":"+((Adr_Cli)Clients_conx.get(i)).get_port()+
                                          "/"+((Adr_Cli)Clients_conx.get(i)).get_n());
           I.newcliConnexion(nom);
         }
         catch (MalformedURLException ex2) {
           System.out.println(ex2);
         }
         catch (NotBoundException ex2) {
           System.out.println(ex2);
         }
         catch (RemoteException ex) {
           System.out.println(ex);
         }
         }
         }
       }
     }
     return res;
   }
   /**
    * Permet d'envoyer un message
    */
   public synchronized void envoyer(Communication c) throws RemoteException {
//La vérification du destinataire est faite dans la partie graphique en affichant les destinataires connectés
     int i=0;
     while(i<Clients_conx.size()&&!((Adr_Cli)Clients_conx.get(i)).get_n().equals(c.get_dest()))
            {
              i++;
            }
            if(i<Clients_conx.size()){
              Interface1 I = null;
              try {
                I = (Interface1) Naming.lookup("rmi://"+((Adr_Cli)Clients_conx.get(i)).get_adr()+
                                          ":"+((Adr_Cli)Clients_conx.get(i)).get_port()+
                                          "/"+((Adr_Cli)Clients_conx.get(i)).get_n());
                I.updatemsgs(c);
              }
              catch (MalformedURLException ex2) {
              System.out.println(ex2);
              }
              catch (NotBoundException ex2) {
                System.out.println(ex2);
              }
              catch (RemoteException ex2) {
                System.out.println(ex2);
              }
            }
   }
   /**
    * Permet la déconnexion de client
    */
   public synchronized void  disconnect(String nom)throws RemoteException {
     int i=0;
     while(i<Clients_conx.size()&&nom.compareTo(((Adr_Cli)Clients_conx.get(i)).get_n())!=0)
       i++;
       if(i<Clients_conx.size())
       {
         Clients_conx.remove(i);
        if(Clients_conx.size()>=1){
          for (i = 0; i < Clients_conx.size(); i++) {
            Interface1 I = null;
                   try {
                     I = (Interface1) Naming.lookup("rmi://"+((Adr_Cli)Clients_conx.get(i)).get_adr()+
                                            ":"+((Adr_Cli)Clients_conx.get(i)).get_port()+
                                            "/"+((Adr_Cli)Clients_conx.get(i)).get_n());
                     I.clidisconnect(nom);
                   }
                   catch (MalformedURLException ex2) {
                   }
                   catch (NotBoundException ex2) {
                   }
                   catch (RemoteException ex) {
                   }
     }
   }
   //Changement de l'état du client dans le serveur
         i=0;
         while(i<Clients.size()&&nom.compareTo(((Client)Clients.get(i)).get_nom())!=0)
           i++;
           ((Client)Clients.get(i)).set_state(false);
           dataTable[i][2]="Non connecté";
           jTable1.repaint();
       }
    }
public static void main(String []args)  {
 try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
       }
       catch(Exception e) {
         e.printStackTrace();}
      try {
        new Srv_Chat();
      }
      catch (RemoteException ex) {
        ex.printStackTrace();
      }
     }

/**
 * Fonction qui récupère le numéro de port à utiliser
 */
  public int get_num_port()throws RemoteException {
    return num_port++;
  }

  /**
   *
   *Classe de l'interface graphique du serveur
   *
   */
  private class Server_GUI extends JFrame {
     //Panel principal du GUI
     JPanel contentPane;
     //Tabbed pane et layouts pour pane 1 et 2
     //Pane 3 utilise un Layout null
     JTabbedPane jTabbedPane1 = new JTabbedPane();
     JPanel jPanel1 = new JPanel();
     JPanel jPanel2 = new JPanel();
     FlowLayout flowLayout1_panel1 = new FlowLayout();
     FlowLayout flowLayout1_panel2 = new FlowLayout();


//Le menu de l'application
     JMenuBar jMenuBar1 = new JMenuBar();
     JMenu jMenuFile = new JMenu();
     JMenuItem jMenuFileExit = new JMenuItem();
     JMenu jMenuHelp = new JMenu();
     JMenuItem jMenuHelpAbout = new JMenuItem();

     //Radio buttons on pane 1; buttons are in a
     //mutually exclusive button group
     ButtonGroup buttonGroup1 = new ButtonGroup();

     //Labels sur pane 2
     JLabel JL_Pseudo_supp = new JLabel();
     JLabel warning = new JLabel();
     //Table et bordure sur pane 3
     TitledBorder titledBorder1;
     JScrollPane scrollPane1 = new JScrollPane(jTable1);
     JButton start_B = new JButton();
     JButton off_B = new JButton();
     JLabel jLabel_Pseudo = new JLabel();
     JButton Gen_mdp = new JButton();
     JTextField mdp = new JTextField();
     JTextField nom = new JTextField();
     JButton add_B = new JButton();
     JTextField nom_supp = new JTextField();
     JButton supp_B = new JButton();


     /**
      * Constructeur de la fenetre
      */
     public Server_GUI() {
       boolean trouve=true;
       enableEvents(AWTEvent.WINDOW_EVENT_MASK);
       try {
         jbInit();
       }
       catch(Exception e) {
         e.printStackTrace();
       }
     }

     /**
      * Procedure qui permet l'initialisation des composants
      *
      * @throws Exception exception
      */
     private void jbInit() throws Exception  {

       //Initialisation de la frame principale
       contentPane = (JPanel) this.getContentPane();
       contentPane.setLayout(null);
       this.setJMenuBar(jMenuBar1);
       this.setSize(new Dimension(416, 356));
       this.setTitle("Interface du serveur de chat");
       //Initialisation du menu de l'application
       this.setJMenuBar(jMenuBar1);
       jMenuFile.setText("Fichier");
       jMenuFileExit.setText("Quitter");
       jMenuFileExit.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(ActionEvent e) {
          System.exit(HIDE_ON_CLOSE);
         }
       });
       mdp.setEditable(false);
       jTable1.setToolTipText("La liste des clients inscrits dans le service chat avec leurs états");

       jMenuFile.add(jMenuFileExit);
       jMenuHelp.setText("Aide");
       jMenuHelpAbout.setText("A propos");
       jMenuHelpAbout.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 JOptionPane.showMessageDialog(null, "Not yet implemented","info", JOptionPane.INFORMATION_MESSAGE, null);;
         }
       });
       jMenuHelp.add(jMenuHelpAbout);
       jMenuBar1.add(jMenuFile);
       jMenuBar1.add(jMenuHelp);



       //Initialisation du tabbed pane
       jTabbedPane1.setBounds(new Rectangle(-2, 17, 418, 235));
       JPanel panel1 = new JPanel(flowLayout1_panel1);
       JPanel panel2 = new JPanel(flowLayout1_panel2);
       JPanel panel3 = new JPanel();
        //Creation du tabbed pane et des tabs
       //Création d'un écouteur respocable de capter les évenements générés par les boutons
        ButtonHandler gestionnaire=new ButtonHandler();

       start_B.setBackground(new Color(150, 163, 255));
       start_B.setBounds(new Rectangle(35, 266, 91, 28));
       start_B.setToolTipText("Démarrer le serveur");
       start_B.setText("Lancer");

       //Enregistrement d'un écouteur pour les boutons de l'interface
       start_B.addActionListener(gestionnaire);
       off_B.setEnabled(false);
       off_B.setBackground(new Color(150, 163, 253));
       off_B.setBounds(new Rectangle(290, 266, 89, 28));
       off_B.setToolTipText("Arréter le serveur");
       off_B.setText("Arrêter ");

       //Enregistrement d'un écouteur pour les boutons de l'interface
       off_B.addActionListener(gestionnaire);

       jLabel_Pseudo.setText("Pseudonyme :");
       jLabel_Pseudo.setBounds(new Rectangle(32, 25, 75, 26));
       jPanel1.setLayout(null);
       Gen_mdp.setBackground(new Color(150, 163, 253));
       Gen_mdp.setBounds(new Rectangle(25, 66, 80, 27));
       Gen_mdp.setToolTipText("Attribuer automatiquement un mot de passe");
       Gen_mdp.setText("Attribuer");

       //Enregistrement d'un écouteur pour les boutons de l'interface
       Gen_mdp.addActionListener(gestionnaire);

       mdp.setToolTipText("Mot de passe généré automatiquement");
       mdp.setText("");
       mdp.setBounds(new Rectangle(129, 67, 120, 25));
       mdp.setFont(new Font("serif",Font.BOLD,14));
       nom.setBounds(new Rectangle(130, 29, 120, 25));
       add_B.setBackground(new Color(150, 163, 253));
       add_B.setBounds(new Rectangle(105, 110, 83, 25));
       add_B.setToolTipText("Ajouter un clients à la liste des clients inscrits dans le service de chat");
       add_B.setText("Ajouter");
       warning.setFont(new java.awt.Font("Dialog", 1, 11));
       warning.setForeground(Color.red);
       warning.setText("Le service de chat n\'est pas démarré");
       warning.setBounds(new Rectangle(11, 165, 313, 33));
       panel1.add(warning, null);
       //Enregistrement d'un écouteur pour les boutons de l'interface
       add_B.addActionListener(gestionnaire);

       nom_supp.setText("");
       nom_supp.setToolTipText("Supprimer un client de la liste des clients inscrits dans le service de chat");
       nom_supp.setBounds(new Rectangle(145, 32, 104, 31));
       supp_B.setBounds(new Rectangle(145, 85, 103, 27));
       supp_B.setText("Supprimer");
       supp_B.setBackground(new Color(150, 163, 253));

       //Enregistrement d'un écouteur pour les boutons de l'interface
       supp_B.addActionListener(gestionnaire);

       panel3.setBackground(new Color(212, 208, 255));
       jPanel1.setBackground(new Color(212, 208, 255));
       jPanel2.setBackground(new Color(212, 208, 255));
       contentPane.add(jTabbedPane1, null);
       jTabbedPane1.addTab( "Ajouter", panel1);
       panel1.add(jPanel1, null);
       jPanel1.add(jLabel_Pseudo, null);
       jPanel1.add(Gen_mdp, null);
       jPanel1.add(mdp, null);
       jPanel1.add(nom, null);
       jPanel1.add(add_B, null);
       jTabbedPane1.addTab("Supprimer", panel2);
       jTabbedPane1.addTab("Liste des clients", panel3);
       panel3.add(scrollPane1, null);
       scrollPane1.setViewportView(jTable1);

       //Initialisation de pane 1
       panel1.setLayout(null);
       jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
       jPanel1.setBounds(new Rectangle(37, 12, 285, 145));


       //Initialisation du pane 2
       panel2.setLayout(null);
       panel2.setBorder(BorderFactory.createRaisedBevelBorder());
       jPanel2.setBorder(BorderFactory.createRaisedBevelBorder());
       jPanel2.setBounds(new Rectangle(37, 17, 293, 132));
       jPanel2.setLayout(null);
       JL_Pseudo_supp.setText("Pseudonyme :");
       JL_Pseudo_supp.setBounds(new Rectangle(61, 30, 68, 34));

       //Initialisation de pane 3
       panel3.setBorder(titledBorder1);


       titledBorder1 = new TitledBorder("");
       jTable1.setBackground(Color.WHITE);
       jTable1.setBorder(BorderFactory.createEtchedBorder());
       jTable1.setGridColor(Color.BLUE);
       jTable1.setPreferredScrollableViewportSize(new Dimension(360, 160));
       jTable1.setEnabled(false);

       panel2.add(jPanel2, null);

       contentPane.add(off_B, null);
       contentPane.add(start_B, null);
       jPanel2.add(nom_supp, null);
       jPanel2.add(JL_Pseudo_supp, null);
       jPanel2.add(supp_B, null);
       this.setResizable(false);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }


     /**
      * Procedure gerant l'action faite lors de la sélection de "Quitter"
      * @param e
      */
     public void jMenuFileExit_actionPerformed(ActionEvent e) {
          System.exit(0);
        }

     	/**
     	 * Procedure gerant l'action faite lors de la sélection de "Aide"
     	 * @param e
     	 */
        public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
        }

        protected void processWindowEvent(WindowEvent e) {
                    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
                      try {
        Naming.unbind("JChat");
      }
      catch (MalformedURLException ex1) {
      }
      catch (NotBoundException ex1) {
      }
      catch (RemoteException ex1) {
      }
        for(int i=0;i<Clients_conx.size();i++)
          {
            Interface1 I = null;
        try {
          I = (Interface1) Naming.lookup("rmi://"+((Adr_Cli)Clients_conx.get(i)).get_adr()+
                                        ":"+((Adr_Cli)Clients_conx.get(i)).get_port()+
                                        "/"+((Adr_Cli)Clients_conx.get(i)).get_n());
          I.close_notification();
        }
        catch (MalformedURLException ex2) {
          System.out.println(ex2);
        }
        catch (NotBoundException ex2) {
         System.out.println(ex2);
        }
        catch (RemoteException ex) {
          System.out.println(ex);
        }
          }
          ecritData();
          System.exit(0);
                    }
                  }

    /**
     *  
     * Classe qui permet de capter les événements engendrés par les boutons
     *
     */
    private class ButtonHandler implements ActionListener {
    //Pour vérifier que le boutton "démarrer" est actionné une seule fois
    boolean clicked=false;
      public void actionPerformed(ActionEvent e) {

    	//Actionnement du bouton qui démarre le serveur
        if(e.getSource()==start_B)
        {
            if(!clicked)
            {
            demarrer();
            warning.setVisible(false);
            clicked=true;
            start_B.setEnabled(false);
            off_B.setEnabled(true);
            }
        }
          else
          {
            //Actionnement du bouton qui arrete le serveur
            if (e.getSource() == off_B)
            {
          //Invocation de la méthode qui arrete le serveur
          terminate_Srv();
          clicked=false;
          start_B.setEnabled(true);
          off_B.setEnabled(false);
        }
            else {
              //Actionnement du bouton qui génére un mot de passe
              if (e.getSource() ==Gen_mdp )
              {
              mdp.setText(attribuer_pwd());
              }
              else
              {
                  //Actionnement du bouton ajouter un client
                if (e.getSource() == add_B) {
                  if (nom.getText().length() == 0)
                    JOptionPane.showMessageDialog(null,
                        "Vous devez donner le nom du client à ajouter",
                                                  "Informations manquantes",
                                                  JOptionPane.WARNING_MESSAGE);
                  else
                    Ajouter_Cli(nom.getText(), mdp.getText());
                }
                else {
                  //Actionnement du bouton supprimer un client
                  if (e.getSource() == supp_B) {
                    if (nom_supp.getText().length() == 0)
                      JOptionPane.showMessageDialog(null,
                          "Vous devez donner le nom du client à supprimer",
                                                    "Informations manquantes",
                                                    JOptionPane.WARNING_MESSAGE);
                    else
                      Supprimer_Cli(nom_supp.getText());
                  }
                }
              }
              }
            }
          }
      }
   }
}
