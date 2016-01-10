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
import java.io.*;

public class Adr_Cli  {
  private String nom;
  private String adr;
  private int num_port;
  public Adr_Cli(String nom,String adr,int num_port) {
    this.nom=nom;
    this.adr=adr;
    this.num_port=num_port;
  }
public String get_adr(){
  return adr;
}
public int get_port(){
  return num_port;
}
public String get_n(){
  return nom;
}
}
