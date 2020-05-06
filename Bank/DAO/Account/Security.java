package Bank.DAO.Account;

// import DAO.Account;

import Bank.DAO.Account.Account;

public class Security extends Account {
	//Constructor
	public Security(String customerID) {
		super(customerID);
		this.type = "SEC";
	}

}
