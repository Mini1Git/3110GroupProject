//reorders
public class Date {

	private int year;
	private int month;
	private int day;
	
	public Date(int day, int month, int year) {
		this.year = year;
		this.day = day;
		this.month = month;
	}

	public boolean equals(Date date) {
		boolean equal = true;
		if (this.day != date.day) {
			equal = false;
		}
		if (this.month != date.month) {
			equal = false;
		}
		if (this.year != date.year) {
			equal = false;
		}
		return equal;
	}
	
}