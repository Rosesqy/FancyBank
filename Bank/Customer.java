package Bank;

import java.util.*;
import java.io.*;

public class Customer extends Person{
    private ArrayList myAccountList;
    private Currency currency;
    private int totalAmount;
    private int loan;

    public Customer(String name, String pwd, String cur){
        super(name,pwd);
        this.myAccountList = new ArrayList<Account> ();
        switch(cur){
            case("$"):
                this.currency = new Currency();
            case("€"):
                this.currency = new Currency("EUR",0.5);
            case("¥"):
                this.currency = new Currency("RMB",0.6);
            case("￥"):
                this.currency = new Currency("YEN",0.7);
        }
    }

    public boolean makeTransaction(){
        return false;
    }

    public void createAccount(){

    }

    public void updateTotalAmt(){

    }

    public boolean transBtwMy(){
        return true;
    }

    public String getCurSig(){
        return this.currency.getType();
    }

    public double getCurRate(){
        return this.currency.getRate();
    }

    public int getTotal(){
        return this.totalAmount;
    }

    public int getLoan(){
        return this.loan;
    }


}