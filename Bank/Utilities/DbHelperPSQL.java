package Bank.Utilities;

import Bank.DAO.*;
import Bank.Utilities.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

public class DbHelperPSQL {
   String url;
   String username;
   String pwd;
   Connection c;
   String psql;
   DbConnectionHelper dbcon;
   PasswordEncryptionService pwdencrypt;

   public DbHelperPSQL() {
      this.url = "jdbc:postgresql://localhost:5432/fancybank";
      this.username = "postgres";
      this.pwd = null;
      this.test();
   }

   public DbHelperPSQL(String url, String username, String pwd) {
      this.url = url;
      this.username = username;
      this.pwd = pwd;
      this.test();
      
   }

   public void test() {
      // check if need to initialize tables in the database based on the existence of
      // customer table
      c = this.getConnection();
      DatabaseMetaData md;
      try {
         md = c.getMetaData();
         ResultSet rs = md.getTables(null, null, "customer", null);
         if (!rs.next())
            new InitializeTables();

      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public Connection getConnection() {
      Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection(this.url, this.username, this.pwd);
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
      // dbcon = new DbConnectionHelper();
      // c = dbcon.getConnection();
      // System.out.println("Opened database successfully");
      return c;
   }

   public void alterTable(String tabname, String psql) {

   }

   public void createTable(String tabname, String psql) {
      Connection c = this.getConnection();
      try {
         Statement stmt = c.createStatement();
         stmt.executeUpdate(psql);
         System.out.println(tabname + " table created");
         stmt.close();
         c.commit();
         c.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public boolean insertCustomer(String uname, String fname, String mname, String lname, String phone, String email,
         String pwd) {
      Connection c = this.getConnection();
      String psql = "INSERT INTO CUSTOMER(USERNAME, FIRST_NAME, MIDDLE_NAME,LAST_NAME, PHONE_NUM, EMAIL, SALT, PWD)"
            + "VALUES(?,?,?,?,?,?,?,?)";
      try {
         //produce salt and encrypted password
         pwdencrypt = new PasswordEncryptionService();
         byte[] salt = null;
         byte[] epwd = null;
         try {
            salt = pwdencrypt.generateSalt();
            epwd = pwdencrypt.getEncryptedPassword(pwd, salt);
         } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
         }
         PreparedStatement pstmt = c.prepareStatement(psql, Statement.RETURN_GENERATED_KEYS);
         pstmt.setString(1, uname);
         pstmt.setString(2, fname);
         pstmt.setString(3, mname);
         pstmt.setString(4, lname);
         pstmt.setString(5, phone);
         pstmt.setString(6, email);
         pstmt.setBytes(7, salt);
         pstmt.setBytes(8, epwd);
         int result = pstmt.executeUpdate();
         System.out.println(result+" customer created");
         pstmt.close();
         c.commit();
         c.close();
         return true;
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false;
   }

   public boolean insertSavAccount(String cid, double balance, String cursig, double interest, double openfee, double closefee) {
      Connection c = this.getConnection();
      String psql = "INSERT INTO SAVING_ACC(CUSTOMERID, BALANCE, CURRENCY, INTERESTRATE,OPENFEE, CLOSEFEE)"
            + "VALUES(?,?,?,?,?,?)";
      try {
         PreparedStatement pstmt = c.prepareStatement(psql, Statement.RETURN_GENERATED_KEYS);
         pstmt.setString(1, cid);
         pstmt.setDouble(2, balance);
         pstmt.setString(3, cursig);
         pstmt.setDouble(4, interest);
         pstmt.setDouble(5, openfee);
         pstmt.setDouble(6, closefee);
         int result = pstmt.executeUpdate();
         System.out.println(result+" saveacc created");
         pstmt.close();
         c.commit();
         c.close();
         return true;
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false;
   }

   public boolean insertCheckAccount(String cid, double balance, String cursig, double openfee, double closefee) {
      Connection c = this.getConnection();
      String psql = "INSERT INTO CECKING_ACC(CUSTOMERID, BALANCE, CURRENCY, OPENFEE, CLOSEFEE)"
            + "VALUES(?,?,?,?,?)";
      try {
         PreparedStatement pstmt = c.prepareStatement(psql, Statement.RETURN_GENERATED_KEYS);
         pstmt.setString(1, cid);
         pstmt.setDouble(2, balance);
         pstmt.setString(3, cursig);
         pstmt.setDouble(4, openfee);
         pstmt.setDouble(5, closefee);
         int result = pstmt.executeUpdate();
         System.out.println(result + " checking account created"); 
         pstmt.close();
         c.commit();
         c.close();
         return true;
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false;
   }

   public boolean insertSecAccount(String cid, double balance, String cursig, double openfee, double closefee) {
      Connection c = this.getConnection();
      String psql = "INSERT INTO SEC_ACC(CUSTOMERID, BALANCE, CURRENCY,OPENFEE, CLOSEFEE)"
            + "VALUES(?,?,?,?,?,?)";
      try {
         PreparedStatement pstmt = c.prepareStatement(psql, Statement.RETURN_GENERATED_KEYS);
         pstmt.setString(1, cid);
         pstmt.setDouble(2, balance);
         pstmt.setString(3, cursig);
         pstmt.setDouble(4, openfee);
         pstmt.setDouble(5, closefee);
         int result = pstmt.executeUpdate();
         System.out.println(result+" secacc created");
         pstmt.close();
         c.commit();
         c.close();
         return true;
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false;
   }

   public boolean dropCustomer(String username) {
      Connection c = this.getConnection();
      int result = 0;
      String psql = "DELETE FROM CUSTOMER WHERE USERNAME = ?";
      PreparedStatement pstmt;
      try {
         pstmt = c.prepareStatement(psql);
         pstmt.setString(1, username);
         result = pstmt.executeUpdate();
         System.out.println(result+" customer dropped");
         c.commit();
         c.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return (result != 0);
   }

   public boolean dropAccount(Account account) {
      Connection c = this.getConnection();
      int result = 0;
      String type = account.getType();
      String psql = "DELETE FROM " +type+"_ACC WHERE " +type+ "ID = ?";
      PreparedStatement pstmt;
      try {
         pstmt = c.prepareStatement(psql);
         pstmt.setString(1, account.getID());
         result = pstmt.executeUpdate();
         c.commit();
         c.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return (result != 0);
   }

   public boolean checkPwd(Person person, String inpwd){
      return true;
   }

   public boolean updateInfo(Person person){
      return true;
   }

   public String getCusInfo(Customer customer){
      Connection c = this.getConnection();
      Statement stmt;
      String info = "";
      try{
         String psql = String.format("SELECT * FROM CUSTOMER WHERE USERNAME = '%s'",customer.getUname());
         
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery(psql);
         while(rs.next()){
            System.out.println(rs.getString(1));
            info += rs.getString(2);
            info += rs.getString(3);
            info += rs.getString(4);
         }
         System.out.println(info);
         rs.close();
         stmt.close();
         c.close();
      }catch(SQLException e){
         e.printStackTrace();
      }
	   return info;
   }


   public static void main(String args[]) throws SQLException {
      // String url = "jdbc:postgresql://localhost:5432/FancyBank";
      // String username = "postgres";
      // String pwd = null;
      // DbHelperPSQL dtbase = new DbHelperPSQL(url,username,pwd);
      DbHelperPSQL dtbase = new DbHelperPSQL();
      Customer c = new Customer("try2","test","test","test","123456","1@2","0000");
      dtbase.insertCustomer("try2","test","test","test","123456","1@2","0000");
      
      System.out.println("we have "+dtbase.getCusInfo(c));
      // Account a = 
      dtbase.dropCustomer("try2");


   }
}  