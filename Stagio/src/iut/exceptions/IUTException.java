package iut.exceptions;
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
public class IUTException extends Exception {
	public IUTException() {
		super();
		iut.app.ApplicationSession.instance().getExceptionLogger().severe("empty");
	}
	public IUTException(IUTException e) {
		super (e);
		iut.app.ApplicationSession.instance().getExceptionLogger().severe(e.getMessage());
	}
	public IUTException(String message) {
		super(message);
		iut.app.ApplicationSession.instance().getExceptionLogger().severe(message);
	}
	
}
