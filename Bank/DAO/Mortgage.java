package Bank.DAO;

/*
 * Something to mortgage to the bank when applying for a loan
 */

public class Mortgage {
	protected String name; //To specify what this mortgage is
	protected double value; //How much is this mortgage worth
	//Constructor
	public Mortgage() {
		name = "";
		value = 0;
	}
	public Mortgage(String name, double value) {
		this.name = name;
		this.value = value;
	}
	//Getter and setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
