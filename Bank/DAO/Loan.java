package Bank.DAO;

/*
 * Allow customers to have a loan
 */

import Bank.DAO.Account.Currency;

public class Loan {
	protected Currency currency; //What type of currency is that loan in
	protected double amount; //How much does a client own the bank
	protected Mortgage mortgage; //What to mortgage to the bank when owning a loan
	//Constructor
	public Loan() {
		currency = new Currency();
		amount = 0;
		mortgage = new Mortgage();
	}
	public Loan(Currency currency, double amount, Mortgage mortgage) {
		this.currency = currency;
		this.amount = amount;
		this.mortgage = mortgage;
	}
	//Getter and setter
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Mortgage getMortgage() {
		return mortgage;
	}
	public void setMortgage(Mortgage mortgage) {
		this.mortgage = mortgage;
	}
}
