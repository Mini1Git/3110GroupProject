//reorders
public class Date {

	private int day;
	private int month;
	private int year;
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
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