package Bank.DAO.Account;

import Bank.DAO.Loan;

import java.util.Scanner;

public class Savings extends Account {
	protected Loan loan; //If the customer owns a loan to the bank in this account

	protected double interest;//may need a default value
	protected static double withdrawalFee;
	protected Date lastOperateDate;

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

	public double getWithdrawalFee() {
		return withdrawalFee;
	}
	public void setWithdrawalFee(double withdrawalFee) {
		Savings.withdrawalFee = withdrawalFee;
	}
	public Date getLastOperateDate() {
		return lastOperateDate;
	}
	public void setLastOperateDate(Date lastOperateDate) {
		this.lastOperateDate = lastOperateDate;
	}

	//Deposit money in this account
	public void depositMoney(Date operateDate) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input how much money you would like to put in:");
		double balance = sc.nextDouble();
		int days = (operateDate.year-lastOperateDate.year)*365+(operateDate.month-lastOperateDate.month)*30+(operateDate.day-lastOperateDate.day);
		setBalance(this.balance*(1+interest*days));
		changeBalance(balance);
		lastOperateDate = operateDate;
	}
	//Withdraw money from an account
	public void withdrawMoney(Date operateDate) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input how much money you would like to withdraw:");
		double balance = sc.nextDouble();
		if(this.balance >= balance*(1 + withdrawalFee)) {
			int days = (operateDate.year-lastOperateDate.year)*365+(operateDate.month-lastOperateDate.month)*30+(operateDate.day-lastOperateDate.day);
			setBalance(this.balance*(1+interest*days));
			changeBalance(-balance*(1 + withdrawalFee));
			lastOperateDate = operateDate;
		}
		else {
			System.out.println("You do not have enough balance!");
		}
	}

	public <T extends Account> void transaction(T account, Date operateDate) {
		Scanner sc = new Scanner(System.in);
		if(account instanceof Security) {
			if(account.customerID.equals(customerID)) {
				System.out.println("Input how much money you would like to transfer(over $1000):");
				double balance = sc.nextDouble();
				if(this.balance*currency.rate >= 5000 && balance*currency.rate >= 1000 && (this.balance-balance)*currency.rate >= 2500) {
					int days = (operateDate.year-lastOperateDate.year)*365+(operateDate.month-lastOperateDate.month)*30+(operateDate.day-lastOperateDate.day);
					setBalance(this.balance*(1+interest*days));
					changeBalance(-balance);
					lastOperateDate = operateDate;
					account.changeBalance(balance*currency.rate/account.currency.rate);
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
			System.out.println("Input how much money you would like to transfer:");
			double balance = sc.nextDouble();
			if(this.balance >= balance) {
				int days = (operateDate.year-lastOperateDate.year)*365+(operateDate.month-lastOperateDate.month)*30+(operateDate.day-lastOperateDate.day);
				setBalance(this.balance*(1+interest*days));
				changeBalance(-balance);
				lastOperateDate = operateDate;
				account.changeBalance(balance*currency.rate/account.currency.rate);
			}
			else {
				System.out.println("You do not have enough balance!");
			}
		}
	}
}
