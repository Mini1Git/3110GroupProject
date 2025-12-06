//add to main, change comments, change some words
public class Person {

	//Task:7 = Define a Person class with three instance variables: String name, Date birthday, and String country.
	
	private String name;
	private Date date;
	private String country;
	
	//Initializer
	public Person(String name, Date date, String country) {
		this.name = name;
		this.date = date;
		this.country = country;
	}
	
	//The copy constructor
	public Person(Person anotherPerson) {
		this.name = anotherPerson.getName();
		this.date = anotherPerson.getDate();
		this.country = anotherPerson.getcountry();
	}
	
    // person as a string
	public String toString() {
		return (this.getName()+", born "+
				this.getDate().toString()); 
	}
	
    //getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getcountry() {
		return country;
	}
	public void setcountry(String country) {
		this.country = country;
	}
    //main
    public static void main(String[] args) {
		System.out.println("Hooray main method la la la ");
        System.out.println("Seven times seven is 49");
	}

}
