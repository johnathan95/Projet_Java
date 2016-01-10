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
public class Classroom {
	
	public Classroom() {
		classRoomNumber="not affected";
	}
	public Classroom(String classRoomNumber) {
		this.classRoomNumber = classRoomNumber;
	}
	
	public void setClassroomNumber(String number) {
		this.classRoomNumber = number;
	}
	public String getClassRoomNumber() {
		return classRoomNumber;
	}
	
	protected String classRoomNumber;
}
