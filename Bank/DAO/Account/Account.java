package Bank.DAO.Account;

/*
 * An abstract class representing all kinds of accounts including Checking accounts, saving accounts and Security accounts
 */

public abstract class Account {
	protected String customerID;
	protected String id; //Every bank account has an id to specify it
	protected Date openDate;
	protected Date closeDate;

	protected double balance;
	protected Currency currency;
	protected String type;

	protected static double openFee;
	protected static double closeFee;
	//Constructor
	public Account(String customerID) {
		this.customerID = customerID;
		// this.id = id;

		this.type = "default";
		// this.openDate = openDate;
	}
	//Getter and setter

	// public void setCur(String cur){
    //     switch(cur){
    //         //or enter string and get sign?
    //         case("$"):
    //             this.currency = new Currency();
    //         case("€"):
    //             this.currency = new Currency("EUR");
    //         case("¥"):
    //             this.currency = new Currency("RMB");
    //         case("￥"):
    //             this.currency = new Currency("YEN");
    //     }
	// }

	public void setCurrency(int i) {
		switch(i) {
		case 1:
			this.currency = new Currency("$", 1);
			break;
		case 2:
			this.currency = new Currency("€", 1.1);
			break;
		case 3:
			this.currency = new Currency("￥", 0.01);
			break;
		case 4:
			this.currency = new Currency("￡", 1.25);
			break;
		case 5:
			this.currency = new Currency("¥", 0.15);
		default: break;
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

	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void changeBalance(double balance) {
		this.balance += balance;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	

	public double getBalance(){
		return this.balance;
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
