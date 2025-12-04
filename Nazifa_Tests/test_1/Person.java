public class Person{
	private String name;
	private Date birthday;
	private String country;
	//constructor with 3 parameters
	public Person1(String name, Date birthday, String country)
	{
		this.name = name;
		this.birthday = birthday;
		this.country = country;
	}
	//copy constructor
	public Person1(Person1 p)
	{
		this.name = p.name;
		this.birthday = p.birthday;
		this.country = p.country;
	}

	public String toString()
	{
		return String.format("%s born on %d/%d/%d in %s", name, birthday.getDay(), birthday.getMonth(), birthday.getYear(), country);
	}

	//setters
	public void setName(String n)
	{
		try
		{
			if(n.equals(""))//if name is empty
				throw new Exception("invalid input");
			name = n;
		}
		catch(Exception e)
		{
			//name remains unchanged maintain encapsulation
		}
	}

	public void setCountry(String c)
	{
		country = c;
	}

}

