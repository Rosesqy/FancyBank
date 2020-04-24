package Bank;

/*
 * Provide methods for customers to make transaction between different accounts
 */

public interface Transaction {
	//To let a customer transfer money to another account
	public abstract <T extends Account> void Transaction(T account);
}
