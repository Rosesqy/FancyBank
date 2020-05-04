package Bank;

public class Security extends Account {
	//Constructor
	public Security(String customerID) {
		super(customerID);
		this.type = "SEC";
	}

}
