package DAO;

import DAO.Account;
import DAO.Person;

public class Manager extends Person {

    public boolean approveTrans(Account ac1, Account ac2, int amt){

        return false;
    }

    public boolean approveLoan(Account ac1, int amt){
        return false;
    }

    

}