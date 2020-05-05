package DAO;

/*
 * Create a class to let customers deal with their deposits
 */

import DAO.Currency;

public class Deposit {
	protected Currency currency; //In which currency is that deposit
	protected double balance; //Amount of deposit in a specific currency
	//Constructor
	public Deposit() {
		currency = new Currency();
		balance = 0;
	}
	public Deposit(Currency currency, double balance) {
		this.currency = currency;
		this.balance = balance;
	}
	//Getter and Setter
	public Currency getCurency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	//Mutater
	public void changeBalance(double balance) {
		this.balance += balance; 
	}
}
