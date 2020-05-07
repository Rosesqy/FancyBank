package Bank.DAO.Account;

public class Date {
	protected int year;
	protected int month;
	protected int day;
	//Constructor
	public Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	//Getter and setter
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String toString() {
		return year + "-" + month + "-" + day;
	}
}
