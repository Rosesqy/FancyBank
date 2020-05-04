package Bank;

import java.util.ArrayList;
import java.util.List;

/*
 * An abstract class representing all kinds of accounts including Checking accounts, saving accounts and Security accounts
 */

public abstract class Account {
	protected String customerID;
	protected String id; //Every bank account has an id to specify it
	protected List<Deposit> deposits; //Allow customers to manage deposits in at least three different currencies

	protected Deposit deposit;
	protected Currency currency;
	protected String type;

	protected static double openFee;
	protected static double closeFee;
	//Constructor
	public Account(String customerID) {
		this.customerID = customerID;
		// this.id = id;
		deposits = new ArrayList<Deposit>();

		this.type = "default";
	}
	//Getter and setter

	public void setCur(String cur){
        switch(cur){
            //or enter string and get sign?
            case("$"):
                this.currency = new Currency();
            case("€"):
                this.currency = new Currency("EUR");
            case("¥"):
                this.currency = new Currency("RMB");
            case("￥"):
                this.currency = new Currency("YEN");
        }
	}

	public Currency getCurrency(){
		return this.currency;
	}

	public String getCurSig(){
        return this.currency.getType();
    }

    public double getCurRate(){
        return this.currency.getRate();
	}
	
	public String getType(){
		return this.type;
	}

	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id = id;
	}
	public Deposit getDeposit(int i) {
		return deposits.get(i);
	}
	public void addDeposit(Deposit deposit) {
		deposits.add(deposit);
	}
	public void removeDeposit(int i) {
		deposits.remove(i);
	}
	public List<Deposit> getDeposits() {
		return deposits;
	}

	public double getDepositVal(){
		//return deposit object or just the value?
		return this.deposit.getBalance();
	}

	public double getOpenFee() {
		return openFee;
	}
	public void setOpenFee(double openFee) {
		Account.openFee = openFee;
	}
	public double getCloseFee() {
		return closeFee;
	}
	public void setCloseFee(double closeFee) {
		Account.closeFee = closeFee;
	}
}
