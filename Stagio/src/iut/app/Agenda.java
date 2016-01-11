package iut.app;
import java.util.ArrayList;
import java.util.Date;
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
import java.util.LinkedList;
import java.util.Random;

public class Agenda extends LinkedList<ExamEvent> {
	public Agenda() {		
	}
	
	public void addCheckedEvent(ExamEvent examEvent) {
		this.add(examEvent);
	}
	
	public void setUp() {
		for (int i=0; i<GenerateValues.getRandNum(5,50); i++) {
			Date date = new Date(java.lang.System.currentTimeMillis()+GenerateValues.getRandLong()/2);
			Person student = new Person(Person.PersonFunction.STUDENT, GenerateValues.generateRandomString(GenerateValues.getRandNum(5,10)), GenerateValues.generateRandomString(GenerateValues.getRandNum(5,10)),GenerateValues.generateRandomString(GenerateValues.getRandNum(5,10))+'@'+GenerateValues.generateRandomString(GenerateValues.getRandNum(5,10))+'.'+GenerateValues.generateRandomString(GenerateValues.getRandNum(2,3)), "06"+GenerateValues.generateRandomNum(8));

			ArrayList<Person> jury = new ArrayList<Person>();
			for (int j=0;j< GenerateValues.getRandNum(1, 3); j++)
				jury.add(new Person(Person.PersonFunction.JURY, GenerateValues.generateRandomString(GenerateValues.getRandNum(5,10)), GenerateValues.generateRandomString(GenerateValues.getRandNum(5,10)),GenerateValues.generateRandomString(GenerateValues.getRandNum(5,10))+'@'+GenerateValues.generateRandomString(GenerateValues.getRandNum(5,10))+'.'+GenerateValues.generateRandomString(GenerateValues.getRandNum(2,3)), "06"+GenerateValues.generateRandomNum(8)));

			Classroom classroom = new Classroom(GenerateValues.generateRandomString(3));
			ArrayList<Document> documents = new ArrayList<Document>();
			for (int k=0;k< GenerateValues.getRandNum(1, 3); k++)
				documents.add(new Document(GenerateValues.generateRandomString(GenerateValues.getRandNum(10,20))));

			add(new ExamEvent(date, student, jury, classroom, documents));
		}
	}
	private static class GenerateValues {
		private static final String CHAR_LIST =
				"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

		public static String generateRandomString(int lenght) {
			StringBuffer randStr = new StringBuffer();
			randStr.append(CHAR_LIST.charAt(getRandNum(26, CHAR_LIST.length() - 11)));
			for (int i = 1; i < lenght; i++) {
				char ch = CHAR_LIST.charAt(getRandNum(0, 25));
				randStr.append(ch);
			}
			return randStr.toString();
		}

		public static String generateRandomNum(int lenght) {
			StringBuffer randStr = new StringBuffer();
			for (int i = 0; i < lenght; i++) {
				char ch = CHAR_LIST.charAt(getRandNum(CHAR_LIST.length() - 10, CHAR_LIST.length() - 1));
				randStr.append(ch);
			}
			return randStr.toString();
		}

		private static int getRandNum(int min, int max) {
			Random rand = new Random();
			return rand.nextInt(max - min + 1) + min;
		}
		private static long getRandLong() {
			return new Random().nextLong();
		}
	}
}
