//test
package Bank;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
   static String url = "jdbc:postgresql://localhost:5432/FancyBank";
   static String username = "postgres";
   static String pwd = "123456";

   

   public Database(){
      Connection c = this.getConnection();
   }

   public Connection getConnection(){
      Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection(url, username, pwd);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
      return c;
   }

   public void initialize(){
      
   }

   public static void createTable(){

   }

   public static boolean insertAccount(){

      return true;
   }

   public static boolean dropAccount(){
      return true;
   }

   public static boolean checkPwd(){
      return true;
   }

   public static boolean updateInfo(){
      return true;
   }


   public static void main(String args[]) {
      Database dtbase = new Database();

      
   }

   
}  
