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
import java.util.ArrayList;
/**
 *  Classe mere des ApplicationLog
 * 
 * @author Jonhatan, Joe, Pierre et Thibault
 *
 */
public abstract class AbstractApplicationLog implements IApplicationLog {

	protected String message;
	protected ArrayList<IApplicationLogListener> listeners;
	/**
	 * Constructeur sans parametre de la classe AbstractApplicationLog
	 */
	public AbstractApplicationLog() {
		message = null;
		listeners = new ArrayList<IApplicationLogListener>();
	}
	/**
	 * Constructeur avec parametre de la classe AbstractApplicationLog
	 * @param message le messsage du log
	 * @param listener le listener du log
	 */
	public AbstractApplicationLog(String message,IApplicationLogListener listener) {
		this.message = message;
		listeners = new ArrayList<IApplicationLogListener>();
		listeners.add(listener); 
	}
	/**
	 * defini le message
	 */
	@Override
	public abstract void setMessage(String message);
	/**
	 * retourne le message
	 */
	@Override
	public String getMessage() {
		return message;
	}
	/**
	 * ajoute un listener
	 */
	@Override
	public void addListener(IApplicationLogListener listener) {
		listeners.add(listener);

	}
	@Override
	public IApplicationLogListener[] getApplicationLogListeners() {
		return (IApplicationLogListener[])listeners.toArray();
	}
	/**
	 * Procedure permettant d'envoyer un message en resneignant
	 * en parametre le niveau du message et le message
	 * @param level
	 * @param message
	 */
	protected void fireMessage(String level, String message) {
		for (IApplicationLogListener listener_i : listeners) {
			listener_i.newMessage(level, message);
		}
	}
}
