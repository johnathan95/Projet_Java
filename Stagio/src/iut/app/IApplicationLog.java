package iut.app;
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
public interface IApplicationLog {
	public void setMessage(String message);
	public String getMessage();
	public void addListener(IApplicationLogListener listener);
	public IApplicationLogListener[] getApplicationLogListeners();
}
