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
import java.rmi.*;
public interface Contrat extends Remote {
	
	/**
	 * Fonction qui sera appellée par le client pour se connecter au serveur de chat
	 * ajout du client concernée à la liste des clients connectés du serveur
	 * @param nom
	 * @param mdp
	 * @param adr
	 * @param num_port
	 * @return
	 * @throws RemoteException
	 */
	boolean connect(String nom,String mdp,String adr,int num_port)throws RemoteException;

 /**
  * Fonction qui ajoute une communication à la liste des communications du serveur.
  * @param c
  * @throws RemoteException
  */
  void envoyer(Communication c)throws RemoteException;
  
  /**
   * Fonction permettant de deconnecter un client
   * @param nom
   * @throws RemoteException
   */
  void disconnect(String nom)throws RemoteException;
  int getnbrcon() throws RemoteException;
  String []list_con()throws RemoteException;
  int get_num_port() throws RemoteException;
}
