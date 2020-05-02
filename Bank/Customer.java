package Bank;

import java.util.*;
import java.io.*;

public class Customer extends Person{

    static Scanner stdin = new Scanner(System.in);

    private ArrayList<Savings> mySaveList;
    private ArrayList<Checking> myCheckList;
    // private ArrayList<Account> knownAccountList;
    // private Currency currency;
    private int deposit;
    private int loan;
    private String userName;

    private Transaction trans;

    public Customer(String uname, String fname, String mname, String lname, String pwd, String phone, String email){
        super(fname,mname, lname, pwd, phone, email);
        this.userName = uname;
        this.myCheckList = new ArrayList<Checking> ();
        this.mySaveList = new ArrayList<Savings>();
        // this.knownAccountList = new ArrayList<Account>();
    }

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

    

    public void makeSaveTransaction(){
        System.out.println("from which account do you want to make the trans");
        String fromId = stdin.next();
        System.out.println("to which account do you want to make the trans");
        String toId = stdin.next(); 
        System.out.println("How much do you want to transfer");
        float amt = stdin.nextFloat();
        // for(Account a: this.knownAccountList){
        //     if(a.getID().equals(toId))

        // }
        for(Savings s: this.mySaveList){
            
			if (s.getID().equals(fromId))
                trans = new Transaction(s.getID(), toId, s.getCurrency(), amt);
                trans.makeTrans();
        }

        
    }

    public void createCheckAccount(){

    }

    public void createSaveAccount(){
        
    }

    public void updateDepo(){

    }

    public boolean transBtwMy(){
        return true;
    }

    // public String getCurSig(){
    //     return this.currency.getType();
    // }

    // public double getCurRate(){
    //     return this.currency.getRate();
    // }

    public void setUname(String uname){
        this.userName = uname;
    }

    public String getUname(){
        return this.userName;
    }

    public int getTotal(){
        return this.deposit;
    }

    public int getLoan(){
        return this.loan;
    }


}