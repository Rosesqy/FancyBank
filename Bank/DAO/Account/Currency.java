package Bank.DAO.Account;

/*
 * A class representing currency
 */

public class Currency {
	protected String type; //Type of currency like Dollar, Euro...
	protected double rate; //Their exchange rate with Dollar
	//Constructor
	public Currency() {
		type = "United States Dollar";
		rate = 1;
		
	}

	public Currency(String type){
		this.type = type;
	}

	public Currency(String type, double rate) {
		this.type = type;
		this.rate = rate;
	}
	//Getter and setter
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
}
