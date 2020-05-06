package Bank.DAO.Person;

import Bank.DAO.Account.Checking;
import Bank.DAO.Account.Savings;
import Bank.DAO.Transaction;

import java.util.*;

public class Customer extends Person {
    static Scanner stdin = new Scanner(System.in);

    private ArrayList<Savings> mySaveList;
    private ArrayList<Checking> myCheckList;
    // private ArrayList<Account> knownAccountList;
    // private Currency currency;
    
    private double deposit;
    private double loan;
    private String userName;
    private long id;

    private Transaction trans;

    public Customer(String uname, String fname, String mname, String lname, String pwd, String phone, String email){
        super(fname,mname, lname, pwd, phone, email);
        this.userName = uname;
        this.myCheckList = new ArrayList<Checking> ();
        this.mySaveList = new ArrayList<Savings>();
        // this.id = id;
        // this.knownAccountList = new ArrayList<Account>();
    }

    public void makeSaveTransaction(){
        System.out.println("from which account do you want to make the trans");
        String fromId = stdin.next();
        System.out.println("to which account do you want to make the trans");
        String toId = stdin.next(); 
        System.out.println("How much do you want to transfer");
        double amt = stdin.nextDouble();
        // for(Account a: this.knownAccountList){
        //     if(a.getID().equals(toId))

        // }
        for(Savings s: this.mySaveList){

			if (s.getID().equals(fromId))
                trans = new Transaction(s.getID(), toId, s.getCurrency(), amt);
                trans.makeTrans();
        }


    }

    public void createAccount(){

    }

    public void updateTotalAmt(){

    }

    public boolean transBtwMy(){
        return true;
    }

    public void setUname(String uname){
        this.userName = uname;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getUname(){
        return this.userName;
    }

    public double getTotal(){
        return this.deposit;
    }

    public double getLoan(){
        return this.loan;
    }

    public long getID(){
        return this.id;
    }


}