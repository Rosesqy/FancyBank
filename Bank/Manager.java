package Bank;

import java.util.*;
import java.io.*;

public class Manager extends Person {

    public Manager(String name, String pwd){
        super(name, pwd);
    }

    public boolean approveTrans(Account ac1, Account ac2, int amt){

        return false;
    }

    public boolean approveLoan(Account ac1, int amt){
        return false;
    }

    

}