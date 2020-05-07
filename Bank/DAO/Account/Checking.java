package Bank.DAO.Account;

import Bank.DAO.Loan;

import java.util.Scanner;

public class Checking extends Account {
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

	//Deposit money in this account
	public void depositMoney() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input how much money you would like to put in:");
		double balance = sc.nextDouble();
		changeBalance(balance);
	}
	//Withdraw money from an account
	public void withdrawMoney() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input how much money you would like to withdraw:");
		double balance = sc.nextDouble();
		if(this.balance >= balance*(1 + withdrawalFee)) {
			changeBalance(-balance*(1 + withdrawalFee));
		}
		else {
			System.out.println("You do not have enough balance!");
		}
	}

	public <T extends Account> void transaction(T account) {
		Scanner sc = new Scanner(System.in);
		if(account instanceof Security) {
			System.out.println("Cannot transfer from a checking account to a security account!");
		}
		else {
			System.out.println("Input how much money you would like to transfer:");
			double balance = sc.nextDouble();
			if(this.balance >= balance*(1 + transactionFee)) {
				changeBalance(-balance*(1 + transactionFee));
			}
		}
	}
}
