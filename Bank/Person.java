import java.util.*;
import java.io.*;

public abstract class Person {

    protected String name;
    protected String pwd;

    public Person(){
        this.name = null;
        this.pwd = null;
    }
    
    public Person(String name, String pwd){
        this.name = name;
        this.pwd = pwd;
    }

    public String getName(){
        return this.name;
    }

    

}