package Bank.DAO.Person;

import java.util.*;
import java.io.*;

public abstract class Person {

    protected String fname = "";
    protected String mname = "";
    protected String lname = "";
    protected String pwd;
    protected String phone;
    protected String email;


    public Person(){

    }

    public Person(String fname, String mname, String lname, String pwd, String phone, String email){
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.pwd = pwd;
        this.phone = phone;
        this.email = email;
    }

    public void setName(String fname, String mname, String lname){
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }


    public void setEmail(String email){
        this.email = email;
    }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }

    public String getPwd(){
        return this.pwd;
    }

    public String getFullName(){
        return this.fname + " "+this.mname+" "+this.lname;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getEmail(){
        return this.email;
    }

    public String getFname(){
        return this.fname;
    }

    public String getMname(){
        return this.mname;
    }

    public String getLname(){
        return this.lname;
    }

}