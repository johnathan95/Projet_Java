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
public class Person {
	
	public enum PersonFunction{
		NONE("None"),
		JURY("Jury"),
		STUDENT("Student");
		
		private String personFunction;
		
		PersonFunction(String personFunction) {
			this.personFunction = personFunction;
		}
		
		public String toString() {
			return personFunction;
		}		
	}
	
	public Person() {
		personFunction = PersonFunction.NONE;
	}
	
	public Person(PersonFunction personFunction,
				  String firstname,
				  String lastname,
				  String email,
				  String phone) {
		this.personFunction = personFunction;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
	}
	
	public void setFunction(PersonFunction function) {
		this.personFunction = function;
	}
	public PersonFunction getFunction() {
		return personFunction;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getFirstname() {
		return firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone() {
		return phone;
	}

	
	protected PersonFunction personFunction;
	protected String firstname;
	protected String lastname;
	protected String email;
	protected String phone;
	

}
