package Bank.Utilities;

import Bank.DAO.Account.Account;
import Bank.DAO.Person.Customer;

import java.math.BigDecimal;
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
         // produce salt and encrypted password
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
         System.out.println(result + " customer created");
         pstmt.close();
         c.commit();
         c.close();
         return true;
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false;
   }

   public boolean insertSavAccount(long cid, double balance, String cursig, double interest, double openfee, double closefee) {
      Connection c = this.getConnection();
      BigDecimal tbalance = BigDecimal.valueOf(balance);
      BigDecimal topenfee = BigDecimal.valueOf(openfee);
      BigDecimal tclosefee = BigDecimal.valueOf(closefee);
      String psql = "INSERT INTO SAVING_ACC(CUSTOMERID, BALANCE, CURRENCY, INTEREST_RATE,OPENFEE, CLOSEFEE)"
      + "VALUES(?,?,?,?,?,?)";
            // CAST(CAST(? AS NUMERIC) AS MONEY))
      try {
         PreparedStatement pstmt = c.prepareStatement(psql, Statement.RETURN_GENERATED_KEYS);
         pstmt.setLong(1, cid);
         pstmt.setBigDecimal(2, tbalance);
         
         pstmt.setString(3, cursig);
         pstmt.setDouble(4, interest);
         pstmt.setBigDecimal(5, topenfee);
         pstmt.setBigDecimal(6, tclosefee);
         int result = pstmt.executeUpdate();
         System.out.println(result + " saveacc created");
         pstmt.close();
         c.commit();
         c.close();
         return true;
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false;
   }

   public boolean insertCheckAccount(long cid, double balance, String cursig, double openfee, double closefee) {
      Connection c = this.getConnection();
      BigDecimal tbalance = BigDecimal.valueOf(balance);
      BigDecimal topenfee = BigDecimal.valueOf(openfee);
      BigDecimal tclosefee = BigDecimal.valueOf(closefee);
      String psql = "INSERT INTO CECKING_ACC(CUSTOMERID, BALANCE, CURRENCY, OPENFEE, CLOSEFEE)" + "VALUES(?,?,?,?,?)";
      try {
         PreparedStatement pstmt = c.prepareStatement(psql, Statement.RETURN_GENERATED_KEYS);
         pstmt.setLong(1, cid);
         pstmt.setBigDecimal(2, tbalance);
         pstmt.setString(3, cursig);
         pstmt.setBigDecimal(4, topenfee);
         pstmt.setBigDecimal(5, tclosefee);
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

   public boolean insertSecAccount(long cid, double balance, String cursig, double openfee, double closefee) {
      Connection c = this.getConnection();
      BigDecimal tbalance = BigDecimal.valueOf(balance);
      BigDecimal topenfee = BigDecimal.valueOf(openfee);
      BigDecimal tclosefee = BigDecimal.valueOf(closefee);
      String psql = "INSERT INTO SEC_ACC(CUSTOMERID, BALANCE, CURRENCY,OPENFEE, CLOSEFEE)" + "VALUES(?,?,?,?,?,?)";
      try {
         PreparedStatement pstmt = c.prepareStatement(psql, Statement.RETURN_GENERATED_KEYS);
         pstmt.setLong(1, cid);
         pstmt.setBigDecimal(2, tbalance);
         pstmt.setString(3, cursig);
         pstmt.setBigDecimal(4, topenfee);
         pstmt.setBigDecimal(5, tclosefee);
         int result = pstmt.executeUpdate();
         System.out.println(result + " secacc created");
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
         System.out.println(result + " customer dropped");
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
      String psql = "DELETE FROM " + type + "_ACC WHERE " + type + "ID = ?";
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

   public long checkUser(String uname) {
      Connection c = this.getConnection();
      String psql = String.format("SELECT * FROM CUSTOMER WHERE USERNAME = '%s'",uname) ;
      
      try {
         Statement stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery(psql);
         while(rs.next()){
            return rs.getLong("ID");
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return 0;
   }

   public boolean checkPwd(String uname, String inpwd) {
      Connection c = this.getConnection();
      pwdencrypt = new PasswordEncryptionService();
      String psql = String.format("SELECT * FROM CUSTOMER WHERE USERNAME = '%s'",uname) ;
      
      try {
         Statement stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery(psql);
         if(rs.next()){
            byte[] salt = rs.getBytes("SALT");
            byte[] epwd = rs.getBytes("PWD");
            return pwdencrypt.authenticate(inpwd, epwd, salt);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return false;
   }

   public boolean checkRich(long uid){
      Connection c = this.getConnection();
      String psql = String.format("SELECT * FROM SAVING_ACC WHERE CUSTOMERID = '%d' AND BALANCE >= 5000::MONEY AND CURRENCY = '$'",uid) ;
      
      try {
         Statement stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery(psql);
         return rs.next();
      } catch (Exception e) {
         e.printStackTrace();
      }

      return false;
   }

   public boolean updateAccount(){
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

   public String getAccountInfo(String username){
      return "";
   }

   public static void main(String args[]) throws SQLException {
      // String url = "jdbc:postgresql://localhost:5432/FancyBank";
      // String username = "postgres";
      // String pwd = null;
      // DbHelperPSQL dtbase = new DbHelperPSQL(url,username,pwd);
      DbHelperPSQL dtbase = new DbHelperPSQL();
      Customer c = new Customer("try2","test","test","test","123456","1@2","0000");
      Customer d = new Customer("1017","test","test","test","123456","1@2","0000");
      // dtbase.insertCustomer("1017","test","test","test","123456","1@2","0000");
      System.out.println("we have "+dtbase.getCusInfo(c));
      // System.out.println(dtbase.checkUser("try"));
      // System.out.println(dtbase.checkUser("1017"));
      // System.out.println(dtbase.checkPwd(c.getUname(),"0000"));
      // dtbase.dropCustomer("try2");
      dtbase.insertSavAccount(dtbase.checkUser(d.getUname()),300.0, "$", 0.0, 10.0, 10.0);
      dtbase.insertSavAccount(dtbase.checkUser(d.getUname()),5000.0, "$", 0.0, 10.0, 10.0);
      System.out.println(dtbase.checkRich(dtbase.checkUser("1017")));

   }
}  