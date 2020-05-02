//test
package Bank;

import java.sql.*;

public class Database {
   static String url = "jdbc:postgresql://localhost:5432/FancyBank";
   static String username = "postgres";
   static String pwd = "123456";
   Connection c;
   String psql;

   public Database() {
      if (!test()) {
         initialize();
      }
   }

   public boolean test() {
      // check if need to initialize tables in the database
      c = this.getConnection();
      DatabaseMetaData md;
      try {
         md = c.getMetaData();
         ResultSet rs = md.getTables(null, null, "customer", null);
         return rs.next();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false;
   }

   public Connection getConnection() {
      Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection(url, username, pwd);
         c.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
      return c;
   }

   public void initialize() {
      String psql = "CREATE TABLE CUSTOMER " + "(ID BIGSERIAL PRIMARY KEY NOT NULL," + "USERNAME TEXT NOT NULL,"
            + "FIRST_NAME TEXT NOT NULL," + "MIDDLE_NAME TEXT," + "LAST_NAME TEXT NOT NULL,"
            + "PHONE_NUM TEXT NOT NULL," + "EMAIL TEXT NOT NULL)";
      this.createTable("customer", psql);
      psql = "CREATE TABLE SAVING_ACC " + "(SACCID BIGSERIAL PRIMARY KEY NOT NULL," + "CUSTOMERID BIGINT NOT NULL,"
            + "BALANCE MONEY NOT NULL," + "INTEREST_RATE NUMERIC(8,4) NOT NULL," + "CLOSEFEE MONEY NOT NULL,"
            + "OPENFEE MONEY NONULL)";
      this.createTable("saving_acc", psql);
      psql = "CREATE TABLE CHECKING_ACC " + "(CHECKACCID BIGSERIAL PRIMARY KEY NOT NULL,"
            + "CUSTOMERID BIGINT NOT NULL," + "BALANCE MONEY NOT NULL," + "INTEREST_RATE NUMERIC(8,4) NOT NULL,"
            + "CLOSEFEE MONEY NOT NULL," + "OPENFEE MONEY NONULL)";
      this.createTable("checking_acc", psql);
      psql = "CREATE TABLE SEC_ACC " + "(SECACCID BIGSERIAL PRIMARY KEY NOT NULL," + "CUSTOMERID BIGINT NOT NULL,"
            + "BALANCE MONEY NOT NULL," + "CLOSEFEE MONEY NOT NULL," + "OPENFEE MONEY NONULL)";
      this.createTable("sec_acc", psql);
      psql = "CREATE TABLE TRANSACTION " + "(TRANSID BIGSERIAL PRIMARY KEY NOT NULL," + "FROMNAME TEXT NOT NULL,"
            + "FROMID BIGINT NOT NULL," + "TONAME TEXT NOTNULL," + "TOID BIGINT NOT NULL," + "AMT MONEY NOT NULL,"
            + "CURRENCY VARCHAR(5) NOT NULL," + "TIME TIMESTAMP NOT NULL)";
      this.createTable("transaction", psql);
      psql = "CREATE TABLE LOAN " + "(LOANID BIGSERIAL PRIMARY KEY NOT NULL," + "CUSTOMERNAME TEXT NOT NULL,"
            + "CUSTOMERID BIGINT NOT NULL," + "AMT MONEY NOT NULL," + "COLLATERAL TEXT NOT NULL,"
            + "COLLATERAL_VALUE MONEY NOT NULL" + "INTEREST_RATE NUMERIC(8,4) NOT NULL," + "TIME TIMESTAMP NOT NULL)";
      this.createTable("loan", psql);
      psql = "CREATE TABLE STOCKMKT " + "(STOCKID BIGSERIAL PRIMARY KEY NOT NULL," + "STOCKNAME TEXT NOT NULL,"
            + "QUANTITY BIGINT NOT NULL," + "PRICE MONEY NOT NULL)";
      this.createTable("stockmkt", psql);
      psql = "CREATE TABLE MYSTOCK " + "(STOCKID BIGSERIAL PRIMARY KEY NOT NULL," + "STOCKNAME TEXT NOT NULL"
            + "CUSTOMERNAME TEXT NOT NULL," + "CUSTOMERSECID BIGINT NOT NULL," + "PURCHASE_PRICE MONEY NOT NULL,"
            + "PURCHASE_QUANTITY BIGINT NOT NULL," + "CURRENT_PRICE MONEY NOT NULL)";
      this.createTable("mystock", psql);
      psql = "CREATE TABLE STOCKTRANS " + "(STRANSID BIGSERIAL PRIMARY KEY," + "STOCKNAME TEXT NOT NULL"
            + "CUSTOMERNAME TEXT NOT NULL," + "CUSTOMERSECID BIGINT NOT NULL," + "PRICE MONEY NOT NULL,"
            + "QUANTITY BIGINT NOT NULL," + "TIME TIMESTAMP NOT NULL)";
      this.createTable("stock_transaction", psql);
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

   public boolean insertCustomer(Customer customer) {
      Connection c = this.getConnection();
      String psql = "INSERT INTO CUSTOMER(USERNAME, FIRST_NAME, MIDDLE_NAME,LAST_NAME, PHONE_NUM, EMAIL)"
            + "VALUE(?,?,?,?,?,?)";
      try {
         PreparedStatement pstmt = c.prepareStatement(psql, Statement.RETURN_GENERATED_KEYS);
         pstmt.setString(1, customer.getUname());
         pstmt.setString(2, customer.getFname());
         pstmt.setString(3, customer.getMname());
         pstmt.setString(4, customer.getLname());
         pstmt.setString(5, customer.getPhone());
         pstmt.setString(6, customer.getEmail());
         pstmt.executeUpdate(psql);
         try (ResultSet rs = pstmt.getGeneratedKeys()) {
            if (rs.next()) {
               System.out.println(rs.getLong(1) + "customer created");
               return true;
            }
         } catch (SQLException ee) {
            System.out.println(ee.getMessage());
         }
         pstmt.close();
         c.commit();
         c.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false;
   }

   public boolean insertSavAccount(Savings savacc) {
      Connection c = this.getConnection();
      String psql = "INSERT INTO SAVING_ACC(CUSTOMERID, BALANCE, INTERESTRATE,OPENFEE, CLOSEFEE)"
            + "VALUE(?,?,?,?,?)";
      try {
         PreparedStatement pstmt = c.prepareStatement(psql, Statement.RETURN_GENERATED_KEYS);
         pstmt.setString(1, savacc.getCustomerID());
         pstmt.setString(2, savacc.getDeposit());
         pstmt.setString(3, savacc.getMname());
         pstmt.setString(4, savacc.getLname());
         pstmt.setString(5, savacc.getPhone());
         pstmt.executeUpdate(psql);
         try (ResultSet rs = pstmt.getGeneratedKeys()) {
            if (rs.next()) {
               System.out.println(rs.getLong(1) + "saving account created");
               return true;
            }
         } catch (SQLException ee) {
            System.out.println(ee.getMessage());
         }
         pstmt.close();
         c.commit();
         c.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false;
   }

   public boolean dropCustomer(Customer customer) {
      Connection c = this.getConnection();
      int result = 0;
      String psql = "DELETE FROM CUSTOMER WHERE USERNAME = ?";
      PreparedStatement pstmt;
      try {
         pstmt = c.prepareStatement(psql);
         pstmt.setString(1, customer.getUname());
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


   public static void main(String args[]) throws SQLException {
      Database dtbase = new Database();
      // dtbase.test();
      
   }
}  