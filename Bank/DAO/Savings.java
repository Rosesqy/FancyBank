package DAO;

import java.util.Scanner;

public class Savings extends Account implements iTransaction {
	protected Loan loan; //If the customer owns a loan to the bank in this account

	protected double interest;//may need a default value

	//Constructor
	public Savings(String customerID, double interest) {
		super(customerID);
		loan = new Loan();
		this.interest = interest;
		this.type = "SAVING";
	}
	//Getter and setter

	public void setInterest(double interest){
		this.interest = interest;
	}

	public double getInterest(){
		return this.interest;
	}

	public Loan getLoan() {
		return loan;
	}
	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	//Override
	public <T extends Account> void iTransaction(T account) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose currency:");
		String type = sc.next();
		int x = 0;
		for(int i = 0; i < deposits.size(); i++) {
			if(deposits.get(i).currency.type.equals(type)) {
				x = i;
				break;
			}
		}
		if(deposits.get(x).currency.type.equals(type)) {
			System.out.println("Input how much money you would like to transfer:");
			double balance = sc.nextDouble();
			if(account instanceof Security) {
				double sum = 0;
				for(int i = 0; i < deposits.size(); i++) {
					sum += deposits.get(i).currency.rate*deposits.get(i).balance;
				}
				if(account.customerID.equals(customerID)) {
					if(sum - balance*deposits.get(x).currency.rate >= 2500 && balance*deposits.get(x).currency.rate >= 1000) {
						deposits.get(x).changeBalance(-balance);
						int y = 0;
						for(int i = 0; i < account.deposits.size(); i++) {
							if(account.deposits.get(i).currency.type.equals(type)) {
								y = i;
								break;
							}
						}
						if(account.deposits.get(y).currency.type.equals(type)) {
							account.deposits.get(y).changeBalance(balance);
						}
						else {
							Currency currency = deposits.get(x).currency;
							Deposit deposit = new Deposit(currency, balance);
							account.addDeposit(deposit);
						}
					}
					else {
						System.out.println("Do not meet the requirement to transfer money to a security account!");
					}
				}
				else {
					System.out.println("Cannot transfer to another customer's security account!");
				}
			}
			else {
				if(deposits.get(x).balance >= balance) {
					deposits.get(x).changeBalance(-balance);
					int y = 0;
					for(int i = 0; i < account.deposits.size(); i++) {
						if(account.deposits.get(i).currency.type.equals(type)) {
							y = i;
							break;
						}
					}
					if(account.deposits.get(y).currency.type.equals(type)) {
						account.deposits.get(y).changeBalance(balance);
					}
					else {
						Currency currency = deposits.get(x).currency;
						Deposit deposit = new Deposit(currency, balance);
						account.addDeposit(deposit);
					}
				}
				else {
					System.out.println("You do not have enough balance in that currency!");
				}
			}
		}
		else {
			System.out.println("You do not have a deposit in that currency!");
		}
	}
}
