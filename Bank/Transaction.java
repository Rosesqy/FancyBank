

package Bank;

import java.util.*;
import java.io.*;

public class Transaction{

    static Scanner stdin = new Scanner(System.in);

    private String fromId;
    private String toId;
    private Currency currency;
    private float amt;

    public Transaction(String from, String to, Currency cur, float amt){
        this.fromId = from;
        this.toId = to;
        this.currency = cur;
        this.amt = amt;
    }

	public void makeTrans(){
		//TODO: interaction with database
	}
    
}
