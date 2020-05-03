package Bank;

public class Security extends Account {
	//Constructor
	public Security(String customerID, String id) {
		super(customerID, id);
		this.type = "SEC";
	}

}
