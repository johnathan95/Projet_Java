import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.text.html.parser.ParserDelegator; 

public class agenda extends JApplet implements ActionListener, Runnable, MouseListener
{
    public agenda()
    {
       	locale = Locale.getDefault();
        	boutonsMois = new JButton[42];
            abrv_noms_jours = new JButton[7];
        	titre_activites = new JLabel("AGENDA - cliquer sur un jour de la grille pour obtenir plus d'information",JLabel.CENTER);
        	info_activites = new JLabel("",JLabel.CENTER);
	info_activites_jp = new JLabel("<html><br /><br /></html>");
	choix_activite = new JLabel ("",JLabel.CENTER);
        	zoneMoisAnnee = new JLabel("",JLabel.CENTER);
        	zoneDate = new JLabel("",JLabel.CENTER);
        	fonte = new Font("Helvetica", 1, 8);
        	fonte1 = new Font("Helvetica", 1, 12);
	cal = new GregorianCalendar();
        	maxvect = 0;
        	jours_ouverts = new int[7];
        	lignes_decale = 31;//jusque [liste des expositions] dans agenda.ini
        	couleur_RGB = new Color[11];
        	strchoix_activite = new String[42];
     	lien_bouton = new String[42];
	
    }    

    public void init()
    {
        	//recuperation du seul paramètre - nom du fichier de configuration - agenda.ini par défaut
	nomFichier = getParameter("fichier");
        	if(nomFichier == null) nomFichier = "agenda.ini";//est un fichier texte
        	//compte les lignes du fichier texte
	try
       	{
            	URL url = new URL(getCodeBase(), nomFichier);
            	InputStream inputstream = url.openStream();
            	BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
            	String s;
            	while((s = bufferedreader.readLine()) != null) 
                		maxvect++;
            	bufferedreader.close();
        	}
        	catch(Exception exception) { }
        	vect = new String[maxvect];//dimensionne la variable string  vect en fonction du nombre de lignes du fichier
        	//place les lignes du fichier dans la variable vect
	try
        	{
            	URL url1 = new URL(getCodeBase(), nomFichier);
            	InputStream inputstream1 = url1.openStream();
            	BufferedReader bufferedreader1 = new BufferedReader(new InputStreamReader(inputstream1));
            	String s1;
            	for(int j1 = 0; (s1 = bufferedreader1.readLine()) != null; j1++) vect[j1] = s1;//1ère ligne = vect[0]
	            bufferedreader1.close();
        	}
        	catch(Exception exception1) { }
        	int i = 0;
	// récupération des couleurs RGB - 24 premières lignes du fichier texte [info] et paramètres sur 2 lignes consécutives
        	for(int j = 1; j < lignes_decale - 8; j += 2)
        		{
            	st = new StringTokenizer(vect[j], ",");
            	try
            	{
                		rouge = Integer.parseInt(st.nextToken());
                		vert = Integer.parseInt(st.nextToken());
                		bleu = Integer.parseInt(st.nextToken());
            	}
            	catch(Exception exception5)
            	{
                		showStatus((new StringBuilder()).append("Les valeurs des parametres couleur sont fausses - cas").append(j).toString());
            	}
            	couleur_RGB[i] = new Color(rouge, vert, bleu);
            	i++;
        		}

        	//récupération des jours de fermeture-ouverture
	st = new StringTokenizer(vect[lignes_decale - 8], ",");
        	try
        	{
            	for( int ii=0 ; ii < 7 ; ii++)
			{
			jours_ouverts[ii] = Integer.parseInt(st.nextToken());
            		}
        	}
        	catch(Exception exception2)
        	{
            	showStatus("Les valeurs des jours de fermeture sont fausses");
        	}
        	//récupération du texte de l'infobulle lorsque jour de fermeture - texte en html
	try
        	{
            	info_bulle_ferme = vect[lignes_decale - 6];
        	}
        	catch(Exception exception3)
        	{
            	showStatus("Le texte de l'infobulle ferme est faux");
        	}
        	//récupération du début du texte de l'infobulle lorsque jour ouverture - texte en html
	try
        	{
            	info_bulle_ouvert = vect[lignes_decale - 4];
        	}
        	catch(Exception exception4)
        	{
            	showStatus("Le texte de debut de l'infobulle ouvert est faux");
        	}
 	// récupération de la fin du texte de infobulle lorsque jour ouverture- texte en html
	try
        	{
            	info_bulle_ouvert2 = vect[lignes_decale - 2];
        	}
        	catch(Exception exception4)
        	{
            	showStatus("Le texte de fin de l'infobulle ouvert est faux");
        	}
	
	//récupération des données de chaque activité (4 données séparées par virgule)
       	int k = lignes_decale;
        	i = 0;
        	int i1 = 0;
        	lien_expo = new String[maxvect - lignes_decale];
        	type_expo = new String[maxvect - lignes_decale];
        	stractivite = new String[maxvect - lignes_decale];
        	maxdate = (maxvect - lignes_decale) * 3;
        	date_debut = new int[maxdate];
        	date_fin = new int[maxdate];
        	while(k < maxvect) 
        	{
            	st = new StringTokenizer(vect[k], ",");
            	try
            	{
                		datedebut = st.nextToken();//1ère donnée - date de debut de l'expo
                		datefin = st.nextToken();//2ème donnée - date de fin de l'expo
                		lien_expo[i1] = st.nextToken();//3ème donnée - le lien vers une page html ou php pour plus de détails
                		type_expo[i1] = st.nextToken();//4ème donnée - le titre de l'expo - repris dans l'infobulle de jour ouverture
            	}
            	catch(Exception exception6)
            	{
                		showStatus("Les valeurs des parametres exposition sont fausses");
            	}
            	//récupération des 3 données de date - jj/mm/aaaa en entier  jj, mm, aaaa
		st = new StringTokenizer(datedebut, "/");
            	try
            	{
                		jourdebut = Integer.parseInt(st.nextToken());//entier jj
                		moisdebut = Integer.parseInt(st.nextToken());//entier mm
                		anneedebut = Integer.parseInt(st.nextToken());//entier aaaa
            	}
            	catch(Exception exception7)
            	{
                		showStatus("Les valeurs des parametres date de debut sont fausses");
            	}
            	moisdebut--;//enlève 1 car janvier = 0
            	//place les 3 entiers consécutivement dans la vaible int date_debut
		date_debut[i] = jourdebut;
            	date_debut[i + 1] = moisdebut;
            	date_debut[i + 2] = anneedebut;
            	// on recommence avec les dates de fin d'expo
		st = new StringTokenizer(datefin, "/");
            	try
            	{
                		jourfin = Integer.parseInt(st.nextToken());
                		moisfin = Integer.parseInt(st.nextToken());
                		anneefin = Integer.parseInt(st.nextToken());
            	}
            	catch(Exception exception8)
            	{
                		showStatus("Les valeurs des parametres date de fin sont fausses");
            	}
            	moisfin--;
            	date_fin[i] = jourfin;
            	date_fin[i + 1] = moisfin;
            	date_fin[i + 2] = anneefin;
            
		//construction du texte des éléments dans la liste des activités (si date de debiut = date de fin alors expo d'1 jour)
		DateFormat dateformat1 = DateFormat.getDateInstance(0, locale);
            	GregorianCalendar gregoriancalendar = new GregorianCalendar();
            	gregoriancalendar.set(anneedebut, moisdebut, jourdebut);
            	date1 = gregoriancalendar.getTime();
            	gregoriancalendar.set(anneefin, moisfin, jourfin);
            	date2 = gregoriancalendar.getTime();
            	if(date1.equals(date2))
                		stractivite[i1] = "<html>"+type_expo[i1]+" du "+dateformat1.format(date1)+"</html>";
            	else
                		stractivite[i1] ="<html>"+type_expo[i1]+"  du "+dateformat1.format(date1)+" au "+dateformat1.format(date2)+"</html>";
            	//on incrémente les 3 variables
		k++;
            	i += 3;
            	i1++;
        	}//fin de while
           
	// mise en place des éléments dans applet
	ParserDelegator workaround = new ParserDelegator(); //utilisation de balises <html> dans les textes du JLabel et du ToolTip

            this.getContentPane().setLayout(new BorderLayout());
	//panel est le Panel principal de mise en page dans l'applet
	JPanel panel = new JPanel();
        	//mise en page choisie
	panel.setLayout(new BorderLayout(2, 2));
	panel.setBorder(BorderFactory.createLineBorder(couleur_RGB[0],2));

	//panel1 est un Panel secondaire
        	JPanel panel1 = new JPanel();
        	//mise en page choisie dans panel1
	panel1.setLayout(new BorderLayout());
	
	//les éléments placés dans panel1
        	boutonPrec = new JButton("<<");
        	boutonPrec.setCursor(new Cursor(12));
        	boutonPrec.setToolTipText("<html><boby><table border='1'><tr><td bgcolor='white' text='black'>Passer au mois pr\351c\351dent</td></tr></table></body></html>");
        	boutonPrec.addActionListener(this);
	boutonPrec.addMouseListener(this);
	
	boutonSuiv = new JButton(">>");
        	boutonSuiv.setCursor(new Cursor(12));
        	boutonSuiv.setToolTipText("<html><boby><table border='1'><tr><td bgcolor='white' text='black'>Passer au mois suivant</td></tr></table></body></html>");
	boutonSuiv.addActionListener(this);
	boutonSuiv.addMouseListener(this);

	zoneMoisAnnee.setOpaque(true);
	zoneMoisAnnee.setForeground(couleur_RGB[5]);
	zoneMoisAnnee.setBackground(couleur_RGB[6]);

	zoneDate.setOpaque(true);
	zoneDate.setForeground(couleur_RGB[3]);
            zoneDate.setBackground(couleur_RGB[4]);
            zoneDate.setFont(fonte1);

        	panel1.add(boutonPrec,BorderLayout.WEST);
        	panel1.add(zoneMoisAnnee, BorderLayout.CENTER);
	panel1.add(boutonSuiv,BorderLayout.EAST);
        	panel1.add(zoneDate,BorderLayout.SOUTH);
        	
	//panel2 est un Panel secondaire
	JPanel panel2 = new JPanel();
        	//mise en page de 7 colonnes sur 7 lignes
	panel2.setLayout(new GridLayout(7, 7,2,2));
	
        	//les éléments de panel2
            for (int ii=0 ; ii<7; ii++)
		{
		abrv_noms_jours[ii] = new JButton(tabJours[ii]);
		abrv_noms_jours[ii].setOpaque(true);
		abrv_noms_jours[ii].setFont(fonte1);
            	abrv_noms_jours[ii].setForeground(couleur_RGB[1]);
            	abrv_noms_jours[ii].setBackground(couleur_RGB[2]);	
		panel2.add(abrv_noms_jours[ii]);
		}
        	
        	for(int l1 = 0; l1 < 42; l1++)
	        	{
            	boutonsMois[l1] = new JButton(" ");
		boutonsMois[l1].setForeground(couleur_RGB[5]);
            	boutonsMois[l1].setBackground(couleur_RGB[6]);
            	boutonsMois[l1].addMouseListener(this);
		panel2.add(boutonsMois[l1]);
        		}
	
	//panel3 est un Panel secondaire        
	JPanel panel3 = new JPanel();
        	//choix de la mise en page
	panel3.setLayout(new BorderLayout());
	
        	//éléments du panel3
	titre_activites.setOpaque(true);
	titre_activites.setBackground(couleur_RGB[8]);
        	titre_activites.setForeground(couleur_RGB[7]);
        	panel3.add(titre_activites,BorderLayout.NORTH);//titre de la liste est pacé au nord du panel3
        	
	//info_activites_jp est un JTextPane vide pour éviter scroll quand le JLabel info_activites est vide
	info_activites.setOpaque(true);
	info_activites.setBackground(couleur_RGB[8]);
	info_activites.setForeground(couleur_RGB[7]);
	panel3.add(info_activites,BorderLayout.CENTER );//info est placé au sud du panel3
        	info_activites_jp.setBackground(couleur_RGB[8]);
	panel3.add(info_activites_jp,BorderLayout.EAST);//placer à droite du JLabel info_activites
	//place la panel1 au nord du panel
	//place le panel2 au centre du panel
	//place le panel3 au sud du panel
	panel.add(panel1,BorderLayout.NORTH);
	panel.add(panel2,BorderLayout.CENTER);
	panel.add(panel3,BorderLayout.SOUTH);
        	
	//défini le calendrier
	cal = (GregorianCalendar)Calendar.getInstance();
        	//détermine la date du jour
	nAnnee = cal.get(1);
        	nAnnee_present = nAnnee;
        	nMois = cal.get(2);
        	nMois_present = nMois;
        	nJour = cal.get(5);
        	nJour_present = nJour;
        	
	//appel la procédure pour afficher la date du jour
	ecrit_date(nAnnee, nMois, 1);
        	
	//place la panel principal dans applet
	add(panel,BorderLayout.CENTER);
	addMouseListener(this);

	//défini le thread pour l'horloge numérique
	t = new Thread(this, "Horloge");
        	t.start();//démarre le thread
	
     }// fin de init

     //démarre l'applet
    public void start(){
	afficheMois(numprejoursem+chgjour, nMois, nAnnee);
	} 
    //arrête l'applet (pour la forme ...)
    public void stop(){} 	
    public void destroy(){}


     //actions sur clique boutons
     public void actionPerformed(ActionEvent actionevent)//événement clic sur bouton
    {
        cmd = actionevent.getActionCommand();
        if(">>".equals(cmd))
            moissuivant();//clic sur bouton >> appel la procédure moissuivant
        if("<<".equals(cmd))
            moisprecedent();//clic sur bouton << appel la procédure moisprecedent
        Object source = actionevent.getSource();//clique sur un des boutons jour de la grille et détermine lequel
	for (int ii=0; ii<42; ii++)
	{
 		if (source == boutonsMois[ii] && info_bulle_ferme.equals(boutonsMois[ii].getToolTipText()) == false)// le bouton est dans une période d'expo et la galerie doit etre dans un jour ouverture
		 {
		 	try
        			{
            		URL url = new URL(getDocumentBase(), lien_bouton[ii]);//lien vers url
            		getAppletContext().showDocument(url);
            		showStatus(lien_bouton[ii]);
			}
        			catch(Exception exception)
        			{
            		showStatus("url indeterminee ...");
            		exception.printStackTrace();
        			}

		}
	}
    }
    
    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    
   public void mouseEntered(MouseEvent e)
   {
	requestFocus();
	Object source = e.getSource();
	for (int ii=0; ii<42; ii++)
	{
 		
		if (source == boutonsMois[ii])
		 {
			if (strchoix_activite[ii] != null){
			info_activites.setText(strchoix_activite[ii]);
			}
			else{
			info_activites.setText("Pas d'information pour ce jour");
		        	}	
		 }
	}
    }

   public void mouseExited(MouseEvent e){info_activites.setText("");} 

   public void moisprecedent()
    {
      if(nAnnee > 1 || nMois > 0)
           {
            nMois--;
            if(nMois < 0)
            	{
                	nMois = 11;
                	nAnnee--;
            	}
            zoneMoisAnnee.setText(tabMois[nMois]+" "+nAnnee);
            ecrit_date(nAnnee, nMois, 1);
            afficheMois(numprejoursem, nMois, nAnnee);
           }
    }

    public void moissuivant()
    {
        nMois++;
        if(nMois > 11)
        {
            nMois = 0;
            nAnnee++;
        }
        zoneMoisAnnee.setText(tabMois[nMois]+" "+nAnnee);
        ecrit_date(nAnnee, nMois, 1);
        afficheMois(numprejoursem, nMois, nAnnee);
    }

    public void ecrit_date(int i, int j, int k)
    {
        	mois_annee = tabMois[j]+" "+Integer.toString(i);
        	zoneMoisAnnee.setForeground(couleur_RGB[5]);
        	zoneMoisAnnee.setBackground(couleur_RGB[6]);
        	zoneMoisAnnee.setText(mois_annee);//écrit le mois et l'année dans panel1 partie nord entre les 2 boutons << et >>
         
	//recherche du nom du jour du 1er jour du mois affiché dans applet pour déterminer la valeur de numprejoursem (décalage du 1er jour mois sur grille)
	cal.set(nAnnee, nMois, 1);
        	datejour = cal.getTime();
        	DateFormat dateformat1 = DateFormat.getDateInstance(0, locale);
        	datedujour = dateformat1.format(datejour);
        	abrvnomjour = datedujour.substring(0, 2);
        	if(abrvnomjour.equals("lu")) numprejoursem = 1;
        	if(abrvnomjour.equals("ma")) numprejoursem = 2;
        	if(abrvnomjour.equals("me")) numprejoursem = 3;
        	if(abrvnomjour.equals("je"))  numprejoursem = 4;
        	if(abrvnomjour.equals("ve")) numprejoursem = 5;
        	if(abrvnomjour.equals("sa")) numprejoursem = 6;
        	if(abrvnomjour.equals("di")) numprejoursem = 7;
    }

    public void afficheMois(int i, int j, int k)
    {
        	//cas du mois de février - année bisextile
	if(j == 1) 
		if(cal.isLeapYear(k)) joursParMois[1] = 29;
		else
	         	joursParMois[1] = 28;
        	
	jourdecales = i - 1;
     
     	//tous les boutons sont couleur_RGB[6] (nettoyage de la grille)
	for(int ii = 0; ii < 42; ii++)
        		{
            	boutonsMois[ii].setBackground(couleur_RGB[6]);
            	boutonsMois[ii].setCursor(new Cursor(0));
		boutonsMois[ii].setText("");
		boutonsMois[ii].setToolTipText("");
		strchoix_activite = new String[42];
		lien_bouton = new String[42];
	        	}
	
        	int i1 = 0;
        	for(int j1 = jourdecales; j1 < joursParMois[j] + jourdecales; j1++)
        	{
            	i1++;
            	cal.set(k, j, i1);
            	datejourbouton = cal.getTime();
            	int k1 = 0;
		int mm=0;
            	do
            	{
                		if(k1 >= maxdate) break;
                		jourdebut = date_debut[k1];
                		moisdebut = date_debut[k1 + 1];
                		anneedebut = date_debut[k1 + 2];
                		cal.set(anneedebut, moisdebut, jourdebut);
                		date1 = cal.getTime();
                		jourfin = date_fin[k1] + 1;
                		moisfin = date_fin[k1 + 1];
                		anneefin = date_fin[k1 + 2];
                		cal.set(anneefin, moisfin, jourfin);
                		date2 = cal.getTime();
                		boutonsMois[j1].setBackground(couleur_RGB[6]);
			boutonsMois[j1].setForeground(couleur_RGB[5]);//couleur du caractère inscrit sur le bouton
			//si la date indiquée par le bouton de la grille est une date comprise dans un moment d'expo alors le bouton change de couleur de fond
                		if(datejourbouton.compareTo(date1) >= 0 && datejourbouton.compareTo(date2) < 0)
                		{
                    			boutonsMois[j1].setBackground(couleur_RGB[8]);
                    			boutonsMois[j1].setForeground(couleur_RGB[7]);
                    			boutonsMois[j1].setToolTipText(info_bulle_ouvert + type_expo[mm] + info_bulle_ouvert2);//texte infobulle du bouton compris dans un moment d'expo
                    			boutonsMois[j1].addMouseListener(this);
				boutonsMois[j1].addActionListener(this);
				boutonsMois[j1].setCursor(new Cursor(12));
				lien_bouton[j1] = lien_expo[mm];
				strchoix_activite[j1] = stractivite[mm];
				break;
                		}
                    		boutonsMois[j1].setToolTipText(info_bulle_ferme);//texte infobulle quand le bouton est en dehors d'un moment d'expo
			

	        		k1 += 3; mm++;
            	} while(true);
            	
            	boutonsMois[j1].setText(Integer.toString(i1));//inscrit les caractères sur le bouton
        	}

        	verifie_jour_present();//appel la procédure
    }

    public void verifie_jour_present()
    {
       	 for(int i = 0; i < 42; i+=7)
        		{
            	//on avance du lundi au lundi suivant i=+7 ordre de fermeture 0,0,0,1,1,1,1 pour lu,ma,me,je,ve,sa,di
            		for(int j = 0; j < 7; j++)
                			if(jours_ouverts[j] == 0)
                			{
                    				boutonsMois[i + j].setForeground(couleur_RGB[9]);
                    				boutonsMois[i + j].setBackground(couleur_RGB[10]);
					boutonsMois[i + j].setCursor(new Cursor(0));
					strchoix_activite[i + j] = null;
					//infobulle s'affiche s' il y a un chiffre sur le bouton
					if (boutonsMois[i + j].getText().equals("") == false) {boutonsMois[i + j].setToolTipText(info_bulle_ferme);}
	     			}

        		}

        	//affiche le bouton de la date du jour dans une couleur particulière
	if(nAnnee == nAnnee_present && nMois == nMois_present)
        		{
            	nJour = nJour_present;
            	boutonsMois[(nJour + jourdecales) - 1].setFont(fonte1);
            	boutonsMois[(nJour + jourdecales) - 1].setBackground(couleur_RGB[4]);
            	boutonsMois[(nJour + jourdecales) - 1].setForeground(couleur_RGB[3]);
        		}
    	}

    //horloge numérique - date du jour suivie par horloge numérique
    public String donnerHeure()
    {
        Date date = new Date();
        TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");
        TimeZone.setDefault(timezone);
        GregorianCalendar gregoriancalendar = new GregorianCalendar();
        int i = gregoriancalendar.get(11);
        int j = gregoriancalendar.get(12);
        int k = gregoriancalendar.get(13);
        if(i < 10)
            heures = "0" + i;
        else
            heures = "" + i;
       if(j < 10)
            minutes = "0" + j;
        else
            minutes = ""+ j;
        if(k < 10)
            secondes = "0" + k;
        else
            secondes = "" + k;
        heure = heures+":"+minutes+":"+secondes;
	if (heure.equals("00:00:01")){chgjour=1;start();}
        DateFormat dateformat1 = DateFormat.getDateInstance(0, locale);
        jour = dateformat1.format(date);
        return (jour + "  -  " +heure);
    }
   
    //thread pour horloge numérique
 public void run()
    {
       
	boolean fin = false; 
	while( !fin ) { 
		zoneDate.setText("Date du jour : "+donnerHeure());
		try 
		{
		 // traitement 
		 Thread.sleep(1000);


		synchronized(this) { t.yield();
					 // lecture du boolean 
					fin = this.stopThread; } 
		}
	            catch( InterruptedException e ) { }
  }
 
}

//liste des variables
    private String tabJours[] = {"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"};
    private String tabMois[] = {"janvier", "f\351vrier", "mars", "avril", "mai", "juin", "juillet", "ao\373t", "septembre", "octobre", "novembre", "d\351cembre"};
    private String nomFichier;
    private String valeurtest;
    private String heure;
    private String jour;
    private String heures;
    private String minutes;
    private String secondes;
    private String abrvnomjour;
    private String mois_annee;
    private String datedujour;
    private String datedebut;
    private String datefin;
    private String cmd;
    private String lien_expo[];
    private String vect[];
    private String type_expo[];
    private String stractivite[];
    private String lien_bouton[];
    private String info_bulle_ferme;
    private String info_bulle_ouvert;
    private String info_bulle_ouvert2;
    private StringTokenizer st;
    private String strchoix_activite[];
    
    private Date date_debut_expo;
    private Date date_fin_expo;
    private Date datejourbouton;
    private Date date1;
    private Date date2;
    private Date datejour;

    private Color couleur_RGB[];
    
    private int rouge;
    private int vert;
    private int bleu;
    private int maxvect;
    private int maxdate;
    private int nMois;
    private int nMois_present;
    private int nAnnee;
    private int nAnnee_present;
    private int nJour;
    private int nJour_present;
    private int numprejoursem;
    private int jourdecales;
    private int Jour_present;
    private int jourdebut;
    private int moisdebut;
    private int anneedebut;
    private int jourfin;
    private int moisfin;
    private int anneefin;
    private int joursParMois[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31,  30, 31};
    private int date_debut[];
    private int date_fin[];
    private int jours_ouverts[];
    private int lignes_decale;
    private int chgjour=0;

    private DateFormat dateformat;
    
    private JLabel zoneMoisAnnee;
    private JLabel zoneDate;
    private JLabel titre_activites;
    private JLabel choix_activite;
    private JLabel labelMois[];
    private JLabel info_activites;
    private JLabel info_activites_jp;
      
    private JButton abrv_noms_jours[];
    private JButton boutonPrec;
    private JButton boutonSuiv;
    private JButton boutonsMois[];
    
    private Locale locale;
    
    private Font fonte1;
    private Font fonte;
    
    private Thread t;
    
    private boolean stopThread = false; 

    private GregorianCalendar cal;
  
}