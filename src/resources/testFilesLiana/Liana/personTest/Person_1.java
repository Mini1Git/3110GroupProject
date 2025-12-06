//add to main, change comments, change some words
public class Person {

	//Task:7 = Define a Person class with three instance variables: String name, Date birthday, and String countre.
	
	private String name;
	private Date date;
	private String countre;
	
	//Task 8: Create constructor to initialize the variables and also create a copy constructor.
	public Person(String name, Date date, String countre) {
		this.name = name;
		this.date = date;
		this.countre = countre;
	}
	
	//The copy constructor
	public Person(Person anotherPerson) {
		this.name = anotherPerson.getName();
		this.date = anotherPerson.getDate();
		this.countre = anotherPerson.getcountre();
	}
	
	public String toString() {
		return (this.getName()+", born on "+
				this.getDate().toString()); 
	}
	
	public static void main(String[] args) {

	}

}
