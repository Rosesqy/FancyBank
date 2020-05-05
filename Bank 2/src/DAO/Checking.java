package DAO;

import java.util.Scanner;

public class Checking extends Account implements iTransaction {
	protected Loan loan; //If the customer owns a loan to the bank in this account
	protected static double transactionFee;
	protected static double withdrawalFee;
	//Constructor
	public Checking(String customerID) {
		super(customerID);
		loan = new Loan();
		this.type = "CHECKING";
	}
	//Getter and setter
	public Loan getLoan() {
		return loan;
	}
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	public double getTransactionFee() {
		return transactionFee;
	}
	public void setTransactionFee(double transactionFee) {
		Checking.transactionFee = transactionFee;
	}
	public double getWithdrawalFee() {
		return withdrawalFee;
	}
	public void setWithdrawalFee(double withdrawalFee) {
		Checking.withdrawalFee = withdrawalFee;
	}
	//Override
	public <T extends Account> void iTransaction(T account) {
		Scanner sc = new Scanner(System.in);
		if(account instanceof Security) {
			System.out.println("Cannot transfer from a checking account to a security account!");
		}
		else {
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
				if(account.customerID.equals(customerID)) {
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
				else {
					if(deposits.get(x).balance >= balance*(1+transactionFee)) {
						deposits.get(x).changeBalance(-balance*(1+transactionFee));
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
}
