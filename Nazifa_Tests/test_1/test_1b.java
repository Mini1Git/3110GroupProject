public class Person{
	private String name;
	private Date birthday;
	private String country;
	//constructor with 3 parameters
	public Person1(String newName, Date newBirthday, String newCountry)
	{
		name = newName;
		birthday = newBirthday;
		country = newCountry;
	}

	public String toString()
	{
		return String.format("%s born on %d/%d/%d in %s", name, birthday.getDay(), birthday.getMonth(), birthday.getYear(), country);
	}

	//getters
	public String getName()
	{
		return name;
	}
	public Date getBirthday()
	{
		return birthday;
	}
	public String getCountry()
	{
		return country;
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
		try
		{
			if(c.equals(""))//if country is empty
				throw new Exception("invalid input");
			country = c;
		}
		catch(Exception e)
		{
			//country remains unchanged maintain encapsulation
		}
	}

}

